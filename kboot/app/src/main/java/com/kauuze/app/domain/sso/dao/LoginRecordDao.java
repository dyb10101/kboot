package com.kauuze.app.domain.sso.dao;

import com.kauuze.app.domain.sso.entity.LoginRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
@Repository
public interface LoginRecordDao extends JpaRepository<LoginRecord,Integer> {
    public int countByUid(int uid);
    public List<LoginRecord> findByUid(int uid);

    @Query(value = "select min(e.id) from LoginRecord e where e.uid=?1")
    public int getSmallIdByUid(int uid);
}
