package pl.rp.ws.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import pl.rp.ws.controller.TestController;

public class ProcessingTimeLogInterceptor implements HandlerInterceptor {
   
    private static final Logger LOGGER = Logger.getLogger(ProcessingTimeLogInterceptor.class);
 
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    	TestController.traceCounter(TestController.InsertType.IN, "ProcessingTimeLogInterceptor preHandle(" + request + ", " + response + ", " + handler + ")");
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        TestController.traceCounter(TestController.InsertType.OUT, "ProcessingTimeLogInterceptor preHandle(" + request + ", " + response + ", " + handler + ")");
        return true;
    }
 
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    	TestController.traceCounter(TestController.InsertType.IN, "ProcessingTimeLogInterceptor postHandle(" + request + ", " + response + ", " + handler + ", " + modelAndView + ")");
    	String queryString = request.getQueryString() == null ? "" : "?" + request.getQueryString();
        String path = request.getRequestURL() + queryString;

        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        
        LOGGER.info(String.format("%s millisecond taken to process the request %s.",(endTime - startTime), path));
        TestController.traceCounter(TestController.InsertType.OUT, "ProcessingTimeLogInterceptor postHandle(" + request + ", " + response + ", " + handler + ", " + modelAndView + ")");
    }
 
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exceptionIfAny){
       // NO operation. 
    	TestController.traceCounter(TestController.InsertType.INOUT, "ProcessingTimeLogInterceptor afterCompletion(" + request + ", " + response + ", " + handler + ", " + exceptionIfAny + ")");
    }
}
