package com.kauuze.app.domain.sso.entity;


import com.kauuze.app.include.BoxUtil;
import com.kauuze.app.include.Rand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 用户权限信息
 * @author kauuze
 * @createTime 2019.01.18 15:23
 * @IDE IntelliJ IDEA 2017.2.5
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "index_token_uid",  columnList="uid",unique = true)
})
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int uid;

    /**
     * 鉴权签名 uid + 32-128位随机,每次登陆后都会变化，防止重复登录
     */
    private String authorization;
    /**
     * 角色 user--普通用户 vip--vip用户 manager--后台
     */
    private String role;
    /**
     * 角色最后期限，完了变成user
     */
    private Long roleEndTime;

    /**
     * 状态 normal--正常状态 shutup--禁言状态 ban--禁用状态
     */
    private String state;
    /**
     * 状态最后期限，完了变成normal
     */
    private Long stateEndTime;
    /**
     * 状态改变的原因
     */
    private String stateCause;


    /**
     * 生成32-128位随机token
     * @return
     */
    public static String generateAuthorization(int uid){
        return uid + "_" + Rand.getUUID() + Rand.getString(Rand.getRand(32));
    }


    /**
     * 从Authorization中获取uid
     * @param authorization
     * @return
     */
    public static String cutAuthUid(String authorization) {
        return BoxUtil.splitUnderline(authorization,2)[0];
    }
}
