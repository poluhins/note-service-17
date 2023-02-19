package com.example.demo.filter;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public abstract class AbstractFilter implements Filter {

    public abstract boolean shouldFilter(HttpServletRequest req);

    public abstract boolean filter(HttpServletRequest req, HttpServletResponse res);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        val httpReq = (HttpServletRequest) request;
        val httpRes = (HttpServletResponse) response;

        if (shouldFilter(httpReq)) {
            if (!filter(httpReq, httpRes)) {
                log.info("Request with URI {} doesn't pass the filter", httpReq.getRequestURI());
                return;
            }
        }

        chain.doFilter(httpReq, httpRes);
    }
}
