package com.ynthm.order.controller;

import com.ynthm.common.domain.Result;
import com.ynthm.order.client.DemoClient;
import com.ynthm.order.client.InventoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ethan
 */
@RestController
public class OrderController {

  private InventoryClient inventoryClient;

  private DemoClient demoClient;

  @Autowired
  public void setDemoClient(DemoClient demoClient) {
    this.demoClient = demoClient;
  }

  @Autowired
  public void setInventoryClient(InventoryClient inventoryClient) {
    this.inventoryClient = inventoryClient;
  }

  @GetMapping("/test")
  public Result<String> test() {
    Result<Void> test = inventoryClient.test();
    return Result.ok(test.getMsg());
  }

  @GetMapping("/demo")
  public Result<String> demo() {
    Result<Void> test = demoClient.demo();
    return Result.ok(test.getMsg());
  }
}
