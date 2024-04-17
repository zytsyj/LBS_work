package com.yy.controller;


import com.example.demo.common.Result;
import com.yy.dao.RaceMapper;
import com.yy.entity.Race;
import com.yy.service.RaceService;
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
@RequestMapping("/race")
public class RaceController {
    @Autowired
    private RaceMapper RaceMapper;
    @Autowired
    private RaceService RaceService;
    @GetMapping("all")
    public List<Race> getAll() {
        return RaceMapper.selectList(null);
    }
    @PostMapping
    public Result<?> save(@RequestBody Race user){
        RaceService.saveOrUpdate(user);
        return Result.success();
    }
    @DeleteMapping("/delete/{RaceId}")
    public Result<?> update(@PathVariable long RaceId){
        RaceMapper.deleteById(RaceId);


        return Result.success();
    }
}

