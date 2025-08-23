package altamirano.hernandez.app1_springboot_2025.configuration;

import altamirano.hernandez.app1_springboot_2025.interceptors.HorarioInterceptor;
import altamirano.hernandez.app1_springboot_2025.interceptors.InterceptorPrueba;
import altamirano.hernandez.app1_springboot_2025.interceptors.TiempoTranscurridoInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@org.springframework.context.annotation.Configuration
public class Configuration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    //Configuracion de clases interceptoras

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //Inteceptor prueba
        registry.addInterceptor(new InterceptorPrueba())
                .addPathPatterns("/formController/**");

        //Interceptor tiempo transcurrido
        registry.addInterceptor(new TiempoTranscurridoInterceptor())
                .addPathPatterns("/formController/**");

        //Interceptor de horarios
        registry.addInterceptor(new HorarioInterceptor())
                .addPathPatterns("/horarioController/**");
    }
}
