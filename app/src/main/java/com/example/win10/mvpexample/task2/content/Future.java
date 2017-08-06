
package com.example.win10.mvpexample.task2.content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Future {

    @SerializedName("time")
    @Expose
    private Long time;
    @SerializedName("description")
    @Expose
    private String description;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
