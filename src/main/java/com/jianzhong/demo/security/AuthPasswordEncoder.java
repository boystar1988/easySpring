package com.jianzhong.demo.security;

import com.jianzhong.demo.constant.AuthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
@Slf4j
public class AuthPasswordEncoder implements PasswordEncoder
{
    //md5(md5(pass)+salt)
    @Override
    public String encode(CharSequence charSequence) {
        String pass = charSequence.toString();
        pass = DigestUtils.md5DigestAsHex(pass.getBytes());
        pass += AuthConstant.AUTH_SECURITY_SALT;
        return DigestUtils.md5DigestAsHex(pass.getBytes());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return DigestUtils.md5DigestAsHex((DigestUtils.md5DigestAsHex(charSequence.toString().getBytes())+AuthConstant.AUTH_SECURITY_SALT).getBytes()).equals(s);
    }

}