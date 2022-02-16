package com.coderhouse.utils.AOP;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.*;

import java.sql.Timestamp;
import java.util.Date;


@Aspect
@Component
public class ApiAspect {
    private static final Logger logger = LogManager.getLogger(ApiAspect.class);

    @Pointcut("execution(* com.coderhouse.controller.*.*(..))")
    void afterRequest() {}
    @Pointcut("execution(* com.coderhouse.service.*.*(..))")
    void afterService() {}

    @After("afterRequest()")
    void afterAdviceMethodAll(JoinPoint jp) {
        logger.info("Se ejecuto el request "+jp.getSignature().getName());
    }
    @AfterThrowing(value="afterService()",throwing = "ex")
    void afterThrowingExceptionAll(JoinPoint jp,Exception ex){
        logger.error("Se encontro la siguiente excepcion {} en el método {}",ex.getMessage(),jp.getSignature().getName());

    }
//    @After(""execution(* com.coderhouse.controller.*.*(..))"")
//    public void logAnnotation(JoinPoint jp){
//        logger.info("Fin de ejecucion del metodo {}",jp.getSignature().getName());
//    }
//
//    @AfterThrowing("execution(* com.coderhouse.controller.*.*(..))")
//    public void logException(JoinPoint jp){
//        logger.error("en after throwing advice method. Hora: {}", System.currentTimeMillis());
//    }
}
