
package com.example.win10.mvpexample.task2.content;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date {

    @SerializedName("past")
    @Expose
    private List<Past> past = null;
    @SerializedName("future")
    @Expose
    private List<Future> future = null;

    public List<Past> getPast() {
        return past;
    }

    public void setPast(List<Past> past) {
        this.past = past;
    }

    public List<Future> getFuture() {
        return future;
    }

    public void setFuture(List<Future> future) {
        this.future = future;
    }

}
