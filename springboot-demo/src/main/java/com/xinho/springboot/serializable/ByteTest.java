package com.xinho.springboot.serializable;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/3117:02
 */
public class ByteTest {

    public static void main(String[] args) {
        System.out.println(-1>>>1);

        System.out.println(1<<31);
        System.out.println(1<<-1);

        System.out.println((10&1)==1?"奇数":"偶数");

       int[] ar=new int[]{2,8};
       int i=0;
       int j=ar.length-1;
       while (j>i){
           System.out.println("第一次：ar[i]="+ar[i]+"  ar[j]="+ar[j]+"  ar[i]^ar[j]结果为："+(ar[i]^ar[j]));
           ar[i]=ar[i]^ar[j];
           System.out.println("第二次：ar[i]="+ar[i]+"  ar[j]="+ar[j]+"  ar[i]^ar[j]结果为："+(ar[i]^ar[j]));
           ar[j]=ar[i]^ar[j];
           System.out.println("第三次：ar[i]="+ar[i]+"  ar[j]="+ar[j]+"  ar[i]^ar[j]结果为："+(ar[i]^ar[j]));
           ar[i]=ar[i]^ar[j];
           i++;
           j--;
       }

        /**
         * a=a^b
         * b=(a^b)^b=a
         * a=(a^b)^(a^b)^b=b
         */

        for (int x=0;x<ar.length;x++){
           System.out.println(ar[x]);
       }

        int a=-20;
        System.out.println((a^(a>>31))-(a>>31));
    }
}
