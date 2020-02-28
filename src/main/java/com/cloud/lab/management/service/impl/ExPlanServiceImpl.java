package com.cloud.lab.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.lab.management.entity.CameraParameters;
import com.cloud.lab.management.entity.ExPlan;
import com.cloud.lab.management.entity.dto.explan.ExPlanAdd;
import com.cloud.lab.management.entity.dto.explan.ExPlanSearch;
import com.cloud.lab.management.entity.dto.explan.ExPlanUpdate;
import com.cloud.lab.management.mapper.CameraParametersMapper;
import com.cloud.lab.management.mapper.ExPlanMapper;
import com.cloud.lab.management.service.ICameraParametersService;
import com.cloud.lab.management.service.IExPlanService;
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
public class ExPlanServiceImpl extends ServiceImpl<ExPlanMapper, ExPlan> implements IExPlanService {

    @Autowired
    private ExPlanMapper exPlanMapper;


    private static QueryWrapper<ExPlan> baseQueryWrapper(ExPlanSearch exPlanSearch) {
        QueryWrapper<ExPlan> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(exPlanSearch.getCustomerCode())) {
            queryWrapper.eq("customer_code", exPlanSearch.getCustomerCode());
        }
        if (!StringUtils.isBlank(exPlanSearch.getProductCode())) {
            queryWrapper.eq("product_code", exPlanSearch.getProductCode());
        }
        if (!StringUtils.isBlank(exPlanSearch.getSampleCode())) {
            queryWrapper.eq("sample_cde", exPlanSearch.getSampleCode());
        }
        if (!Objects.isNull(exPlanSearch.getCameraQty())) {
            queryWrapper.eq("camera_qty", exPlanSearch.getCameraQty());
        }
        return queryWrapper;
    }


    @Override
    public boolean save(ExPlanAdd exPlanAdd) {
        ExPlan exPlan = new ExPlan();
        BeanUtils.copyProperties(exPlanAdd, exPlan);
        return this.save(exPlan);
    }

    @Override
    public boolean update(String id, ExPlanUpdate exPlanUpdate) {
        ExPlan exPlan = this.getById(id);
        BeanUtils.copyProperties(exPlanUpdate, exPlan);
        return this.updateById(exPlan);
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public ExPlan selectOneById(String id) {
        return this.getById(id);
    }

    @Override
    public List<ExPlan> listBySearch(ExPlanSearch exPlanSearch) {
        QueryWrapper<ExPlan> exPlanQueryWrapper = baseQueryWrapper(exPlanSearch);
        return this.list(exPlanQueryWrapper);
    }

    @Override
    public IPage<ExPlan> selectPageBySearch(ExPlanSearch exPlanSearch) {
        IPage<ExPlan> page = new Page<>(exPlanSearch.getPageNo(), exPlanSearch.getPageSize());
        QueryWrapper<ExPlan> queryWrapper = baseQueryWrapper(exPlanSearch);
        return exPlanMapper.selectPage(page, queryWrapper);
    }
}
