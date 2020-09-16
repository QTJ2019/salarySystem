package com.scau.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.entity.ImportItem;


public interface ImportItemService extends IService<ImportItem> {
    IPage<ImportItem> selectImportItemByPage(Integer currentPage);
}
