package cn.zerry.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLog {

    @Before("execution(* cn.zerry.example.aop.CalcProcessImpl.createCalcProcess(..))")
    public void beforeCalc(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println("before calc: " + args[0]);
    }

}
