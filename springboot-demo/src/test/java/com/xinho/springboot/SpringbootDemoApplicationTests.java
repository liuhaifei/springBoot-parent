package com.xinho.springboot;

import com.xinho.springboot.jpa.service.UserStoreImpl;
import com.xinho.springboot.rabbitMq.direct.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {

	@Autowired
	HelloSender helloSender;

	@Autowired
	private RedisTemplate redisTemplate;

//	@Autowired
//	private JedisCluster jedisCluster;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private UserStoreImpl userStore;

	@Test
	public void getUser(){
		userStore.findStoreByUserName("11").forEach(item->{
			System.out.println(item.getId());
		});
	}

	/**
	 * 0dbf6de6-9388-4f1b-8529-9f49ab822cb5
	 * 0ffc204b-c9cb-48aa-a92c-a296a175a58f
	 * 12c5 3160-97e0-4537-ad5a-36fac4c8d917
	 * 1d3f6c9d-7c4a-4d26-9593-ec15686c0b44
	 *
	 * 项目中如有有依赖第三方缓存插件如：redis mamerchache 等，会默认与这些插件整合
	 */
	@Test
	public void getUserByCache(){
		System.out.println(userStore.findUserById("0dbf6de6-9388-4f1b-8529-9f49ab822cb5"));
		System.out.println(userStore.findUserById("0ffc204b-c9cb-48aa-a92c-a296a175a58f"));
		System.out.println(userStore.findUserById("12c5 3160-97e0-4537-ad5a-36fac4c8d917"));


		try {
			Thread.sleep(3000L);
			System.out.println("休眠结束  查询使用缓存");

			System.out.println(userStore.findUserById("0dbf6de6-9388-4f1b-8529-9f49ab822cb5"));
			System.out.println(userStore.findUserById("0ffc204b-c9cb-48aa-a92c-a296a175a58f"));
			System.out.println(userStore.findUserById("12c5 3160-97e0-4537-ad5a-36fac4c8d917"));
			System.out.println(userStore.findUserById("1d3f6c9d-7c4a-4d26-9593-ec15686c0b44"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void contextLoads() {
		for (int i=0;i<10;i++){
			helloSender.send(i+"");
		}
	}
	@Test
	public void topicSend(){
		helloSender.send1();
	}

	@Test
	public void topicSendConfim(){
		helloSender.sendConfim();
	}

	@Test
	public void topicFanout(){
		helloSender.send2();
	}

	@Test
	public void deadLetter(){
		helloSender.sendDeadLetter();
	}

	@Test
	public void redisTest(){

//		redisTemplate.opsForValue().set("test:set","testValue1");
//		redisTemplate.opsForValue().set("1","testValue2");
//		stringRedisTemplate.opsForValue().set("1","testValue2");
//		jedisCluster.set("000","12121");

//		redisTemplate.opsForValue().set("liu","testValue2");

		String s= (String) redisTemplate.opsForValue().get("liu");

		redisTemplate.boundListOps("");
		System.out.println(s);
	}

//	@Test
//	public void redisTestDel(){
////		String a= (String) redisTemplate.opsForValue().get("test:set");
////		jedisCluster.decr("1");
//		jedisCluster.del("1");
////		System.out.println(a);
//	}
//
//	@Test
//	public void redisTestGet(){
//		String a=jedisCluster.get("2");
//		System.out.println(a);
//	}

	@Test
	public void test(){
        List<String> famous = new ArrayList<String>();
        famous.add("liudehua");
        famous.add("madehua");
        famous.add("liushishi");
        famous.add("tangwei");



		Iterator<String> iter =  famous.iterator();
		while (iter.hasNext()) {
			String x=iter.next();
			if ("madehua".equals(x)){
				iter.remove();
			}
		}
		for (String s : famous) {
			System.out.println("list中的元素："+s);
		}

		for (int i=0;i<famous.size();i++){
			if("madehua".equals(famous.get(i))){
				famous.remove(i);
			}
		}

		for (String s : famous) {
			System.out.println("list中的元素："+s);
		}
	}



}
