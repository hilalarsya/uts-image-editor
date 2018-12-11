package com.example.hilal.uts;

public class VideoModel {
    private String nama;
    private String durasi;
    private String videoRawId;

    //array nama sinetron
    public static final VideoModel[] drama = {

            new VideoModel("VIDEO & PHOTOGRAPHY SHOWREEL 2016 - tuukkakiviranta.com","2:11","photo1"),
            new VideoModel("7 Mobile Photography Tips & Tricks!","9:10","photo2"),
            new VideoModel("9 photo composition tips (feat. Steve McCurry)","3:09","photo3")
    };

    private VideoModel(String nama, String durasi, String videoRawId){
        this.nama = nama;
        this.durasi = durasi;
        this.videoRawId = videoRawId;
    }

    public String getNama() {
        return nama;
    }

    public String getDurasi() {
        return durasi;
    }

    public String getVideoRawId() {
        return videoRawId;
    }

    public String toString() {
        return this.nama;
    }
}
