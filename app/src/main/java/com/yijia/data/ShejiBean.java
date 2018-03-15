package com.yijia.data;

import java.util.List;

/**
 * Created by DELL on 2018/3/15.
 */

public class ShejiBean {
    private int head;
    private String name;
    private String style;
    private List<Integer> list;

    public ShejiBean(int head, String name, String style, List<Integer> list) {
        this.head = head;
        this.name = name;
        this.style = style;
        this.list = list;
    }

    public int getHead() {
        return head;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public List<Integer> getList() {
        return list;
    }
}
