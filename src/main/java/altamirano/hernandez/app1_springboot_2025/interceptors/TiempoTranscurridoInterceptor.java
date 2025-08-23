package altamirano.hernandez.app1_springboot_2025.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

public class TiempoTranscurridoInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Ejemplo de error
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        String json = "{ \"error\": \"Acceso denegado\"}";
        response.getWriter().write(json);
        response.getWriter().flush();

        return false;
//        logger.info("preHandle de TiempoTranscurridoInterceptor");
//        long startTime = System.currentTimeMillis();
//        request.setAttribute("startTime", startTime);
//
//        System.out.println("Username: " + request.getParameter("username"));
//
//        //Simulacion de demora
//        Random random = new Random();
//        Integer delete = random.nextInt(500);
//        Thread.sleep(delete);
//
//        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle de TiempoTranscurridoInterceptor");
        long finshTime = System.currentTimeMillis();
        long startTimeRequest = (long) request.getAttribute("startTime");
        logger.info("Tiempo transcurrido: " + (finshTime - startTimeRequest));
    }
}
