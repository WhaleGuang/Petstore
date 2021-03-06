package com.daqi.entity;

import javax.validation.constraints.DecimalMax;

public class Pet {

    private Integer id;

    private Integer category;

    private String name;

    private String photourls;

    @DecimalMax(value = "333.33")
    private Integer tags;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhotourls() {
        return photourls;
    }

    public void setPhotourls(String photourls) {
        this.photourls = photourls == null ? null : photourls.trim();
    }


    public Integer getTags() {
        return tags;
    }


    public void setTags(Integer tags) {
        this.tags = tags;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}