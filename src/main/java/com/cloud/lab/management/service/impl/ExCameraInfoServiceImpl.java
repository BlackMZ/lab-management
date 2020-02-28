package com.cloud.lab.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.lab.management.entity.ExCameraInfo;
import com.cloud.lab.management.entity.dto.excamerainfo.ExCameraInfoAdd;
import com.cloud.lab.management.entity.dto.excamerainfo.ExCameraInfoSearch;
import com.cloud.lab.management.entity.dto.excamerainfo.ExCameraInfoUpdate;
import com.cloud.lab.management.mapper.ExCameraInfoMapper;
import com.cloud.lab.management.service.IExCameraInfoService;
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
public class ExCameraInfoServiceImpl extends ServiceImpl<ExCameraInfoMapper, ExCameraInfo> implements IExCameraInfoService {


    @Autowired
    private ExCameraInfoMapper exCameraInfoMapper;

    private static QueryWrapper<ExCameraInfo> baseQueryWrapper(ExCameraInfoSearch exCameraInfoSearch) {
        QueryWrapper<ExCameraInfo> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(exCameraInfoSearch.getCameraCode())) {
            queryWrapper.eq("camera_code", exCameraInfoSearch.getCameraCode());
        }
        return queryWrapper;
    }

    @Override
    public boolean save(ExCameraInfoAdd exCameraInfoAdd) {
        ExCameraInfo exCameraInfo = new ExCameraInfo();
        BeanUtils.copyProperties(exCameraInfoAdd, exCameraInfo);
        return this.save(exCameraInfo);
    }

    @Override
    public boolean update(String id, ExCameraInfoUpdate exCameraInfoUpdate) {
        ExCameraInfo exCameraInfo = this.getById(id);
        BeanUtils.copyProperties(exCameraInfoUpdate, exCameraInfo);
        return this.updateById(exCameraInfo);
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public ExCameraInfo selectOneById(String id) {
        return this.getById(id);
    }

    @Override
    public List<ExCameraInfo> listBySearch(ExCameraInfoSearch search) {
        QueryWrapper<ExCameraInfo> exCameraInfoQueryWrapper = baseQueryWrapper(search);
        return this.list(exCameraInfoQueryWrapper);
    }

    @Override
    public IPage<ExCameraInfo> selectPageBySearch(ExCameraInfoSearch search) {
        IPage<ExCameraInfo> page = new Page<>(search.getPageNo(), search.getPageSize());
        QueryWrapper<ExCameraInfo> queryWrapper = baseQueryWrapper(search);
        return exCameraInfoMapper.selectPage(page, queryWrapper);
    }


}
