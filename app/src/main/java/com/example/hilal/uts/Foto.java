package com.example.hilal.uts;

public class Foto {
    private String nama;
    private int gambar1;
    private int gambar2;

    public static final Foto[] foto = {
            new Foto("Red Photo", R.drawable.g1, R.drawable.g2),
            new Foto("Green Photo", R.drawable.g3, R.drawable.g4),
            new Foto("Blue Photo", R.drawable.g5, R.drawable.g6),
    };

    private Foto (String name, int gambar1, int gambar2){
        this.nama = name;
        this.gambar1 = gambar1;
        this.gambar2 = gambar2;
    }

    public String getName() {
        return nama;
    }

    public int getGambar1() {
        return gambar1;
    }

    public int getGambar2() {
        return gambar2;
    }

    public String toString(){
        return this.nama;
    }
}
