package com.felix.interfaces.vo;

import com.felix.infra.models.Member;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/28 15:08
 */
public class MemberVO {

    private Long memberId;

    private String memberName;

    private String mobile;

    private String email;

    private String birthday;

    private String createdTime;

    private String updatedTime;

    public static MemberVO build(Member member) {
        if (member == null) {
            return null;
        }
        MemberVO vo = new MemberVO();
        BeanUtils.copyProperties(member, vo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date birthday = member.getBirthday();
        vo.setBirthday(birthday == null ? null : sdf.format(birthday));
        vo.setCreatedTime(sdf.format(member.getCreatedTime()));
        vo.setUpdatedTime(sdf.format(member.getUpdatedTime()));
        return vo;
    }


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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
