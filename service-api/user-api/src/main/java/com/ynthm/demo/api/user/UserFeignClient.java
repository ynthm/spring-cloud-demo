package com.ynthm.demo.api.user;

import com.ynthm.common.domain.Result;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Ethan Wang
 */
@FeignClient(name = "wb-system", path = "/role")
@LoadBalancerClient(name = "wb-system", configuration = LoadBalancerConfig.class)
public interface UserFeignClient {

  @LoadBalanced
  @GetMapping(value = "/{roleId}")
  Result<Void> getInfo(@PathVariable Long roleId);
}
