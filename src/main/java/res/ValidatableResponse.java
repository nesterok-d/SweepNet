package res;

import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.testng.Assert;
import io.restassured.response.Response;
import org.testng.asserts.Assertion;

import java.util.Map;


public class ValidatableResponse {

    private Response response;

    //метод создающий объект ответа
    @SneakyThrows
    public ValidatableResponse(Response response) {
        this.response = response;
    }

    //метод сравнения статус кода ответа
    public ValidatableResponse checkStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
        return this;
    }

    public String getCookies() {
        Map<String, String> cookies = response.cookies();
        return cookies.get("PersonsSweepnetCookie");
    }

    //метод сравнения json по строчно
    public ValidatableResponse checkJsonValue(String stringPath, String value) {
        String answer = response.jsonPath().get(stringPath).toString();
        Assert.assertEquals(answer,value, "Значение элемента "+stringPath+" = " + answer + " не совпадает с " + value);
        return this;
    }
    //метод получения json по строчно
    public String getJsonValue(String stringPath) {
        return response.jsonPath().get(stringPath).toString();
    }
    //метод сравнения объекта json
    public ValidatableResponse checkJsonValue(String stringPath, Object json) {
        Assert.assertEquals(response.jsonPath().get(stringPath),json,"Значение элемента "+stringPath+" не совпадает с " + json.toString());
        return this;
    }

    //проверка, что строки нет в JSON
    public ValidatableResponse checkJsonNullValue(String stringPath) {
        response.then().body(stringPath, Matchers.nullValue());
        return this;
    }
    //проверка, что строки нет в JSON
    public ValidatableResponse checkJsonNotNullValue(String stringPath) {
        response.then().body(stringPath, Matchers.notNullValue());
        return this;
    }
}
