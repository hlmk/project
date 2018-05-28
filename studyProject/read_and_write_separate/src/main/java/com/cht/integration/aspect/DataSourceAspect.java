package com.cht.integration.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 切换数据源(不同方法调用不同数据源)
 *
 * @author
 * @version
 */
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {
    private final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);
    //配置切入点
   // @Pointcut("execution(* com.zhx.service..*.*(..))")
    public void aspect() {
    }

    /**
     * 配置前置通知,使用在方法aspect()上注册的切入点
     */
   // @Before("com.cht.integration.aspect()")
    public void before(JoinPoint point) {
            String className = point.getTarget().getClass().getName();
        String method = point.getSignature().getName();
        logger.info(className + "." + method + "(" + StringUtils.join(point.getArgs(), ",") + ")");
        try {
            L: for (String key : ChooseDataSource.METHODTYPE.keySet()) {
                for (String type : ChooseDataSource.METHODTYPE.get(key)) {
                    if (method.startsWith(type)) {
                        logger.info(key);
                        HandleDataSource.putDataSource(key);
                        break L;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            HandleDataSource.putDataSource("write");
        }
    }

   // @After("com.cht.integration.aspect()")
    public void after(JoinPoint point) {
        HandleDataSource.clear();
    }
}