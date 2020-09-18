package com.scau.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.entity.Station;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 岗位 服务类
 * </p>
 *
 * @author wyn
 * @since 2020-09-16
 */
public interface StationService extends IService<Station> {

    void saveStation(MultipartFile file, StationService stationService, DepartmentService departmentService);
}
