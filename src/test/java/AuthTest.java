import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.testng.annotations.Test;
import req.Auth;
import res.ValidatableResponse;

public class AuthTest{
//sw-test.pltco.ru
    //не валидные данные
    @Test
    public void postAuthLoginInvalid(){
        Auth res = new Auth();
        JSONObject authData = new JSONObject();
        authData.put("email", "123456");
        authData.put("password", "123456");
        res.postAuthLogin(authData)
                .checkStatusCode(200)
                .checkJsonValue("isError", true)
                .checkJsonValue("statusCode", 401)
                .checkJsonValue("message", "Неверное имя пользователя или пароль")
                .checkJsonNullValue("data.id");
    }

    //валидные данные
    @Test
    public void postAuthLoginValid(){
        Auth res = new Auth();
        JSONObject authData = new JSONObject();
        authData.put("email", "demo");
        authData.put("password", "demo");
        res.postAuthLogin(authData)
                .checkStatusCode(200)
                .checkJsonValue("isError", false)
                .checkJsonValue("statusCode", 200)
                .checkJsonValue("message", "")
                .checkJsonNotNullValue("data.id")
                .checkJsonValue("data.email", "demo");
    }
    @Test
    public void getAuthUserIdTest(){
        Auth res = new Auth();
        JSONObject authData = new JSONObject();
        authData.put("email", "demo");
        authData.put("password", "demo");
        res.postAuthLogin(authData);
        res.getAuthUserId("f04ce32c-70d9-4a14-a6cf-f7739aef186d")
                .checkJsonValue("data.id", "f04ce32c-70d9-4a14-a6cf-f7739aef186d");
    }
    @Test
    public void postAuthLogoutTest(){
        Auth res = new Auth();
        JSONObject authData = new JSONObject();
        authData.put("email", "demo");
        authData.put("password", "demo");
        res.postAuthLogin(authData);
        res.postAuthLogout()
                .checkStatusCode(200);
    }
    @Test
    public void getAuthRolesTest(){
        Auth res = new Auth();
        JSONObject authData = new JSONObject();
        authData.put("email", "demo");
        authData.put("password", "demo");
        res.postAuthLogin(authData);
        res.getAuthRoles()
                .checkStatusCode(200);
    }



//    @Test
//    public void postAuthRegisterTest(){
//        Auth res = new Auth();
//        JSONObject authData = new JSONObject();
//        authData.put("email", "123456");
//        authData.put("password", "123456");
//        res.postAuthRegister(authData)
//                .checkStatusCode(200);
//    }
}
