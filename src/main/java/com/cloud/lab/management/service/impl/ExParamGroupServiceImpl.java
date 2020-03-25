package com.cloud.lab.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.lab.management.entity.ExParamGroup;
import com.cloud.lab.management.entity.ExPlan;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupAdd;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupSearch;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupUpdate;
import com.cloud.lab.management.mapper.ExParamGroupMapper;
import com.cloud.lab.management.service.IExParamGroupService;
import com.cloud.lab.management.service.IExPlanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/11/29 14:36
 */
@Service
public class ExParamGroupServiceImpl extends ServiceImpl<ExParamGroupMapper, ExParamGroup> implements IExParamGroupService {


    @Autowired
    private ExParamGroupMapper exParamGroupMapper;

    @Autowired
    private IExPlanService exPlanService;


    private static QueryWrapper<ExParamGroup> baseQueryWrapper(ExParamGroupSearch exParamGroupSearch) {
        QueryWrapper<ExParamGroup> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(exParamGroupSearch.getCustomerCode())) {
            queryWrapper.eq("customer_code", exParamGroupSearch.getCustomerCode());
        }
        if (!StringUtils.isBlank(exParamGroupSearch.getProductCode())) {
            queryWrapper.eq("product_code", exParamGroupSearch.getProductCode());
        }
        if (!StringUtils.isBlank(exParamGroupSearch.getSampleCode())) {
            queryWrapper.eq("sample_code", exParamGroupSearch.getSampleCode());
        }
        if (!Objects.isNull(exParamGroupSearch.getCameraQty())) {
            queryWrapper.eq("camera_qty", exParamGroupSearch.getCameraQty());
        }
        if (!StringUtils.isBlank(exParamGroupSearch.getGroupCode())) {
            queryWrapper.like("group_code", exParamGroupSearch.getGroupCode());
        }
        return queryWrapper;
    }


    @Override
    public boolean save(ExParamGroupAdd exParamGroupAdd) {
        ExParamGroup exParamGroup = new ExParamGroup();
        BeanUtils.copyProperties(exParamGroupAdd, exParamGroup);
        return this.save(exParamGroup);
    }

    @Override
    public boolean update(String id, ExParamGroupUpdate exParamGroupUpdate) {
        ExParamGroup exParamGroup = this.getById(id);
        BeanUtils.copyProperties(exParamGroupUpdate, exParamGroup);
        return this.updateById(exParamGroup);
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public ExParamGroup selectOneById(String id) {
        return this.getById(id);
    }

    @Override
    public List<ExParamGroup> listBySearch(ExParamGroupSearch exParamGroupSearch) {
        QueryWrapper<ExParamGroup> exPlanQueryWrapper = baseQueryWrapper(exParamGroupSearch);
        return this.list(exPlanQueryWrapper);
    }

    @Override
    public IPage<ExParamGroup> selectPageBySearch(ExParamGroupSearch exParamGroupSearch) {
        IPage<ExParamGroup> page = new Page<>(exParamGroupSearch.getPageNo(), exParamGroupSearch.getPageSize());
        QueryWrapper<ExParamGroup> queryWrapper = baseQueryWrapper(exParamGroupSearch);
        return exParamGroupMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<ExParamGroup> selectByPlanIdAndGroupCode(String planId, String groupCode) {
        ExPlan exPlan = exPlanService.selectOneById(planId);
        ExParamGroupSearch search = new ExParamGroupSearch();
        search.setCustomerCode(exPlan.getCustomerCode())
                .setProductCode(exPlan.getProductCode())
                .setSampleCode(exPlan.getSampleCode())
                .setCameraQty(exPlan.getCameraQty())
                .setGroupCode(groupCode);
        return this.listBySearch(search);
    }

    @Override
    public List<ExParamGroup> getByPlanIdWithDefault(String planId) {
        ExPlan exPlan = exPlanService.selectOneById(planId);
        ExParamGroupSearch search = new ExParamGroupSearch();
        search.setCustomerCode(exPlan.getCustomerCode())
                .setProductCode(exPlan.getProductCode())
                .setSampleCode(exPlan.getSampleCode())
                .setCameraQty(exPlan.getCameraQty());
        List<ExParamGroup> exParamGroups = this.listBySearch(search);
//        if (CollectionUtils.isEmpty(exParamGroups)) {
//            ExParamGroup group = new ExParamGroup();
//            group.setCustomerCode(exPlan.getCustomerCode());
//            group.setProductCode(exPlan.getProductCode());
//            group.setSampleCode(exPlan.getSampleCode());
//            group.setCameraQty(exPlan.getCameraQty());
//            group.setGroupCode("00");
//            group.setGroupStatus(0);
//            exParamGroups.add(group);
//        }
        List<ExParamGroup> sorted = exParamGroups.stream().sorted(Comparator.comparing(ExParamGroup::getGroupCode)).collect(Collectors.toList());
        return sorted;
    }
}
