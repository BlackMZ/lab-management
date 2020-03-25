package com.cloud.lab.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.lab.management.entity.ExDefectRecord;
import com.cloud.lab.management.entity.ExParamGroup;
import com.cloud.lab.management.entity.ExPhotoArchiving;
import com.cloud.lab.management.entity.ExPlan;
import com.cloud.lab.management.entity.dto.exdefectrecord.DefectExport;
import com.cloud.lab.management.entity.dto.exdefectrecord.DefectParam;
import com.cloud.lab.management.entity.dto.exdefectrecord.DefectRecordSaveAll;
import com.cloud.lab.management.entity.dto.exdefectrecord.ExDefectRecordAdd;
import com.cloud.lab.management.entity.dto.exdefectrecord.ExDefectRecordSearch;
import com.cloud.lab.management.entity.dto.exdefectrecord.ExDefectRecordUpdate;
import com.cloud.lab.management.entity.dto.exphotoarchiving.ExPhotoArchivingSearch;
import com.cloud.lab.management.entity.dto.explan.ExPlanSearch;
import com.cloud.lab.management.mapper.ExDefectRecordMapper;
import com.cloud.lab.management.service.IExDefectRecordService;
import com.cloud.lab.management.service.IExParamGroupService;
import com.cloud.lab.management.service.IExPhotoArchivingService;
import com.cloud.lab.management.service.IExPlanService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/11/29 14:36
 */
@Service
public class ExDefectRecordServiceImpl extends ServiceImpl<ExDefectRecordMapper, ExDefectRecord> implements IExDefectRecordService {

    @Autowired
    private ExDefectRecordMapper exDefectRecordMapper;

    @Autowired
    private IExParamGroupService exParamGroupService;

    @Autowired
    private IExPlanService exPlanService;

    @Autowired
    private IExPhotoArchivingService exPhotoArchivingService;

