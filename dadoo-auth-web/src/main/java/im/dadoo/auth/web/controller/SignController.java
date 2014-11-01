package im.dadoo.auth.web.controller;

import javax.annotation.Resource;

import im.dadoo.auth.biz.bo.SignBO;
import im.dadoo.auth.data.dto.SignDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Optional;

@Controller
public class SignController {
  
  @Resource
  private SignBO signBO;
  
  @RequestMapping(value = "/signin", method = RequestMethod.GET)
  public String getSigninPage() {
    return "signin";
  }
  
  @RequestMapping(value = "/signin", method = RequestMethod.POST)
  @ResponseBody
  public SignDTO signin(@RequestParam String email, @RequestParam String password) {
    System.out.println(email);
    System.out.println(password);
    Optional<SignDTO> signDTO = this.signBO.signin(email, password);
    return signDTO.get();
  }
}
