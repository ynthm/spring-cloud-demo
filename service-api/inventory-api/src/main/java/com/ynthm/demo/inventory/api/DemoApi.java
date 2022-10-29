package com.ynthm.demo.inventory.api;

import com.ynthm.common.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ethan Wang
 */
public interface DemoApi {

  @GetMapping("/demo")
  Result<Void> demo();
}
