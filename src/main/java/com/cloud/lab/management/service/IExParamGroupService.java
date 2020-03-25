package com.cloud.lab.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.lab.management.entity.ExParamGroup;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupAdd;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupSearch;
import com.cloud.lab.management.entity.dto.exparamgroup.ExParamGroupUpdate;

import java.util.List;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/11/29 16:57
 */
public interface IExParamGroupService extends IService<ExParamGroup> {

    /**
     * 新增
     * @param exParamGroupAdd
     * @return
     */
    boolean save(ExParamGroupAdd exParamGroupAdd);

    /**
     * 更新
     * @param id
     * @param exParamGroupUpdate
     * @return
     */
    boolean update(String id, ExParamGroupUpdate exParamGroupUpdate);


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
    ExParamGroup selectOneById(String id);

    /**
     * 查询
     * @param exParamGroupSearch
     * @return
     */
    List<ExParamGroup> listBySearch(ExParamGroupSearch exParamGroupSearch);

    /**
     * 分页查询
     * @param exParamGroupSearch
     * @return
     */
    IPage<ExParamGroup> selectPageBySearch(ExParamGroupSearch exParamGroupSearch);

    /**
     * 根据id查询
     * @param planId
     * @param groupCode
     * @return
     */
    List<ExParamGroup> selectByPlanIdAndGroupCode(String planId, String groupCode);

    /**
     * 根据planid查询, 返回默认值
     * @param planId
     * @return
     */
    List<ExParamGroup> getByPlanIdWithDefault(String planId);
}
