package com.cloud.lab.management.controller;

import com.cloud.lab.management.base.ResponseEntity;
import com.cloud.lab.management.base.ResultModel;
import com.cloud.lab.management.entity.vo.CameraParametersVO;
import com.cloud.lab.management.service.ICameraParametersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: John.ma
 * @Description: 图片处理
 * @Date: 2019/11/14 17:20
 */
@Slf4j
@Api(tags = "图片处理")
@RestController
@RequestMapping("/picture")
public class CameraParametersController {

    @Autowired
    private ICameraParametersService cameraParametersService;


    @ApiOperation(value = "保存相机参数", notes = "保存相机参数")
    @PostMapping(value = "/save/parameters")
    public ResponseEntity<ResultModel> saveParameters(@RequestBody List<CameraParametersVO> voList) {

        return cameraParametersService.save(voList);
    }

    @ApiOperation(value = "根据机构代码查询参数", notes = "根据机构代码查询参数")
    @GetMapping(value = "/get/{mechanismCode}")
    public ResponseEntity<ResultModel> getByMechanismCode(@PathVariable("mechanismCode") String mechanismCode) {
        Map<String, String> parampeterMap = cameraParametersService.getParameterByMechanismCode(mechanismCode);
        return new ResponseEntity<>(ResultModel.ok(parampeterMap));
    }
}
