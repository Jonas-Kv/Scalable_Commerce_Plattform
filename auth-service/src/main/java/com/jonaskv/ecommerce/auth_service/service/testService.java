package com.jonaskv.ecommerce.auth_service.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/test")
public class testService {
  @GetMapping
  public void test() {
  }
}
