package com.example.jillipi.bythebolt.data;

/**
 * Created by jpyle on 1/13/2015.
 * This object holds all the information about a given Fabric
 */
public class Fabric {
    private long id;
    private String name;
    private String type;
    private String yardage;
    private String date;
    private String pic;
    private String details_pic;
    private String location;


    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getYardage(){
        return yardage;
    }

    public void setYardage(String yardage){
        this.yardage=yardage;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDetails_pic() {
        return details_pic;
    }

    public void setDetails_pic(String details_pic) {
        this.details_pic = details_pic;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

}
