package com.cloud.lab.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.lab.management.entity.ExPlan;
import com.cloud.lab.management.entity.dto.explan.ExPlanAdd;
import com.cloud.lab.management.entity.dto.explan.ExPlanSearch;
import com.cloud.lab.management.entity.dto.explan.ExPlanUpdate;

import java.util.List;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/11/29 16:57
 */
public interface IExPlanService extends IService<ExPlan> {

    /**
     * 新增实验计划
     * @param exPlanAdd
     * @return
     */
    boolean save(ExPlanAdd exPlanAdd);

    /**
     * 更新
     * @param id
     * @param exPlanUpdate
     * @return
     */
    boolean update(String id, ExPlanUpdate exPlanUpdate);


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
    ExPlan selectOneById(String id);

    /**
     * 查询
     * @param exPlanSearch
     * @return
     */
    List<ExPlan> listBySearch(ExPlanSearch exPlanSearch);

    /**
     * 分页查询
     * @param exPlanSearch
     * @return
     */
    IPage<ExPlan> selectPageBySearch(ExPlanSearch exPlanSearch);
}
