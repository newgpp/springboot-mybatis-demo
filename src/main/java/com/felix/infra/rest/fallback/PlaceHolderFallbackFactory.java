package com.felix.infra.rest.fallback;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.felix.infra.rest.PlaceHolderFeignClient;
import com.felix.infra.rest.dto.UserFeignDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/30 9:05
 */
@Component
public class PlaceHolderFallbackFactory implements FallbackFactory<PlaceHolderFeignClient> {

    private static final Logger log = LoggerFactory.getLogger(PlaceHolderFallbackFactory.class);

    @Override
    public PlaceHolderFeignClient create(Throwable cause) {
        return new PlaceHolderFeignClient() {
            @Override
            public List<UserFeignDTO> getByUserId(Long userId) {
                log.error("getByUserId fallback: ", cause);
                return null;
            }

            @Override
            public String formatJson(ObjectNode objectNode) {
                log.error("getByUserId fallback: ", cause);
                return null;
            }

            @Override
            public String formatForm(Map<String, String> form) {
                log.error("getByUserId fallback: ", cause);
                return null;
            }
        };
    }
}
