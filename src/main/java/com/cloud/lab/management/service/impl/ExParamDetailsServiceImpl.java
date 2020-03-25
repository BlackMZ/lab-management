package com.cloud.lab.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.lab.management.constant.JavaType;
import com.cloud.lab.management.constant.ModuleType;
import com.cloud.lab.management.entity.ExParamDetails;
import com.cloud.lab.management.entity.ExParamGroup;
import com.cloud.lab.management.entity.ExPlan;
import com.cloud.lab.management.entity.dto.exparamdetails.DetailsAddAll;
import com.cloud.lab.management.entity.dto.exparamdetails.ExParamDetailsAdd;
import com.cloud.lab.management.entity.dto.exparamdetails.ExParamDetailsSearch;
import com.cloud.lab.management.entity.dto.exparamdetails.ExParamDetailsUpdate;
import com.cloud.lab.management.entity.dto.exparamdetails.ModuleAdd;
import com.cloud.lab.management.entity.dto.exparamdetails.ParamAdd;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupSearch;
import com.cloud.lab.management.mapper.ExParamDetailsMapper;
import com.cloud.lab.management.service.IExParamDetailsService;
import com.cloud.lab.management.service.IExParamGroupService;
import com.cloud.lab.management.service.IExPlanService;
import com.cloud.lab.management.util.TypeConversion;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
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
public class ExParamDetailsServiceImpl extends ServiceImpl<ExParamDetailsMapper, ExParamDetails> implements IExParamDetailsService {

    @Autowired
    private ExParamDetailsMapper exParamDetailsMapper;

    @Autowired
    private IExPlanService exPlanService;

    @Autowired
    private IExParamGroupService exParamGroupService;

