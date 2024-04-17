package com.yy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author young
 * @since 2024年04月08日
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("member")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "member_id", type = IdType.AUTO)
    private Integer memberId;

    @TableField("team_id")
    private Integer teamId;
    @TableField("name")
    private String name;
    @TableField("password")
    private String password;
    @TableField("memberx")
    private Integer memberx = 100;

    @TableField("membery")
    private Integer membery = 100;
    @TableField("distance")
    private Integer distance;
    @TableField("desx")
    private Integer desx;
    @TableField("desy")
    private Integer desy;
    @TableField("raceid")
    private Integer raceid;

    public static Member init( String b, String c) {
        Member member = new Member();
        // 设置默认的团队ID
        member.setTeamId(1);
        member.setName(b); // 设置默认的姓名
        member.setPassword(c); // 设置默认的密码
        member.setMemberx(5);
        member.setMembery(5);// 设置默认的比赛ID
        member.setDistance(50);
        member.setDesx(0);
        member.setDesy(0);
        member.setRaceid(1);

        return member;

    }
}
