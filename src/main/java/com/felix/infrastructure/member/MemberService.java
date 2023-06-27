package com.felix.infrastructure.member;

import com.felix.domain.member.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/26 12:53
 */
@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int create(Member member) {
        return memberMapper.save(member);
    }

    public Member getById(Long memberId) {
        return memberMapper.getById(memberId);
    }
}
