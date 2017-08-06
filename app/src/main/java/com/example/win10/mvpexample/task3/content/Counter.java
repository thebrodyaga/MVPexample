
package com.example.win10.mvpexample.task3.content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Counter {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("error")
    @Expose
    private Integer error;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

}
