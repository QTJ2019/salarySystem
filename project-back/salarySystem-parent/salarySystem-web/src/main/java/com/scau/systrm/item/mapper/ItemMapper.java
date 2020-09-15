package com.scau.systrm.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scau.systrm.item.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ItemMapper extends BaseMapper<Item> {
}
