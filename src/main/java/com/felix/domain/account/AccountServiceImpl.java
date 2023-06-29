package com.felix.domain.account;

import com.felix.infra.account.AccountHistoryMapper;
import com.felix.infra.account.AccountMapper;
import com.felix.infra.models.account.Account;
import com.felix.infra.models.account.AccountHistory;
import com.felix.interfaces.enums.ETransactionType;
import com.felix.infra.util.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/26 13:02
 */
@Service
public class AccountServiceImpl implements AccountService {

    private Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountHistoryMapper accountHistoryMapper;

    /**
     * 查询账户
     *
     * @param accountId 账户id
     * @return 账户
     */
    @Override
    public Account getById(Long accountId) {
        Assert.notNull(accountId, "accountId不允许为空");
        return accountMapper.getById(accountId);
    }

    @Override
    public List<Account> getList(Long memberId, String accountType) {
        return accountMapper.getList(memberId, accountType);
    }

    /**
     * 参数校验
     *
     * @param accountId
     * @param amount
     * @return
     */
    private Account checkGetAccount(Long accountId, BigDecimal amount) {
        Assert.notNull(accountId, "accountId不允许为空");
        Assert.notNull(amount, "amount不允许为空");
        //查询账户
        Account account = accountMapper.getById(accountId);
        Assert.notNull(account, "无效的accountId");
        return account;
    }

    /**
     * 冻结金额扣减 amount<0 冻结扣减
     * freeze = freeze + amount
     * total = total + amount
     *
     * @param accountId 账户id
     * @param amount    金额
     * @return 修改条数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int freezeDeduct(ETransactionType type, Long accountId, BigDecimal amount) {
        //查询账户
        Account account = checkGetAccount(accountId, amount);
        //检查金额
        switch (type) {
            case orderFreezeDeduct:
            case withdrawFreezeDeduct:
                Assert.isTrue(amount.compareTo(BigDecimal.ZERO) < 0, String.format("ETransactionType=%s, amount=%s, amount必须小于0", type, amount));
                //检查账户余额
                Assert.isTrue(account.getFreeze().compareTo(amount.abs()) >= 0, "冻结金额不足");
                break;
            default:
                throw new RuntimeException("无效的交易类型");
        }
        //余额冻结
        int n = accountMapper.freezeDeduct(account.getAccountId(), account.getVersion(), amount);
        if (n == 0) {
            freezeDeduct(type, accountId, amount);
        }
        //保存history
        saveAccountHistory(type, account, amount, System.currentTimeMillis());
        return n;
    }

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
    @Override
    public int balanceFreeze(ETransactionType type, Long accountId, BigDecimal amount) {
        //查询账户
        Account account = checkGetAccount(accountId, amount);
        //检查金额
        switch (type) {
            case orderFreeze:
            case withdrawFreeze:
                Assert.isTrue(amount.compareTo(BigDecimal.ZERO) < 0, String.format("ETransactionType=%s, amount=%s, amount必须小于0", type, amount));
                //检查账户余额
                Assert.isTrue(account.getBalance().compareTo(amount.abs()) >= 0, "账户余额不足");
                break;
            case orderFreezeCancel:
            case withdrawFreezeCancel:
                Assert.isTrue(amount.compareTo(BigDecimal.ZERO) > 0, String.format("ETransactionType=%s, amount=%s, amount必须大于0", type, amount));
                break;
            default:
                throw new RuntimeException("无效的交易类型");
        }
        //余额冻结
        int n = accountMapper.balanceFreeze(account.getAccountId(), account.getVersion(), amount);
        if (n == 0) {
            balanceFreeze(type, accountId, amount);
        }
        //保存history
        saveAccountHistory(type, account, amount, System.currentTimeMillis());
        return n;
    }


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
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int balanceDeduct(ETransactionType type, Long accountId, BigDecimal amount) {
        //查询账户
        Account account = checkGetAccount(accountId, amount);
        //检查金额
        switch (type) {
            case deposit:
                Assert.isTrue(amount.compareTo(BigDecimal.ZERO) > 0, String.format("ETransactionType=%s, amount=%s, amount必须大于0", type, amount));
                break;
            default:
                throw new RuntimeException("无效的交易类型");
        }
        //余额冻结
        int n = accountMapper.balanceDeduct(account.getAccountId(), account.getVersion(), amount);
        if (n == 0) {
            balanceDeduct(type, accountId, amount);
        }
        //保存history
        saveAccountHistory(type, account, amount, System.currentTimeMillis());
        return n;
    }

    private void saveAccountHistory(ETransactionType type, Account account, BigDecimal amount, long ts) {
        AccountHistory history = new AccountHistory();
        history.setAccountHistoryId(IdUtils.nextSnowflakeId());
        history.setTransactionType(type.getCode());
        history.setAccountId(account.getAccountId());
        history.setBalance(account.getBalance());
        history.setFreeze(account.getFreeze());
        history.setTotal(account.getTotal());
        history.setAmount(amount);
        history.setTs(ts);
        accountHistoryMapper.save(history);
    }

    /**
     * 创建账户
     *
     * @param account
     * @return
     */
    @Override
    public int create(Account account) {
        return accountMapper.save(account);
    }

    /**
     * 无页码分页
     * @param accountId
     * @param startTs
     * @param endTs
     * @param from
     * @param size
     * @param direct
     * @return
     */
    @Override
    public List<AccountHistory> getPage(Long accountId, Long startTs, Long endTs, Long from, Integer size, String direct) {

        return accountHistoryMapper.getPage(accountId, startTs, endTs, from, size, direct);

    }
}
