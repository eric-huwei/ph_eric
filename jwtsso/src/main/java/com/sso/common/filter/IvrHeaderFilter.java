package com.sso.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class IvrHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器拦截");
//        IvrHttpServletRequestWrapper requestWrapper = null;
        if(servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String requestURI = httpServletRequest.getRequestURI();
            if(!requestURI.startsWith("/mock/test") && !requestURI.startsWith("/iserv")&& !requestURI.startsWith("/cache")){
//                requestWrapper = new IvrHttpServletRequestWrapper(httpServletRequest);
//                //当callId 存在头信息则重写
//                String callId = httpServletRequest.getHeader(RequestHeaderConstant.CALL_ID);
//                if(!StringUtils.isBlank(callId)){
//                    RequestBodyUtil.fillBodyWithCallId(requestWrapper,httpServletRequest);
//                }
            }
        }
        	//防止流读取一次就没有了,将流传递下去
            filterChain.doFilter(servletRequest, servletResponse);
    }


}
