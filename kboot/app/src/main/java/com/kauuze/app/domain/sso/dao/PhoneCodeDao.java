package com.kauuze.app.domain.sso.dao;


import com.kauuze.app.domain.sso.entity.PhoneCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
@Repository
public interface PhoneCodeDao extends JpaRepository<PhoneCode,Integer> {
    public PhoneCode findByPhone(String phone);

    @Query(value = "update PhoneCode set failCount = failCount+1 where id = ?1")
    @Modifying
    public int incrFailCount(int id);
}
