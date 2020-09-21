package com.scau.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scau.entity.FixedItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface FixedItemMapper extends BaseMapper<FixedItem> {
//    @Select("select `fixed_item`.`id`,`employee`.`departmentId`,`employee`.`departmentName`,`employee`.`stationId`,\n" +
//            "`employee`.`stationName`,`fixed_item`.`employee_id`,`employee`.`name`as 'employee_name',`fixed_item`.`item_id`,`item`.`name`as 'item_name',`fixed_item`.`value`,`fixed_item`.`date`\n" +
//            "from `item`,`employee`,`fixed_item`\n" +
//            "where `item`.`id`=`fixed_item`.`item_id`\n" +
//            "and `employee`.`id`=`fixed_item`.`employee_id`;")
    List<FixedItem> selectAll();

//    @Select("select `fixed_item`.`id`,`employee`.`departmentId`,`employee`.`departmentName`,`employee`.`stationId`,\n" +
//            "`employee`.`stationName`,`fixed_item`.`employee_id`,`employee`.`name`as 'employee_name',`fixed_item`.`item_id`,`item`.`name`as 'item_name',`fixed_item`.`value`,`fixed_item`.`date`\n" +
//            "from `item`,`employee`,`fixed_item`\n" +
//            "where `item`.`id`=`fixed_item`.`item_id`\n" +
//            "and `employee`.`id`=`fixed_item`.`employee_id`\n"+
//            "and `fixed_item`.`employee_id`= #{employeeId};")
    List<FixedItem> selectByEmployeeId(Integer employeeId);

//    List<FixedItem> selectBy(@Param("employeeId") Integer employeeId,
//                             @Param("employeeName")String employeeName,
//                             @Param("departmentName")String departmentName,
//                             @Param("stationName") String stationName,
//                             @Param("itemName") String itemName,
//                             @Param("startDate") Date startDate,
//                             @Param("endDate") Date endDate);

//    List<FixedItem> selectBy(@Param("params" ) FixedItem fixedItem,
//                             @Param("limit") Limitation limitation);
    List<FixedItem> selectByCondition(@Param("params" ) FixedItem fixedItem);
}
