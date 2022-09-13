import org.testng.annotations.Test;
import req.Auth;

public class AuthTest extends BaseTest{
    @Test //не валидные данные
    public void postAuthLoginInvalid(){
        Auth res = new Auth();

        res.postAuthLogin(authDataInvalid())
                .checkStatusCode(200)
                .checkJsonValue("isError", true)
                .checkJsonValue("statusCode", 401)
                .checkJsonValue("message", "Неверное имя пользователя или пароль")
                .checkJsonNullValue("data.id");
    }
    @Test //валидные данные
    public void postAuthLoginValidAndLogoutTest(){
        Auth res = new Auth();

        res.postAuthLogin(authDataAdmin())
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

        res.postAuthLogin(authDataAdmin());
        res.postAuthRegister(authDataUser1())
                .checkStatusCode(200)
                .checkJsonValue("isError", true)
                .checkJsonValue("statusCode", 201);
        res.postAuthRegister(authDataUser1())
                .checkStatusCode(200)
                .checkJsonValue("isError", true)
                .checkJsonValue("statusCode", 401)
                .checkJsonValue("message", "Username 'testReg@pltco.ru' is already taken.")
                .checkJsonNullValue("data.id");
        res.postAuthLogout();

        String id = res.postAuthLogin(authDataUser1()).getJsonValue("data.id");
        res.postAuthLogout();

        res.postAuthLogin(authDataAdmin());
        res.postAuthUsersDelete(deleteList(id))
                .checkStatusCode(200)
                .checkJsonValue("isError", false)
                .checkJsonValue("statusCode", 200)
                .checkJsonValue("message", "");
        res.postAuthLogout();

        res.postAuthLogin(authDataUser1())
                .checkStatusCode(200)
                .checkJsonValue("isError", true)
                .checkJsonValue("message", "Неверное имя пользователя или пароль");
    }


    @Test
    public void getAuthUserIdTest(){
        Auth res = new Auth();
        String id = res.postAuthLogin(authDataAdmin()).getJsonValue("data.id");
        res.getAuthUserId(id)
                .checkJsonValue("data.id", id);
    }

    @Test
    public void getAuthRolesTest(){
        Auth res = new Auth();

        res.postAuthLogin(authDataAdmin());
        res.postAuthRegister(authDataUser1());

        String id = res.postAuthLogin(authDataUser1()).getJsonValue("data.id");
        res.postAuthLogout();

        res.postAuthLogin(authDataAdmin());
        res.postAuthUsersIdUpdate(id, authDataUpdate(id)).checkStatusCode(200);
        res.postAuthLogout();

        res.postAuthLogin(authDataUser1()).checkStatusCode(200);
        res.getAuthRoles()
                .checkStatusCode(200)
                .checkJsonValue("data.roles", "[admin, user]");
        res.postAuthLogout();

        res.postAuthLogin(authDataAdmin());
        res.postAuthUsersDelete(deleteList(id))
                .checkStatusCode(200)
                .checkJsonValue("statusCode", 200);
        res.postAuthLogout();

    }
}
