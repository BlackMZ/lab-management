package com.cloud.lab.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.lab.management.entity.ExCuttingMapping;
import com.cloud.lab.management.entity.dto.excuttingmapping.ExCuttingMappingAdd;
import com.cloud.lab.management.entity.dto.excuttingmapping.ExCuttingMappingSearch;
import com.cloud.lab.management.mapper.ExCuttingMappingMapper;
import com.cloud.lab.management.service.IExCuttingMappingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/11/29 14:36
 */
@Service
public class ExCuttingMappingServiceImpl extends ServiceImpl<ExCuttingMappingMapper, ExCuttingMapping> implements IExCuttingMappingService {


    @Autowired
    private ExCuttingMappingMapper exCuttingMappingMapper;

    private static QueryWrapper<ExCuttingMapping> baseQueryWrapper(ExCuttingMappingSearch exCuttingMappingSearch) {
        QueryWrapper<ExCuttingMapping> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(exCuttingMappingSearch.getProductCode())) {
            queryWrapper.eq("product_code", exCuttingMappingSearch.getProductCode());
        }
        if (!StringUtils.isBlank(exCuttingMappingSearch.getCuttingVersion())) {
            queryWrapper.eq("cutting_version", exCuttingMappingSearch.getCuttingVersion());
        }
        if (!StringUtils.isBlank(exCuttingMappingSearch.getCameraCode())) {
            queryWrapper.eq("camera_code", exCuttingMappingSearch.getCameraCode());
        }
        if (!StringUtils.isBlank(exCuttingMappingSearch.getLocation())) {
            queryWrapper.eq("location", exCuttingMappingSearch.getLocation());
        }
        return queryWrapper;
    }

    @Override
    public boolean save(ExCuttingMappingAdd exCuttingMappingAdd) {
        ExCuttingMapping exCuttingMapping = new ExCuttingMapping();
        BeanUtils.copyProperties(exCuttingMappingAdd, exCuttingMapping);
        return this.save(exCuttingMapping);
    }

    @Override
    public boolean saveImport(List<ExCuttingMapping> exCuttingMappings) {
        return this.saveBatch(exCuttingMappings);
    }

    @Override
    public List<ExCuttingMapping> listBySearch(ExCuttingMappingSearch exCuttingMappingSearch) {
        QueryWrapper<ExCuttingMapping> exCuttingMappingQueryWrapper = baseQueryWrapper(exCuttingMappingSearch);
        return this.list(exCuttingMappingQueryWrapper);
    }
}
