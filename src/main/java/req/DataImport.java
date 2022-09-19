package req;

import res.ValidatableResponse;

public class DataImport extends Base {


    //Пост запрос для импорта данных
    public ValidatableResponse postAuthDataImportZup(Integer value1, Integer value2, String file){
        return PostIntIntFile("data-import/zup", "normalizationMode", value1, "cachingMode", value2, file);
    }
    public ValidatableResponse postAuthDataImportZup(){
        return PostIntIntFile("data-import/zup", "normalizationMode", 0, "cachingMode", 0, "xw_1204841.png");
    }

    //гет запрос для просмотра прогресса импорта
    public ValidatableResponse getAuthDataImportZupProgress(String id){
        return GetNoParams("data-import/zup-prosses/"+id);
    }

    //загрузка основного фото родителю
    public ValidatableResponse postAuthDataImportMainPhotoAdd(){
        return PostStrIntFile("data-import/main-photo/add", "entityId", "f04ce32c-70d9-4a14-a6cf-f7739aef186d", "parentEntityType", 0, "xw_1204841.png");
    }

    public ValidatableResponse postAuthDataImportMainPhotoAdd(String id, Integer type, String file){
        return PostStrIntFile("data-import/main-photo/add", "entityId", id, "parentEntityType", type, file);
    }

    public ValidatableResponse postAuthDataImportMainPhotosUpdate(Integer type, String file){
        return PostIntFile("data-import/main-photos/update", "parentEntityType", type, file);
    }

    //скачивание документа по id
    public ValidatableResponse getAuthDataImportDocumentsDownloadId(String id){
        return GetNoParams("data-import/documents/download/"+id);
    }
    public ValidatableResponse postAuthDataImportFileIdRemove(String id){
        return PostNoParams("data-import/file/"+id+"/remove", "");
    }

}
