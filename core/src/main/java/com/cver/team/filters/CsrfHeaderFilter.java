package com.cver.team.filters;

import com.cver.team.filters.helper.CsrfCookieAddingHelper;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dimitar on 6/3/2016.
 */
public class CsrfHeaderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        CsrfCookieAddingHelper.addingCSRFasCookie(request,response);

        //CORS - accepting cookies on server side
        //  response.addHeader("Access-Control-Allow-Credentials", "true");

        filterChain.doFilter(request, response);
    }
}