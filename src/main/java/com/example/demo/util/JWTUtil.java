package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.token.MyAuth;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/22
 */

public class JWTUtil {

    // 过期时间 24 小时
//    private static final long EXPIRE_TIME = 60 * 24 * 60 * 1000;
    private static final long EXPIRE_TIME = 1000 * 1000;
    // 密钥
    private static final String SECRET = "SHIRO+JWT";

    /**
     * 生成 token, 5min后过期
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String createToken(String s) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim("myAuth", s)
                    //到期时间
                    //创建一个新的JWT，并使用给定的算法进行标记
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }


    /**
     * verify 校验token是否正确
     *
     * @param token token
     * @param s s
     * @return {@link boolean}
     *
     */
    public static boolean verify(String token, String s) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .withClaim("myAuth", s)
                    .build();
            //验证 token
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getAuthString(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("myAuth").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static MyAuth getAuthObject(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            String authString = jwt.getClaim("myAuth").asString();
            return JSON.parseObject(authString, MyAuth.class);
        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
        return null;
    }

}
