package com.felix.interfaces;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.felix.infra.rest.PlaceHolderFeignClient;
import com.felix.infra.rest.dto.UserFeignDTO;
import com.felix.interfaces.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/29 14:49
 */
@RestController
@RequestMapping("/feign/test/")
public class FeignTestController {

    private static final Logger log = LoggerFactory.getLogger(FeignTestController.class);

    @Resource
    private PlaceHolderFeignClient placeHolderFeignClient;

    @GetMapping("/user/{userId}")
    public Result<List<UserFeignDTO>> getUserList(@PathVariable Long userId) {
        List<UserFeignDTO> userList = placeHolderFeignClient.getByUserId(userId);
        return Result.success(userList);
    }

    @PostMapping("/json/format")
    public Result<Integer> format(@RequestBody ObjectNode objectNode) {
        String json = placeHolderFeignClient.formatJson(objectNode);
        log.info("formatted json: \n" + json);
        return Result.success(json.length());
    }

    @PostMapping("/form/format")
    public Result<Integer> format(@RequestParam Map<String, String> form) {
        String json = placeHolderFeignClient.formatForm(form);
        log.info("formatted form: \n" + json);
        return Result.success(json.length());
    }


}
