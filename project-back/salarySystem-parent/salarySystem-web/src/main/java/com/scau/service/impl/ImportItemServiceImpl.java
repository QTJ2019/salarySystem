package com.scau.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.entity.ImportItem;
import com.scau.mapper.ImportItemMapper;
import com.scau.service.ImportItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportItemServiceImpl extends ServiceImpl<ImportItemMapper, ImportItem> implements ImportItemService {

    @Autowired
    private ImportItemMapper importItemMapper;

    @Override
    public IPage<ImportItem> selectImportItemByPage(Integer currentPage) {
        IPage<ImportItem> myImportItemPage=new Page<>(currentPage,3);
        IPage<ImportItem> importItemIPage=  importItemMapper.selectPage(myImportItemPage,null);
        return importItemIPage;
    }

    @Override
    public List<ImportItem> selectByCondition(ImportItem importItem) {
        return this.baseMapper.selectByCondition(importItem);
    }
}
