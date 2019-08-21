package com.lee.vocabulary.core.config;

import freemarker.ext.jsp.TaglibFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class VocabularyMvcConfig implements WebMvcConfigurer {

    @Autowired
    FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void freeMarkerConfigurer() {
        List<String> tlds = new ArrayList<>();
        tlds.add("/static/tags/security.tld");
        TaglibFactory taglibFactory = freeMarkerConfigurer.getTaglibFactory();
        taglibFactory.setClasspathTlds(tlds);
        if (taglibFactory.getObjectWrapper() == null) {
            taglibFactory.setObjectWrapper(freeMarkerConfigurer.getConfiguration().getObjectWrapper());
        }
    }

}
