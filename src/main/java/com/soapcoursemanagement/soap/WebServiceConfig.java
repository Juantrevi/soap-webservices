package com.soapcoursemanagement.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Spring Configuration
@Configuration
//Enable Spring Web Services
@EnableWs
public class WebServiceConfig {
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
}
