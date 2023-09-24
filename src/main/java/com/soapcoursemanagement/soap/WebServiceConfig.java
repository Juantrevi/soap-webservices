package com.soapcoursemanagement.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;

//Spring Configuration
@Configuration
//Enable Spring Web Services
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {
    //MessageDispatcherServlet -> Frame controller, any request from client will be handled by this servlet
        //ApplicationContext -> Pass the request to Spring WS
    //URL -> /ws/*
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
    }

    //Web Service Definition Language
    // /ws/courses.wsdl
        //CoursePort
        //Namespace - http://soapcoursemanagement.com/courses
    //course-details.xsd
    @Bean(name = "courses")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        //PortType - CoursePort
        definition.setPortTypeName("CoursePort");
        //Namespace - http://soapcoursemanagement.com/courses
        definition.setTargetNamespace("http://soapcoursemanagement.com/courses");
        // /ws
        definition.setLocationUri("/ws");
        //Schema
        definition.setSchema(coursesSchema);
        return definition;
    }

    @Bean
    XsdSchema coursesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
    }

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() {
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
        securityInterceptor.setSecurementActions("UsernameToken");
        securityInterceptor.setSecurementUsername("user");
        securityInterceptor.setSecurementPassword("password");

        return securityInterceptor;
    }


    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(securityInterceptor());
    }

}
