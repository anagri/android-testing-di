package co.creativev.bootcamp.got.deps;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EnvironmentModule {
    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public AppEnvironment providesAppEnvironment() {
        return new AppEnvironment();
    }
}
