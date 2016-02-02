package co.creativev.bootcamp.got.deps;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import co.creativev.bootcamp.got.GoTService;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

@Module
public class ServiceModule {
    public static final long HTTP_TIMEOUT = 10 * 1000;
    public static final long CACHE_SIZE = 1024 * 1024 * 10l;  // 10 MB HTTP Cache

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public GoTService providesGoTService(Context context, AppEnvironment appEnvironment) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.setWriteTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
        File cacheDir = new File(context.getCacheDir(), "http-cache");
        Cache cache = new Cache(cacheDir, CACHE_SIZE);
        okHttpClient.setCache(cache);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setEndpoint(appEnvironment.getServerEndpoint())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();
        return restAdapter.create(GoTService.class);
    }
}
