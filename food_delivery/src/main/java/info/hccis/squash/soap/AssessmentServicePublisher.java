package info.hccis.squash.soap;

import javax.xml.ws.Endpoint;

public class AssessmentServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish(
          "http://localhost:8083/assessmentservice", 
           new AssessmentServiceImpl());

    }
}