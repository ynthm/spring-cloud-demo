package com.ynthm.demo.inventory.api;

import com.ynthm.common.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ethan Wang
 */
public interface InventoryApi {
  @GetMapping("/test")
  Result<Void> test();
}
