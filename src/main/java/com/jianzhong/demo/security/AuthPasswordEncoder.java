package com.jianzhong.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义的密码加密方法，实现了PasswordEncoder接口
 * @author 程就人生
 *
 */
@Component
@Slf4j
public class AuthPasswordEncoder implements PasswordEncoder
{

    @Override
    public String encode(CharSequence charSequence) {
        log.info("密码1："+charSequence.toString());
        return charSequence.toString();
        //加密方法可以根据自己的需要修改
//        String str = charSequence.toString();
////        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        String idForEncode = "auth";
//        Map<String,PasswordEncoder> encoders = new HashMap<>();
//        encoders.put(idForEncode, this);
//        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode,encoders);
//        return passwordEncoder.encode(str);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        log.info("密码2："+charSequence.toString());
        log.info("s:"+s);
        return charSequence.toString().equals(s);
//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        String idForEncode = "auth";
//        Map<String,PasswordEncoder> encoders = new HashMap<>();
//        encoders.put(idForEncode, new AuthPasswordEncoder());
//        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode,encoders);
//        log.info(charSequence.toString());
//        log.info(s);
//        return passwordEncoder.matches(charSequence,s);
    }

}