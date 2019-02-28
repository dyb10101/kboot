package com.kauuze.app.api.inje.sso;

import com.kauuze.app.include.annotation.valid.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginInje {
    @Phone
    private String phone;
    @Password(require = false)
    private String password;
    @MsCode(require = false)
    private Integer msCode;
    @StrInfo(require = false,max = 50)
    private String province;
    @StrInfo(require = false,max = 50)
    private String city;
    @Ip
    private String ip;
    @StrInfo(require = false,max = 50)
    private String deviceName;
}
