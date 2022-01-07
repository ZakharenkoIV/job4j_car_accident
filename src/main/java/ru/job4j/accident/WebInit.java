package ru.job4j.accident;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.job4j.accident.config.WebConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebInit implements WebApplicationInitializer {
    public static final AnnotationConfigWebApplicationContext APPLICATION_CONTEXT
            = new AnnotationConfigWebApplicationContext();

    public void onStartup(ServletContext servletCxt) {
        APPLICATION_CONTEXT.register(WebConfig.class);
        APPLICATION_CONTEXT.scan("ru.job4j.accident");
        APPLICATION_CONTEXT.refresh();
        DispatcherServlet servlet = new DispatcherServlet(APPLICATION_CONTEXT);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}