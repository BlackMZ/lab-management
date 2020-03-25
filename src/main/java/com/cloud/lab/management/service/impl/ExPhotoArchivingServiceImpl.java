package com.cloud.lab.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.lab.management.entity.ExParamGroup;
import com.cloud.lab.management.entity.ExPhotoArchiving;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ArchivingAdd;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingAdd;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingSearch;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingUpdate;
import com.cloud.lab.management.entity.vo.PhotoVO;
import com.cloud.lab.management.entity.vo.SampleVO;
import com.cloud.lab.management.mapper.ExPhotoArchivingMapper;
import com.cloud.lab.management.service.IExParamGroupService;
import com.cloud.lab.management.service.IExPhotoArchivingService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/11/29 14:36
 */
@Service
public class ExPhotoArchivingServiceImpl extends ServiceImpl<ExPhotoArchivingMapper, ExPhotoArchiving> implements IExPhotoArchivingService {


    @Autowired
    private ExPhotoArchivingMapper exPhotoArchivingMapper;

    @Autowired
    private IExParamGroupService exParamGroupService;

    private static QueryWrapper<ExPhotoArchiving> baseQueryWrapper(ExPhotoArchivingSearch exPhotoArchivingSearch) {
        QueryWrapper<ExPhotoArchiving> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(exPhotoArchivingSearch.getCustomerCode())) {
            queryWrapper.eq("customer_code", exPhotoArchivingSearch.getCustomerCode());
        }
        if (!StringUtils.isBlank(exPhotoArchivingSearch.getProductCode())) {
            queryWrapper.eq("product_code", exPhotoArchivingSearch.getProductCode());
        }
        if (!StringUtils.isBlank(exPhotoArchivingSearch.getSampleCode())) {
            queryWrapper.eq("sample_code", exPhotoArchivingSearch.getSampleCode());
        }
        if (!Objects.isNull(exPhotoArchivingSearch.getCameraQty())) {
            queryWrapper.eq("camera_qty", exPhotoArchivingSearch.getCameraQty());
        }
        if (!StringUtils.isBlank(exPhotoArchivingSearch.getGroupCode())) {
            queryWrapper.eq("group_code", exPhotoArchivingSearch.getGroupCode());
        }
        if (!StringUtils.isBlank(exPhotoArchivingSearch.getPhotoNo())) {
            queryWrapper.eq("photo_no", exPhotoArchivingSearch.getPhotoNo());
        }
        if (!StringUtils.isBlank(exPhotoArchivingSearch.getCameraCode())) {
            queryWrapper.eq("camera_code", exPhotoArchivingSearch.getCameraCode());
        }
        if (!CollectionUtils.isEmpty(exPhotoArchivingSearch.getCameraCodeList())) {
            queryWrapper.in("camera_code", exPhotoArchivingSearch.getCameraCodeList());
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveArchive(ArchivingAdd archivingAdd) {
        ExParamGroup paramGroup = exParamGroupService.getById(archivingAdd.getGroupId());
        if (Objects.isNull(paramGroup)) {
            return false;
        }
        List<ExPhotoArchiving> archivingList = Lists.newLinkedList();
        String photoNo = archivingAdd.getPhotoNo();
        List<String> cameraCode = archivingAdd.getCameraCode();
        cameraCode.forEach(code -> {
            ExPhotoArchiving exPhotoArchiving = new ExPhotoArchiving();
            exPhotoArchiving.setCustomerCode(paramGroup.getCustomerCode())
                    .setProductCode(paramGroup.getProductCode())
                    .setSampleCode(paramGroup.getSampleCode())
                    .setCameraQty(paramGroup.getCameraQty())
                    .setGroupCode(paramGroup.getGroupCode())
                    .setPhotoNo(photoNo)
                    .setCameraCode(code)
                    .setInspectFlag("N");
            archivingList.add(exPhotoArchiving);
        });
        return this.saveBatch(archivingList);
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

    @Override
    public List<SampleVO> selectByGroupId(String groupId) {
        ExParamGroup exParamGroup = exParamGroupService.selectOneById(groupId);
        ExPhotoArchivingSearch search = new ExPhotoArchivingSearch();
        search.setCustomerCode(exParamGroup.getCustomerCode());
        search.setProductCode(exParamGroup.getProductCode());
        search.setSampleCode(exParamGroup.getSampleCode());
        search.setCameraQty(exParamGroup.getCameraQty());
        search.setGroupCode(exParamGroup.getGroupCode());
        List<ExPhotoArchiving> exPhotoArchivings = this.listBySearch(search);
        Map<String, List<ExPhotoArchiving>> photoMap = exPhotoArchivings.stream().collect(Collectors.groupingBy(ExPhotoArchiving::getPhotoNo));
        List<SampleVO> sampleVOList = Lists.newLinkedList();
        photoMap.forEach((k, v) -> {
            //客户编号-产品类型-來樣编号-相機數量-参数组编号/照片流水编号-相機編號.png
            SampleVO sampleVO = new SampleVO();
            sampleVO.setPhotoNo(v.get(0).getPhotoNo());
            sampleVO.setFlag(v.get(0).getInspectFlag());
            sampleVO.setPhotoDescription(v.get(0).getPhotoDescription());
            List<PhotoVO> photoVOS = Lists.newLinkedList();
//            List<Map<String, String>> totalList = Lists.newLinkedList();
            Map<String, String> resultMap = Maps.newHashMap();
            v.forEach(archiving -> {
                PhotoVO photoVO = new PhotoVO();

                StringBuilder sb = new StringBuilder();
                sb.append(archiving.getCustomerCode());
                sb.append("-");
                sb.append(archiving.getProductCode());
                sb.append("-");
                sb.append(archiving.getSampleCode());
                sb.append("-");
                sb.append(archiving.getCameraQty());
                sb.append("-");
                sb.append(archiving.getGroupCode());
                sb.append("/");
                sb.append(archiving.getPhotoNo());
                sb.append("-");
                sb.append(archiving.getCameraCode());
                sb.append(".png");
                photoVO.setUrl(sb.toString());
                photoVO.setType(archiving.getCameraCode());
                photoVOS.add(photoVO);
                resultMap.put(archiving.getCameraCode(), sb.toString());
//                totalList.add(resultMap);
            });
            sampleVO.setPhotos(photoVOS);
            Map<String, String> stringStringMap = sortByKey(resultMap);
            sampleVO.setPhotoMap(stringStringMap);
            sampleVOList.add(sampleVO);
        });
        List<SampleVO> collect = sampleVOList.stream().sorted(Comparator.comparing(SampleVO::getPhotoNo)).collect(Collectors.toList());
        return collect;
    }


    private <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(Map.Entry.<K, V>comparingByKey()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;

    }


    @Override
    public String getPhotoNo(ExPhotoArchivingSearch exPhotoArchivingSearch) {
        QueryWrapper<ExPhotoArchiving> archivingQueryWrapper = baseQueryWrapper(exPhotoArchivingSearch);
        archivingQueryWrapper.orderByDesc("modified_at").last("limit 0 , 1");
        ExPhotoArchiving exPhotoArchiving = this.getOne(archivingQueryWrapper);
        final String[] newPhotoNo = {"00001"};
        Optional.ofNullable(exPhotoArchiving)
                .map(ExPhotoArchiving::getPhotoNo)
                .ifPresent(photoNo -> newPhotoNo[0] = String.format("%05d", (Integer.parseInt(photoNo) + 1)));
        return newPhotoNo[0];
    }

    @Override
    public String getSingleNoByGroupId(String groupId) {
        ExParamGroup exParamGroup = exParamGroupService.selectOneById(groupId);
        ExPhotoArchivingSearch search = new ExPhotoArchivingSearch();
        search.setCustomerCode(exParamGroup.getCustomerCode())
                .setProductCode(exParamGroup.getProductCode())
                .setSampleCode(exParamGroup.getSampleCode())
                .setCameraQty(exParamGroup.getCameraQty())
                .setGroupCode(exParamGroup.getGroupCode());
        return this.getPhotoNo(search);
    }

    @Override
    public boolean retake(ExPhotoArchivingSearch exPhotoArchivingSearch) {
        List<ExPhotoArchiving> exPhotoArchivings = this.listBySearch(exPhotoArchivingSearch);
        List<String> ids = exPhotoArchivings.stream().map(ExPhotoArchiving::getId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(ids)) {
            return true;
        }
        return this.removeByIds(ids);
    }
}
