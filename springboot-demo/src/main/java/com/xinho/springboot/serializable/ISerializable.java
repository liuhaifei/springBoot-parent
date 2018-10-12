package com.xinho.springboot.serializable;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/3115:14
 */
public interface ISerializable {

    /**
     * 序列化
     * @param <T>
     * @return
     */
   <T> byte[] serializer(T obj);

    /**
     * 反序列化
     * @param bytes
     * @param classz
     * @param <T>
     * @return
     */
   <T> T deSerializer(byte[] bytes,Class<T> classz);
}
