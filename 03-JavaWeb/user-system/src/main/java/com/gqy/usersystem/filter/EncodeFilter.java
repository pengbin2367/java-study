package com.gqy.usersystem.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;

import java.io.IOException;

@WebFilter("/*")
public class EncodeFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;

	public void destroy() {
		System.out.println(this.getClass().getName()+"正在销毁");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println(this.getClass().getName()+"正在过滤请求...");
		
		//实现编码过滤
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println(this.getClass().getSimpleName()+"正在初始化...");
	}

}