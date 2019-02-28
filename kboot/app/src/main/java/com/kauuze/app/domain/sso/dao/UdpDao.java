package com.kauuze.app.domain.sso.dao;


import com.kauuze.app.domain.sso.entity.Udp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
@Repository
public interface UdpDao extends JpaRepository<Udp,Integer> {
}
