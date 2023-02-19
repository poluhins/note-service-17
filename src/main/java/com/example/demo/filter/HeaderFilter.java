package com.example.demo.filter;

import com.example.demo.service.impl.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HeaderFilter extends AbstractFilter {

    private static final String AUTH_HEADER = "Authorization";

    UserService userService;

    @Override
    public boolean shouldFilter(HttpServletRequest req) {
        return !req.getRequestURI().contains("swagger");
    }

    @Override
    public boolean filter(HttpServletRequest req, HttpServletResponse res) {
        val authHeader = req.getHeader(AUTH_HEADER);
        val result = authHeader != null && userService.exists(Integer.parseInt(authHeader));
        if (!result) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        return result;
    }
}
