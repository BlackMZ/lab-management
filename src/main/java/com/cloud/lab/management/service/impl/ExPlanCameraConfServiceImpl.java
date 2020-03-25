package com.cloud.lab.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.lab.management.entity.ExPlan;
import com.cloud.lab.management.entity.ExPlanCameraConf;
import com.cloud.lab.management.entity.dto.explancameraconf.ExPlanCameraConfAdd;
import com.cloud.lab.management.entity.dto.explancameraconf.ExPlanCameraConfSearch;
import com.cloud.lab.management.entity.dto.explancameraconf.ExPlanCameraConfUpdate;
import com.cloud.lab.management.entity.vo.PlanAndCameraVO;
import com.cloud.lab.management.mapper.ExPlanCameraConfMapper;
import com.cloud.lab.management.service.IExPlanCameraConfService;
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
public class ExPlanCameraConfServiceImpl extends ServiceImpl<ExPlanCameraConfMapper, ExPlanCameraConf> implements IExPlanCameraConfService {


    @Autowired
    private ExPlanCameraConfMapper exPlanCameraConfMapper;

    @Autowired
    private IExPlanService exPlanService;

    private static QueryWrapper<ExPlanCameraConf> baseQueryWrapper(ExPlanCameraConfSearch exPlanCameraConfSearch) {
        QueryWrapper<ExPlanCameraConf> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(exPlanCameraConfSearch.getCustomerCode())) {
            queryWrapper.eq("customer_code", exPlanCameraConfSearch.getCustomerCode());
        }
        if (!StringUtils.isBlank(exPlanCameraConfSearch.getProductCode())) {
            queryWrapper.eq("product_code", exPlanCameraConfSearch.getProductCode());
        }
        if (!StringUtils.isBlank(exPlanCameraConfSearch.getSampleCode())) {
            queryWrapper.eq("sample_code", exPlanCameraConfSearch.getSampleCode());
        }
        if (!Objects.isNull(exPlanCameraConfSearch.getCameraQty())) {
            queryWrapper.eq("camera_qty", exPlanCameraConfSearch.getCameraQty());
        }
        return queryWrapper;
    }


    @Override
    public boolean save(ExPlanCameraConfAdd add) {
        ExPlanCameraConf exPlanCameraConf = new ExPlanCameraConf();
        BeanUtils.copyProperties(add, exPlanCameraConf);
        return this.save(exPlanCameraConf);
    }

    @Override
    public boolean update(String id, ExPlanCameraConfUpdate update) {
        ExPlanCameraConf exPlanCameraConf = this.getById(id);
        BeanUtils.copyProperties(update, exPlanCameraConf);
        return this.updateById(exPlanCameraConf);
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public ExPlanCameraConf selectOneById(String id) {
        return this.getById(id);
    }

    @Override
    public List<ExPlanCameraConf> listBySearch(ExPlanCameraConfSearch search) {
        QueryWrapper<ExPlanCameraConf> exPlanCameraConfQueryWrapper = baseQueryWrapper(search);
        return this.list(exPlanCameraConfQueryWrapper);
    }

    @Override
    public IPage<ExPlanCameraConf> selectPageBySearch(ExPlanCameraConfSearch search) {
        IPage<ExPlanCameraConf> page = new Page<>(search.getPageNo(), search.getPageSize());
        QueryWrapper<ExPlanCameraConf> queryWrapper = baseQueryWrapper(search);
        return exPlanCameraConfMapper.selectPage(page, queryWrapper);
    }

    @Override
    public PlanAndCameraVO selectByPlanId(String planId) {
        PlanAndCameraVO planAndCameraVO = new PlanAndCameraVO();
        ExPlan exPlan = exPlanService.selectOneById(planId);
        ExPlanCameraConfSearch search = new ExPlanCameraConfSearch();
        search.setCustomerCode(exPlan.getCustomerCode());
        search.setProductCode(exPlan.getProductCode());
        search.setSampleCode(exPlan.getSampleCode());
        search.setCameraQty(exPlan.getCameraQty());
        List<ExPlanCameraConf> exPlanCameraConfs = this.listBySearch(search);
        List<ExPlanCameraConf> confWithOrder = exPlanCameraConfs.stream()
                .sorted(Comparator.comparing(ExPlanCameraConf::getCameraCode)).collect(Collectors.toList());
        planAndCameraVO.setExPlan(exPlan);
        planAndCameraVO.setConfList(confWithOrder);
        return planAndCameraVO;
    }
}
