package req;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import res.ValidatableResponse;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class Base {

    String baseUri = "https://sw-test.pltco.ru/api/v1/";

    //создание спецификации для запроса без параметров с дефолтным contentType
    public RequestSpecification RequestSpecNoParams() {

        RequestSpecification spec = given().
                baseUri(baseUri).
                contentType(JSON);
        if (!Auth.cookies.equals("")) {
            spec.cookie("PersonsSweepnetCookie", Auth.cookies);
        }
        return spec;
    }

    //создание спецификации для запроса без параметров с выбором contentType
    private RequestSpecification RequestSpecNoParams(String contentType) {
        return given().
                baseUri(baseUri).
                contentType(contentType);

    }

    //создание спецификации для запроса, которому нужны куки
    private RequestSpecification RequestSpec(String cookie) {
        return given().
                baseUri(baseUri).
                contentType(JSON).
                header("Set-Cookie", cookie).
                log().all();
    }
    //создание спецификации для запроса с параметром string
    private RequestSpecification RequestSpecStr(String path, String paramName, String value) {
        return given().
                baseUri(baseUri).
                basePath(path).
                contentType(JSON).
                queryParam(paramName, value).
                log().all();
    }
    //создание спецификации для запроса с параметрами int int
    private RequestSpecification RequestSpecIntInt(String paramName1, Integer value1, String paramName2, Integer value2) {
        return given().
                baseUri(baseUri).
                header("Content-Type", "multipart/json").
                queryParam(paramName1, value1).
                queryParam(paramName2, value2).
                log().all();
    }
    //создание спецификации для запроса с параметрами string string
    private RequestSpecification RequestSpecStrStr(String path, String paramName1, String value1, String paramName2, String value2) {
        return given().
                baseUri(baseUri).
                basePath(path).
                contentType(JSON).
                queryParam(paramName1, value1).
                queryParam(paramName2, value2).
                log().all();
    }
    //создание спецификации для запроса с параметрами string int
    private RequestSpecification RequestSpecStrInt(String paramName1, String value1, String paramName2, Integer value2) {
        return given().
                baseUri(baseUri).
                queryParam(paramName1, value1).
                queryParam(paramName2, value2).
                log().all();
    }

    //спецификация для запроса с телом
    private RequestSpecification RequestSpecNoParams(Object body, String contentType) {
        return RequestSpecNoParams(contentType).
                body(body);
    }

    private RequestSpecification RequestSpecInt(String path, String paramName, Integer value) {
        return RequestSpecInt(path, paramName, value);
    }

    private RequestSpecification RequestSpecStr(String path, String paramName, String value, Object body) {
        return RequestSpecStr(path, paramName, value).
                body(body);
    }


    private RequestSpecification RequestSpecStr(String path, String paramName1, String value1,String paramName2, String value2, Object body) {
        return RequestSpecStrStr(path, paramName1, value1, paramName2, value2).
                body(body);
    }


    public ValidatableResponse GetNoParams(String path) {
        Response response = RequestSpecNoParams().when().
                get(path);
        response.then().log().all();
        return new ValidatableResponse(response);
    }

    //создание пост запросов без параметров, если тело пустое, отправляем пустую строку
    public ValidatableResponse PostNoParams(String path, JSONObject body) {
        Response response = (Response) RequestSpecNoParams().body(body).when().
                post(path);
        response.then().log().all();
        return new ValidatableResponse(response);
    }
    public ValidatableResponse PostNoParams(String path, String body) {
        Response response = (Response) RequestSpecNoParams().body(body).when().
                post(path);
        response.then().log().all();
        return new ValidatableResponse(response);
    }
    //создание пост запросов с параметрами, если тело пустое, отправляем пустую строку String path, String paramName1, Integer value1, String paramName2, Integer value2
    public ValidatableResponse PostIntFile(String path, String paramName1, Integer value1, String file) {
        Response response = (Response) RequestSpecInt(path,paramName1, value1).multiPart("files", new File(file), "multipart/form-data").when().
                post(path);
        response.then().log().all();
        return new ValidatableResponse(response);
    }
    public ValidatableResponse PostIntIntFile(String path, String paramName1, Integer value1, String paramName2, Integer value2,String file) {
        Response response = (Response) RequestSpecIntInt(paramName1, value1, paramName2, value2).multiPart("files", new File(file), "multipart/form-data").when().
                post(path);
        response.then().log().all();
        return new ValidatableResponse(response);
    }
    public ValidatableResponse PostStrIntFile(String path, String paramName1, String value1, String paramName2, Integer value2,String file) {
        Response response = (Response) RequestSpecStrInt(paramName1, value1, paramName2, value2).multiPart("files", new File(file), "multipart/form-data").when().
                post(path);
        response.then().log().all();
        return new ValidatableResponse(response);
    }

//    public ValidatableResponse PostFile(String path, String files[]) {
//        Response response = (Response) RequestSpecStrInt(paramName1, value1, paramName2, value2)
//                .multiPart("files", new File(files), "multipart/form-data")
//                .when()
//                .post(path);
//        response.then().log().all();
//        return new ValidatableResponse(response);
//    }





}
