package com.soapcoursemanagement.soap;

import com.soapcoursemanagement.courses.CourseDetails;
import com.soapcoursemanagement.courses.GetCourseDetailsRequest;
import com.soapcoursemanagement.courses.GetCourseDetailsResponse;
import com.soapcoursemanagement.soap.bean.Course;
import com.soapcoursemanagement.soap.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CourseDetailsEndpoint {

    //Method
    //Input - GetCourseDetailsRequest request
    //Output - GetCourseDetailsResponse response
    //http://soapcoursemanagement.com/courses
    //GetCourseDetailsRequest

    @Autowired
    CourseDetailsService service;
    @PayloadRoot(namespace = "http://soapcoursemanagement.com/courses", localPart = "getCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();

        Course course = service.findById(request.getId());

        return mapCourse(course, response);
    }

    private static GetCourseDetailsResponse mapCourse(Course course, GetCourseDetailsResponse response) {
        CourseDetails courseDetails = new CourseDetails();

        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        response.setCourseDetails(courseDetails);

        return response;
    }

}
