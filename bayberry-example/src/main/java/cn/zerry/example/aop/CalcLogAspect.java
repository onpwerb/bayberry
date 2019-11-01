package cn.zerry.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalcLogAspect {

    @Before("@annotation(cn.zerry.example.aop.CalcLog)")
    public void logTime(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println("before : " + args[0] + "," + args[1]);
    }

}
