package cm.btech2021.houser.models;

import java.util.ArrayList;
import java.util.Date;

public class HouseModel {
    String id;
    boolean isLiked;
    int imageResourceId;
    int likeCount;
    String title;
    String address;
    Date postedDate;

    public HouseModel(String id, boolean isLiked, int imageResourceId, int likeCount, String title, String address, Date postedDate) {
        this.id = id;
        this.isLiked = isLiked;
        this.imageResourceId = imageResourceId;
        this.likeCount = likeCount;
        this.title = title;
        this.address = address;
        this.postedDate = postedDate;
    }

    public HouseModel(ArrayList<String> params){
        this.imageResourceId = Integer.parseInt(params.get(0));
        this.title = params.get(1);
        this.address = params.get(2);
    }

    public ArrayList<String> toShortStringArrayList(){
        ArrayList<String> list = new ArrayList<>();

        list.add(String.valueOf(getImageResourceId()));
        list.add(getTitle());
        list.add(getAddress());

        return list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLiked() {
        return isLiked;
    }


    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public Date getPostedDate() {
        return postedDate;
    }
}
