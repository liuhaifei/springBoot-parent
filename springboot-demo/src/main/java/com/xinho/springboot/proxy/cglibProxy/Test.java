package com.xinho.springboot.proxy.cglibProxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/811:04
 */
public class Test {
    public static void main(String[] args) {
    try {


        Person person=(Person)new PerSonCglibProxy().getInstance(Person.class);
       person.shopping();

        System.out.println(person.getClass());

        //通过反编译工具可以查看源代码
        byte [] bytes = ProxyGenerator.generateProxyClass("Person$$EnhancerByCGLIB$$a92637ae",new Class[]{Person.class});
        FileOutputStream os = new FileOutputStream("E://Person$$EnhancerByCGLIB$$a92637ae.class");
        os.write(bytes);
        os.close();

    }catch (Exception e){
        e.printStackTrace();
    }
    }
}
