package com.cst2335.verm0121;


    import static androidx.test.espresso.Espresso.onView;
    import static androidx.test.espresso.action.ViewActions.click;
    import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
    import static androidx.test.espresso.action.ViewActions.replaceText;
    import static androidx.test.espresso.assertion.ViewAssertions.matches;
    import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
    import static androidx.test.espresso.matcher.ViewMatchers.withId;
    import static androidx.test.espresso.matcher.ViewMatchers.withParent;
    import static androidx.test.espresso.matcher.ViewMatchers.withText;
    import static org.hamcrest.Matchers.allOf;

    import android.view.View;
    import android.view.ViewGroup;
    import android.view.ViewParent;
    import android.widget.Toast;

    import androidx.test.espresso.ViewInteraction;
    import androidx.test.ext.junit.rules.ActivityScenarioRule;
    import androidx.test.ext.junit.runners.AndroidJUnit4;
    import androidx.test.filters.LargeTest;

    import org.hamcrest.Description;
    import org.hamcrest.Matcher;
    import org.hamcrest.TypeSafeMatcher;
    import org.junit.Rule;
    import org.junit.Test;
    import org.junit.runner.RunWith;

/**
 * This class contains JUnit tests to validate the password
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html

        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPersonName));

        appCompatEditText.perform(replaceText("12345"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass")));
    }
    @Test
    public void complex() {

        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPersonName));
        appCompatEditText.perform(replaceText("Verm@0121"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You can go on")));
    }
    /**
     * Tests the password input that does not contain an uppercase letter
     */
    @Test
    public void testFindMissingUpperCaseTest() {

        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPersonName));
        appCompatEditText.perform(replaceText("verm@0121"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass")));
    }

    /**
     * Tests the password input that does not contain a lowercase letter
     */
    @Test
    public void testFindMissingLowerCaseTest() {

        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPersonName));
        appCompatEditText.perform(replaceText("VERM@0121"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass")));
    }


    /**
     * Tests the password input that does not have required length
     */
    @Test
    public void passwordLengthTest() {

        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPersonName));
        appCompatEditText.perform(replaceText("ve@"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass")));
    }
    /**
     * Tests the password input that does not contain a special character
     */
    @Test
    public void testFindMissingSpecialCharacterTest() {

        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPersonName));
        appCompatEditText.perform(replaceText("verm0121"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass")));
    }
    /**
     * Tests the password input that does not contain a number
     */
    @Test
    public void MissingNumbersTest() {

        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPersonName));
        appCompatEditText.perform(replaceText("verm@"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass")));
    }

}
