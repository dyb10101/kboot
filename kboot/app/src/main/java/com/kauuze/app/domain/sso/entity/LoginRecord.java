package com.kauuze.app.domain.sso.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 登录记录
 * @author kauuze
 * @time 2019-02-18 18:14
 * @description
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "index_loginRecord_uid",  columnList="uid"),
})
public class LoginRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int uid;

    /**
     *登录时间
     */
    private Long loginTime;
    /**
     * 登录省份
     */
    private String province;
    /**
     * 登录城市
     */
    private String city;
    /**
     * 登录ip
     */
    private String ip;
    /**
     * 手机型号或pc
     */
    private String deviceName;
}
