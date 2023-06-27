package com.felix.infrastructure.account;

import com.felix.domain.account.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/26 13:14
 */
@Mapper
public interface AccountMapper {

    /**
     * 创建账户
     * @param account
     * @return
     */
    int save(Account account);

    /**
     * 查询账户列表
     *
     * @param memberId    用户id
     * @param accountType 账户类型
     * @return 账户列表
     */
    List<Account> getList(@Param("memberId") Long memberId, @Param("accountType") String accountType);

    /**
     * 查询账户
     *
     * @param accountId 账户id
     * @return 账户
     */
    Account getById(@Param("accountId") Long accountId);

    /**
     * 金额冻结 1.amount<0 冻结 2.amount>0 解冻
     * balance = balance + amount
     * freeze = freeze - amount
     *
     * @param accountId 账户id
     * @param version   版本
     * @param amount    金额
     * @return 修改条数
     */
    int balanceFreeze(@Param("accountId") Long accountId, @Param("version") Long version, @Param("amount") BigDecimal amount);

    /**
     * 冻结金额扣减 amount<0 冻结扣减
     * freeze = freeze + amount
     * total = total + amount
     *
     * @param accountId 账户id
     * @param version   版本
     * @param amount    金额
     * @return 修改条数
     */
    int freezeDeduct(@Param("accountId") Long accountId, @Param("version") Long version, @Param("amount") BigDecimal amount);


    /**
     * 余额扣减 1.amount<0 余额扣减 2.amount>0 余额充值
     * balance = balance + amount
     * total = total + amount
     *
     * @param accountId 账户id
     * @param version   版本
     * @param amount    金额
     * @return 修改条数
     */
    int balanceDeduct(@Param("accountId") Long accountId, @Param("version") Long version, @Param("amount") BigDecimal amount);


}
