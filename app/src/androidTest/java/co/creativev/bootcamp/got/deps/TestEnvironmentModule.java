package co.creativev.bootcamp.got.deps;

public class TestEnvironmentModule extends EnvironmentModule {
    @Override
    public AppEnvironment providesAppEnvironment() {
        return new TestEnvironment();
    }
}
