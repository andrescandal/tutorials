package org.andrescandal.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@ComponentScan({ "org.andrescandal.controller" })
public class MvcConfig extends WebMvcConfigurerAdapter {

    public MvcConfig() {
        super();
    }
    
    @Override
    public void configureMessageConverters(
    		List<HttpMessageConverter<?>> converters) {

    	//adds Jackson converter
    	converters.add(new MappingJacksonHttpMessageConverter());
    	
    	//adds XML converter
    	converters.add(new MarshallingHttpMessageConverter(new XStreamMarshaller()));
    	
    }

}