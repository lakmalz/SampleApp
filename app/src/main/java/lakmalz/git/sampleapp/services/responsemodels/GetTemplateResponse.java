package lakmalz.git.sampleapp.services.responsemodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lakmalz.git.sampleapp.models.Template;

/**
 * Created by Lakmal Weerasekara on 1/4/17.
 */

public class GetTemplateResponse {
    @SerializedName("result")
    public List<Template> templateList;

    @SerializedName("error")
    public String error;

    @SerializedName("status")
    public String status;
}
