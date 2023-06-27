package com.felix.infrastructure.account;

import com.felix.infrastructure.member.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/27 8:27
 */
@Service
public class AccountFacade {

    @Resource
    private AccountServiceImpl accountService;

    @Resource
    private MemberService memberService;

}
