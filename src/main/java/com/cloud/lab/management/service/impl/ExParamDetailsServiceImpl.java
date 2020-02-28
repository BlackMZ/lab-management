package com.cloud.lab.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.lab.management.entity.ExParamDetails;
import com.cloud.lab.management.entity.dto.exparamdetails.ExParamDetailsAdd;
import com.cloud.lab.management.entity.dto.exparamdetails.ExParamDetailsSearch;
import com.cloud.lab.management.entity.dto.exparamdetails.ExParamDetailsUpdate;
import com.cloud.lab.management.mapper.ExParamDetailsMapper;
import com.cloud.lab.management.service.IExParamDetailsService;
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
public class ExParamDetailsServiceImpl extends ServiceImpl<ExParamDetailsMapper, ExParamDetails> implements IExParamDetailsService {

    @Autowired
    private ExParamDetailsMapper exParamDetailsMapper;

    private static QueryWrapper<ExParamDetails> baseQueryWrapper(ExParamDetailsSearch exParamDetailsSearch) {
        QueryWrapper<ExParamDetails> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(exParamDetailsSearch.getCustomerCode())) {
            queryWrapper.eq("customer_code", exParamDetailsSearch.getCustomerCode());
        }
        if (!StringUtils.isBlank(exParamDetailsSearch.getProductCode())) {
            queryWrapper.eq("product_code", exParamDetailsSearch.getProductCode());
        }
        if (!StringUtils.isBlank(exParamDetailsSearch.getSampleCode())) {
            queryWrapper.eq("sample_cde", exParamDetailsSearch.getSampleCode());
        }
        if (!Objects.isNull(exParamDetailsSearch.getCameraQty())) {
            queryWrapper.eq("camera_qty", exParamDetailsSearch.getCameraQty());
        }
        if (!StringUtils.isBlank(exParamDetailsSearch.getGroupCode())) {
            queryWrapper.eq("group_cde", exParamDetailsSearch.getGroupCode());
        }
        return queryWrapper;
    }

    @Override
    public boolean save(ExParamDetailsAdd exParamDetailsAdd) {
        ExParamDetails exParamDetails = new ExParamDetails();
        BeanUtils.copyProperties(exParamDetailsAdd, exParamDetails);
        return this.save(exParamDetails);
    }

    @Override
    public boolean saveList(List<ExParamDetailsAdd> exParamDetailsAdds) {
        List<ExParamDetails> details = Lists.newLinkedList();
        exParamDetailsAdds.forEach(add -> {
            ExParamDetails exParamDetails = new ExParamDetails();
            BeanUtils.copyProperties(add, exParamDetails);
            details.add(exParamDetails);
        });
        return this.saveBatch(details);
    }

    @Override
    public boolean update(String id, ExParamDetailsUpdate exParamDetailsUpdate) {
        ExParamDetails exParamDetails = this.getById(id);
        BeanUtils.copyProperties(exParamDetailsUpdate, exParamDetails);
        return this.updateById(exParamDetails);
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public ExParamDetails selectOneById(String id) {
        return this.getById(id);
    }

    @Override
    public List<ExParamDetails> listBySearch(ExParamDetailsSearch exParamDetailsSearch) {
        QueryWrapper<ExParamDetails> queryWrapper = baseQueryWrapper(exParamDetailsSearch);
        return this.list(queryWrapper);
    }

    @Override
    public IPage<ExParamDetails> selectPageBySearch(ExParamDetailsSearch exParamDetailsSearch) {
        IPage<ExParamDetails> page = new Page<>(exParamDetailsSearch.getPageNo(), exParamDetailsSearch.getPageSize());
        QueryWrapper<ExParamDetails> queryWrapper = baseQueryWrapper(exParamDetailsSearch);
        return exParamDetailsMapper.selectPage(page, queryWrapper);
    }
}
