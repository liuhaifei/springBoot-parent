package com.xinho.springboot.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author 刘海飞
 * @Date 2018/9/28 11:49
 * @Version 1.0
 **/
@RestController
public class TestController {


    @GetMapping("/test")
    public String getName(){

       throw new MyException("405","error");
    }
}
