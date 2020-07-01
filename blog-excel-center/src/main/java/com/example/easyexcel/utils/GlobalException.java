//package com.example.easyexcel.utils;
//
///**
// * @author ZhenHX
// * @description
// * @date 2020-04-03 18:16
// */
//@ControllerAdvice
//public class GlobalException {
//    // 专用的错误日志收集器
//    private static Logger log = LoggerFactory.getLogger(LoggerName.ERROR);
//
//    //    @ExceptionHandler(value={RuntimeException.class,MyRuntimeException.class})针对不同类型的异常
//    //    @ExceptionHandler//默认处理所有异常
//    @ExceptionHandler
//    @ResponseBody //在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
//    public Response<String> exceptionHandler(Exception e, HttpServletResponse response) {
//        Response<String> resp = new Response<>();
//        resp.setCode(Response.HttpResponseStatus.ERR);
//        resp.setMsg(e.getMessage());
//        // 记录日志
//        e.printStackTrace();
//        log.warn(e.getMessage(),e);
//        return resp;
//    }
//}
