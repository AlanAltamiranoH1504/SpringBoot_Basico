package altamirano.hernandez.app1_springboot_2025.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HorarioInterceptor implements HandlerInterceptor {
    protected boolean bandera = false;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (bandera) {
            return true;
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.setContentType("application/json");

            String json = "{\"status\": \"false\", \"message\": \"El horario de atencion se encuentra cerrado\"}";
            response.getWriter().write(json);
            response.getWriter().flush();
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
