package com.ynthm.demo.inventory.controller;

import com.ynthm.common.domain.Result;
import com.ynthm.demo.inventory.api.InventoryApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ethan Wang
 */
@RestController
public class InventoryController implements InventoryApi {

  @Override
  public Result<Void> test() {
    return Result.ok();
  }

  @GetMapping("test1")
  public Result<String> test2() {
    return Result.ok("test1");
  }
}
