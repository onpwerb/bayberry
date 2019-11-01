package cn.zerry.example.aop;

import org.springframework.stereotype.Component;

@Component
public class CalcProcessImpl {

    public void createCalcProcess(Integer i){
        System.out.println(i);
    }

    @CalcLog
    public void edit(String a, Integer b){
        System.out.println(a);
        System.out.println(b);
    }
}
