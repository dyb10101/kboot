package com.kauuze.app.service.sso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户详细信息
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserDetailModel {
    private int uid;
    private String role;
    private String nickName;
    private String sex;
    private String portrait;
    private String province;
    private Integer birthday;
    private Integer experience;
    private String personalSign;
}
