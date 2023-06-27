package com.felix.domain.account;

import com.felix.domain.base.EntityBase;

import java.math.BigDecimal;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/26 12:59
 */
public class Account extends EntityBase {
    /**
     * 账户id
     */
    private Long accountId;
    /**
     * 账户类型
     */
    private String accountType;
    /**
     * 用户id
     */
    private Long memberId;
    /**
     * 可用余额
     */
    private BigDecimal balance;
    /**
     * 冻结余额
     */
    private BigDecimal freeze;
    /**
     * 账户余额 total = balance + freeze
     */
    private BigDecimal total;
    /**
     * 乐观锁版本号（CAS）
     */
    private Long version;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreeze() {
        return freeze;
    }

    public void setFreeze(BigDecimal freeze) {
        this.freeze = freeze;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
