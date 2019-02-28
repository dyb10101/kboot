package com.kauuze.app.domain.sso.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 用户资产信息
 * @author kauuze
 * @createTime 2019.01.20 17:05
 * @IDE IntelliJ IDEA 2017.2.5
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "index_udp_uid",  columnList="uid",unique = true)
})
public class Udp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int uid;

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
