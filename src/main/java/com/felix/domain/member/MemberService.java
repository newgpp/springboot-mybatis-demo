package com.felix.domain.member;

import com.felix.infra.mapper.MemberMapper;
import com.felix.infra.models.Member;
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
