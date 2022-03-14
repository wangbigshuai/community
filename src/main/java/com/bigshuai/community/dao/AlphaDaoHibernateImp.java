package com.bigshuai.community.dao;

import org.springframework.stereotype.Repository;

//这个包下满足 但还是不能被扫描 需要加
@Repository("alphaHibernate")//自定义名字 使容器返给我特定的 实现类
public class AlphaDaoHibernateImp implements AlphaDao {

    @Override
    public String select() {
        return "Hibernate";
    }
}
