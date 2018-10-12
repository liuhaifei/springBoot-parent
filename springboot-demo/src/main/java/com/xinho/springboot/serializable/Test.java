package com.xinho.springboot.serializable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/3115:33
 */
public class Test {

    public static void main(String[] args) throws IOException {

        ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("user.txt"));

        JavaSerializer javaSerializer=new JavaSerializer();
        User user=new User();
        user.setName("liu");
        user.setAge(22);
        user.setSex("男");
        user.setHobby("play");

        Email email=new Email();
        email.setAddress("10");

        user.setEmail(email);

        objectOutputStream.writeObject(user);
        objectOutputStream.flush();

        System.out.println(new File("user.txt").length());


        objectOutputStream.writeObject(user);
        objectOutputStream.close();
        System.out.println(new File("user.txt").length());
//
//        try {
//            User uu=user.deepClone();
//            uu.getEmail().setAddress("20");
//            System.out.println(user.getEmail().getAddress());
//            System.out.println(uu.getEmail().getAddress());
//        }catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//       byte[] bytes= javaSerializer.serializer(user);
//
//      User u= javaSerializer.deSerializer(bytes,User.class);
//
//      System.out.println(u.getName()+"----"+u.getEmail().getAddress()+"----"+u.getHobby());
//
//      System.out.println(bytes.length);

    }
}
