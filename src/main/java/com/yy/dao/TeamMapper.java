package com.yy.dao;

import com.yy.entity.Member;
import com.yy.entity.Team;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
public interface TeamMapper extends BaseMapper<Team> {
    @Select("SELECT * " +
            "FROM member " +
            "WHERE member.team_id  = #{teamId}")
    List<Member> getUserWithTodoEvents(@Param("teamId") Integer teamId);
}
