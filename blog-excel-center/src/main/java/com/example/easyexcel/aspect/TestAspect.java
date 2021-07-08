package com.example.easyexcel.aspect;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.easyexcel.annotation.NotNull;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * <p>
 * 测试切面配置
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-04-28
 */
@Aspect
@Component
@Slf4j
public class TestAspect {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final String POINT_CUT = "execution(* com.example.easyexcel.controller.*.*(..))";

    @Pointcut(POINT_CUT)
    public void pointCut(){}

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint){
        try {
            // 方法参数
            Object[] args = joinPoint.getArgs();
            if (args.length == 0) {
                return;
            }

            // 方法签名对象
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            // 方法名
            String name = signature.getName();
            // 方法对象
            Method method = signature.getMethod();
            Annotation[][] annotations = method.getParameterAnnotations();
            for (int i=0;i<annotations.length;i++) {
                // 参数对象
                Object arg = args[i];
                // 参数注解集合
                Annotation[] annotation = annotations[i];
                if (arg == null && annotation.length == 0) {
                    continue;
                }
                for (int j=0;j<annotation.length;j++) {
                    // 获取注解
                    Annotation an = annotation[j];

                    if (an.annotationType().equals(NotNull.class)){
                        // 是NotNull注解
                        if (ObjectUtils.isEmpty(arg)) {
                            Target target = an.annotationType().getAnnotation(Target.class);

                            String message = ((NotNull) an).message();

                            System.out.println(Arrays.toString(target.value()));
                            throw new RuntimeException(message);

                        }
                    }

                }

            }

            log.info("调用方法:" + name + ",方法参数:" + Arrays.asList(args));
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

//    @AfterReturning(value = "pointCut()",returning = "result")
//    public void afterReturning(JoinPoint joinPoint,Object result){
//        // 方法参数
//        List<Object> list = Arrays.asList(joinPoint.getArgs());
//
//        // 方法签名对象
//        Signature signature = joinPoint.getSignature();
//        // 方法名
//        String name = signature.getName();
//
//        log.info("调用方法:" + name + ",方法参数:" + list + ",返回值:" + result);
//
//    }
//
//    @AfterThrowing(value = "pointCut()",throwing = "ex")
//    public void afterThrowing(JoinPoint joinPoint,Exception ex){
//        // 方法参数
//        List<Object> list = Arrays.asList(joinPoint.getArgs());
//
//        // 方法签名对象
//        Signature signature = joinPoint.getSignature();
//        // 方法名
//        String name = signature.getName();
//
//        log.info("调用方法:" + name + ",方法参数:" + list + ",异常为:" + ex);
//
//    }

}
