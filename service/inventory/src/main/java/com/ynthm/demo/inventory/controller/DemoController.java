package com.ynthm.demo.inventory.controller;

import com.ynthm.common.domain.Result;
import com.ynthm.demo.inventory.api.DemoApi;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ethan Wang
 */
@RestController
public class DemoController implements DemoApi {

  @Override
  public Result<Void> demo() {
    return Result.ok();
  }
}
