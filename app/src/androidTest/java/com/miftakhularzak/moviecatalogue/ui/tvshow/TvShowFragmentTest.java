package com.miftakhularzak.moviecatalogue.ui.tvshow;

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

public class TvShowFragmentTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private TvShowFragment tvShowFragment = new TvShowFragment();

    @Before
    public void setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityRule.getActivity().setFragment(tvShowFragment);
    }
    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadTvShow() {
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv_show)).check(new RecyclerViewItemCountAssertion(20));
        onView(withId(R.id.pb_tv_show)).check(matches(withEffectiveVisibility(Visibility.GONE)));
    }

    @Test
    public void toDetailTvShow(){
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition(6))
                .perform(RecyclerViewActions.actionOnItemAtPosition(6,click()));
        onView(withId(R.id.tv_detail_tv_show_name)).check(matches(isDisplayed()));
        onView(withId(R.id.pb_detail_tv_show)).check(matches(withEffectiveVisibility(Visibility.GONE)));

    }
}