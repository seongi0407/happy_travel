package com.kh.travel.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MDCFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        final UUID uuid = UUID.randomUUID();
        System.out.println("MDCFilter.doFilter");
        MDC.put("request_id", uuid.toString());
        HttpServletRequest httpReq = (HttpServletRequest) req;
        log.info(httpReq.getParameter("userId"));
        chain.doFilter(req, resp);
        MDC.clear();
    } // doFilter
} // class
