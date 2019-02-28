package com.kauuze.app.config.contain;

import com.kauuze.app.include.JsonResult;
import com.kauuze.app.include.annotation.permission.GreenWay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 通用api的配置
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-27 13:27
 */
@RestController
public class CommonController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 查看服务器状态,如果超30秒没有响应则服务器可能崩溃
     * @return
     */
    @RequestMapping("/ping")
    @GreenWay
    public JsonResult ping(){
        try {
            namedParameterJdbcTemplate.queryForMap("select 1",new HashMap<>());
        } catch (DataAccessException e) {
            //数据库可能崩溃
            return JsonResult.failure("database no response");
        }
        //一切正常
        return JsonResult.success(null,"all going well");
    }
}
