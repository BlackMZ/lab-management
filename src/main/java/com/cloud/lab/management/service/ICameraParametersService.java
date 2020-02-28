package com.cloud.lab.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.lab.management.base.ResponseEntity;
import com.cloud.lab.management.base.ResultModel;
import com.cloud.lab.management.entity.CameraParameters;
import com.cloud.lab.management.entity.vo.CameraParametersVO;

import java.util.List;
import java.util.Map;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/11/29 16:57
 */
public interface ICameraParametersService extends IService<CameraParameters> {

    /**
     * 保存相机参数
     * @param cameraParametersVOS
     * @return
     */
    ResponseEntity<ResultModel> save(List<CameraParametersVO> cameraParametersVOS);

    /**
     * 根据机构代码查询相机参数
     * @param mechanismCode
     * @return
     */
    Map<String, String> getParameterByMechanismCode (String mechanismCode);
}
