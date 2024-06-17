package com.atqingke.security.config;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();
        Map<String, Object> res = new HashMap<>();
        res.put("code", 0);
        res.put("msg", "success");
        res.put("data", principal);
        String jsonResult = JSON.toJSONString(res);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonResult);
    }
}
