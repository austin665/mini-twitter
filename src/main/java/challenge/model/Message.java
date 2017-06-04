package challenge.model;

import java.io.Serializable;

import challenge.serializer.MessageSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * A simple POJO based on the Messages Table schema
 */
@JsonSerialize(using = MessageSerializer.class)
public class Message implements Serializable {

    private Integer id;
    private Integer person_id;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}