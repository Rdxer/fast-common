package com.rdxer.common.debug;

import com.rdxer.common.ex.ToStringEx;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        ToStringEx.toStringWithPrettyPrint(httpServletRequest.getParameterMap());

        filterChain.doFilter(httpServletRequest, httpServletResponse);

//        ToStringEx.toStringWithPrettyPrint(httpServletResponse.getWriter().toString());
    }
}
