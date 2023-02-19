package com.example.demo.filter;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Order(-1)
@Component
public class LogFilter extends AbstractFilter {

    @Override
    public boolean shouldFilter(HttpServletRequest req) {
        return true;
    }

    @Override
    public boolean filter(HttpServletRequest req, HttpServletResponse res) {
        log.info("Handle request {}", req.getRequestURI());
        return true;
    }

}
