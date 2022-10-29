package com.ynthm.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ethan Wang
 */
@RestController
public class AuthController {
  @GetMapping("/test")
  public String hello() {
    return "hello";
  }
}
