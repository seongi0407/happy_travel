package com.kh.travel.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 캐싱을 위한 캐싱래퍼로 형변환
        ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;

        // Request 정보 로깅
        log.info(new String(cachingRequest.getContentAsByteArray()));
    } // afterCompletion
} // class
