package com.felix.infra.models.member;

import com.felix.domain.base.EntityBase;

import java.util.Date;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/26 12:53
 */
public class Member extends EntityBase {

    private Long memberId;

    private String memberName;

    private String mobile;

    private String email;

    private Date birthday;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
