package com.springboot.lock;

import com.springboot.annotation.Klock;
import com.springboot.lock.Lock;
import com.springboot.lock.LockFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Content :给添加@KLock切面加锁处理
 */
@Aspect
@Component
public class KlockAspectHandler {

    @Autowired
    LockFactory lockFactory;

    @Around(value = "@annotation(klock)")
    public Object around(ProceedingJoinPoint joinPoint, Klock klock) throws Throwable {
        Lock lock = lockFactory.getLock(joinPoint,klock);
        boolean currentThreadLock = false;
        try {
            currentThreadLock = lock.acquire();
            return joinPoint.proceed();
        } finally {
            if (currentThreadLock) {
                lock.release();
            }
        }
    }
}
