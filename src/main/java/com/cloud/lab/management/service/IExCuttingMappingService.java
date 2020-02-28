package com.cloud.lab.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.lab.management.entity.ExCuttingMapping;
import com.cloud.lab.management.entity.dto.excuttingmapping.ExCuttingMappingAdd;
import com.cloud.lab.management.entity.dto.excuttingmapping.ExCuttingMappingSearch;

import java.util.List;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/11/29 16:57
 */
public interface IExCuttingMappingService extends IService<ExCuttingMapping> {

    /**
     * 新增
     * @param exCuttingMappingAdd
     * @return
     */
    boolean save(ExCuttingMappingAdd exCuttingMappingAdd);

    /**
     * 批量新增
     * @param exCuttingMappings
     * @return
     */
    boolean saveImport(List<ExCuttingMapping> exCuttingMappings);

    /**
     * 查询
     * @param exCuttingMappingSearch
     * @return
     */
    List<ExCuttingMapping> listBySearch(ExCuttingMappingSearch exCuttingMappingSearch);
}
