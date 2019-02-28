package com.kauuze.app.domain.sso.dao;


import com.kauuze.app.domain.sso.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

/**
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    @Query(value = "update User set failCount = failCount+1")
    @Modifying
    public void incrFailCount(int id);
    @Query(value = "update User set failCount = 0")
    @Modifying
    public void setZeroFailCount(int id);
    public User findByPhone(String phone);
    public User findByNickName(String nickName);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select e from User e where e.id = ?1")
    public User findByIdForUpdate(int id);
}
