/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.marco.kwetter.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import nl.marco.kwetter.exceptions.UserNotFoundException;
import nl.marco.kwetter.model.Kweet;
import nl.marco.kwetter.model.User;

/**
 *
 * @author Marco
 */
@Stateless
public class UserDaoColl implements UserDao {

    List<User> users = new ArrayList<>();
    private List<Integer> following;
    private List<Integer> followers;
    private int nextId;

    public UserDaoColl() {
        following = new ArrayList<>();
        followers = new ArrayList<>();
        List<Integer> mentions = new ArrayList<>(); //valt nog even buiten de opdracht
        Date date = new Date();

        // <editor-fold defaultstate="collapsed" desc="setup methods.">
        setupUser(1, "Marco", "Marco Snoek", "marco@esw-kwetter.nl", "test", "1.jpg", "bla bla", "Hilvarenbeek", "", mentions, date);
        setupUser(2, "Piet", "Pietje Puk", "piet@esw-kwetter.nl", "test", "2.jpg", "bla bla", "Hilvarenbeek", "", mentions, date);
        setupUser(3, "Klaas", "Klaas Janssen", "klaas@esw-kwetter.nl", "test", "3.jpg", "bla bla", "Hilvarenbeek", "", mentions, date);
        setupUser(4, "Jan", "Jan van Gorp", "jan@esw-kwetter.nl", "test", "4.jpg", "bla bla", "Hilvarenbeek", "", mentions, date);
        setupUser(5, "Kees", "Kees Klep", "kees@esw-kwetter.nl", "test", "5.jpg", "bla bla", "Hilvarenbeek", "", mentions, date);
        setupUser(6, "Gerrit", "Gerrit Gerritsen", "gerrit@esw-kwetter.nl", "test", "6.jpg", "bla bla", "Hilvarenbeek", "", mentions, date);
        setupUser(7, "Bert", "Bert is Ernie", "bert@esw-kwetter.nl", "test", "7.jpg", "bla bla", "Hilvarenbeek", "", mentions, date);
        setupUser(8, "Martijn", "Martijn", "martijn@esw-kwetter.nl", "test", "8.jpg", "bla bla", "Hilvarenbeek", "", mentions, date);
        setupUser(9, "Henk", "Henk Kraan", "henk@esw-kwetter.nl", "test", "9.jpg", "bla bla", "Hilvarenbeek", "", mentions, date);
        setupUser(10, "Harrie", "Harrie Nak", "harrie@esw-kwetter.nl", "test", "10.jpg", "bla bla", "Hilvarenbeek", "", mentions, date);

        nextId = 11;

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println(user.toString());
        }

        // </editor-fold>
    }

    @Override
    public User get(int userId) throws UserNotFoundException {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        throw new UserNotFoundException("User with ID " + userId + " does not exist");
    }

    @Override
    public List<User> get(int count, int offset) {
        return users.subList(count, offset);
    }

    @Override
    public void create(User user) {
        user.setUserId(nextId++);
        users.add(user);
    }

    @Override
    public void Update(User user) throws UserNotFoundException {
        int index = this.getIdex(user.getUserId());
        if (index == -1) {
            throw new UserNotFoundException("User with ID " + user.getUserId() + " does not exist");
        }
        users.set(index, user);
    }

    @Override
    public void Delete(User user) {
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            User u = userIterator.next();
            if (u.equals(user)) {
                users.remove(user);
                return;
            }
        }
    }

    @Override
    public List<User> getFollowers(User user) throws UserNotFoundException {
        List<User> retList = new ArrayList<>();
        for (int followerId : user.getFollowers()) {
            retList.add(this.get(followerId));
        }
        return retList;
    }

    @Override
    public List<User> getFollowing(User user) throws UserNotFoundException {
        List<User> retList = new ArrayList<>();
        for (int followerId : user.getFollowing()) {
            retList.add(this.get(followerId));
        }
        return retList;
    }

    /**
     * Helper method to get the index of the User object from the Users array
     *
     * @param userId
     * @return index of the User object
     */
    private int getIdex(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return users.indexOf(user);
            }
        }
        return -1;
    }

    private void setupUser(int userId, String username, String name, String emailAddress, String password,
            String profilePicture, String bio, String location, String website,
            List<Integer> mentions,
            Date registerDate) {
        setRandomFollowing(1, 10, 5);
        setRandomFollowers(1, 10, 5);
        User u = new User(username, name, emailAddress, password, profilePicture, bio, location, website);
        u.setUserId(userId++);
        users.add(u);
    }

    private void setRandomFollowing(int min, int max, int number) {
        following.clear();
        Random rand = new Random();
        for (int i = 0; i < number; i++) {
            following.add(rand.nextInt(max) + min);
        }
    }

    private void setRandomFollowers(int min, int max, int number) {
        followers.clear();
        Random rand = new Random();
        for (int i = 0; i < number; i++) {
            followers.add(rand.nextInt(max) + min);
        }
    }
}
