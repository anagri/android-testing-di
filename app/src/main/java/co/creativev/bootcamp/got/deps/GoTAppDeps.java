package co.creativev.bootcamp.got.deps;

import javax.inject.Singleton;

import co.creativev.bootcamp.got.MainActivity;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, EnvironmentModule.class, ServiceModule.class})
public interface GoTAppDeps {
    public void inject(MainActivity mainActivity);
}
