/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.marco.kwetter.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Marco
 */
public class Kweet {

    private int kweetId;
    private int userId;
    private String content;
    private Date timestamp;
    private List<Integer> hearts;

    public Kweet(int userId, String content, Date timeStamp) {
        this.userId = userId;
        this.content = content;
        this.timestamp = timeStamp;
        this.hearts = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.kweetId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kweet other = (Kweet) obj;
        if (this.kweetId != other.kweetId) {
            return false;
        }
        return true;
    }

    /**
     * @return the kweetId
     */
    public int getKweetId() {
        return kweetId;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the hearts
     */
    public List<Integer> getHearts() {
        return hearts;
    }

    /**
     * @param hearts the hearts to set
     */
    public void setHearts(List<Integer> hearts) {
        this.hearts = hearts;
    }

    /**
     * @param kweetId the kweetId to set
     */
    public void setKweetId(int kweetId) {
        this.kweetId = kweetId;
    }
}
