package com.example.flagquiz;

public class FlagData {
    private Integer flag_id;
    private String flag_name;
    private String flag_img;

    public FlagData() {
    }

    public FlagData(Integer flag_id, String flag_name, String flag_img) {
        this.flag_id = flag_id;
        this.flag_name = flag_name;
        this.flag_img = flag_img;
    }

    public Integer getFlag_id() {
        return flag_id;
    }

    public void setFlag_id(Integer flag_id) {
        this.flag_id = flag_id;
    }

    public String getFlag_name() {
        return flag_name;
    }

    public void setFlag_name(String flag_name) {
        this.flag_name = flag_name;
    }

    public String getFlag_img() {
        return flag_img;
    }

    public void setFlag_img(String flag_img) {
        this.flag_img = flag_img;
    }
}
