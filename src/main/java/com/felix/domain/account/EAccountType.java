package com.felix.domain.account;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/27 8:50
 */
public enum EAccountType {

    TRADE("trade", "交易"),
    FINANCE("finance", "理财");

    private String code;
    private String desc;

    EAccountType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static Optional<EAccountType> findByCode(String code) {
        return Arrays.stream(EAccountType.values()).filter(x -> x.getCode().equals(code))
                .findFirst();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
