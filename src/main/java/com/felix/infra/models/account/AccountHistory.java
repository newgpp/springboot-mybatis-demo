package com.felix.infra.models.account;

import com.felix.domain.base.EntityBase;

import java.math.BigDecimal;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/26 12:53
 */
public class AccountHistory extends EntityBase {
    /**
     * 账户变动记录id
     */
    private Long accountHistoryId;
    /**
     * 交易类型
     */
    private String transactionType;
    /**
     * 账户id
     */
    private Long accountId;
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
     * 动账金额 正数:增加, 负数:减少
     */
    private BigDecimal amount;
    /**
     * 动账毫秒时间戳
     */
    private Long ts;

    public Long getAccountHistoryId() {
        return accountHistoryId;
    }

    public void setAccountHistoryId(Long accountHistoryId) {
        this.accountHistoryId = accountHistoryId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }
}
