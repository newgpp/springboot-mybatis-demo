package com.felix.infrastructure.account;

import com.felix.domain.account.Account;
import com.felix.domain.account.AccountHistory;
import com.felix.domain.account.ETransactionType;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/26 13:02
 */
interface AccountService {

    /**
     * 查询账户
     *
     * @param accountId 账户id
     * @return 账户
     */
    Account getById(Long accountId);


    List<Account> getList(Long memberId, String accountType);

    /**
     * 金额冻结 1.amount<0 冻结 2.amount>0 解冻
     * balance = balance + amount
     * freeze = freeze - amount
     *
     * @param type      交易类型
     * @param accountId 账户id
     * @param amount    金额
     * @return 修改条数
     */
    @Transactional(rollbackFor = Exception.class)
    int balanceFreeze(ETransactionType type, Long accountId, BigDecimal amount);

    /**
     * 冻结金额扣减 amount<0 冻结扣减
     * freeze = freeze + amount
     * total = total + amount
     *
     * @param accountId 账户id
     * @param amount    金额
     * @return 修改条数
     */
    int freezeDeduct(ETransactionType type, Long accountId, BigDecimal amount);

    /**
     * 余额扣减 1.amount>0 余额充值
     * balance = balance + amount
     * total = total + amount
     *
     * @param accountId 账户id
     * @param type      类型
     * @param amount    金额
     * @return 修改条数
     */
    int balanceDeduct(ETransactionType type, Long accountId, BigDecimal amount);

    /**
     * 创建账户
     *
     * @param account
     * @return
     */
    int create(Account account);

    List<AccountHistory> getPage(Long accountId, Long startTs, Long endTs, Long from, Integer size, String direct);
}
