package com.springboot.lock;

import com.springboot.annotation.Klock;
import com.springboot.annotation.KlockKey;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kl on 2018/1/24.
 * Content :获取用户定义业务key
 */
public class BusinessKeyProvider {

    private ParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    private ExpressionParser parser = new SpelExpressionParser();

    public String getKeyName(ProceedingJoinPoint joinPoint, Klock klock) {
        List<String> keyList = new ArrayList<>();
        Method method = getMethod(joinPoint);
        List<String> definitionKeys = getSpelDefinitionKey(klock.keys(), method, joinPoint.getArgs());
        keyList.addAll(definitionKeys);
        List<String> parameterKeys = getParameterKey(method.getParameters(), joinPoint.getArgs());
        keyList.addAll(parameterKeys);
        return StringUtils.collectionToDelimitedString(keyList,"","-","");
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(),
                        method.getParameterTypes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return method;
    }

    private List<String> getSpelDefinitionKey(String[] definitionKeys, Method method, Object[] parameterValues) {
        List<String> definitionKeyList = new ArrayList<>();
        for (String definitionKey : definitionKeys) {
            if (definitionKey != null && !definitionKey.isEmpty()) {
                //EvaluationContext context = new MethodBasedEvaluationContext(null, method, parameterValues, nameDiscoverer);
               /* String key = parser.parseExpression(definitionKey).getValue(context).toString();
                definitionKeyList.add(key);*/
            }
        }
        return definitionKeyList;
    }

    private List<String> getParameterKey(Parameter[] parameters, Object[] parameterValues) {
        List<String> parameterKey = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getAnnotation(KlockKey.class) != null) {
                KlockKey keyAnnotation = parameters[i].getAnnotation(KlockKey.class);
                if (keyAnnotation.value().isEmpty()) {
                    parameterKey.add(parameterValues[i].toString());
                } else {
                    StandardEvaluationContext context = new StandardEvaluationContext(parameterValues[i]);
                    String key = parser.parseExpression(keyAnnotation.value()).getValue(context).toString();
                    parameterKey.add(key);
                }
            }
        }
        return parameterKey;
    }

    /**

     <redisson:client id="redissonClient" >
     <redisson:single-server address="${redisson.host}" connection-pool-size="${redis.pool.maxTotal}"
     password="${redis.pool.password}" connection-minimum-idle-size="${redis.pool.maxIdle}"/>

     </redisson:client>

     <bean id="lockInfo_" class=".lock.LockInfo">
     <property name="name" value="_myLock_"/>
     <property name="waitTime" value="3"/>
     <property name="leaseTime" value="10"/>

     </bean>

     <!-- 公平锁 -->
     <bean id="fairLock" class=".lock.FairLock">
     <constructor-arg index="0" name="redissonClient" ref="redissonClient"/>
     <property name="lockInfo" ref="lockInfo_"/>
     </bean>

     <!-- 重入锁 -->
     <bean id="reentrantLock" class=".lock.ReentrantLock">
     <constructor-arg index="0" name="redissonClient" ref="redissonClient"/>
     <property name="lockInfo" ref="lockInfo_"/>
     </bean>
     <!-- 读锁 -->
     <bean id="readLock" class=".lock.ReadLock">
     <constructor-arg index="0" name="redissonClient" ref="redissonClient"/>
     <property name="lockInfo" ref="lockInfo_"/>
     </bean>
     <!-- 写锁 -->
     <bean id="writeLock" class=".lock.WriteLock">
     <constructor-arg index="0" name="redissonClient" ref="redissonClient"/>
     <property name="lockInfo" ref="lockInfo_"/>
     </bean>
     */
}
