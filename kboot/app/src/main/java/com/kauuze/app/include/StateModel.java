package com.kauuze.app.include;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用于方法返回 验证参数对应内容 的类型
 * @author kauuze
 * @createTime 2019.01.20 20:40
 * @IDE IntelliJ IDEA 2017.2.5
 **/
@Data
@Accessors(chain = true)
public class StateModel {
    /**
     * 状态码
     */
    private int state;
    /**
     * 内容
     */
    private Object data;
}
