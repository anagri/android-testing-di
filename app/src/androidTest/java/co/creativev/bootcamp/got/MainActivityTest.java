package co.creativev.bootcamp.got;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import co.creativev.bootcamp.got.deps.DaggerGoTAppDeps;
import co.creativev.bootcamp.got.deps.TestEnvironmentModule;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<MainActivity>(MainActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            GoTApplication goTApplication = (GoTApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
            goTApplication.rebuildDeps(DaggerGoTAppDeps.builder().environmentModule(new TestEnvironmentModule()));
        }
    };

    @Test
    public void testOnLoadLoadsMoreItem() throws Exception {
        String responseBody = readAsset("got_characters_index.json");
        MockWebServer mockWebServer = new MockWebServer();
        MockResponse response = new MockResponse().setBody(responseBody);
        mockWebServer.enqueue(response);
        mockWebServer.start(4567);

        try {
            onData(anything())
                    .inAdapterView(withId(R.id.list))
                    .atPosition(0)
                    .check(matches(withText("Load More")))
                    .perform(click());
            onData(anything())
                    .inAdapterView(withId(R.id.list))
                    .atPosition(0)
                    .onChildView(withId(R.id.text_character_name))
                    .check(matches(withText("Test User 1")));
        } finally {
            mockWebServer.shutdown();
        }
    }

    private String readAsset(String file) throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStream json = InstrumentationRegistry.getContext().getAssets().open(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
        String str;
        while ((str = in.readLine()) != null) {
            builder.append(str);
        }
        in.close();
        return builder.toString();
    }
}