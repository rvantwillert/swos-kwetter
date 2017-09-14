/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.marco.kwetter.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Marco
 */
public class User {

    private int userId;
    private String username;
    private String name;
    private String emailAddress;
    private String password;
    private String profilePicture;
    private String bio;
    private String location;
    private String website;
    private List<Kweet> kweets;
    private List<Integer> following;
    private List<Integer> followers;
    private List<Integer> mentions;
    private Date registerDate;

    public User(String username, String name, String emailAddress, String password,
            String profilePicture, String bio, String location, String website) {
        this.username = username;
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.mentions = new ArrayList<>();
        //TODO registerDate als defaultvalue opnemen in de DB?
        this.registerDate = new Date();
        kweets = new ArrayList<>();
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the profilePicture
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * @param profilePicture the profilePicture to set
     */
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * @return the bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio the bio to set
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return the following
     */
    public List<Integer> getFollowing() {
        return following;
    }

    /**
     * @param following the following to set
     */
    public void setFollowing(List<Integer> following) {
        this.following = following;
    }

    /**
     * @return the followers
     */
    public List<Integer> getFollowers() {
        return followers;
    }

    /**
     * @param followers the followers to set
     */
    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    /**
     * @return the mentions
     */
    public List<Integer> getMentions() {
        return mentions;
    }

    /**
     * @param mentions the mentions to set
     */
    public void setMentions(List<Integer> mentions) {
        this.mentions = mentions;
    }

    /**
     * @return the registerDate
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    @Override
    public String toString() {
        return "userId: " + userId + ", username: " + username + ", emailAddress: " + emailAddress + ", password: " + password
                + ", profilePicture: " + profilePicture + ", bio: " + bio + ", location: " + location + ", website: " + website + ""
                + ", nrOfKweets: " + kweets.size();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.userId;
        hash = 31 * hash + Objects.hashCode(this.username);
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
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    /**
     * @return the kweets
     */
    public List<Kweet> getKweets() {
        return kweets;
    }

    /**
     * @param kweets the kweets to set
     */
    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public void addKweet(Kweet kweet) {
        this.kweets.add(kweet);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

}
