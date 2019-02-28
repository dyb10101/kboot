package com.kauuze.app.test;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class TestService {
    public void test(){
        System.out.println("test");
    }
}
