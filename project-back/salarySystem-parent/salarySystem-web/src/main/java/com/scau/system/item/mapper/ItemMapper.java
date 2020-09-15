package com.scau.system.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scau.system.item.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface ItemMapper extends BaseMapper<Item> {
}
