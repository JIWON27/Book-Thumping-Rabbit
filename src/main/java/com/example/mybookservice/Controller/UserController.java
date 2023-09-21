package com.example.mybookservice.Controller;

import com.example.mybookservice.domain.User;
import com.example.mybookservice.repository.User.UserRepository;
import com.example.mybookservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/add")
public class UserController {

  private final UserService UserService;

  // 회원가입 폼 보여주기
  @GetMapping("/user")
  public String addUserForm(Model model){
    model.addAttribute("user", new User());
    return "login/addForm";
  }
  // 회원가입 하기
  @PostMapping("/user")
  public String addUser(@Validated @ModelAttribute User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "login/addForm";
    }
    UserService.save(user);
    return "login/home";
  }
}
