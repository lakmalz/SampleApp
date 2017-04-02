package lakmalz.git.sampleapp.services.sync;


import android.content.Context;

import java.util.HashMap;

import lakmalz.git.sampleapp.services.RestAPIInterface;
import lakmalz.git.sampleapp.services.ServiceGenerator;
import lakmalz.git.sampleapp.services.responsemodels.UploadPostResponse;
import lakmalz.git.sampleapp.utilities.Constant;
import lakmalz.git.sampleapp.utilities.NetworkAccess;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadSync {

    private Context mContext;

    public UploadSync(Context context) {
        mContext = context;
    }


    public void uploadPost(final UploadPostCallback callBack, HashMap<String, RequestBody> map, MultipartBody.Part body) {
        if (!NetworkAccess.isNetworkAvailable(mContext)) {
            callBack.onFailureUploadPost(null);
            return;
        }

        ServiceGenerator.createService(RestAPIInterface.class).uploadPost(map, body)
                .enqueue(new Callback<UploadPostResponse>() {
                    @Override
                    public void onResponse(Call<UploadPostResponse> call, Response<UploadPostResponse> response) {
                        if (response.code() == Constant.CODE_200) {
                            callBack.onSuccessUploadPost(response);
                        } else {
                            //callBack.onFailureUploadPost(ErrorHandler.getApiErrorMessage(response));
                        }
                    }

                    @Override
                    public void onFailure(Call<UploadPostResponse> call, Throwable t) {
                        //callBack.onFailureUploadPost(Constant.BAD_REQUEST);

                    }
                });
    }

    public interface UploadPostCallback {
        void onSuccessUploadPost(Response<UploadPostResponse> response);

        void onFailureUploadPost(String message);
    }

}
