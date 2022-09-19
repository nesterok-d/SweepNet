package req;

import res.ValidatableResponse;

public class DataTableSettingsProfile extends Base{

    public ValidatableResponse postDataTableSettingsProfile(String type){
        return PostNoParams("profile/data-table-settings/"+ type, "");
    }

    public ValidatableResponse getDataTableSettingsProfile(String type){
        return GetNoParams("profile/data-table-settings/"+ type);
    }
}
