package com.jianzhong.demo.service;

import com.jianzhong.demo.domain.User;
import com.jianzhong.demo.repository.UserRepository;
import com.jianzhong.demo.utils.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.*;
import com.google.common.collect.Lists;

@Service
@Slf4j
@SuppressWarnings("unchecked")
public class UserService implements Serializable, UserDetailsService
{
    @Autowired
    IdUtil idUtil;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ApplicationContext publisher;

    public int delete(Long uid)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        // 先加载一个持久化对象
        User User = (User) session.get(User.class, uid);
        try{
            session.delete(User);
            tx.commit();
            return 1;
        }catch (Exception e){
            tx.rollback();
            log.error(e.getMessage());
            return 0;
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    public Long insert(Map record)
    {
        //获取SessionFactory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try{
            User User =new User();
            Long uid = idUtil.nextId();
            User.setUid(uid);
            User.setUsername(record.get("username").toString());
            User.setPassword(record.get("password").toString());
            session.save(User);
            tx.commit();
            return uid;
        }catch (Exception e){
            tx.rollback();
            log.error(e.getMessage());
            return 0L;
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    public User findUserByUid(Long uid)
    {
        return userRepository.findUserByUid(uid);
    }

    public List<User> findAll()
    {
        return Lists.newArrayList(userRepository.findAll());
    }

    public int updateUserByUid(Long uid,Map record)
    {
        //获取SessionFactory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try{
            User User = userRepository.findUserByUid(uid);
            User.setUsername(record.get("username").toString());
            User.setPassword(record.get("password").toString());
            session.save(User);
            tx.commit();
            return 1;
        }catch (Exception e){
            tx.rollback();
            log.error(e.getMessage());
            return 0;
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不正确");
        }
        return user;
    }
}