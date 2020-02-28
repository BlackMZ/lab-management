package com.cloud.lab.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.lab.management.entity.ExCameraInfo;
import com.cloud.lab.management.entity.dto.excamerainfo.ExCameraInfoAdd;
import com.cloud.lab.management.entity.dto.excamerainfo.ExCameraInfoSearch;
import com.cloud.lab.management.entity.dto.excamerainfo.ExCameraInfoUpdate;

import java.util.List;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/11/29 16:57
 */
public interface IExCameraInfoService extends IService<ExCameraInfo> {

    /**
     * 新增
     * @param exCameraInfoAdd
     * @return
     */
    boolean save(ExCameraInfoAdd exCameraInfoAdd);

    /**
     * 更新
     * @param id
     * @param exCameraInfoUpdate
     * @return
     */
    boolean update(String id, ExCameraInfoUpdate exCameraInfoUpdate);


    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    ExCameraInfo selectOneById(String id);

    /**
     * 查询
     * @param search
     * @return
     */
    List<ExCameraInfo> listBySearch(ExCameraInfoSearch search);

    /**
     * 分页查询
     * @param search
     * @return
     */
    IPage<ExCameraInfo> selectPageBySearch(ExCameraInfoSearch search);
}
