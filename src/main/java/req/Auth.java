package req;

import net.minidev.json.JSONObject;
import res.ValidatableResponse;

public class Auth extends Base{
    public static String cookies = "";



    //Пост запрос для авторизации
    public ValidatableResponse postAuthLogin(JSONObject authData){
        ValidatableResponse response = PostNoParams("/auth/login", authData.toString());
        cookies = response.getCookies();
        return response;
    }

    //Пост запрос для регистрации
    public ValidatableResponse postAuthRegister(JSONObject registerData){
        ValidatableResponse response = PostNoParams("/auth/register", registerData.toString());
        cookies = response.getCookies();
        return response;
    }

    //Гет запрос для получения данных пользователя по ID
    public ValidatableResponse getAuthUserId(String id){
        return GetNoParams("auth/user/" + id);

    }

    //Пост запрос для завершения сессии пользователя
    public ValidatableResponse postAuthLogout(){
        return PostNoParams("auth/logout/", "");
    }

    //Гет запрос для получения ролей авторизованного пользователя
    public ValidatableResponse getAuthRoles(){
        return GetNoParams("auth/roles");
    }

    //Пост запрос для получения списка пользователей
    public ValidatableResponse postAuthUsers(){
        return PostNoParams("auth/users/", "");
    }

    //Пост запрос для получения списка пользователей
    public ValidatableResponse postAuthUsersIdUpdate(String id, Object body){
        return PostNoParams("auth/users/"+id+"/update", body);
    }

    public ValidatableResponse postAuthUsersDelete(Object body){
        return PostNoParams("auth/users/delete", body);
    }

    public ValidatableResponse postAuthUsersLockout(Object body){
        return PostNoParams("auth/users/lockout", body);
    }

    //Гет запрос для верификации токена
    public ValidatableResponse getAuthValidateAntiforgeryRequest(){
        return GetNoParams("auth/validate-antiforgery-request");
    }


}
