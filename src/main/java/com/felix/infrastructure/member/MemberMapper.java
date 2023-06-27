package com.felix.infrastructure.member;

import com.felix.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/26 13:15
 */
@Mapper
public interface MemberMapper {

    int save(Member member);

    Member getById(@Param("memberId") Long memberId);
}
