package com.felix.interfaces;

import com.felix.app.MemberFacade;
import com.felix.interfaces.vo.MemberVO;
import com.felix.interfaces.enums.RestCode;
import com.felix.interfaces.vo.Result;
import com.felix.infra.models.Member;
import com.felix.infra.util.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/26 13:12
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    private static Logger log = LoggerFactory.getLogger(MemberController.class);

    @Resource
    private MemberFacade memberFacade;

    @PostMapping("/create")
    public Result<Integer> create(@RequestParam Long memberId, @RequestParam String memberName, @RequestParam String mobile) {
        log.info("member-create, memberName={}, mobile={}", memberName, mobile);
        Member member = new Member();
        member.setMemberId(memberId == null ? IdUtils.nextSnowflakeId() : memberId);
        member.setMemberName(memberName);
        member.setMobile(mobile);
        int n = memberFacade.create(member);
        if (n > 0) {
            return Result.success(n);
        }
        return Result.fail(RestCode.SYSTEM_ERROR, "创建会员失败");
    }

    @GetMapping("/getById")
    public Result<MemberVO> getById(@RequestParam Long memberId) {
        log.info("member-getById, memberId={}", memberId);
        Member member = memberFacade.getById(memberId);
        return Result.success(MemberVO.build(member));
    }
}
