package com.kh.travel.common.filter.MDCFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/*")
public class MDCFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        StopWatch stopWatch = null;
        try {
            // 요청 처리 시간 측정 시작
            stopWatch = new StopWatch();
            stopWatch.start();

            // 랜덤 값으로 uuid 값 할당해서 로깅
            final UUID uuid = UUID.randomUUID();
            MDC.put("request_id", uuid.toString());

            // 동기 요청과 비동기 요청을 구분해서 진행
            if (isAsyncDispatch(request)) {
                filterChain.doFilter(request, response);
            } else {
                doFilterWrapped(new RequestWrapper(request), new ResponseWrapper(response), filterChain);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 요청 처리 시간 측정 종료 및 response 정보 로깅
            stopWatch.stop();
            log.info("response time = {} s", String.valueOf(stopWatch.getTotalTimeSeconds()));

            // MDC 비우기
            MDC.clear();
        }
    } // doFilterInternal

    // 동기 요청 처리하는 메서드
    protected void doFilterWrapped(RequestWrapper request, ResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Request 로깅 메서드
            logRequest(request);
            logPayload("Request", request.getContentType(), request.getInputStream());
            
            // 요청 진행
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // Response 로깅 메서드
            logPayload("Response", response.getContentType(), response.getContentInputStream());
            
            // 응답 복사
            response.copyBodyToResponse();
        }
    } // doFilterWrapped

    // Request 정보 로깅하는 메서드
    private static void logRequest(RequestWrapper request) throws IOException {
        String queryString = request.getQueryString();
        
        // Request 요청 정보 로깅
        log.info("Request: {} uri=[{}], content-type=[{}]",
                request.getMethod(),
                queryString == null ? request.getRequestURI() : request.getRequestURI() + queryString,
                request.getContentType()
        );
    } // logRequest

    // Request, Response Payload 로깅하는 메서드
    private static void logPayload(String prefix, String contentType, InputStream inputStream) throws IOException {
        byte[] content = StreamUtils.copyToByteArray(inputStream);
        if (content.length > 0) {
            String contentString = new String(content);

            // Payload 로깅
            log.info("{} Payload: {}", prefix, contentString);
        } else{
            // Payload 로깅
            log.info("{} Payload: {}", prefix, "empty response");
        }
    } // logPayload
} // class
