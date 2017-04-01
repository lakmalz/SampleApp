package lakmalz.git.sampleapp.services.sync;

import lakmalz.git.sampleapp.services.RestAPIInterface;
import lakmalz.git.sampleapp.services.ServiceGenerator;
import lakmalz.git.sampleapp.services.requestmodels.GetTemplateRequest;
import lakmalz.git.sampleapp.services.responsemodels.GetTemplateResponse;
import lakmalz.git.sampleapp.utilities.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lakmal Weerasekara on 1/4/17.
 */

public class TemplateSync {
    public TemplateSync() {
    }

    public void getTemplateList(GetTemplateRequest request, final GetTemplateCallback callback) {
        ServiceGenerator.createService(RestAPIInterface.class).getTemplateList(request).enqueue(new Callback<GetTemplateResponse>() {
            @Override
            public void onResponse(Call<GetTemplateResponse> call, Response<GetTemplateResponse> response) {

                if (response.code() == Constant.CODE_200) {
                    callback.getTemplateListSuccess(response.body());
                } else {
                    callback.getTemplateListFailed("Request is failed.");
                }
            }

            @Override
            public void onFailure(Call<GetTemplateResponse> call, Throwable t) {
                callback.getTemplateListFailed("Request is failed.");
            }
        });
    }

    public interface GetTemplateCallback {
        void getTemplateListSuccess(GetTemplateResponse response);

        void getTemplateListFailed(String message);
    }

}
