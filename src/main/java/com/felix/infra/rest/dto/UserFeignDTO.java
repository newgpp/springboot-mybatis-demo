package com.felix.infra.rest.dto;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/29 14:46
 */
public class UserFeignDTO {

    private Long userId;

    private Long id;

    private String title;

    private String body;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
