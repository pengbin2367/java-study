package com.atqingke.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(
        urlPatterns = "/servletConfig",
        initParams = {
                @WebInitParam(name = "key1", value = "value1"),
                @WebInitParam(name = "key2", value = "value2")
        }
)
public class ServletConfigServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig servletConfig = getServletConfig();
        Enumeration<String> initParameterNames = servletConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String element = initParameterNames.nextElement();
            System.out.println(element + " = " + servletConfig.getInitParameter(element));
        }
    }
}
