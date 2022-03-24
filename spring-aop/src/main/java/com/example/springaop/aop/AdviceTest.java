package com.example.springaop.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: wzy
 * @time: 2022/3/16 9:58
 */
@Aspect //标注增强处理类（切面类）
@Component //交由Spring容器管理
@Slf4j
public class AdviceTest {


    /**
     * 一、匹配方法签名:
     * // 匹配指定包中的所有的方法
     * execution(* com.xys.service.*(..))
     * // 匹配当前包中的指定类的所有方法
     * execution(* UserService.*(..))
     * // 匹配指定包中的所有 public 方法
     * execution(public * com.xys.service.*(..))
     * // 匹配指定包中的所有 public 方法, 并且返回值是 int 类型的方法
     * execution(public int com.xys.service.*(..))
     * // 匹配指定包中的所有 public 方法, 并且第一个参数是 String, 返回值是 int 类型的方法
     * execution(public int com.xys.service.*(String name, ..))
     *
     * 二、匹配类型签名
     * // 匹配指定包中的所有的方法, 但不包括子包
     * within(com.xys.service.*)
     * // 匹配指定包中的所有的方法, 包括子包
     * within(com.xys.service..*)
     * // 匹配当前包中的指定类中的方法
     * within(UserService)
     * // 匹配一个接口的所有实现类中的实现的方法
     * within(UserDao+)
     *
     * 三、匹配 Bean 名字
     * // 匹配以指定名字结尾的 Bean 中的所有方法
     * bean(*Service)
     *
     * 四、切点表达式组合
     * // 匹配以 Service 或 ServiceImpl 结尾的 bean
     * bean(*Service || *ServiceImpl)
     * // 匹配名字以 Service 结尾, 并且在包 com.xys.service 中的 bean
     * bean(*Service) && within(com.xys.service.*)
     */
    //定义一个 Pointcut, 使用 切点表达式函数 来描述对哪些 Join point 使用 advise.
//    @Pointcut("execution(* com.example.springaop.controller.TestController.test(..))")  //匹配TestController类中test的方法
    @Pointcut("execution(* com.example.springaop.controller.TestController.*(..))") //匹配TestController类中所有的方法
    public void point() {
    }
    @Before("point()")
    public void before(JoinPoint joinPoint) {
        System.out.println("======目标接口方法===执行前======");
        log.info("---Before method {} invoke, param: {}---", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }


    /**
     * 指的注意的是 @Around 修饰的环绕通知类型，是将整个目标方法封装起来了，在使用时，我们传入了 ProceedingJoinPoint 类型的参数，
     * 这个对象是必须要有的，并且需要调用 ProceedingJoinPoint 的 proceed() 方法.
     * 如果不调用该对象的 proceed() 方法，表示原目标方法被阻塞调用.
     */
    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("======around===开始");
        //第一步：目标方法执行前的逻辑。。。
        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();

        //得到其方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("请求参数为{}",args);

        //第二步：执行目标方法
        Object proceed = joinPoint.proceed();

        //第三步：目标方法执行后的逻辑(在这里可以拿到方法返回的结果)...
        System.out.println("目标接口结果返回：" + JSONObject.toJSONString(proceed));

        System.out.println("======around===结束");
        return proceed;
    }

    @After("point()")
    public void after() {
        System.out.println("======目标接口方法===执行后======");
    }
}
