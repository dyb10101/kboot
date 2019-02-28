package com.kauuze.app.service.sso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 用户数据资产信息
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UdpModel {
    /**
     * 用户充值人民币
     */
    private BigDecimal cash;

    /**
     * 人民币代金券
     */
    private BigDecimal coupon;

    /**
     * 虚拟币
     */
    private BigDecimal coin;
}
