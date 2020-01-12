package com.miftakhularzak.moviecatalogue.ui.movie;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.matcher.ViewMatchers.Visibility;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.miftakhularzak.moviecatalogue.R;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.MovieEntity;
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

public class DetailMovieActivityTest {
    private MovieEntity dummyMovie = DataDummy.generateMovieDummy().get(0);

    @Rule
    public ActivityTestRule<DetailMovieActivity> activityRule = new ActivityTestRule<DetailMovieActivity>(DetailMovieActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailMovieActivity.class);
            result.putExtra(Constants.MOVIE_ID, dummyMovie.getMovieId());
            return result;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadDetailMovie() {
        onView(withId(R.id.tv_detail_movie_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_movie_title)).check(matches(withText(dummyMovie.getTitle())));
        onView(withId(R.id.tv_detail_movie_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_movie_genre)).check(matches(withText(dummyMovie.getGenre())));
        onView(withId(R.id.tv_detail_movie_companies_production)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_movie_language)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_movie_language)).check(matches(withText(dummyMovie.getLanguage())));
        onView(withId(R.id.tv_detail_movie_date)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_movie_date)).check(matches(withText(dummyMovie.getReleaseDate())));
        onView(withId(R.id.tv_detail_movie_rating)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_movie_runtime)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_movie_runtime)).check(matches(withText(dummyMovie.getRuntime() + " minutes")));
        onView(withId(R.id.tv_detail_movie_overview)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_detail_movie_poster)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_detail_movie_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.pb_movie_detail)).check(matches(withEffectiveVisibility(Visibility.GONE)));
    }

    @Test
    public void addAndRemoveFavorite() {
        onView(withId(R.id.iv_detail_movie_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_detail_movie_favorite)).check(matches(isDisplayed())).perform(click());
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText("Added to Favorite")));
        onView(withId(com.google.android.material.R.id.snackbar_action))
                .check(matches(withText("FAVORITE"))).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
        onView(withId(R.id.iv_detail_movie_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_detail_movie_favorite)).check(matches(isDisplayed())).perform(click());
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText("Removed from Favorite")));

    }

}