package com.ynthm.gateway.config;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

/**
 * @author Ethan Wang
 */
@Component
public class GatewayErrorAttributes extends DefaultErrorAttributes {

  @Override
  public Map<String, Object> getErrorAttributes(
      ServerRequest request, ErrorAttributeOptions options) {
    Throwable error = super.getError(request);

    Map<String, Object> map = super.getErrorAttributes(request, options);
    map.put("method", request.methodName());
    map.put("exception", error.getClass().getName());
    return map;
  }
}
