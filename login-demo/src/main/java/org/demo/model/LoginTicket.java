package org.demo.model;

import java.util.Date;

/**
 * 登陆记录
 * @author yuxuan
 * @create 2017-12-13 下午11:01
 **/
public class LoginTicket {
    private int id;
    private int userId;
    /**
     * 过期时间
     */
    private Date expired;
    /**
     * 0有效，1无效
     */
    private int status;
    private String ticket;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

