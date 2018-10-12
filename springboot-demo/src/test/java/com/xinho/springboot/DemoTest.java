package com.xinho.springboot;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.xinho.springboot.serializable.UserProto;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/3110:39
 */
public class DemoTest {

    public static void main(String[] args) {

//        UserEntity userEntity=new UserEntity();
//
//        userEntity.setName("Mr.Liu");
//        userEntity.setAge(22);
//
//        String s= JSONObject.toJSONString(userEntity);
//        JSONObject jsonObject=JSONObject.parseObject(s);
//
//        System.out.println("name--->"+jsonObject.get("name"));
//        System.out.println("name--->"+jsonObject.get("age"));

        UserProto.User user=UserProto.User.newBuilder().setAge(18).setName("xy").build();
        ByteString byteString=user.toByteString();

        System.out.println(byteString);

        try {
            UserProto.User user1=UserProto.User.parseFrom(byteString);

            System.out.println(user1.getAge()+"---"+user1.getName());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        int x=15;
        System.out.println((x&1)==1?"奇数":"偶数");
        System.out.println(x & (x - 1));
        System.out.println((x & (x - 1))==0?"2的幂次方":"不是2的幂次方");
    }
}
