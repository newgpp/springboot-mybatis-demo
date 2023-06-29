package com.felix.domain.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/29 12:42
 */
public class NoNumPageDTO {

    @NotNull
    private Long accountId;
    @Min(0)
    private Long from;
    @NotNull
    @Min(1)
    private Integer size;
    @Min(1)
    private Long startTs;
    @Min(1)
    private Long endTs;
    @NotNull(message = "direct不允许为null")
    @Pattern(regexp = "next|prev")
    private String direct;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getStartTs() {
        return startTs;
    }

    public void setStartTs(Long startTs) {
        this.startTs = startTs;
    }

    public Long getEndTs() {
        return endTs;
    }

    public void setEndTs(Long endTs) {
        this.endTs = endTs;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }
}
