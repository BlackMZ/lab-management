package com.cloud.lab.management.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2019/12/19 14:11
 */
@Setter
@Getter
@Accessors(chain = true)
@ToString
public class PicResult implements Serializable {
    /**
     * 是否成功
     */
    private boolean IsSuccess;

    /**
     * 消息
     */
    private String Message;
}
