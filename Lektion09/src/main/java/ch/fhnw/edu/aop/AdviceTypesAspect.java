package ch.fhnw.edu.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by benjamin on 09.11.2015.
 */
@Aspect
@Component
public class AdviceTypesAspect {
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * After Advice: limits join points to calls into the dataaccess
     * layer and methods with just one parameter of type Long
     */
    @After("execution(* ch.fhnw.edu.dataaccess..*(Long))")
    public void showRequest(JoinPoint jp) {
        log.info("Show Request: " + jp.getArgs()[0]);
    }

    /**
     * AfterReturning Advice: limits join points to calls into the dataaccess
     * layer and methods with just one parameter of type Long
     */
    @AfterReturning(pointcut = "execution(* ch.fhnw.edu.dataaccess..*(Long))", returning = "object")
    public void showResponse(Object object) {
        log.info("showResponse: " + object.toString());
    }

    /**
     * AfterThrowing Advice: limits join points to calls into the dataaccess
     * layer and methods with just one parameter of type Object
     */
    @AfterThrowing(pointcut = "execution(* ch.fhnw.edu.dataaccess..*(Object+)))", throwing = "exception")
    public void showException(Throwable exception) {
        log.error("showExcepton: " + exception.toString());
    }

    @Around("within(ch.fhnw.edu.domain.service.*) && args(id)")
    public Object validate(ProceedingJoinPoint pjp, Long id) throws Throwable {
        if (id != null) {
            log.info(pjp.getSignature());
            Object o = pjp.proceed();
            log.info(pjp.getSignature());
            return o;
        } else {
            log.error("Error: id is null");
            throw new IllegalArgumentException("id is null");
        }
    }
}
