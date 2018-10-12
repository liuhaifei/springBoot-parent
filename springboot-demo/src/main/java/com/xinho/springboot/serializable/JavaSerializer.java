package com.xinho.springboot.serializable;

import java.io.*;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/3115:19
 */
public class JavaSerializer implements  ISerializable {

    @Override
    public <T> byte[] serializer(T obj) {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);

        }catch (IOException e){
            throw  new RuntimeException(e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public <T> T deSerializer(byte[] bytes, Class<T> classz) {
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);

            return (T)objectInputStream.readObject();
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