    private static QueryWrapper<ExParamDetails> baseQueryWrapper(ExParamDetailsSearch exParamDetailsSearch) {
        QueryWrapper<ExParamDetails> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isBlank(exParamDetailsSearch.getCustomerCode())) {
            queryWrapper.eq("customer_code", exParamDetailsSearch.getCustomerCode());
        }
        if (!StringUtils.isBlank(exParamDetailsSearch.getProductCode())) {
            queryWrapper.eq("product_code", exParamDetailsSearch.getProductCode());
        }
        if (!StringUtils.isBlank(exParamDetailsSearch.getSampleCode())) {
            queryWrapper.eq("sample_code", exParamDetailsSearch.getSampleCode());
        }
        if (!Objects.isNull(exParamDetailsSearch.getCameraQty())) {
            queryWrapper.eq("camera_qty", exParamDetailsSearch.getCameraQty());
        }
        if (!StringUtils.isBlank(exParamDetailsSearch.getGroupCode())) {
            queryWrapper.eq("group_code", exParamDetailsSearch.getGroupCode());
        }
        return queryWrapper;
    }

    @Override
    public boolean save(ExParamDetailsAdd exParamDetailsAdd) {
        ExParamDetails exParamDetails = new ExParamDetails();
        BeanUtils.copyProperties(exParamDetailsAdd, exParamDetails);
        return this.save(exParamDetails);
    }

    private ExParamGroupSearch groupSearch(ExPlan exPlan) {
        ExParamGroupSearch search = new ExParamGroupSearch();
        search.setCustomerCode(exPlan.getCustomerCode())
                .setProductCode(exPlan.getProductCode())
                .setSampleCode(exPlan.getSampleCode())
                .setCameraQty(exPlan.getCameraQty());
        return search;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveAll(DetailsAddAll detailsAddAll) {
        ExPlan exPlan = exPlanService.selectOneById(detailsAddAll.getPlanId());
        if (Objects.isNull(exPlan)) {
            return false;
        }
        ExParamGroupSearch search = groupSearch(exPlan);
        List<ExParamGroup> exParamGroups = exParamGroupService.listBySearch(search);

        final String[] newGroupCode = {"01"};
        exParamGroups.stream().map(ExParamGroup::getGroupCode)
                .max(Comparator.naturalOrder())
                .ifPresent(code -> newGroupCode[0] = String.format("%02d", (Integer.parseInt(code) + 1)));
        ExParamGroup exParamGroup = new ExParamGroup();
        exParamGroup.setCustomerCode(exPlan.getCustomerCode())
                .setProductCode(exPlan.getProductCode())
                .setSampleCode(exPlan.getSampleCode())
                .setCameraQty(exPlan.getCameraQty())
                .setGroupCode(newGroupCode[0])
                .setGroupStatus(0);
        exParamGroupService.save(exParamGroup);

        List<ExParamDetails> detailsAll = Lists.newLinkedList();
        List<ParamAdd> cameraList = detailsAddAll.getCameraList();
        cameraList.forEach(paramAdd -> {
            String cameraNo = paramAdd.getCameraNo();
            List<ExParamDetails> base = getParameters(paramAdd.getBase(), exPlan, cameraNo, ModuleType.P_BASE, newGroupCode[0], ModuleType.CAMERA);
            List<ExParamDetails> camera = getParameters(paramAdd.getCamera(), exPlan, cameraNo, ModuleType.P_CAMERA, newGroupCode[0], ModuleType.CAMERA);
            List<ExParamDetails> axis = getParameters(paramAdd.getAxis(), exPlan, cameraNo, ModuleType.P_AXIS, newGroupCode[0], ModuleType.CAMERA);
            List<ExParamDetails> cross = getParameters(paramAdd.getCross(), exPlan, cameraNo, ModuleType.P_CROSS, newGroupCode[0], ModuleType.CAMERA);
            List<ExParamDetails> light = getParameters(paramAdd.getLight(), exPlan, cameraNo, ModuleType.P_LIGHT, newGroupCode[0], ModuleType.CAMERA);
            List<ExParamDetails> border = getParameters(paramAdd.getBorder(), exPlan, cameraNo, ModuleType.P_BORDER, newGroupCode[0], ModuleType.CAMERA);
            detailsAll.addAll(base);
            detailsAll.addAll(camera);
            detailsAll.addAll(axis);
            detailsAll.addAll(cross);
            detailsAll.addAll(light);
            detailsAll.addAll(border);
        });
        List<ModuleAdd> visualModule = detailsAddAll.getVisualModule();
        visualModule.forEach(moduleAdd -> {
            String moduleNo = moduleAdd.getModuleNo();
            List<ExParamDetails> module = getParameters(moduleAdd.getVisual(), exPlan, moduleNo, ModuleType.P_MODULE, newGroupCode[0], ModuleType.MODULE);
            detailsAll.addAll(module);
        });
        return this.saveBatch(detailsAll);
    }


    /**
     * 获取对应java类型
     *
     * @param object
     * @return
     */
    private String getJaveType(Object object) {
        if (TypeConversion.isString(object)) {
            return JavaType.STR_STRING;
        } else if (TypeConversion.isInt(object)) {
            return JavaType.STR_INT;
        } else if (TypeConversion.isDouble(object)) {
            return JavaType.STR_DOUBLE;
        } else if (TypeConversion.isBoolean(object)) {
            return JavaType.STR_BOOLEAN;
        } else {
            return JavaType.STR_STRING;
        }
    }


    /**
     * 提取参数
     *
     * @param paramMap
     * @param exPlan
     * @param paramType
     * @param newGroupCode
     * @return
     */
    private List<ExParamDetails> getParameters(Map<String, Object> paramMap,
                                               ExPlan exPlan,
                                               String cameraNo,
                                               String paramType,
                                               String newGroupCode,
                                               String moduleType) {
        List<ExParamDetails> details = Lists.newLinkedList();
        paramMap.forEach((k, v) -> {
            ExParamDetails exParamDetails = new ExParamDetails();
            exParamDetails.setCustomerCode(exPlan.getCustomerCode())
                    .setProductCode(exPlan.getProductCode())
                    .setSampleCode(exPlan.getSampleCode())
                    .setCameraQty(exPlan.getCameraQty())
                    .setGroupCode(newGroupCode)
                    .setParamName(k)
                    .setMechanismCode(cameraNo)
                    .setParamType(paramType)
                    .setParamValue(String.valueOf(v))
                    .setValueType(getJaveType(v))
                    .setModuleType(moduleType);
            details.add(exParamDetails);
        });
        return details;
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

    @Override
    public DetailsAddAll getByGroupId(String groupId) {
        DetailsAddAll detailsAddAll = new DetailsAddAll();
        List<ParamAdd> cameraList = Lists.newLinkedList();
        List<ModuleAdd> visualModule = Lists.newLinkedList();
        ExParamGroup paramGroup = exParamGroupService.getById(groupId);
        if (Objects.isNull(paramGroup)) {
            return new DetailsAddAll();
        }
        ExParamDetailsSearch search = getDetailSearch(paramGroup);
        List<ExParamDetails> exParamDetails = this.listBySearch(search);
        if (CollectionUtils.isEmpty(exParamDetails)) {
            return new DetailsAddAll();
        }
        Map<String, List<ExParamDetails>> moduleMap = exParamDetails.stream().collect(Collectors.groupingBy(ExParamDetails::getModuleType));

        //相机参数
        List<ExParamDetails> cameras = moduleMap.get(ModuleType.CAMERA);
        //模组参数
        List<ExParamDetails> module = moduleMap.get(ModuleType.MODULE);

        //按相机编号分组
        Map<String, List<ExParamDetails>> cameraMap = cameras.stream().collect(Collectors.groupingBy(ExParamDetails::getMechanismCode));
        cameraMap.forEach((mechanismCode, detailsList) -> {
            //按参数类型分组
            Map<String, List<ExParamDetails>> paramType = detailsList.stream().collect(Collectors.groupingBy(ExParamDetails::getParamType));
            ParamAdd paramAdd = new ParamAdd();
            paramType.forEach((pType, paramDetail) -> {
                Map<String, Object> map = handleResult(paramType.get(pType));
                handleParam(pType, paramAdd, map);
            });
            paramAdd.setCameraNo(mechanismCode);
            cameraList.add(paramAdd);
        });

        //处理模组数据，按照编号分组
        Map<String, List<ExParamDetails>> mMap = module.stream().collect(Collectors.groupingBy(ExParamDetails::getMechanismCode));
        mMap.forEach((moduleNo, details)->{
            ModuleAdd moduleAdd = new ModuleAdd();
            moduleAdd.setModuleNo(moduleNo);
            Map<String, Object> moduleResult = handleResult(details);
            moduleAdd.setVisual(moduleResult);
            visualModule.add(moduleAdd);
        });

        detailsAddAll.setCameraList(cameraList);
        detailsAddAll.setVisualModule(visualModule);
        return detailsAddAll;
    }

    private Map<String, Object> handleResult(List<ExParamDetails> exParamDetails) {
        if (CollectionUtils.isEmpty(exParamDetails)) {
            return Maps.newHashMap();
        }
        Map<String, Object> resultMap = Maps.newLinkedHashMap();
        exParamDetails.forEach(detail -> {
            String valueType = detail.getValueType();
            String paramName = detail.getParamName();
            String paramValue = detail.getParamValue();
            if (Objects.equals(JavaType.STR_STRING, valueType)) {
                resultMap.put(paramName, String.valueOf(paramValue));
            } else if (Objects.equals(JavaType.STR_INT, valueType)) {
                resultMap.put(paramName, Integer.valueOf(paramValue));
            } else if (Objects.equals(JavaType.STR_DOUBLE, valueType)) {
                resultMap.put(paramName, Double.valueOf(paramValue));
            } else if (Objects.equals(JavaType.STR_BOOLEAN, valueType)) {
                resultMap.put(paramName, Boolean.valueOf(paramValue));
            }
        });
        return resultMap;
    }

    private void handleParam(String paramType, ParamAdd paramAdd, Map<String, Object> resultMap) {
        if (Objects.equals(paramType, ModuleType.P_BASE)) {
            paramAdd.setBase(resultMap);
        } else if (Objects.equals(paramType, ModuleType.P_AXIS)) {
            paramAdd.setAxis(resultMap);
        } else if (Objects.equals(paramType, ModuleType.P_BORDER)) {
            paramAdd.setBorder(resultMap);
        } else if (Objects.equals(paramType, ModuleType.P_CAMERA)) {
            paramAdd.setCamera(resultMap);
        } else if (Objects.equals(paramType, ModuleType.P_CROSS)) {
            paramAdd.setCross(resultMap);
        }else if (Objects.equals(paramType, ModuleType.P_LIGHT)) {
            paramAdd.setLight(resultMap);
        }
    }

    private ExParamDetailsSearch getDetailSearch(ExParamGroup paramGroup) {
        ExParamDetailsSearch search = new ExParamDetailsSearch();
        search.setCustomerCode(paramGroup.getCustomerCode())
                .setProductCode(paramGroup.getProductCode())
                .setSampleCode(paramGroup.getSampleCode())
                .setCameraQty(paramGroup.getCameraQty())
                .setGroupCode(paramGroup.getGroupCode());
        return search;
    }
}
