package com.xinho.springboot.serializable;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/111:12
 */
public class User1Test {

    public static void main(String[] args) {
        JavaSerializer javaSerializer=new JavaSerializer();

        User1 user1=new User1();
        user1.setAge(18);
        user1.setName("xy");

        byte[] bytes=javaSerializer.serializer(user1);

        System.out.println(bytes.length);
    }
}
