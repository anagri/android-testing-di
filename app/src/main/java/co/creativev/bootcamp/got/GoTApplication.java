package co.creativev.bootcamp.got;

import android.app.Application;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import co.creativev.bootcamp.got.deps.AppModule;
import co.creativev.bootcamp.got.deps.DaggerGoTAppDeps;
import co.creativev.bootcamp.got.deps.GoTAppDeps;

public class GoTApplication extends Application {
    public static final String LOG_TAG = "APP_LOG";
    private GoTAppDeps goTAppDeps;

    @Override
    public void onCreate() {
        super.onCreate();
        Picasso picasso = new Picasso.Builder(this)
                .indicatorsEnabled(BuildConfig.DEBUG)
                .loggingEnabled(BuildConfig.DEBUG)
                .memoryCache(new LruCache(1024 * 1024 * 30)) // 30 MB
                .build();
        Picasso.setSingletonInstance(picasso);
        goTAppDeps = DaggerGoTAppDeps.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public void rebuildDeps(DaggerGoTAppDeps.Builder builder) {
        goTAppDeps = builder.appModule(new AppModule(this)).build();
    }

    public GoTAppDeps getGoTAppDeps() {
        return goTAppDeps;
    }
}
