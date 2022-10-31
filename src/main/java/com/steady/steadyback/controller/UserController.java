package com.steady.steadyback.controller;

import com.steady.steadyback.config.JwtTokenProvider;

import com.steady.steadyback.domain.User;
import com.steady.steadyback.domain.UserRepository;
import com.steady.steadyback.dto.*;
import com.steady.steadyback.service.TokenService;
import com.steady.steadyback.service.UserService;
import com.steady.steadyback.util.errorutil.CustomException;
import com.steady.steadyback.util.errorutil.ErrorCode;
import lombok.RequiredArgsConstructor;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final RedisTemplate redisTemplate;


    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequestDto signupRequestDto){
        if (userService.checkDuplicateUsers(signupRequestDto))
            throw new CustomException(ErrorCode.CANNOT_DUPLICATE_EMAIL);
        signupRequestDto.encryptPassword(passwordEncoder);
        return userService.joinUser(signupRequestDto);
    }


    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        User user = userService.findUserByEmail(loginRequestDto.getEmail().trim());

        if (!userRepository.existsByEmail(loginRequestDto.getEmail().trim())) {
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        }

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw new CustomException(ErrorCode.INCORRECT_PASSWORD);
        }

        String access = jwtTokenProvider.createAccessToken(user.getEmail(), user.getRole());
        String refresh = jwtTokenProvider.createRefreshToken(user.getEmail(), user.getRole());

        tokenService.updateRefreshToken(loginRequestDto.getEmail(), refresh); //리프레시 토큰 저장

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .user(user)
                .accessToken(access)
                .refreshToken(refresh)
                .build();
        return loginResponseDto;
    }

    @PostMapping("/refresh/token")
    public RefreshTokenResponseDto refreshToken(@RequestHeader(value="KEY-EMAIL") String email,
                                                @RequestHeader(value="REFRESH-TOKEN") String refreshToken) {
        RefreshTokenResponseDto refreshTokenResponseDto = tokenService.refreshToken(email, refreshToken);
        return refreshTokenResponseDto;
    }

    @DeleteMapping("/{userId}")
    public UserDeleteResponseDto deleteUser(@PathVariable Long userId) {

        userService.deleteUserById(userId);
        UserDeleteResponseDto userDeleteResponseDto = new UserDeleteResponseDto(userId, "SUCCESS");
        return userDeleteResponseDto;
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

    @PostMapping("/findId")
    public UserFindResponseDto getUserIdByNameAndPhone(@RequestBody Map<String,String> param) {
        String name = param.get("name");
        String phone = param.get("phone");
        return userService.findUserIdByNameAndPhone(name, phone);

    }

    @PostMapping("/findPw")
    public UserFindResponseDto findPwPOST(@RequestBody Map<String,String> param) {
        String email = param.get("email");
        User user= userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        String pw = "";
        for(int i=0; i<12; i++) {
            pw+=(char) ((Math.random()*26) +97 );
        }
        UserFindPwRequestDto userFindPwRequestDto= new UserFindPwRequestDto(user,pw);
        userFindPwRequestDto.encryptPassword(passwordEncoder);
        return userService.findPw(user, userFindPwRequestDto, pw);
    }


    @GetMapping
    public List<UserResponseDto> getUserList() {

        return userService.findUserList();
    }

    @PatchMapping("/{userId}")
    public UserUpdateResponseDto updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequestDto updateRequestDto){
        updateRequestDto.encryptPassword(passwordEncoder);
        return userService.updateUser(userId, updateRequestDto);
    }

    @PostMapping("/logout")
    public LogoutResponseDto logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        //1. Access Token 검증
        if(!jwtTokenProvider.validateToken(logoutRequestDto.getAccessToken())) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        //2. Access Token에서 user email 가져옴
        Authentication authentication = jwtTokenProvider.getAuthentication(logoutRequestDto.getAccessToken());
        //3. Redis에서 해당 user email로 저장된 refresh token이 있는지 여부를 확인 후 있을 경우 삭제
        String userEmail = authentication.getName();
        if(redisTemplate.opsForValue().get(userEmail) != null) {
            //Refresh token 삭제
            redisTemplate.delete(userEmail);
        }
        else {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        //4. 해당 Access Token 유효시간 가지고 와서 로그아웃 등록
        Long expiration = jwtTokenProvider.getExpiration(logoutRequestDto.getAccessToken());
        redisTemplate.opsForValue()
                .set(logoutRequestDto.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);

        return new LogoutResponseDto("로그아웃 되었습니다.");
    }
}
