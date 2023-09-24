package com.soapcoursemanagement.soap.exceptions;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://soapcoursemanagement.com/courses}001_COURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 351817010175339L;
    public CourseNotFoundException(String message) {
        super(message);
    }
}
