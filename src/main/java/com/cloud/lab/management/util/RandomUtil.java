package com.cloud.lab.management.util;

import java.util.Random;

/**
 * @Author: John.ma
 * @Description: 生成随机码
 * @Date: 2019/12/3 16:26
 */
public class RandomUtil {


    /**
     * 随机生成数字加字母的 随机码
     *
     * @param length 长度
     * @return
     */
    public static String getStringRandom(int length) {
        String code = "";
        Random random = new Random();
        //length为几位密码
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                code += (char) (random.nextInt(26) + temp);
            } else {
                code += String.valueOf(random.nextInt(10));
            }
        }
        return code.toLowerCase();
    }
}
