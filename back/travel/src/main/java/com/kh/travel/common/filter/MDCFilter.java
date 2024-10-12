package com.kh.travel.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.StopWatch;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MDCFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 요청 처리 시간 측정 시작
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 캐싱을 위한 캐싱래퍼 선언
        ContentCachingRequestWrapper cachingRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper cachingResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        // 랜덤 값으로 uuid 값 할당해서 로깅
        final UUID uuid = UUID.randomUUID();
        MDC.put("request_id", uuid.toString());

        // 요청 처리 진행
        chain.doFilter(cachingRequest, cachingResponse);

        // 요청 처리 시간 측정 종료 및 response 정보 로깅
        stopWatch.stop();
        log.info(new String(cachingResponse.getContentAsByteArray()));
        log.info(stopWatch.prettyPrint());

        // response 정보 복사해서 클라이언트로 보내기
        cachingResponse.copyBodyToResponse();

        // MDC 비우기
        MDC.clear();
    } // doFilter
} // class
