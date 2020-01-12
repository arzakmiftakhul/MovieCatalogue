package com.miftakhularzak.moviecatalogue.data.source.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tvshowentities")
public class TvShowEntity {
    @PrimaryKey
    @ColumnInfo(name = "tvId")
    private int tvId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "posterUrl")
    private String posterUrl;

    @ColumnInfo(name = "genre")
    private String genre;

    @ColumnInfo(name = "companies")
    private String companies;

    @ColumnInfo(name = "language")
    private String language;

    @ColumnInfo(name = "rating")
    private String rating;

    @ColumnInfo(name = "date")
    private String date;

    @Ignore
    public TvShowEntity(int tvId, String name, String overview, String posterUrl) {
        this.tvId = tvId;
        this.name = name;
        this.overview = overview;
        this.posterUrl = posterUrl;
    }

    public TvShowEntity(int tvId, String name, String overview, String posterUrl, String genre, String companies, String language, String rating, String date) {
        this.tvId = tvId;
        this.name = name;
        this.overview = overview;
        this.posterUrl = posterUrl;
        this.genre = genre;
        this.companies = companies;
        this.language = language;
        this.rating = rating;
        this.date = date;
    }

    public int getTvId() {
        return tvId;
    }

    public void setTvId(int tvId) {
        this.tvId = tvId;
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
}
