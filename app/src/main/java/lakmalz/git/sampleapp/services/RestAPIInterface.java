package lakmalz.git.sampleapp.services;

import lakmalz.git.sampleapp.services.requestmodels.GetTemplateRequest;
import lakmalz.git.sampleapp.services.responsemodels.GetTemplateResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Lakmal Weerasekara on 1/4/17.
 */

public interface RestAPIInterface {

    @POST("api/v1/listTemplates")
    Call<GetTemplateResponse> getTemplateList(@Body GetTemplateRequest request);
}
