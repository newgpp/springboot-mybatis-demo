package com.felix.application;

import com.felix.domain.account.Account;
import com.felix.domain.account.AccountHistory;
import com.felix.domain.account.EAccountType;
import com.felix.domain.account.ETransactionType;
import com.felix.domain.base.RestCode;
import com.felix.domain.base.Result;
import com.felix.infrastructure.account.AccountHistoryMapper;
import com.felix.infrastructure.account.AccountServiceImpl;
import com.felix.infrastructure.util.IdentifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/26 13:12
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    public static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Resource
    private AccountServiceImpl accountService;

    @PostMapping("/create")
    public Result<Integer> create(@RequestParam Long memberId, @RequestParam String accountType) {
        log.info("account-create, memberId={}, accountType={}", memberId, accountType);
        Optional<EAccountType> typeOptional = EAccountType.findByCode(accountType);
        if (!typeOptional.isPresent()) {
            return Result.fail(RestCode.PARAM_ERROR, "无效的账户类型");
        }
        Account account = new Account();
        account.setAccountId(IdentifyUtils.getId());
        account.setMemberId(memberId);
        account.setAccountType(accountType);
        int n = accountService.create(account);
        if (n > 0) {
            return Result.success(n);
        }
        return Result.fail(RestCode.SYSTEM_ERROR, "创建账户失败");
    }

    @GetMapping("/getList")
    public Result<List<Account>> getList(@RequestParam Long memberId, @RequestParam String accountType) {
        log.info("account-getList, memberId={}, accountType={}", memberId, accountType);
        Optional<EAccountType> typeOptional = EAccountType.findByCode(accountType);
        if (!typeOptional.isPresent()) {
            return Result.fail(RestCode.PARAM_ERROR, "无效的账户类型");
        }
        List<Account> list = accountService.getList(memberId, accountType);
        return Result.success(list);
    }

    @PostMapping("/deposit")
    public Result<Integer> deposit(@RequestParam Long accountId, @RequestParam BigDecimal amount) {

        log.info("account-deposit, accountId={}, amount={}", accountId, amount);

        int n = accountService.balanceDeduct(ETransactionType.deposit, accountId, amount);

        return Result.success(n);

    }

    @PostMapping("/orderFreeze")
    public Result<Integer> orderFreeze(@RequestParam Long accountId, @RequestParam BigDecimal amount) {

        log.info("account-orderFreeze, accountId={}, amount={}", accountId, amount);

        int n = accountService.balanceFreeze(ETransactionType.orderFreeze, accountId, amount);

        return Result.success(n);

    }

    @PostMapping("/orderFreezeDeduct")
    public Result<Integer> orderFreezeDeduct(@RequestParam Long accountId, @RequestParam BigDecimal amount) {

        log.info("account-orderFreezeDeduct, accountId={}, amount={}", accountId, amount);

        int n = accountService.freezeDeduct(ETransactionType.orderFreezeDeduct, accountId, amount);

        return Result.success(n);

    }

    @GetMapping("/history/page")
    public Result<List<AccountHistory>> historyPage(@RequestParam Long accountId, @RequestParam Long from
            , @RequestParam Integer size, @RequestParam String direct) {
        log.info("account-history-page, accountId={}, fromId={}, limit={}, direct={}", accountId, from, size, direct);
        List<AccountHistory> list = accountService.getPage(accountId, null, null, from, size, direct);
        return Result.success(list);

    }


}
