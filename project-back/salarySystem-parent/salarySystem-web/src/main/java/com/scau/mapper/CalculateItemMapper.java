package com.scau.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scau.entity.CalculateItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface CalculateItemMapper extends BaseMapper<CalculateItem> {
    List<CalculateItem> selectByCondition(@Param("params" ) CalculateItem calculateItem);
}
