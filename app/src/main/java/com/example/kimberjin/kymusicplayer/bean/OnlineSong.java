package com.example.kimberjin.kymusicplayer.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ky4910 on 2019/11/22 23:45
 */
public class OnlineSong {

    private int error_code;
    @SerializedName("songinfo")
    private SongInfo songInfo;
    @SerializedName("bitrate")
    private SongBitrate songBitrate;

    public SongInfo getSongInfo() {
        return songInfo;
    }

    public void setSongInfo(SongInfo songInfo) {
        this.songInfo = songInfo;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public SongBitrate getSongBitrate() {
        return songBitrate;
    }

    public void setSongBitrate(SongBitrate songBitrate) {
        this.songBitrate = songBitrate;
    }

    public class SongInfo {

        @SerializedName("pic_huge")
        private String pic_huge;
        @SerializedName("author")
        private String author;
        @SerializedName("song_id")
        private String sondId;
        @SerializedName("title")
        private String title;
        @SerializedName("song_source")
        private String song_source;
        @SerializedName("all_rate")
        private String all_rate;

        public String getPic_huge() {
            return pic_huge;
        }

        public void setPic_huge(String pic_huge) {
            this.pic_huge = pic_huge;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getSondId() {
            return sondId;
        }

        public void setSondId(String sondId) {
            this.sondId = sondId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSong_source() {
            return song_source;
        }

        public void setSong_source(String song_source) {
            this.song_source = song_source;
        }

        public String getAll_rate() {
            return all_rate;
        }

        public void setAll_rate(String all_rate) {
            this.all_rate = all_rate;
        }
    }

    public class SongBitrate {

        @SerializedName("file_link")
        private String file_link;
        @SerializedName("file_size")
        private long file_size;
        @SerializedName("file_extension")
        private String file_extension;
        @SerializedName("file_duration")
        private int file_duration;
        @SerializedName("file_bitrate")
        private int file_bitrate;

        public String getFile_link() {
            return file_link;
        }

        public void setFile_link(String file_link) {
            this.file_link = file_link;
        }

        public long getFile_size() {
            return file_size;
        }

        public void setFile_size(long file_size) {
            this.file_size = file_size;
        }

        public String getFile_extension() {
            return file_extension;
        }

        public void setFile_extension(String file_extension) {
            this.file_extension = file_extension;
        }

        public int getFile_duration() {
            return file_duration;
        }

        public void setFile_duration(int file_duration) {
            this.file_duration = file_duration;
        }

        public int getFile_bitrate() {
            return file_bitrate;
        }

        public void setFile_bitrate(int file_bitrate) {
            this.file_bitrate = file_bitrate;
        }
    }
}
