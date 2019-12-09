package com.example.kimberjin.kymusicplayer.bean;

/**
 * Created by ky4910 on 2019/9/30
 */
public class Music {

    private long id;
    private String title;
    private String albumId;
    private String albumImgPath;
    private String artist;
    private int duration;
    private String url;
    private String lrcLink;
    private String composer;
    private String data_added;
    private int type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumImgPath() {
        return albumImgPath;
    }

    public void setAlbumImgPath(String albumImgPath) {
        this.albumImgPath = albumImgPath;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLrcLink() {
        return lrcLink;
    }

    public void setLrcLink(String lrcLink) {
        this.lrcLink = lrcLink;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getData_added() {
        return data_added;
    }

    public void setData_added(String data_added) {
        this.data_added = data_added;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Music() {}

    public Music(long id, String title, String albumId, String albumImgPath, String artist, int duration,
                 String url, String lrcLink, String composer, String data_added, int type) {
        this.id = id;
        this.title = title;
        this.albumId = albumId;
        this.albumImgPath = albumImgPath;
        this.artist = artist;
        this.duration = duration;
        this.url = url;
        this.lrcLink = lrcLink;
        this.composer = composer;
        this.data_added = data_added;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", albumId='" + albumId + '\'' +
                ", artist='" + artist + '\'' +
                ", duration=" + duration +
                ", url='" + url + '\'' +
                ", lrcLink='" + lrcLink + '\'' +
                ", composer='" + composer + '\'' +
                ", data_added='" + data_added + '\'' +
                '}';
    }
}
