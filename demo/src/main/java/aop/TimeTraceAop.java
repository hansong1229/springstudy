package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
// Aspect를 명시해줘야 AOP로 사용 가능
@Component
// bean에 등록해도 되고 component로 등록해도 됨
public class TimeTraceAop {
	
	@Around("execution(* java..*(..))")
	// 해당하는 패키지명을 적어주면 된다. 또는 다양한 경로도 가능하다
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.currentTimeMillis();
		System.out.println("START: "+ joinPoint.toString());
		try {
			Object result = joinPoint.proceed();
			return result;
		}finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("END: "+ joinPoint.toString() + " "+ timeMs + "ms");
		}
	}
	
}
