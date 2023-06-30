package com.felix.infra.rest;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.felix.infra.rest.dto.UserFeignDTO;
import com.felix.infra.rest.fallback.PlaceHolderFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/29 13:58
 */
@FeignClient(name = "jsonplaceholder", url = "https://jsonplaceholder.typicode.com"
        , fallbackFactory = PlaceHolderFallbackFactory.class)
public interface PlaceHolderFeignClient {

    @GetMapping("/posts")
    List<UserFeignDTO> getByUserId(@RequestParam Long userId);

    @PostMapping("/posts")
    String formatJson(@RequestBody ObjectNode objectNode);

    @PostMapping("/posts")
    String formatForm(Map<String, String> form);

}