    private static QueryWrapper<ExDefectRecord> baseQueryWrapper(ExDefectRecordSearch exDefectRecordSearch) {
        QueryWrapper<ExDefectRecord> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(exDefectRecordSearch.getCustomerCode())) {
            queryWrapper.eq("customer_code", exDefectRecordSearch.getCustomerCode());
        }
        if (!StringUtils.isBlank(exDefectRecordSearch.getProductCode())) {
            queryWrapper.eq("product_code", exDefectRecordSearch.getProductCode());
        }
        if (!StringUtils.isBlank(exDefectRecordSearch.getSampleCode())) {
            queryWrapper.eq("sample_code", exDefectRecordSearch.getSampleCode());
        }
        if (!Objects.isNull(exDefectRecordSearch.getCameraQty())) {
            queryWrapper.eq("camera_qty", exDefectRecordSearch.getCameraQty());
        }
        if (!StringUtils.isBlank(exDefectRecordSearch.getPhotoNo())) {
            queryWrapper.eq("photo_no", exDefectRecordSearch.getPhotoNo());
        }
        if (!StringUtils.isBlank(exDefectRecordSearch.getGroupCode())) {
            queryWrapper.eq("group_code", exDefectRecordSearch.getGroupCode());
        }
        if (!StringUtils.isBlank(exDefectRecordSearch.getCuttingVersion())) {
            queryWrapper.eq("cutting_version", exDefectRecordSearch.getCuttingVersion());
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
    public List<ExDefectRecord> listByPlanIdAndPhotoNo(String planId, String groupId, String photoNo) {
        ExPlan exPlan = exPlanService.selectOneById(planId);
        ExParamGroup exParamGroup = exParamGroupService.selectOneById(groupId);
        if (Objects.isNull(exParamGroup) || Objects.isNull(exPlan)) {
            return Lists.newArrayList();
        }
        ExDefectRecordSearch search = new ExDefectRecordSearch();
        search.setCustomerCode(exPlan.getCustomerCode());
        search.setProductCode(exPlan.getProductCode());
        search.setSampleCode(exPlan.getSampleCode());
        search.setCameraQty(exPlan.getCameraQty());
        search.setGroupCode(exParamGroup.getGroupCode());
        search.setPhotoNo(photoNo);
        List<ExDefectRecord> exDefectRecords = this.listBySearch(search);
        return exDefectRecords;
    }

    @Override
    public IPage<ExDefectRecord> selectPageBySearch(ExDefectRecordSearch exDefectRecordSearch) {
        IPage<ExDefectRecord> page = new Page<>(exDefectRecordSearch.getPageNo(), exDefectRecordSearch.getPageSize());
        QueryWrapper<ExDefectRecord> queryWrapper = baseQueryWrapper(exDefectRecordSearch);
        return exDefectRecordMapper.selectPage(page, queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveRecordAndUpdateFlag(DefectRecordSaveAll recordSaveAll) {
        Map<String, Object> deleteMap = Maps.newHashMap();
        deleteMap.put("photo_no", recordSaveAll.getPhotoNo());
        this.removeByMap(deleteMap);
        ExParamGroup exParamGroup = exParamGroupService.selectOneById(recordSaveAll.getGroupId());
        if (Objects.isNull(exParamGroup)) {
            return false;
        }
        ExPlanSearch search = new ExPlanSearch();
        search.setCustomerCode(exParamGroup.getCustomerCode());
        search.setProductCode(exParamGroup.getProductCode());
        search.setSampleCode(exParamGroup.getSampleCode());
        search.setCameraQty(exParamGroup.getCameraQty());
        ExPlan exPlan = exPlanService.selectOneBySearch(search);
        if (Objects.isNull(exPlan)) {
            return false;
        }
        List<DefectParam> defectList = recordSaveAll.getDefectList();
        List<ExDefectRecord> saveList = Lists.newLinkedList();
        defectList.forEach(param -> {
            List<String> location = param.getLocation();
            location.forEach(loc -> {
                ExDefectRecord record = new ExDefectRecord();
                record.setCustomerCode(exParamGroup.getCustomerCode());
                record.setProductCode(exParamGroup.getProductCode());
                record.setSampleCode(exParamGroup.getSampleCode());
                record.setCameraQty(exParamGroup.getCameraQty());
                record.setGroupCode(exParamGroup.getGroupCode());
                record.setPhotoNo(recordSaveAll.getPhotoNo());
                record.setCuttingVersion(exPlan.getCuttingVersion());
                record.setCameraCode(param.getCameraNo());
                record.setDefectLocation(loc);
                saveList.add(record);
            });
        });
        boolean result = this.saveBatch(saveList);
        if (result) {
            List<String> cameraCodes = defectList.stream().map(DefectParam::getCameraNo).collect(Collectors.toList());
            ExPhotoArchivingSearch archivingSearch = new ExPhotoArchivingSearch();
            archivingSearch.setCustomerCode(exParamGroup.getCustomerCode());
            archivingSearch.setProductCode(exParamGroup.getProductCode());
            archivingSearch.setSampleCode(exParamGroup.getSampleCode());
            archivingSearch.setCameraQty(exParamGroup.getCameraQty());
            archivingSearch.setGroupCode(exParamGroup.getGroupCode());
            archivingSearch.setPhotoNo(recordSaveAll.getPhotoNo());
            archivingSearch.setCameraCodeList(cameraCodes);
            List<ExPhotoArchiving> exPhotoArchivings = exPhotoArchivingService.listBySearch(archivingSearch);
            List<ExPhotoArchiving> updateList = exPhotoArchivings.stream().map(p -> p.setInspectFlag("Y")).collect(Collectors.toList());
            boolean updateResult = exPhotoArchivingService.updateBatchById(updateList);
            return updateResult;
        }

        return false;
    }


    @Override
    public List<DefectExport> exportDefect(ExDefectRecordSearch exDefectRecordSearch) {
        QueryWrapper<ExDefectRecord> recordQueryWrapper = baseQueryWrapper(exDefectRecordSearch);
        recordQueryWrapper.orderByAsc("photo_no");
        recordQueryWrapper.orderByAsc("camera_code");
        recordQueryWrapper.orderByAsc("defect_location");
        List<ExDefectRecord> exDefectRecords = this.list(recordQueryWrapper);
        List<DefectExport> exports = Lists.newArrayList();
        exDefectRecords.forEach(record -> {
            DefectExport defectExport = new DefectExport();
            StringBuilder sb = new StringBuilder();
            sb.append(record.getPhotoNo());
            sb.append("-");
            sb.append(record.getCameraCode());
            sb.append(".png");
            defectExport.setPhotoName(sb.toString());
            defectExport.setCamera(record.getCameraCode());
            defectExport.setCoordinate(record.getDefectLocation());
            exports.add(defectExport);
        });
        return exports;
    }
}
