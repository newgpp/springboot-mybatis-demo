package com.felix.interfaces.enums;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/26 15:15
 */
public enum ETransactionType {
    /**
     * 充值 amount > 0
     */
    deposit("deposit", "充值"),
    /**
     * 提现冻结 amount < 0
     */
    withdrawFreeze("withdrawFreeze", "提现冻结"),
    /**
     * 提现冻结撤销
     */
    withdrawFreezeCancel("withdrawFreezeCancel", "提现冻结撤销"),
    /**
     * 提现冻结扣减
     */
    withdrawFreezeDeduct("withdrawFreezeDeduct", "提现冻结扣减"),
    /**
     * 下单冻结
     */
    orderFreeze("orderFreeze", "下单冻结"),
    /**
     * 下单冻结撤销
     */
    orderFreezeCancel("orderFreezeCancel", "下单冻结撤销"),
    /**
     * 下单冻结扣减
     */
    orderFreezeDeduct("orderFreezeDeduct", "下单冻结扣减");

    private String code;

    private String desc;

    ETransactionType(String code, String desc) {
        this.code = code;
        this.desc = desc;
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
