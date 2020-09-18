package com.scau.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scau.entity.ActualSalaryItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface ActualSalaryItemMapper extends BaseMapper<ActualSalaryItem> {
    List<ActualSalaryItem> selectByCondition(@Param("params" ) ActualSalaryItem actualSalaryItem);
}
