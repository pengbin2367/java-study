package com.atqingke.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity//Spring项目总需要添加此注解，SpringBoot项目中不需要
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //authorizeRequests()：开启授权保护
        //anyRequest()：对所有请求开启授权保护
        //authenticated()：已认证请求会自动被授权
        http
                .authorizeRequests(authorize -> {
                    authorize
                            .requestMatchers("/user/list").hasAuthority("USER_LIST")
                            .requestMatchers("/user/add").hasAuthority("USER_ADD")
                            .requestMatchers("/user/**").hasRole("ADMIN")
                            .anyRequest()
                            .authenticated()
                    ;
                })
                .formLogin( form -> {
                    form
                            .loginPage("/login").permitAll() //登录页面无需授权即可访问
                            .usernameParameter("username") //自定义表单用户名参数，默认是username
                            .passwordParameter("password") //自定义表单密码参数，默认是password
                            .failureUrl("/login?error") //登录失败的返回地址
                            .successHandler(new MyAuthenticationSuccessHandler())
                            .failureHandler(new MyAuthenticationFailureHandler())
                    ;
                })
                .sessionManagement(session -> {
                    session
                            .maximumSessions(1)
                            .expiredSessionStrategy(new MySessionInformationExpiredStrategy())
                    ;
                })
                .logout(logout -> {
                    logout.logoutSuccessHandler(new MyLogoutSuccessHandler());
                })
                .exceptionHandling(ex -> {
                    ex
                            .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                            .accessDeniedHandler(new MyAccessDeniedHandler())
                    ;
                })
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
        ;
        return http.build();
    }
}