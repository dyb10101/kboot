package com.kauuze.app.api.inje.sso;

import com.kauuze.app.include.annotation.valid.DateStr;
import com.kauuze.app.include.annotation.valid.StringMax;
import com.kauuze.app.include.annotation.valid.TrueName;
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
public class AlertUserDataInje {
    @DateStr
    private Integer birthday;
    @TrueName
    private String trueName;
    @StringMax
    private String address;
}
