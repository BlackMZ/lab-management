package com.cloud.lab.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.lab.management.entity.ExPlanCameraConf;
import com.cloud.lab.management.entity.dto.explancameraconf.ExPlanCameraConfAdd;
import com.cloud.lab.management.entity.dto.explancameraconf.ExPlanCameraConfSearch;
import com.cloud.lab.management.entity.dto.explancameraconf.ExPlanCameraConfUpdate;

import java.util.List;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/11/29 16:57
 */
public interface IExPlanCameraConfService extends IService<ExPlanCameraConf> {

    /**
     * 新增
     * @param add
     * @return
     */
    boolean save(ExPlanCameraConfAdd add);

    /**
     * 更新
     * @param id
     * @param update
     * @return
     */
    boolean update(String id, ExPlanCameraConfUpdate update);


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
    ExPlanCameraConf selectOneById(String id);

    /**
     * 查询
     * @param search
     * @return
     */
    List<ExPlanCameraConf> listBySearch(ExPlanCameraConfSearch search);

    /**
     * 分页查询
     * @param search
     * @return
     */
    IPage<ExPlanCameraConf> selectPageBySearch(ExPlanCameraConfSearch search);
}
