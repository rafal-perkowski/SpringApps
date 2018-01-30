package pl.rp.ws.interceptor;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import pl.rp.ws.controller.TestController;

public class PromoCodeInterceptor extends HandlerInterceptorAdapter {

   private String promoCode;
   private String errorRedirect;
   private String offerRedirect;

   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException{
	   
	  TestController.traceCounter(TestController.InsertType.IN, "PromoCodeInterceptor preHandle(" + request + ", " + response + ", " + handler + ")");
      String givenPromoCode = request.getParameter("promo");

      if (promoCode.equals(givenPromoCode)) {
         response.sendRedirect(request.getContextPath() + "/" + offerRedirect);
      } else {
         response.sendRedirect(errorRedirect);
      }
      TestController.traceCounter(TestController.InsertType.OUT, "PromoCodeInterceptor preHandle(" + request + ", " + response + ", " + handler + ")");
      
      return false;
   }

   public void setPromoCode(String promoCode) {
      this.promoCode = promoCode;
   }

   public void setErrorRedirect(String errorRedirect) {
      this.errorRedirect = errorRedirect;
   }

   public void setOfferRedirect(String offerRedirect) {
      this.offerRedirect = offerRedirect;
   }
}
