package co.creativev.bootcamp.got.deps;

public class TestEnvironment extends AppEnvironment {
    @Override
    public String getServerEndpoint() {
        return "http://localhost:4567/";
    }
}
