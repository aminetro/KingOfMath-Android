package com.example.admin.kingofmath2.Entities;

/**
 * Created by ADMIN on 02/01/2016.
 */
public class Leader {

    public String objectId;
    public String name;
    public String score;
    int Rang;
    String idfacebook;
    String idposition;

    public String getIdfacebook() {
        return idfacebook;
    }

    public void setIdfacebook(String idfacebook) {
        this.idfacebook = idfacebook;
    }

    public String getIdposition() {
        return idposition;
    }

    public void setIdposition(String idposition) {
        this.idposition = idposition;
    }

    public String getId() {
        return objectId;
    }

    public void setId(String id) {
        objectId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getRang() {
        return Rang;
    }

    public void setRang(int rang) {
        Rang = rang;
    }

    public Leader(String id, String name,String score,  int rang, String idfacebook, String idposition) {
        objectId = id;
        Rang = rang;
        this.score = score;
        this.name = name;
        this.idfacebook=idfacebook;
        this.idposition=idposition;

    }
    public Leader() {


    }




}
