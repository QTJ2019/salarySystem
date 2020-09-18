package com.scau.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.entity.Station;
import com.scau.entity.excel.StationExcel;
import com.scau.listener.StationExcelListener;
import com.scau.mapper.StationMapper;
import com.scau.service.DepartmentService;
import com.scau.service.StationService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 岗位 服务实现类
 * </p>
 *
 * @author wyn
 * @since 2020-09-16
 */
@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements StationService {

    /**
     * 将excel里的数据导入数据库
     * @param file
     * @param stationService
     */
    @Override
    public void saveStation(MultipartFile file, StationService stationService, DepartmentService departmentService) {
        try{
            InputStream in=file.getInputStream();
            EasyExcel.read(in, StationExcel.class,new StationExcelListener(stationService,departmentService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
