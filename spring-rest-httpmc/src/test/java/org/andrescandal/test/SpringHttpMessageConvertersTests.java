package org.andrescandal.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.andrescandal.domain.Student;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.client.RestTemplate;

/**
 * Integration Test class. Tests methods hits the server's rest services.
 * @author ACandal
 *
 */
public class SpringHttpMessageConvertersTests {

	private static String BASE_URI = "http://localhost:8080/spring-rest-httpmc/";
	/**
	 * Without specifying Accept Header, uses the default response from the
	 * server (in this case json)
	 */
	@Test
	public void testGetStudent() {

		String URI = BASE_URI + "student/{id}";

		RestTemplate restTemplate = new RestTemplate();
		Student student = restTemplate.getForObject(URI, Student.class, "1");

		Assert.assertEquals(new Integer(1), student.getId());

	}

	/**
	 * Specifying Accept Header with application/xml for getting the xml response from the server.
	 */
	@Test
	public void testGetStudentAcceptXML() {

		String URI =BASE_URI + "student/{id}";

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(getMessageConverters());

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<Student> response = restTemplate.exchange(URI, HttpMethod.GET,
				entity, Student.class, "1");
		Student student = response.getBody();
		
		Assert.assertEquals(new Integer(1), student.getId());

	}
	
	/**
	 * Specifying Accept Header with application/xml for getting the xml response from the server.
	 */
	@Test
	public void testPUTStudentXML() {

		String URI = BASE_URI + "student/{id}";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(getMessageConverters());

		Student student = new Student(4, "andres", "email@host.com");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
		headers.setContentType((MediaType.APPLICATION_XML));
		HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);
		
		ResponseEntity<Student> response = restTemplate.exchange(URI, HttpMethod.PUT,
				entity, Student.class, student.getId());
		Student studentResponse = response.getBody();
		
		Assert.assertEquals(student.getId(), studentResponse.getId());

	}


	/**
	 * Configures Message Converters. 
	 * @return
	 */
	private List<HttpMessageConverter<?>> getMessageConverters() {
		
		
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		//adds XML converter using XStreamMarshaller
    	XStreamMarshaller marshaller = new XStreamMarshaller();
    	marshaller.setAnnotatedClasses(Student.class);
    	
    	MarshallingHttpMessageConverter marshallingConverter = new MarshallingHttpMessageConverter(marshaller);
    	converters.add(marshallingConverter);
    	
    	return converters;
	}

}
