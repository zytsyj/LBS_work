package com.yy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.dao.RaceMapper;
import com.yy.dao.TeamMapper;
import com.yy.entity.Member;
import com.yy.dao.MemberMapper;
import com.yy.entity.Race;
import com.yy.entity.Team;
import com.yy.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.service.RaceService;
import com.yy.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author young
 * @since 2024年04月08日
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Autowired
    private MemberMapper MemberMapper;
    @Autowired
    private RaceMapper RaceMapper;
    @Autowired
    private TeamMapper TeamMapper;
    @Autowired
    private RaceService RaceService;
    @Autowired
    private TeamService TeamService;
    public Member getMemberByMembername(String Membername) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", Membername);
        return MemberMapper.selectOne(queryWrapper);
    }

    public void resetDatabase(){
            MemberMapper.delete(null);
            RaceMapper.delete(null);
            TeamMapper.delete(null);

        Race race = new Race();
        race.setRaceId(1);
        race.setDesx(0);
        race.setDesy(0);
        RaceService.saveOrUpdate(race);
        Team team = new Team();
        team.setRaceId(1);
        team.setTeamId(1);

        TeamService.saveOrUpdate(team);
        Member member = Member.init("laowang","12346");
        Member member1 = Member.init("laowang1","12346");
        Member member2 = Member.init("laowang2","12346");

        saveOrUpdate(member);
        saveOrUpdate(member1);
        saveOrUpdate(member2);

    }
}
