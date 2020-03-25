package com.cloud.lab.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.lab.management.entity.ExPlan;
import com.cloud.lab.management.entity.ExPlanCameraConf;
import com.cloud.lab.management.entity.dto.explan.ExPlanAdd;
import com.cloud.lab.management.entity.dto.explan.ExPlanSearch;
import com.cloud.lab.management.entity.dto.explan.ExPlanUpdate;
import com.cloud.lab.management.mapper.ExPlanMapper;
import com.cloud.lab.management.service.IExPlanCameraConfService;
import com.cloud.lab.management.service.IExPlanService;
import com.google.common.collect.Lists;
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

    @Autowired
    private IExPlanCameraConfService exPlanCameraConfService;


    private static QueryWrapper<ExPlan> baseQueryWrapper(ExPlanSearch exPlanSearch) {
        QueryWrapper<ExPlan> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(exPlanSearch.getCustomerCode())) {
            queryWrapper.eq("customer_code", exPlanSearch.getCustomerCode());
        }
        if (!StringUtils.isBlank(exPlanSearch.getProductCode())) {
            queryWrapper.eq("product_code", exPlanSearch.getProductCode());
        }
        if (!StringUtils.isBlank(exPlanSearch.getSampleCode())) {
            queryWrapper.eq("sample_code", exPlanSearch.getSampleCode());
        }
        if (!Objects.isNull(exPlanSearch.getCameraQty())) {
            queryWrapper.eq("camera_qty", exPlanSearch.getCameraQty());
        }
        return queryWrapper;
    }


    @Override
    public boolean save(ExPlanAdd exPlanAdd) {
        ExPlan exPlan = new ExPlan();
        int cuttingWidth = exPlanAdd.getCuttingWidth();
        int cuttingHeight = exPlanAdd.getCuttingHeight();
        String cutting = cuttingWidth + "*" + cuttingHeight;
        exPlanAdd.setCuttingVersion(cutting);
        BeanUtils.copyProperties(exPlanAdd, exPlan);
        this.save(exPlan);

        List<String> cameras = Lists.newArrayList("C1", "C3", "C4", "C5");
        List<ExPlanCameraConf> confList = Lists.newArrayList();
        cameras.forEach(ca -> {
            ExPlanCameraConf conf = new ExPlanCameraConf();
            conf.setCustomerCode(exPlanAdd.getCustomerCode()).setProductCode(exPlanAdd.getProductCode()).setSampleCode(exPlanAdd.getSampleCode()).setCameraQty(exPlanAdd.getCameraQty()).setCameraCode(ca);
            confList.add(conf);
        });
        return exPlanCameraConfService.saveBatch(confList);
    }

    @Override
    public boolean update(String id, ExPlanUpdate exPlanUpdate) {
        ExPlan exPlan = this.getById(id);
        int cuttingWidth = exPlanUpdate.getCuttingWidth();
        int cuttingHeight = exPlanUpdate.getCuttingHeight();
        String cutting = cuttingWidth + "*" + cuttingHeight;
        exPlanUpdate.setCuttingVersion(cutting);
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
    public ExPlan selectOneBySearch(ExPlanSearch search) {
        QueryWrapper<ExPlan> exPlanQueryWrapper = baseQueryWrapper(search);
        exPlanQueryWrapper.orderByDesc("modified_at").last("limit 0 , 1");
        return this.getOne(exPlanQueryWrapper);
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
