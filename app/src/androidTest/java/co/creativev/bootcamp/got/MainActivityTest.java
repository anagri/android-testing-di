package co.creativev.bootcamp.got;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import co.creativev.bootcamp.got.deps.DaggerGoTAppDeps;
import co.creativev.bootcamp.got.deps.TestEnvironment;
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
        onData(anything())
                .inAdapterView(withId(R.id.list))
                .atPosition(0)
                .check(matches(withText("Load More")))
                .perform(click());
        onData(anything())
                .inAdapterView(withId(R.id.list))
                .atPosition(0)
                .onChildView(withId(R.id.text_character_name))
                .check(matches(withText("Arya Stark")));
    }
}