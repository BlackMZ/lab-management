package com.cloud.lab.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.lab.management.entity.ExDefectRecord;
import com.cloud.lab.management.entity.dto.exdefectrecord.ExDefectRecordAdd;
import com.cloud.lab.management.entity.dto.exdefectrecord.ExDefectRecordSearch;
import com.cloud.lab.management.entity.dto.exdefectrecord.ExDefectRecordUpdate;
import com.cloud.lab.management.mapper.ExDefectRecordMapper;
import com.cloud.lab.management.service.IExDefectRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/11/29 14:36
 */
@Service
public class ExDefectRecordServiceImpl extends ServiceImpl<ExDefectRecordMapper, ExDefectRecord> implements IExDefectRecordService {

    @Autowired
    private ExDefectRecordMapper exDefectRecordMapper;

    private static QueryWrapper<ExDefectRecord> baseQueryWrapper(ExDefectRecordSearch exDefectRecordSearch) {
        QueryWrapper<ExDefectRecord> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(exDefectRecordSearch.getCustomerCode())) {
            queryWrapper.eq("customer_code", exDefectRecordSearch.getCustomerCode());
        }
        if (!StringUtils.isBlank(exDefectRecordSearch.getProductCode())) {
            queryWrapper.eq("product_code", exDefectRecordSearch.getProductCode());
        }
        if (!StringUtils.isBlank(exDefectRecordSearch.getSampleCode())) {
            queryWrapper.eq("sample_cde", exDefectRecordSearch.getSampleCode());
        }
        if (!Objects.isNull(exDefectRecordSearch.getCameraQty())) {
            queryWrapper.eq("camera_qty", exDefectRecordSearch.getCameraQty());
        }
        return queryWrapper;
    }

    @Override
    public boolean save(ExDefectRecordAdd exDefectRecordAdd) {
        ExDefectRecord exDefectRecord = new ExDefectRecord();
        BeanUtils.copyProperties(exDefectRecordAdd, exDefectRecord);
        return this.save(exDefectRecord);
    }

    @Override
    public boolean update(String id, ExDefectRecordUpdate exDefectRecordUpdate) {
        ExDefectRecord exDefectRecord = this.getById(id);
        BeanUtils.copyProperties(exDefectRecordUpdate, exDefectRecord);
        return this.updateById(exDefectRecord);
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public ExDefectRecord selectOneById(String id) {
        return this.getById(id);
    }

    @Override
    public List<ExDefectRecord> listBySearch(ExDefectRecordSearch exDefectRecordSearch) {
        QueryWrapper<ExDefectRecord> exDefectRecordQueryWrapper = baseQueryWrapper(exDefectRecordSearch);
        return this.list(exDefectRecordQueryWrapper);
    }

    @Override
    public IPage<ExDefectRecord> selectPageBySearch(ExDefectRecordSearch exDefectRecordSearch) {
        IPage<ExDefectRecord> page = new Page<>(exDefectRecordSearch.getPageNo(), exDefectRecordSearch.getPageSize());
        QueryWrapper<ExDefectRecord> queryWrapper = baseQueryWrapper(exDefectRecordSearch);
        return exDefectRecordMapper.selectPage(page, queryWrapper);
    }
}
