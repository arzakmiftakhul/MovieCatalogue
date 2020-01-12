package com.miftakhularzak.moviecatalogue.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.miftakhularzak.moviecatalogue.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class TvShowResponse implements Parcelable {
    int id;
    private String name;
    private String overview;
    private String posterUrl;
    private String genre;
    private String companies;
    private String language;
    private String rating;
    private String date;

    public TvShowResponse(JSONObject object) {
        try {
            int id = object.getInt("id");
            String name = object.getString("name");
            String overview = object.getString("overview");
            String posterUrl = object.getString("poster_path");

            this.id = id;
            this.name = name;
            this.overview = overview;
            this.posterUrl = posterUrl;
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected TvShowResponse(Parcel in) {
        id = in.readInt();
        name = in.readString();
        overview = in.readString();
        posterUrl = in.readString();
        genre = in.readString();
        companies = in.readString();
        language = in.readString();
        rating = in.readString();
        date = in.readString();
    }

    public static final Creator<TvShowResponse> CREATOR = new Creator<TvShowResponse>() {
        @Override
        public TvShowResponse createFromParcel(Parcel in) {
            return new TvShowResponse(in);
        }

        @Override
        public TvShowResponse[] newArray(int size) {
            return new TvShowResponse[size];
        }
    };

    public void setDetail(JSONObject object) {
        try {
            String genre = Utils.getListFromJSONArray(object.getJSONArray("genres"), "name");
            String companies = Utils.getListFromJSONArray(object.getJSONArray("production_companies"), "name");
            String language = object.getString("original_language");
            String rating = object.getString("vote_average");
            String date = object.getString("first_air_date");
            this.genre = genre;
            this.companies = companies;
            this.language = language;
            this.rating = rating;
            this.date = date;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public TvShowResponse(int id, String name, String overview, String posterUrl, String genre, String companies, String language, String rating, String date) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.posterUrl = posterUrl;
        this.genre = genre;
        this.companies = companies;
        this.language = language;
        this.rating = rating;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(overview);
        parcel.writeString(posterUrl);
        parcel.writeString(genre);
        parcel.writeString(companies);
        parcel.writeString(language);
        parcel.writeString(rating);
        parcel.writeString(date);
    }
}
