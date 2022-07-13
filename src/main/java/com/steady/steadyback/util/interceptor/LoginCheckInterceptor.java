package com.steady.steadyback.util.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    private static final String LOGIN_ID = "loginId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        HttpSession session = request.getSession();
        if (isEmpty(session) || isEmpty(session.getAttribute(LOGIN_ID))){
            //로그인 리다이렉트
            String destUri = request.getRequestURI();
            String destQuery = request.getQueryString();
            String dest = (destQuery == null) ? destUri : destUri + "?" + destQuery;
            request.getSession().setAttribute("dest", dest);

            response.sendRedirect("/users/login");
            return false;
        }

        return true;
    }
}
