package com.scau.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.entity.ImportItem;

import java.util.List;


public interface ImportItemService extends IService<ImportItem> {
    IPage<ImportItem> selectImportItemByPage(Integer currentPage);

    List<ImportItem> selectByCondition(ImportItem importItem);
}
