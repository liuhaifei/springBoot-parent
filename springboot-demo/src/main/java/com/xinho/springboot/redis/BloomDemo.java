package com.xinho.springboot.redis;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * @ClassName BloomDemo
 * @Description TODO
 * @Author 刘海飞
 * @Date 2018/9/27 16:54
 * @Version 1.0
 **/
public class BloomDemo {

    public static void main(String[] args) {

        BloomFilter<String> bloomFilter=
                BloomFilter.create(Funnels.stringFunnel
                                    (Charset.defaultCharset()),
                                     100000,
                                            0.0001);
        bloomFilter.put("zs");

        System.out.println(bloomFilter.mightContain("zs"));
    }
}
