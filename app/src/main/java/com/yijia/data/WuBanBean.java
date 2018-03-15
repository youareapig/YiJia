package com.yijia.data;

/**
 * Created by DELL on 2018/3/15.
 */

public class WuBanBean {
    private Integer image;
    private String name;
    private String sale;

    public WuBanBean(Integer image, String name, String sale) {
        this.image = image;
        this.name = name;
        this.sale = sale;
    }

    public Integer getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getSale() {
        return sale;
    }
}
