package com.felix.app;

import com.felix.domain.account.AccountService;
import com.felix.infra.models.account.Account;
import com.felix.infra.models.account.AccountHistory;
import com.felix.interfaces.enums.ETransactionType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/27 8:27
 */
@Service
public class AccountFacade {

    @Resource
    private AccountService accountService;

    public int create(Account account) {
        return accountService.create(account);
    }

    public List<Account> getList(Long memberId, String accountType) {
        return accountService.getList(memberId, accountType);
    }

    public int balanceDeduct(ETransactionType deposit, Long accountId, BigDecimal amount) {
        return accountService.balanceDeduct(deposit, accountId, amount);
    }

    public int balanceFreeze(ETransactionType orderFreeze, Long accountId, BigDecimal amount) {
        return accountService.balanceFreeze(orderFreeze, accountId, amount);
    }

    public int freezeDeduct(ETransactionType orderFreezeDeduct, Long accountId, BigDecimal amount) {
        return accountService.freezeDeduct(orderFreezeDeduct, accountId, amount);
    }

    public List<AccountHistory> getPage(Long accountId, Long startTs, Long endTs, Long from, Integer size, String direct) {
        return accountService.getPage(accountId, startTs, endTs, from, size, direct);
    }
}
