package lakmalz.git.sampleapp.services;

import android.app.usage.ConfigurationStats;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import lakmalz.git.sampleapp.utilities.Constant;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lakmal Weerasekara on 1/4/17.
 */

public class ServiceGenerator {

    public static <S> S createService(Class <S> serviceClass){

        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC).create();
        OkHttpClient mOkHttpClient = new OkHttpClient();

        Retrofit builder = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(mOkHttpClient.newBuilder().connectTimeout(1, TimeUnit.MINUTES).readTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(1, TimeUnit.MINUTES).build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return builder.create(serviceClass);

    }

}
