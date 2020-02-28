package com.cloud.lab.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.lab.management.base.ResponseEntity;
import com.cloud.lab.management.base.ResultModel;
import com.cloud.lab.management.entity.CameraParameters;
import com.cloud.lab.management.entity.vo.CameraParametersVO;
import com.cloud.lab.management.mapper.CameraParametersMapper;
import com.cloud.lab.management.service.ICameraParametersService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/11/29 14:36
 */
@Service
public class CameraParametersServiceImpl extends ServiceImpl<CameraParametersMapper, CameraParameters> implements ICameraParametersService {

    @Autowired
    private CameraParametersMapper cameraParametersMapper;

    @Override
    @Transactional
    public ResponseEntity<ResultModel> save(List<CameraParametersVO> cameraParametersVOS) {
        List<CameraParameters> cameraParameters = Lists.newArrayList();
        cameraParametersVOS.forEach(vo -> {
            CameraParameters cameraPara = new CameraParameters();
            BeanUtils.copyProperties(vo, cameraPara);
            cameraParameters.add(cameraPara);
        });
        System.out.println();
        boolean result = this.saveBatch(cameraParameters);
        if (result) {
            return new ResponseEntity<>(ResultModel.ok());
        }
        return new ResponseEntity<>(ResultModel.failure());
    }


    @Override
    public Map<String, String> getParameterByMechanismCode(String mechanismCode) {
        QueryWrapper<CameraParameters> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CameraParameters::getMechanismCode,mechanismCode);
        List<CameraParameters> cameraParameters = this.list(queryWrapper);
        List<CameraParameters> cameraParameters1 = cameraParametersMapper.selectList(queryWrapper);

        return cameraParameters.stream()
                .collect(Collectors.toMap(CameraParameters::getParamCode, CameraParameters::getParamCode));
    }

    private CameraParameters convert(CameraParametersVO vo) {
        CameraParameters cameraParm = new CameraParameters();
        cameraParm.setParamType(vo.getParamType());
        cameraParm.setParamCode(vo.getParamCode());
        cameraParm.setMechanismCode(vo.getMechanismCode());
        cameraParm.setParamName(vo.getParamName());
        cameraParm.setParamValue(vo.getParamValue());
        cameraParm.setIsControl(vo.getIsControl());
        cameraParm.setIsEdit(vo.getIsEdit());
        cameraParm.setIsRange(vo.getIsRange());
        cameraParm.setParamMinValue(vo.getParamMinValue());
        cameraParm.setParamMaxValue(vo.getParamMaxValue());
        return cameraParm;
    }
}
