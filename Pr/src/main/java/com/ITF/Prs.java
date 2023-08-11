package com.ITF;

public class Prs {
    private int id;
    private String nume;
    private String prenume;
    private int varsta;
    private String email;

    public Prs(int id, String nume, String prenume, int varsta, String email) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
