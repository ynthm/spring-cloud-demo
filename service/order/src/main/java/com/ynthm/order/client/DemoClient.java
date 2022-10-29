package com.ynthm.order.client;

import com.ynthm.demo.inventory.api.DemoApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Ethan Wang
 */
@FeignClient(value = "inventory-svc", contextId = "demo")
public interface DemoClient extends DemoApi {
}
