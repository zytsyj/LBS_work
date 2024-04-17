package com.yy.dao;

import com.yy.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.entity.Race;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author young
 * @since 2024年04月08日
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    @Select("SELECT r.desx, r.desy " +
            "FROM team t " +
            "INNER JOIN race r ON t.race_id = r.race_id " +
            "WHERE t.team_id = (SELECT team_id FROM member WHERE member_id = #{memberId})")
    XYCoordinates getXYByMemberId(@Param("memberId") Integer memberId);



    // 定义一个内部类来封装查询结果，这样可以直接返回 x y 坐标
    class XYCoordinates {
        public Integer desx;
        public Integer desy;
    }

}
