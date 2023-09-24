package com.soapcoursemanagement.soap;

import com.soapcoursemanagement.courses.CourseDetails;
import com.soapcoursemanagement.courses.GetCourseDetailsRequest;
import com.soapcoursemanagement.courses.GetCourseDetailsResponse;
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
    @PayloadRoot(namespace = "http://soapcoursemanagement.com/courses", localPart = "getCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();

        CourseDetails courseDetails = new CourseDetails();

        courseDetails.setId(request.getId());
        courseDetails.setName("Microservices Course");
        courseDetails.setDescription("This is a wonderful course");

        response.setCourseDetails(courseDetails);

        return response;
    }

}
