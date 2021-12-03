package com.kh.khculture.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final String uploadImagesPath;

  public WebConfig(@Value("${custom.path.upload-images}") String uploadImagesPath) {
    this.uploadImagesPath = uploadImagesPath;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    registry.addResourceHandler("swagger-ui.html")
//      .addResourceLocations("classpath:/META-INF/resources/");

    List<String> imageFolders = Arrays.asList("upload", "instructor");
    for(String imageFolder : imageFolders) {
      registry.addResourceHandler("/images/" +imageFolder +"/**")
        .addResourceLocations("file:///" + uploadImagesPath + imageFolder +"/")
        .setCachePeriod(3600)
        .resourceChain(true)
        .addResolver(new PathResourceResolver());
    }
  }
}
