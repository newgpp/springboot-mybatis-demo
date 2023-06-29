package com.felix.interfaces.vo;

import com.felix.infra.models.Account;

import java.text.SimpleDateFormat;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/28 15:00
 */
public class AccountVO {
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
     * 乐观锁版本号（CAS）
     */
    private Long version;

    private String createdTime;

    private String updatedTime;

    public static AccountVO build(Account account) {
        if (account == null) {
            return null;
        }
        AccountVO vo = new AccountVO();
        vo.setAccountId(account.getAccountId());
        vo.setAccountType(account.getAccountType());
        vo.setMemberId(account.getMemberId());
        vo.setBalance(account.getBalance().stripTrailingZeros().toPlainString());
        vo.setFreeze(account.getFreeze().stripTrailingZeros().toPlainString());
        vo.setTotal(account.getTotal().stripTrailingZeros().toPlainString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        vo.setCreatedTime(sdf.format(account.getCreatedTime()));
        vo.setUpdatedTime(sdf.format(account.getUpdatedTime()));
        return vo;
    }

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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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
