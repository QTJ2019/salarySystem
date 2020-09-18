package com.scau.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scau.entity.ImportItem;
import com.scau.entity.Item;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface ItemMapper extends BaseMapper<Item> {
    List<Item> selectByCondition(@Param("params" ) Item item);
}
