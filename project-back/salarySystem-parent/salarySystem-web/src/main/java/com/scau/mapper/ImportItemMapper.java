package com.scau.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scau.entity.ImportItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface ImportItemMapper extends BaseMapper<ImportItem> {
    List<ImportItem> selectByCondition(@Param("params" ) ImportItem importItem);
}
