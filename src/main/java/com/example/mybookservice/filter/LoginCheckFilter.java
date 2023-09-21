package com.example.mybookservice.filter;

import com.example.mybookservice.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {
  private static final String[] whiteList = {"/", "/add/user", "/login","/logout", "/css/*"};

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.info("loginCheckFilter start");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String requestURI = httpRequest.getRequestURI();

    HttpServletResponse httpResponse = (HttpServletResponse) response;
    try {
      if (!isLoginCheckPath(requestURI)) {
        // 화이트리스트에 속하지 않으면 로그인 인증 필터 적용
        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("loginUser") == null) {
          // 로그인 성공 시 다시 본 페이지로 돌아올 수 있도록 redirectURL을 추가함.
          httpResponse.sendRedirect("/login?redirectURL="+requestURI);
          return;
        }
      }
      chain.doFilter(request, response);

    }catch(Exception e) {
      throw e;
    }
  }

  private boolean isLoginCheckPath(String requestURI) {
    return PatternMatchUtils.simpleMatch(whiteList, requestURI);
  }

  @Override
  public void destroy() {
    log.info("loginCheckFilter end");
  }
}
