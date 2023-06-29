package com.felix.app;

import com.felix.domain.member.MemberService;
import com.felix.infra.models.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/29 11:00
 */
@Service
public class MemberFacade {

    @Resource
    private MemberService memberService;

    public int create(Member member) {
        return memberService.create(member);
    }


    public Member getById(Long memberId) {
        return memberService.getById(memberId);
    }
}
