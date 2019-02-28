package com.kauuze.app.domain.sso.dao;


import com.kauuze.app.domain.sso.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

/**
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
@Repository
public interface TokenDao extends JpaRepository<Token,Integer> {
    public Token findByUid(int uid);
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select e from Token e where e.id = ?1")
    public Token findByIdForUpdate(int id);
}
