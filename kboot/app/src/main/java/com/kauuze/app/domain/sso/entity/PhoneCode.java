package com.kauuze.app.domain.sso.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 手机验证码
 * @author kauuze
 * @createTime 2019.01.18 20:15
 * @IDE IntelliJ IDEA 2017.2.5
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "index_phoneCode_phone",  columnList="phone",unique = true),
})
public class PhoneCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 手机号
     */
    private String phone;
    /**
     * 短信验证码
     */
    private int msCode;
    /**
     * 5分钟过期
     */
    private long overdueTime;
    /**
     * 验证失败大于3次需重发
     */
    private int failCount;
}
