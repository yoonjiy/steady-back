package com.steady.steadyback.service;

import com.steady.steadyback.config.Account;
import com.steady.steadyback.domain.User;
import com.steady.steadyback.domain.UserRepository;
import com.steady.steadyback.dto.SignupRequestDto;
import com.steady.steadyback.dto.UserResponseDto;
import com.steady.steadyback.util.errorutil.CustomException;
import com.steady.steadyback.util.errorutil.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<Object> joinUser(SignupRequestDto signupRequestDto){

        User user = signupRequestDto.toEntity(signupRequestDto);

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user.getName()+"님이 성공적으로 가입되었습니다.");
    }


    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        return new UserResponseDto(user);
    }


    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        return user;
    }


    public Boolean checkDuplicateUsers(SignupRequestDto signupRequestDto){
        return userRepository.existsByEmail(signupRequestDto.getEmail());
    }

    public Account loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);
        return userRepository.findByEmail(email).map(User::toAccount)
                .orElseThrow(() -> new UsernameNotFoundException("등록되지 않은 사용자입니다."));

    }


}
