package com.framework.helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * REST API helper class for making HTTP requests
 */
public class RestHelper {

    public static Response get(RequestSpecification spec, String endpoint) {
        return RestAssured.given().spec(spec).get(endpoint);
    }

    public static Response post(RequestSpecification spec, String endpoint, String body) {
        return RestAssured.given().spec(spec).body(body).post(endpoint);
    }

    public static Response put(RequestSpecification spec, String endpoint, String body) {
        return RestAssured.given().spec(spec).body(body).put(endpoint);
    }

    public static Response delete(RequestSpecification spec, String endpoint) {
        return RestAssured.given().spec(spec).delete(endpoint);
    }

    public static Response postWithParams(RequestSpecification spec, String endpoint, String body, String paramKey, String paramValue) {
        return RestAssured.given().spec(spec).body(body).queryParam(paramKey, paramValue).post(endpoint);
    }
}

