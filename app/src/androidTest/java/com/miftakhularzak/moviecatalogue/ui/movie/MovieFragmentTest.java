package com.miftakhularzak.moviecatalogue.ui.movie;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers.Visibility;
import androidx.test.rule.ActivityTestRule;

import com.miftakhularzak.moviecatalogue.R;
import com.miftakhularzak.moviecatalogue.testing.SingleFragmentActivity;
import com.miftakhularzak.moviecatalogue.utils.EspressoIdlingResource;
import com.miftakhularzak.moviecatalogue.utils.RecyclerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MovieFragmentTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private MovieFragment movieFragment = new MovieFragment();

    @Before
    public void setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityRule.getActivity().setFragment(movieFragment);
    }
    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie)).check(new RecyclerViewItemCountAssertion(20));
        onView(withId(R.id.pb_movie)).check(matches(withEffectiveVisibility(Visibility.GONE)));
    }

    @Test
    public void toDetailMovie(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition(6))
                .perform(RecyclerViewActions.actionOnItemAtPosition(6,click()));
        onView(withId(R.id.tv_detail_movie_title)).check(matches(isDisplayed()));
        onView(withId(R.id.pb_movie_detail)).check(matches(withEffectiveVisibility(Visibility.GONE)));
    }

}