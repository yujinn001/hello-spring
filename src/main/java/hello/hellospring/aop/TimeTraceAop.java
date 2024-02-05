package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // AOP라는 정의
@Component // component라는 걸 정의해 줘야 한다
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START " +joinPoint.toString()); // 단축키 sout
        try {
            Object result = joinPoint.proceed(); // 다음메소드로 진행
            return result;
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish -start ;
            System.out.println("END " + joinPoint.toString() + " " +timeMs +"ms");

        }
    }
}
