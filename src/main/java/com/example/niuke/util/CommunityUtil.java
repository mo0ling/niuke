package com.example.niuke.util;

import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.DigestUtils;

import java.util.Map;
import java.util.UUID;

public class CommunityUtil {
    // 生成随机字符串
    public static String generateUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-","");//使字符串不含-
    }
    // MD5加密
    // key表示input psw + salt
    // hello -> abc123def456
    // hello + 12d9k -> abc123def456gsd
    public static String md5(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    public static String getJSONString(int code, String msg, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if (map != null) {
            for (String key : map.keySet()) {
                json.put(key, map.get(key));
            }
        }
        return json.toJSONString();
    }

    public static String getJSONString(int code, String msg) {
        return getJSONString(code, msg, null);
    }

    public static String getJSONString(int code) {
        return getJSONString(code, null, null);
    }
}
