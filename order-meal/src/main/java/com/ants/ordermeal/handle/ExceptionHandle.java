package com.ants.ordermeal.handle;


import com.ants.ordermeal.common.ServerResponse;
import com.ants.ordermeal.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wolf
 */
@ControllerAdvice
public class ExceptionHandle {
    private final  static Logger LOG = LoggerFactory.getLogger(ExceptionHandle.class);
    /**
     * 用户：自定义异常类
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody//返回浏览器json格式，由于头部没有@RestController,所以用@ResponseBody
    public ServerResponse<User> userExceptionHandle(Exception e){
        if(e instanceof UserException){
            UserException userException = (UserException) e;
            return ServerResponse.createByErrorCodeMessage(userException.getCode(),userException.getMessage());
        }else{
            LOG.error("[系统异常]{}",e);
            return ServerResponse.createByErrorCodeMessage(-1,"未知异常");
        }
    }
}
