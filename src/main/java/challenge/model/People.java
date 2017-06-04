package challenge.model;


import java.io.Serializable;

import challenge.serializer.PeopleSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * A simple POJO based on the Followers Table schema
 */
@JsonSerialize(using = PeopleSerializer.class)
public class People implements Serializable{

    private Integer id;
    private String handle;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}


