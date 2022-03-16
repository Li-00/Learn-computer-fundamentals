package com.example.eight_part_essay.jwt;


import com.nimbusds.jose.shaded.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;


/**
 * @author: zy
 * @date: 2022/3/15 21:18
 * @since JDK 1.8
 */
public class CreatJWT {

   static String utf8 = "UTF-8";

   @Test
   public void creatJwt() throws Exception {
       //获取header
       JSONObject originalHeader = new JSONObject();
       originalHeader.put("typ", "JWT");
       originalHeader.put("a1g", "HS256");
       String header = Base64.encodeBase64URLSafeString(originalHeader.toJSONString().getBytes(utf8));
//获取payload载荷
       JSONObject originalPayload = new JSONObject();
       User user = new User();
       user.setName("aaa");
       user.setFlag(false);
       originalPayload.put("userInfo", JSONObject.toJSONString((Map<String, ? extends Object>) user));
       String payload = Base64.encodeBase64URLSafeString(originalPayload.toJSONString().getBytes(utf8));

       //获取signature
       String signature = hmacSha256Encode(header, payload);
       String token = String.format("%s .%s.%s.", header, payload, signature);
       System.out.println(token);

   }
       public static String hmacSha256Encode(String header, String payload) throws Exception {
//获取提供摘要算法的对象
           Mac hmacSha256 = Mac.getInstance("HmacSHA256");
//  指定算法的key
           SecretKeySpec secretKeySpec = new SecretKeySpec(header.getBytes(utf8), "HmacSHA256");
           hmacSha256.init(secretKeySpec);
//进行摘要
           hmacSha256.update(header.getBytes(utf8));
           hmacSha256.update(header.getBytes(utf8));
           hmacSha256.update(".".getBytes(utf8));
           byte[] bytes = hmacSha256.doFinal(payload.getBytes(utf8));
           return Base64.encodeBase64String(bytes);
       }

}
