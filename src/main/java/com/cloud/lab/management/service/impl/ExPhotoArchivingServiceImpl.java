package com.cloud.lab.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.lab.management.entity.ExParamGroup;
import com.cloud.lab.management.entity.ExPhotoArchiving;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingAdd;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingSearch;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingUpdate;
import com.cloud.lab.management.mapper.ExParamGroupMapper;
import com.cloud.lab.management.mapper.ExPhotoArchivingMapper;
import com.cloud.lab.management.service.IExPhotoArchivingService;
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
public class ExPhotoArchivingServiceImpl extends ServiceImpl<ExPhotoArchivingMapper, ExPhotoArchiving> implements IExPhotoArchivingService {


    @Autowired
    private ExPhotoArchivingMapper exPhotoArchivingMapper;

    private static QueryWrapper<ExPhotoArchiving> baseQueryWrapper(ExPhotoArchivingSearch exPhotoArchivingSearch) {
        QueryWrapper<ExPhotoArchiving> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(exPhotoArchivingSearch.getCustomerCode())) {
            queryWrapper.eq("customer_code", exPhotoArchivingSearch.getCustomerCode());
        }
        if (!StringUtils.isBlank(exPhotoArchivingSearch.getProductCode())) {
            queryWrapper.eq("product_code", exPhotoArchivingSearch.getProductCode());
        }
        if (!StringUtils.isBlank(exPhotoArchivingSearch.getSampleCode())) {
            queryWrapper.eq("sample_cde", exPhotoArchivingSearch.getSampleCode());
        }
        if (!Objects.isNull(exPhotoArchivingSearch.getCameraQty())) {
            queryWrapper.eq("camera_qty", exPhotoArchivingSearch.getCameraQty());
        }
        if (!StringUtils.isBlank(exPhotoArchivingSearch.getGroupCode())) {
            queryWrapper.eq("group_cde", exPhotoArchivingSearch.getGroupCode());
        }
        if (!StringUtils.isBlank(exPhotoArchivingSearch.getPhotoNo())) {
            queryWrapper.eq("photo_no", exPhotoArchivingSearch.getPhotoNo());
        }
        return queryWrapper;
    }

    @Override
    public boolean save(ExPhotoArchivingAdd exPhotoArchivingAdd) {
        ExPhotoArchiving exPhotoArchiving = new ExPhotoArchiving();
        BeanUtils.copyProperties(exPhotoArchivingAdd, exPhotoArchiving);
        exPhotoArchiving.setInspectFlag("N");
        return this.save(exPhotoArchiving);
    }

    @Override
    public boolean update(String id, ExPhotoArchivingUpdate exPhotoArchivingUpdate) {
        ExPhotoArchiving exPhotoArchiving = this.getById(id);
        BeanUtils.copyProperties(exPhotoArchivingUpdate, exPhotoArchiving);
        return this.updateById(exPhotoArchiving);
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public ExPhotoArchiving selectOneById(String id) {
        return this.getById(id);
    }

    @Override
    public List<ExPhotoArchiving> listBySearch(ExPhotoArchivingSearch exPhotoArchivingSearch) {
        QueryWrapper<ExPhotoArchiving> exPhotoArchivingQueryWrapper = baseQueryWrapper(exPhotoArchivingSearch);
        return this.list(exPhotoArchivingQueryWrapper);
    }

    @Override
    public IPage<ExPhotoArchiving> selectPageBySearch(ExPhotoArchivingSearch exPhotoArchivingSearch) {
        IPage<ExPhotoArchiving> page = new Page<>(exPhotoArchivingSearch.getPageNo(), exPhotoArchivingSearch.getPageSize());
        QueryWrapper<ExPhotoArchiving> queryWrapper = baseQueryWrapper(exPhotoArchivingSearch);
        return exPhotoArchivingMapper.selectPage(page, queryWrapper);
    }
}
