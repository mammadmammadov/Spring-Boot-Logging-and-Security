package az.edu.ada.wm2.Assignment2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * This method adds view controllers for specified URL paths.
     *
     * @param registry The registry for adding view controllers.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/static").setViewName("static");

        registry.addViewController("/login").setViewName("login");
    }
}
