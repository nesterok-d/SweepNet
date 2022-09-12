import net.minidev.json.JSONObject;
import org.testng.annotations.Test;
import req.DataImport;

public class DataImportTest {

    @Test
    public void postDataImportZupTest(){
        DataImport res = new DataImport();
        res.postAuthDataImportZup()
                .checkStatusCode(200);
    }
    @Test
    public void postAuthDataImportMainPhotoAddTest(){


        DataImport res = new DataImport();
        res.postAuthDataImportMainPhotoAdd()
                .checkStatusCode(200);
    }
}
