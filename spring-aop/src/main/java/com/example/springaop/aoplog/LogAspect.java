package com.example.springaop.aoplog;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @description:
 * @author: wzy
 * @time: 2022/3/16 16:40
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class) ;

//    @Pointcut("@annotation(com.example.springaop.aoplog.LogFilter)") //使用注解，目标方法上加上注解即可
    @Pointcut("execution(* com.example.springaop.aoplog.LogTestController.*(..))") //匹配LogTestController类中所有的方法
    public void logPointCut (){

    }
    @Around("logPointCut()")
    public Object around (ProceedingJoinPoint point) throws Throwable {
        Object result = null ;
        try{
            StopWatch stopWatch = new StopWatch();//计算方法执行的时间
            stopWatch.start();
            // 执行方法
            result = point.proceed();
            stopWatch.stop();
            long totalTimeMillis = stopWatch.getTotalTimeMillis();
            double upTime = new BigDecimal(totalTimeMillis).divide(new BigDecimal(1000)).doubleValue();//方法的执行时间(秒)
            System.out.println("======方法执行时间：" + upTime);
            // 保存请求日志
            saveRequestLog(point);
        } catch (Exception e){
            e.printStackTrace();
            // 保存异常日志
            saveExceptionLog(point,e.getMessage());
            result = "系统异常：" + e.getMessage();
        }
        return result;
    }
    private void saveExceptionLog (ProceedingJoinPoint point,String exeMsg){
        LOGGER.info("捕获异常:"+exeMsg);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        LOGGER.info("请求路径:"+request.getRequestURL());
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        LOGGER.info("请求方法:"+method.getName());
        Object[] args = point.getArgs();
        LOGGER.info("请求参数:"+ JSONObject.toJSONString(args));
        Map<String, String[]> parameterMap = request.getParameterMap();
        LOGGER.info("request请求参数:"+ JSONObject.toJSONString(parameterMap));

        //如果使用注解的方式，则可以取消下面的注释
//        // 获取方法上LogFilter注解
//        LogFilter logFilter = method.getAnnotation(LogFilter.class);
//        String value = logFilter.value() ;
//        LOGGER.info("模块描述:"+value);
//        Object[] args = point.getArgs();
//        LOGGER.info("请求参数:"+ JSONObject.toJSONString(args));
    }
    private void saveRequestLog (ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        LOGGER.info("请求路径:"+request.getRequestURL());
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        LOGGER.info("请求方法:"+method.getName());
        Object[] args = point.getArgs();
        LOGGER.info("请求参数:"+ JSONObject.toJSONString(args));
        Map<String, String[]> parameterMap = request.getParameterMap();
        LOGGER.info("request请求参数:"+ JSONObject.toJSONString(parameterMap));


        //如果使用注解的方式，则可以取消下面的注释
//        // 获取方法上LogFilter注解
//        LogFilter logFilter = method.getAnnotation(LogFilter.class);
//        String value = logFilter.value() ;
//        LOGGER.info("模块描述:"+value);
//        Object[] args = point.getArgs();
//        LOGGER.info("请求参数:"+ JSONObject.toJSONString(args));
//        Object proceed = point.proceed();
//        LOGGER.info("返回结果:"+ JSONObject.toJSONString(JSONObject.toJSONString(proceed)));
    }
}
