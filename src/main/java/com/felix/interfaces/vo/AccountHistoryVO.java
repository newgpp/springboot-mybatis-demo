package com.felix.interfaces.vo;

import com.felix.infra.models.AccountHistory;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/28 15:08
 */
public class AccountHistoryVO {
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
    private String balance;
    /**
     * 冻结余额
     */
    private String freeze;
    /**
     * 账户余额 total = balance + freeze
     */
    private String total;
    /**
     * 动账金额 正数:增加, 负数:减少
     */
    private String amount;
    /**
     * 动账毫秒时间戳
     */
    private Long ts;

    private String createdTime;

    private String updatedTime;

    public static AccountHistoryVO build(AccountHistory history) {
        if (history == null) {
            return null;
        }
        AccountHistoryVO vo = new AccountHistoryVO();
        BeanUtils.copyProperties(history, vo);
        vo.setBalance(history.getBalance().stripTrailingZeros().toPlainString());
        vo.setFreeze(history.getFreeze().stripTrailingZeros().toPlainString());
        vo.setTotal(history.getTotal().stripTrailingZeros().toPlainString());
        vo.setAmount(history.getAmount().stripTrailingZeros().toPlainString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        vo.setCreatedTime(sdf.format(history.getCreatedTime()));
        vo.setUpdatedTime(sdf.format(history.getUpdatedTime()));
        return vo;
    }

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

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getFreeze() {
        return freeze;
    }

    public void setFreeze(String freeze) {
        this.freeze = freeze;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
