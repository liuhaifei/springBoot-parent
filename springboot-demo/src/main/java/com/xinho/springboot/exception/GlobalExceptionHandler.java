package com.xinho.springboot.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author 刘海飞
 * @Date 2018/9/28 13:50
 * @Version 1.0
 **/
@ControllerAdvice
@Order
public class GlobalExceptionHandler {

    private final static Logger log=LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @Description: 应用到@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param
     * @author lhf
     * @return
     * @date 2018/9/28 14:11
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){}

    /**
     * @Description: 把值绑定到model中，使全局@RequetstMapping可以获取到该值
     * @param model
     * @author lhf
     * @return
     * @date 2018/9/28 14:12
     */
    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("author","lhf");
    }

    /**
     * @Description: 全局异常处理
     * @param e
     * @author lhf
     * @return
     * @date 2018/9/28 14:15
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception e){
        Map map=new HashMap();
        map.put("code",100);
        map.put("msg",e.getMessage());
        return map;
    }


    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public Map myErrorHandler(MyException e){
        Map map=new HashMap();
        map.put("code",e.getCode());
        map.put("msg",e.getMessage());
        return map;
    }
}
