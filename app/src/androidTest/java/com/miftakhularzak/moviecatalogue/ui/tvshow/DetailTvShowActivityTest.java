package com.miftakhularzak.moviecatalogue.ui.tvshow;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.matcher.ViewMatchers.Visibility;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.miftakhularzak.moviecatalogue.R;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.miftakhularzak.moviecatalogue.utils.Constants;
import com.miftakhularzak.moviecatalogue.utils.DataDummy;
import com.miftakhularzak.moviecatalogue.utils.EspressoIdlingResource;

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
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailTvShowActivityTest {
    private TvShowEntity dummyTvShow = DataDummy.generateTvShowDummy().get(0);

    @Rule
    public ActivityTestRule<DetailTvShowActivity> activityRule = new ActivityTestRule<DetailTvShowActivity>(DetailTvShowActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailTvShowActivity.class);
            result.putExtra(Constants.TV_ID, dummyTvShow.getTvId());
            return result;
        }
    };
    @Before
    public void setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }
    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadDetailTvShow(){
        onView(withId(R.id.tv_detail_tv_show_name)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_tv_show_name)).check(matches(withText(dummyTvShow.getName())));
        onView(withId(R.id.tv_detail_tv_show_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_tv_show_companies_production)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_tv_show_language)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_tv_show_date)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_tv_show_rating)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_tv_show_overview)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_detail_tv_show_poster)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_detail_tv_show_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.pb_detail_tv_show)).check(matches(withEffectiveVisibility(Visibility.GONE)));
    }
    @Test
    public void addAndRemoveFavorite() {
        onView(withId(R.id.iv_detail_tv_show_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_detail_tv_show_favorite)).check(matches(isDisplayed())).perform(click());
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText("Added to Favorite")));
        onView(withId(com.google.android.material.R.id.snackbar_action))
                .check(matches(withText("FAVORITE"))).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
        onView(withId(R.id.iv_detail_tv_show_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_detail_tv_show_favorite)).check(matches(isDisplayed())).perform(click());
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText("Removed from Favorite")));

    }
}