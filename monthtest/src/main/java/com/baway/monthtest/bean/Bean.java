package com.baway.monthtest.bean;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class Bean {
    //"imageUrl": "http://172.17.8.100/images/movie/stills/ws/ws1.jpg",
    //"name": "无双",
    private String name;
    private String imageUrl;

    public Bean() {
    }

    public Bean(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
