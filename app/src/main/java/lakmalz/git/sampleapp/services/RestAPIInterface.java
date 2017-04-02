package lakmalz.git.sampleapp.services;

import java.util.Map;

import lakmalz.git.sampleapp.services.requestmodels.GetTemplateRequest;
import lakmalz.git.sampleapp.services.responsemodels.GetTemplateResponse;
import lakmalz.git.sampleapp.services.responsemodels.UploadPostResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by Lakmal Weerasekara on 1/4/17.
 */

public interface RestAPIInterface {

    @POST("api/v1/listTemplates")
    Call<GetTemplateResponse> getTemplateList(@Body GetTemplateRequest request);


    @Multipart
    @POST("api/v1/uploadPost")
    Call<UploadPostResponse> uploadPost(@PartMap() Map<String, RequestBody> partMap, @Part MultipartBody.Part file);

}
