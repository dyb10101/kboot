package com.kauuze.app.domain.sso.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 用户基本信息
 * @author kauuze
 * @createTime 2019.01.18 20:19
 * @IDE IntelliJ IDEA 2017.2.5
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "index_userInfo_uid",  columnList="uid",unique = true),
})
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int uid;

    /**
     * 性别
     */
    private String sex;
    /**
     * http url地址
     */
    private String portrait;
    /**
     * 省份
     */
    private String province;
    /**
     * 生日如19951210
     */
    private Integer birthday;
    /**
     *个性签名
     */
    private String personalSign;

    /**
     * 默认地址--非公开
     */
    private String address;
    /**
     * 真实姓名--非公开
     */
    private String trueName;
}
