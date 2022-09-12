import net.minidev.json.JSONObject;
import org.testng.annotations.Test;
import req.Auth;

public class AuthTest{
    @Test //не валидные данные
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
    @Test //валидные данные
    public void postAuthLoginValidAndLogoutTest(){
        Auth res = new Auth();
        JSONObject authData = new JSONObject();
        authData.put("email", "test@pltco.ru");
        authData.put("password", "1234567890");
        res.postAuthLogin(authData)
                .checkStatusCode(200)
                .checkJsonValue("isError", false)
                .checkJsonValue("statusCode", 200)
                .checkJsonValue("message", "")
                .checkJsonNotNullValue("data.id")
                .checkJsonValue("data.email", "test@pltco.ru");
        res.postAuthLogout()
                .checkStatusCode(200)
                .checkJsonValue("isError", false)
                .checkJsonValue("statusCode", 200)
                .checkJsonValue("message", "");
    }
    @Test //регистрация
    public void getAuthRegisterTest(){
        Auth res = new Auth();
        JSONObject authDataLog = new JSONObject();
        authDataLog.put("email", "test@pltco.ru");
        authDataLog.put("password", "1234567890");
        JSONObject authDataReg = new JSONObject();
        authDataReg.put("email", "testReg@pltco.ru");
        authDataReg.put("password", "1234567890");
        res.postAuthLogin(authDataLog);
        res.postAuthRegister(authDataReg)
                .checkStatusCode(200)
                .checkJsonValue("isError", true)
                .checkJsonValue("statusCode", 201);
        res.postAuthLogout();
        res.postAuthLogin(authDataLog);
        res.postAuthRegister(authDataReg)
                .checkStatusCode(200)
                .checkJsonValue("isError", true)
                .checkJsonValue("statusCode", 401)
                .checkJsonValue("message", "Username 'testReg@pltco.ru' is already taken.")
                .checkJsonNullValue("data.id");
        res.postAuthLogout();
        res.postAuthLogin(authDataReg);
        String id = res.postAuthLogin(authDataReg).getJsonValue("data.id");
        res.postAuthLogout();
        res.postAuthLogin(authDataLog);
        JSONObject authDataDel = new JSONObject();
        authDataLog.put("userIdList", "3fa85f64-5717-4562-b3fc-2c963f66afa6");
        authDataLog.put("password", "1234567890");


        res.postAuthUsersDelete(authDataDel)
                .checkStatusCode(200)
                .checkJsonValue("isError", false)
                .checkJsonValue("statusCode", 200)
                .checkJsonValue("message", "")
                .checkJsonNullValue("data.id");

    }


    @Test
    public void getAuthUserIdTest(){
        Auth res = new Auth();
        JSONObject authData = new JSONObject();
        authData.put("email", "test@pltco.ru");
        authData.put("password", "1234567890");
        res.postAuthLogin(authData);
        res.getAuthUserId("f04ce32c-70d9-4a14-a6cf-f7739aef186d")
                .checkJsonValue("data.id", "f04ce32c-70d9-4a14-a6cf-f7739aef186d");
    }

    @Test
    public void getAuthRolesTest(){
        Auth res = new Auth();
        JSONObject authData = new JSONObject();
        authData.put("email", "test@pltco.ru");
        authData.put("password", "1234567890");
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
