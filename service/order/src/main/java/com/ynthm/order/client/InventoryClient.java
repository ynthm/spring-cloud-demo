package com.ynthm.order.client;

import com.ynthm.demo.inventory.api.InventoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Ethan Wang
 */
@FeignClient(value = "inventory-svc", contextId = "inventory")
public interface InventoryClient extends InventoryApi {
}
