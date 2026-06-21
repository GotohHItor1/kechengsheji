package com.example.interceptor;

import com.example.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录校验拦截器
 * 校验请求头中的 JWT 令牌，未登录返回 401
 */
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LoginCheckInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        // 放行登录请求
        if (requestURI.contains("/login")) {
            return true;
        }

        // 获取请求头中的 token
        String token = request.getHeader("token");
        if (token == null || token.trim().isEmpty()) {
            log.warn("未提供 token，URI: {}", requestURI);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":0,\"msg\":\"未登录或令牌已过期\"}");
            return false;
        }

        try {
            // 解析 JWT
            Claims claims = JwtUtils.parseJwt(token);
            // 将员工ID存入 request 属性，供后续使用
            request.setAttribute("empId", claims.get("empId", Integer.class));
            log.debug("token 校验通过，empId: {}", claims.get("empId"));
            return true;
        } catch (Exception e) {
            log.warn("token 解析失败: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":0,\"msg\":\"未登录或令牌已过期\"}");
            return false;
        }
    }
}
