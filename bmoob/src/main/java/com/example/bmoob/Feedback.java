package com.example.bmoob;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/11/23.
 */

public class Feedback extends BmobObject {
    private String name;
    private String feedback;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
