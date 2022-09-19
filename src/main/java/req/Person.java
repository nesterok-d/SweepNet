package req;

import net.minidev.json.JSONObject;
import res.ValidatableResponse;

public class Person extends Base{


    public ValidatableResponse getPersonId(String id){
        ValidatableResponse response = GetNoParams("/person/"+id);
        return response;
    }
    public ValidatableResponse getPersonIdExportXLSX(String id){
        ValidatableResponse response = GetNoParams("/person/"+id+"/export/xlsx");
        return response;
    }

    public ValidatableResponse postPersonFileAdd(String id){
        //ValidatableResponse response = post("/person/"+id+"/export/xlsx");/api/v1/person/file/add
        return ;//response
    }
    public ValidatableResponse getPersonImportProgressPercent(String id){
        ValidatableResponse response = GetNoParams("/person/import-progress-percent/"+id);
        return response;
    }
    public ValidatableResponse getPersonImportProgressResult(String id){
        ValidatableResponse response = GetNoParams("/person/import-progress-result/"+id);
        return response;
    }

    public ValidatableResponse postPersonAdd(JSONObject body){
        ValidatableResponse response = PostNoParams("/person/add", body);
        return response;
    }
    public ValidatableResponse postPersonDelete(JSONObject body){
        ValidatableResponse response = PostNoParams("/person/delete", body);
        return response;
    }




    /api/v1/person/delete
/api/v1/person/update
    /api/v1/person/trigger-merge-all-data
    /api/v1/person/rate/update
}
