package com.scau.controller.infoController;


import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scau.Result.Result;
import com.scau.entity.Department;
import com.scau.entity.Station;
import com.scau.entity.vo.StationVO;
import com.scau.service.DepartmentService;
import com.scau.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 岗位 前端控制器
 * </p>
 *
 * @author wyn
 * @since 2020-09-16
 */
@RestController
@RequestMapping("/api/station")
public class StationController {

    @Autowired
    private StationService stationService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * 通过excel表添加工种
     * @param file
     * @return
     */
    @PostMapping("addStation")
    public Result addStation(MultipartFile file){
        stationService.saveStation(file,stationService,departmentService);
        return Result.ok();
    }

    /**
     * 直接添加工种信息
     * @param stationVO
     * @return
     */
    @PostMapping("saveStation")
    public Result saveStation(@RequestBody StationVO stationVO){
        Station station=new Station();
        station.setName(stationVO.getName());
        QueryWrapper<Department> wrapper=new QueryWrapper<>();
        wrapper.eq("name",stationVO.getDeptName());
        Department department = departmentService.getOne(wrapper);
        station.setDepartmentid(department.getId());
        boolean flag=stationService.save(station);
        if(flag){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    /**
     * 删除工种
     * @param id
     * @return
     */
    @GetMapping("removeStation/{id}")
    public Result removeStation(@PathVariable("id") Integer id){
        boolean flag=stationService.removeById(id);
        if(flag){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    /**
     * 获取所有的工种
     * @return
     */
    @GetMapping("getAllStation")
    public Result getAllStation(){
        List<Station> stationList=stationService.list(null);
        return Result.ok().data("items",stationList);
    }

    /**
     * 修改工种信息
     * @param station
     * @return
     */
    @PostMapping("updateStation")
    public Result updateStation(@RequestBody Station station){
        boolean flag=stationService.updateById(station);
        if(flag){
            return Result.ok();
        }else{
            return Result.error();
        }
    }





}

