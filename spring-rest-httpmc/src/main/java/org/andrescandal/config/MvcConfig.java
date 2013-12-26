package org.andrescandal.config;

import java.util.List;

import org.andrescandal.domain.Student;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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

    	//adds Jackson converter with Custom Object Mapper.
    	MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    	jsonMessageConverter.setObjectMapper(mapper);
    	converters.add(jsonMessageConverter);
    	
    	//adds XML converter using XStreamMarshaller
    	XStreamMarshaller marshaller = new XStreamMarshaller();
    	marshaller.setAnnotatedClasses(Student.class);
    	
    	MarshallingHttpMessageConverter marshallingConverter = new MarshallingHttpMessageConverter(marshaller);
    	converters.add(marshallingConverter);
    	
    }

}