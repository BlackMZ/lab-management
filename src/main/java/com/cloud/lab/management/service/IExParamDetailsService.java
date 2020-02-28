package com.cloud.lab.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.lab.management.entity.ExParamDetails;
import com.cloud.lab.management.entity.dto.exparamdetails.ExParamDetailsAdd;
import com.cloud.lab.management.entity.dto.exparamdetails.ExParamDetailsSearch;
import com.cloud.lab.management.entity.dto.exparamdetails.ExParamDetailsUpdate;

import java.util.List;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/11/29 16:57
 */
public interface IExParamDetailsService extends IService<ExParamDetails> {

    /**
     * 新增
     * @param exParamDetailsAdd
     * @return
     */
    boolean save(ExParamDetailsAdd exParamDetailsAdd);

    /**
     * 新增
     * @param exParamDetailsAdds
     * @return
     */
    boolean saveList(List<ExParamDetailsAdd> exParamDetailsAdds);

    /**
     * 更新
     * @param id
     * @param exParamDetailsUpdate
     * @return
     */
    boolean update(String id, ExParamDetailsUpdate exParamDetailsUpdate);


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
    ExParamDetails selectOneById(String id);

    /**
     * 查询
     * @param exParamDetailsSearch
     * @return
     */
    List<ExParamDetails> listBySearch(ExParamDetailsSearch exParamDetailsSearch);

    /**
     * 分页查询
     * @param exParamDetailsSearch
     * @return
     */
    IPage<ExParamDetails> selectPageBySearch(ExParamDetailsSearch exParamDetailsSearch);
}
