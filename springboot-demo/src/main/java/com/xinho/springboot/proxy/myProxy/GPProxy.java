package com.xinho.springboot.proxy.myProxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/811:14
 */
public class GPProxy {

    public static final String ln = "\r\n";

    //生产代理类

    /***
     * 原理：
     1、拿到被代理对象的引用，并且获取到它的所有的接口，反射获取
     2、JDK Proxy类重新生成一个新的类、同时新的类要实现被代理类所有实现的所有的接口
     3、动态生成Java代码，把新加的业务逻辑方法由一定的逻辑代码去调用（在代码中体现）
     4、编译新生成的Java代码.class
     5、再重新加载到JVM中运行
     以上这个过程就叫字节码重组
     */
    public static Object newProxyInstance(GPClassLoader classLoader,Class<?>[] classInterface,GPInvocationHandler handler){

       try {
           //1、动态生成源代码.java文件
           String src=generateSrc(classInterface);

           //2、Java文件输出磁盘
           //由于我本地工作空间的目录上有空格 JDK获取后会转义为%20 所以需要手动转成空格
           String path=GPProxy.class.getResource("").getPath().replaceAll("%20"," ");
           System.out.println(path);
           File f=new File(path+"$Proxy0.java");
           FileWriter fileWriter=new FileWriter(f);
           fileWriter.write(src);
           fileWriter.flush();
           fileWriter.close();
           //3、把生成的.java文件编译成.class文件
           JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
           StandardJavaFileManager manage = compiler.getStandardFileManager(null,null,null);
           Iterable iterable = manage.getJavaFileObjects(f);

           JavaCompiler.CompilationTask task = compiler.getTask(null,manage,null,null,null,iterable);
           task.call();
           manage.close();
           //4、编译生成的.class文件加载到JVM中来
           Class classProxy=classLoader.findClass("$Proxy0");
           Constructor construntor=classProxy.getConstructor(GPInvocationHandler.class);

           //删除java文件
           f.delete();
           //5、返回字节码重组以后的新的代理对象
            return construntor.newInstance(handler);
       }catch (Exception e){
           e.printStackTrace();
       }
      return null;
    }

    public static String generateSrc(Class<?>[] classInterface){

        StringBuffer stringBuffer=new StringBuffer();
        //包名
        stringBuffer.append("package com.xinho.springboot.proxy.myProxy;" +ln);
        //导入包
        stringBuffer.append("import com.xinho.springboot.proxy.jdkProxy.User;"+ln);
        stringBuffer.append("import java.lang.reflect.Method;"+ln);

        //创建类
        stringBuffer.append("public class $Proxy0 implements "+classInterface[0].getName()+" {"+ln);
        //在类中定义InvocationHandler
        stringBuffer.append("GPInvocationHandler h;"+ln);
        //构造方法
        stringBuffer.append("public $Proxy0(GPInvocationHandler h){ "+ln);
        stringBuffer.append("this.h=h;"+ln);
        stringBuffer.append("}"+ln);

        for (Method method:classInterface[0].getMethods()){
            stringBuffer.append("@Override"+ln);
            stringBuffer.append("public "+method.getReturnType()+" "+method.getName()+"(){"+ln);
            stringBuffer.append("try{"+ln);
                stringBuffer.append("Method m = " + classInterface[0].getName() + ".class.getMethod(\"" + method.getName() + "\",new Class[]{});" + ln);
                stringBuffer.append("this.h.invoke(this,m,null);"+ln);
            stringBuffer.append("}catch(Throwable e){"+ln);
            stringBuffer.append("e.printStackTrace();"+ln);
            stringBuffer.append("}"+ln);
            stringBuffer.append("}"+ln);
        }

        stringBuffer.append("}");



        return  stringBuffer.toString();
    }
}
