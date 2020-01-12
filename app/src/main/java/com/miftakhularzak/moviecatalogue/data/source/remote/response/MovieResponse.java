package com.miftakhularzak.moviecatalogue.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.miftakhularzak.moviecatalogue.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieResponse implements Parcelable {
    private int id;
    private String title;
    private String overview;
    private String releaseDate;
    private String posterUrl;
    private String genre;
    private String companies;
    private String language;
    private String rating;
    private String runtime;

    public MovieResponse(JSONObject object) {
        try {
            int id = object.getInt("id");
            String title = object.getString("title");
            String overview = object.getString("overview");
            String releaseDate = object.getString("release_date");
            String posterUrl = object.getString("poster_path");

            this.id = id;
            this.title = title;
            this.overview = overview;
            this.releaseDate = releaseDate;
            this.posterUrl = posterUrl;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected MovieResponse(Parcel in) {
        id = in.readInt();
        title = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        posterUrl = in.readString();
        genre = in.readString();
        companies = in.readString();
        language = in.readString();
        rating = in.readString();
        runtime = in.readString();
    }

    public MovieResponse(int id, String title, String overview, String releaseDate, String posterUrl, String genre, String companies, String language, String rating, String runtime) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.genre = genre;
        this.companies = companies;
        this.language = language;
        this.rating = rating;
        this.runtime = runtime;
    }

    public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
        @Override
        public MovieResponse createFromParcel(Parcel in) {
            return new MovieResponse(in);
        }

        @Override
        public MovieResponse[] newArray(int size) {
            return new MovieResponse[size];
        }
    };

    public void setDetail(JSONObject object) {
        try {
            String genre = Utils.getListFromJSONArray(object.getJSONArray("genres"), "name");
            String companies = Utils.getListFromJSONArray(object.getJSONArray("production_companies"), "name");
            String language = Utils.getListFromJSONArray(object.getJSONArray("spoken_languages"), "name");
            String rating = object.getString("vote_average");
            String runtime = object.getString("runtime");

            this.genre = genre;
            this.companies = companies;
            this.language = language;
            this.rating = rating;
            this.runtime = runtime;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCompanies() {
        return companies;
    }

    public void setCompanies(String companies) {
        this.companies = companies;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeString(posterUrl);
        parcel.writeString(genre);
        parcel.writeString(companies);
        parcel.writeString(language);
        parcel.writeString(rating);
        parcel.writeString(runtime);
    }
}
