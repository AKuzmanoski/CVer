package com.cver.team.filters.helper;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Dimitar on 6/3/2016.
 */
public class CsrfCookieAddingHelper {
  public static void addingCSRFasCookie(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
      CsrfToken csrf = (CsrfToken) httpServletRequest.getAttribute(CsrfToken.class.getName());

      if (csrf != null) {
          Cookie cookie = WebUtils.getCookie(httpServletRequest, "XSRF-TOKEN");
          String token = csrf.getToken();
          if (cookie == null || token != null && !token.equals(cookie.getValue())) {
              cookie = new Cookie("XSRF-TOKEN", token);
              cookie.setPath("/");
              httpServletResponse.addCookie(cookie);
          }
      }
  }
}
