package com.yy.service;

import com.yy.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author young
 * @since 2024年04月08日
 */
public interface MemberService extends IService<Member> {
    public Member getMemberByMembername(String Membername);

    public void resetDatabase();
}
