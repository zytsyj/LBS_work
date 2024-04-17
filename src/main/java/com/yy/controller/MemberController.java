package com.yy.controller;


import com.example.demo.common.Result;
import com.yy.dao.MemberMapper;
import com.yy.dao.RaceMapper;
import com.yy.dao.TeamMapper;
import com.yy.entity.Member;
import com.yy.entity.Race;
import com.yy.entity.Team;
import com.yy.service.MemberService;
import com.yy.service.RaceService;
import com.yy.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author young
 * @since 2024年04月08日
 */
@RestController
@CrossOrigin
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberService memberService;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private TeamService teamService;
    @Autowired
    private RaceMapper raceMapper;
    @Autowired
    private RaceService raceService;
    @GetMapping("/all")
    public List<Member> getAll() {
        return memberMapper.selectList(null);
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody Member memberd) {


        Member member = memberService.getMemberByMembername(memberd.getName());
        if (member != null && Objects.equals(member.getPassword(), memberd.getPassword())) {
            // 验证成功，返回成功信息或者生成 Token 等操作
            return Result.success(member);
        }
        return Result.error("1","失败");
    }


    @PostMapping
    public Result<?> save(@RequestBody Member member) {

        Team team = teamMapper.selectById(member.getTeamId());
        Race race = raceMapper.selectById(team.getRaceId());
        if (race.winnerId==-1){
            memberService.saveOrUpdate(member);
            MemberMapper.XYCoordinates aa = memberMapper.getXYByMemberId(member.getMemberId());
            int distance = (member.getMemberx() - aa.desx) * (member.getMemberx() - aa.desx) + (member.getMembery() - aa.desy) * (member.getMembery() - aa.desy);
            member.setDistance(distance);
            member.setDesx(aa.desx);
            member.setDesy(aa.desy);
            if (member.getDistance() < 25) {
                int score = team.getScore();
                score = score + 1;
                team.setScore(score);
                teamService.saveOrUpdate(team);
            }
            if (team.getScore() >= 3) {
                race.setWinnerId(team.getTeamId());
                raceService.saveOrUpdate(race);
            }
            member.setRaceid(race.getRaceId());

            memberService.saveOrUpdate(member);

            return Result.success();
        }
        return Result.error("1","已有胜者");
        }

    @PostMapping("/database")
    @Transactional
    public String resetDatabase() {
        memberService.resetDatabase(); // 调用重置数据库的方法
        return "Database reset successfully.";
    }
    @DeleteMapping("/delete/{memberId}")
    public Result<?> update(@PathVariable long memberId){
        memberMapper.deleteById(memberId);


        return Result.success();
    }
}



