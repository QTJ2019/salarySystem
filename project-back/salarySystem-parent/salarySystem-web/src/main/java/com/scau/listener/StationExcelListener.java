package com.scau.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scau.entity.Department;
import com.scau.entity.Station;
import com.scau.entity.excel.StationExcel;
import com.scau.service.DepartmentService;
import com.scau.service.StationService;

public class StationExcelListener extends AnalysisEventListener<StationExcel>{

    private StationService stationService;

    private DepartmentService departmentService;

    public StationExcelListener(){

    }

    public StationExcelListener(StationService stationService, DepartmentService departmentService){
        this.stationService=stationService;
        this.departmentService=departmentService;
    }

    @Override
    public void invoke(StationExcel stationExcel, AnalysisContext analysisContext) {
        if(stationExcel==null) {
            System.out.println("文件数据为空");
        }
        Department department=this.departmentIsExist(departmentService,stationExcel.getDeptName());
        if(department==null){
            System.out.println("该部门不存在");
        }else{
            Station station=this.isExist(stationService,stationExcel.getName());
            if(station==null){
                station=new Station();
                station.setName(stationExcel.getName());
                station.setDepartmentid(department.getId());
                stationService.save(station);
            }
        }

    }

    /**
     * 查看该工种是不是已经存在了
     * @param service
     * @param name
     * @return
     */
    private Station isExist(StationService service, String name){
        QueryWrapper<Station> wrapper=new QueryWrapper<>();
        wrapper.eq("name",name);
        Station station=stationService.getOne(wrapper);
        return station;
    }

    private Department departmentIsExist(DepartmentService departmentService, String deptName){
        QueryWrapper<Department> wrapper=new QueryWrapper<>();
        wrapper.eq("name",deptName);
        Department department=departmentService.getOne(wrapper);
        return department;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
