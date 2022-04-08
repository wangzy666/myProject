package com.lcc.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 私钥加密token
     *
     * @param userMap      载荷中的数据
     * @param privateKey    私钥
     * @param expireMinutes 过期时间，单位是分钟
     * @return
     * @throws Exception
     */
    public static String generateToken(Map<String,Object> userMap, PrivateKey privateKey, Integer expireMinutes) throws Exception {
        return Jwts.builder()
                .claim("userInfo", userMap)
                .setIssuedAt(new Date())
                .setExpiration(expireMinutes == null ? null : DateTime.now().plusMinutes(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 私钥加密token
     *
     * @param userMap      载荷中的数据
     * @param privateKey    私钥字节数组
     * @param expireMinutes 过期时间，单位秒
     * @return
     * @throws Exception
     */
    public static String generateToken(Map<String,Object> userMap, byte[] privateKey, Integer expireMinutes) throws Exception {
        return Jwts.builder()
                .claim("userInfo", userMap)
                .setExpiration(expireMinutes == null ? null : DateTime.now().plusMinutes(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.RS256, RsaUtils.getPrivateKey(privateKey))
                .compact();
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥
     * @return
     * @throws Exception
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥字节数组
     * @return
     * @throws Exception
     */
    private static Jws<Claims> parserToken(String token, byte[] publicKey) throws Exception {
        return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(publicKey))
                .parseClaimsJws(token);
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     * @throws Exception
     */
    public static Map<String, Object> getInfoFromToken(String token, PublicKey publicKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        System.out.println("token生成时间："+ sdf.format(body.getIssuedAt()));
        if(null != body.getExpiration()){
            System.out.println("token过期时间："+ sdf.format(body.getExpiration()));
        }else{
            System.out.println("token过期时间："+ body.getExpiration());
        }
        Map<String, Object> userInfo = (Map<String, Object>)body.get("userInfo");
        return userInfo;
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     * @throws Exception
     */
    public static String getInfoFromToken(String token, byte[] publicKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        return body+"";
    }


    public static void main(String[] args) {
        Integer userId = 123;
        String userName = "张三";
        PrivateKey privateKey = null;
        PublicKey publicKey = null;
        Map<String,Object> infoFromToken = null;
        Map<String,Object> userMap = new HashMap<String,Object>();
        userMap.put("userId",userId);
        userMap.put("userName",userName);
        try {
            privateKey = RsaUtils.getPrivateKey("D:/cjp/key.pri");
            publicKey = RsaUtils.getPublicKey("D:/cjp/key.pub");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer expireMinutes = 1;//过期时间分钟
        String token = "";
        try{
            token = generateToken(userMap,privateKey,expireMinutes);
            infoFromToken = getInfoFromToken(token, publicKey);
        }catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println("当前时间："+ sdf.format(new Date()));
        System.out.println("获取token："  + token);


        System.out.println("获取用户信息："  + infoFromToken);
    }
}
