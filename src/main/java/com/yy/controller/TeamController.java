package com.yy.controller;


import com.example.demo.common.Result;
import com.yy.dao.TeamMapper;
import com.yy.entity.Member;
import com.yy.entity.Team;
import com.yy.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamMapper TeamMapper;
    @Autowired
    private TeamService TeamService;
    @GetMapping("all")
    public List<Team> getAll() {
        return TeamMapper.selectList(null);
    }
    @GetMapping("/{teamId}")
    public List<Member> getUserWithTodoEvents(@PathVariable Integer teamId) {

        return TeamMapper.getUserWithTodoEvents(teamId);
    }
    @PostMapping
    public Result<?> save(@RequestBody Team user){
        TeamService.saveOrUpdate(user);
        return Result.success();
    }
    @DeleteMapping("/delete/{TeamId}")
    public Result<?> update(@PathVariable long TeamId){
        TeamMapper.deleteById(TeamId);


        return Result.success();
    }
}

