package com.example.mybookservice.Controller;

import com.example.mybookservice.domain.Login;
import com.example.mybookservice.domain.User;
import com.example.mybookservice.service.LoginService;
import com.example.mybookservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;
  private final UserService userService;

  @GetMapping("/")
  public String home(@SessionAttribute(name="loginUser", required = false) User loginUser, Model model){
    if (loginUser == null) {
      // 세션에 user 데이터가 없는 경우
      return "login/home";
    }
    // 성공
    model.addAttribute("loginUser", loginUser);
    return "login/home";
  }

  // 로그인 폼 보여주기
  @GetMapping("/login")
  public String loginForm(Model model){
    model.addAttribute("login", new Login());
    return "login/loginForm";
  }
  @PostMapping("/login")
  public String login(@Validated @ModelAttribute Login login, BindingResult bindingResult,
                      @RequestParam(defaultValue = "/") String redirectURL,
                      HttpServletRequest request){
    if (bindingResult.hasErrors()){
      return "login/loginForm";
    }
    Optional<User> addUserCheck = userService.findByLoginId(login.getLoginId());

    if (addUserCheck.isEmpty()) {
      bindingResult.reject("addFail", "존재하지 않는 회원입니다.회원가입을 해주세요.");
      return "login/loginForm";
    }

    User loginUser = loginService.login(login.getLoginId(), login.getPassword());

    if (loginUser == null) {
      // 로그인 인증 과정에서 오류가 발생했을 때,
      bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
      return "login/loginForm";
    }

    //세션이 있으면 있는 세션을 반환하고, 없으면 신규 세션을 생성함.
    HttpSession session = request.getSession();
    //세션에 로그인 회원 정보 보관
    session.setAttribute("loginUser", loginUser );

    return "redirect:"+redirectURL;
  }

  @PostMapping("/logout")
  public String logout(HttpServletRequest request){
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
    return "redirect:/";
  }
}
