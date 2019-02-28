package com.kauuze.app.service.sso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户私人信息
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserPrivateModel {
    private int uid;
    private String authorization;
    private String phone;

    private String nickName;
    private String sex;
    private String portrait;
    private Integer experience;

    private String role;
    private Long roleEndTime;
    private String state;
    private Long stateEndTime;
}
