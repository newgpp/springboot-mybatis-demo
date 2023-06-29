package com.felix.interfaces;

import com.felix.app.AccountFacade;
import com.felix.interfaces.vo.AccountHistoryVO;
import com.felix.interfaces.vo.AccountVO;
import com.felix.infra.models.account.Account;
import com.felix.infra.models.account.AccountHistory;
import com.felix.interfaces.enums.EAccountType;
import com.felix.interfaces.enums.ETransactionType;
import com.felix.domain.base.RestCode;
import com.felix.domain.base.Result;
import com.felix.infra.util.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private AccountFacade accountFacade;

    @PostMapping("/create")
    public Result<Integer> create(@RequestParam Long memberId, @RequestParam String accountType) {
        log.info("account-create, memberId={}, accountType={}", memberId, accountType);
        Optional<EAccountType> typeOptional = EAccountType.findByCode(accountType);
        if (!typeOptional.isPresent()) {
            return Result.fail(RestCode.PARAM_ERROR, "无效的账户类型");
        }
        Account account = new Account();
        account.setAccountId(IdUtils.nextSnowflakeId());
        account.setMemberId(memberId);
        account.setAccountType(accountType);
        int n = accountFacade.create(account);
        if (n > 0) {
            return Result.success(n);
        }
        return Result.fail(RestCode.SYSTEM_ERROR, "创建账户失败");
    }

    @GetMapping("/getList")
    public Result<List<AccountVO>> getList(@RequestParam Long memberId, @RequestParam String accountType) {
        log.info("account-getList, memberId={}, accountType={}", memberId, accountType);
        Optional<EAccountType> typeOptional = EAccountType.findByCode(accountType);
        if (!typeOptional.isPresent()) {
            return Result.fail(RestCode.PARAM_ERROR, "无效的账户类型");
        }
        List<Account> list = accountFacade.getList(memberId, accountType);
        List<AccountVO> voList = list.stream().map(AccountVO::build).collect(Collectors.toList());
        return Result.success(voList);
    }

    @PostMapping("/deposit")
    public Result<Integer> deposit(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        log.info("account-deposit, accountId={}, amount={}", accountId, amount);
        int n = accountFacade.balanceDeduct(ETransactionType.deposit, accountId, amount);
        return Result.success(n);
    }

    @PostMapping("/orderFreeze")
    public Result<Integer> orderFreeze(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        log.info("account-orderFreeze, accountId={}, amount={}", accountId, amount);
        int n = accountFacade.balanceFreeze(ETransactionType.orderFreeze, accountId, amount);
        return Result.success(n);
    }

    @PostMapping("/orderFreezeDeduct")
    public Result<Integer> orderFreezeDeduct(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        log.info("account-orderFreezeDeduct, accountId={}, amount={}", accountId, amount);
        int n = accountFacade.freezeDeduct(ETransactionType.orderFreezeDeduct, accountId, amount);
        return Result.success(n);
    }

    @GetMapping("/history/page")
    public Result<List<AccountHistoryVO>> historyPage(@RequestParam Long accountId, @RequestParam Long from
            , @RequestParam Integer size, @RequestParam String direct) {
        log.info("account-history-page, accountId={}, fromId={}, limit={}, direct={}", accountId, from, size, direct);
        List<AccountHistory> list = accountFacade.getPage(accountId, null, null, from, size, direct);
        List<AccountHistoryVO> voList = list.stream().map(AccountHistoryVO::build).collect(Collectors.toList());
        return Result.success(voList);
    }
}
