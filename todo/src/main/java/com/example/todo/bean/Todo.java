package com.example.todo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Lai
 * @time 2018/8/10 10:56
 * @Description
 */
@Entity
public class Todo {
    @Id
    private Long id;
    private String content;
    private String completeDateStr;
    private int status;
    private String title;
    private int type;
    private int userId;

    @Generated(hash = 274081398)
    public Todo(Long id, String content, String completeDateStr, int status,
            String title, int type, int userId) {
        this.id = id;
        this.content = content;
        this.completeDateStr = completeDateStr;
        this.status = status;
        this.title = title;
        this.type = type;
        this.userId = userId;
    }

    @Generated(hash = 1698043777)
    public Todo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCompleteDateStr() {
        return completeDateStr;
    }

    public void setCompleteDateStr(String completeDateStr) {
        this.completeDateStr = completeDateStr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
