package com.scau.controller.infoController;


import com.scau.Result.Result;
import com.scau.service.DepartmentService;
import com.scau.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("addStation")
    public Result addStation(MultipartFile file){
        stationService.saveStation(file,stationService,departmentService);
        return Result.ok();
    }

}

