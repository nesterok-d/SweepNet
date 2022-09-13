import net.minidev.json.JSONObject;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import req.Base;

import static io.restassured.RestAssured.given;

public class BaseTest {

    public JSONObject authDataAdmin(){
        JSONObject authData = new JSONObject();
        authData.put("email", "test@pltco.ru");
        authData.put("password", "1234567890");
        return authData;
    }
    public JSONObject authDataUser1(){
        JSONObject authData = new JSONObject();
        authData.put("email", "test@pltco.ru");
        authData.put("password", "123456");
        return authData;
    }
    public JSONObject authDataUser2(){
        JSONObject authData = new JSONObject();
        authData.put("email", "test@pltco.ru");
        authData.put("password", "123456");
        return authData;
    }
    public JSONObject authDataInvalid(){
        JSONObject authData = new JSONObject();
        authData.put("email", "1111");
        authData.put("password", "1111");
        return authData;
    }
    public JSONObject authDataUpdate(String id){
        JSONObject updateData = new JSONObject();
        JSONArray userRole = new JSONArray();
        userRole.add("admin");
        userRole.add("user");
        updateData.put("id", id);
        updateData.put("email", "testReg@pltco.ru");
        updateData.put("password", "1234567890");
        updateData.put("lastName", "Тест");
        updateData.put("roles", userRole);
        return updateData;
    }
    public JSONObject deleteList(String id){
        JSONArray usersId = new JSONArray();
        usersId.add(id);
        JSONObject dataDel = new JSONObject();
        dataDel.put("userIdList", usersId);
        return dataDel;
    }
}
