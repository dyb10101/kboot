package com.kauuze.app.domain.sso.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 用户账号信息
 * @author kauuze
 * @createTime 2019.01.18 20:14
 * @IDE IntelliJ IDEA 2017.2.5
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "index_user_phone",columnList = "phone",unique = true),
        @Index(name = "index_user_nickName",  columnList="nickName",unique = true)
})
public class User {
    /**
     * 用户uid 6-10位
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 唯一昵称
     */
    private String nickName;

    /**
     * 经验等级
     */
    private int experience;

    /**
     * 绑定手机号,也是登录名
     */
    private String phone;

    /**
     * 密码:SHA256(明文 + 密码盐)
     */
    private String password;

    /**
     * 密码盐128位
     */
    private String passwordSalt;

    /**
     * 密码登陆失败连续超过10次需要修改密码
     */
    private int failCount;
}
