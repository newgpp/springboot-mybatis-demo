package com.felix.application;

import com.felix.domain.base.RestCode;
import com.felix.domain.base.Result;
import com.felix.domain.member.Member;
import com.felix.infrastructure.member.MemberService;
import com.felix.infrastructure.util.IdentifyUtils;
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
    private MemberService memberService;

    @PostMapping("/create")
    public Result<Integer> create(@RequestParam Long memberId, @RequestParam String memberName, @RequestParam String mobile) {
        log.info("member-create, memberName={}, mobile={}", memberName, mobile);
        Member member = new Member();
        member.setMemberId(memberId == null ? IdentifyUtils.getId() : memberId);
        member.setMemberName(memberName);
        member.setMobile(mobile);
        int n = memberService.create(member);
        if (n > 0) {
            return Result.success(n);
        }
        return Result.fail(RestCode.SYSTEM_ERROR, "创建会员失败");
    }

    @GetMapping("/getById")
    public Result<Member> getById(@RequestParam Long memberId) {
        log.info("member-getById, memberId={}", memberId);
        Member member = memberService.getById(memberId);
        return Result.success(member);
    }
}
