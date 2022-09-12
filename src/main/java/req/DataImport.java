package req;

import net.minidev.json.JSONObject;
import res.ValidatableResponse;

public class DataImport extends Base{


    //Пост запрос для импорта данных
    public ValidatableResponse postAuthDataImportZup(Integer value1, Integer value2, String file){
        return PostIntIntFile("data-import/zup", "normalizationMode", value1, "cachingMode", value2, file);
    }
    public ValidatableResponse postAuthDataImportZup(){
        return PostIntIntFile("data-import/zup", "normalizationMode", 0, "cachingMode", 0, "xw_1204841.png");
    }
    public ValidatableResponse postAuthDataImportMainPhotoAdd(){
        return PostStrIntFile("data-import/main-photo/add", "entityId", "f04ce32c-70d9-4a14-a6cf-f7739aef186d", "parentEntityType", 0, "xw_1204841.png");
    }



}
