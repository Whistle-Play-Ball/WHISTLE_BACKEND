package company.board_project.config;
import com.amazonaws.HttpMethod;
import company.board_project.common.resolver.AuthenticatedUserResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry
                .addMapping("/**")
                .allowedOrigins("http://localhost:3000",
                        "https://dev.dovfpqk67sdce.amplifyapp.com/",
                        "http://localhost:5173",
                        "http://localhost:6379"
                )
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.HEAD.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PATCH.name(),
                        "GET","POST","PUT","PATCH","HEAD","DELETE","OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("*", "Authorization", "Refresh")
                .allowCredentials(true).maxAge(3000);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticatedUserResolver());
    }
}