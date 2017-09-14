/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import nl.marco.kwetter.model.Kweet;
import nl.marco.kwetter.model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marco
 */
public class ModelTest {

    private static List<User> users;
    private static List<Integer> following;
    private static List<Integer> followers;
    private static List<Integer> mentions;

    /**
     *
     */
    public ModelTest() {

    }

    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
        users = new ArrayList<>();
        following = new ArrayList<>();
        followers = new ArrayList<>();
        mentions = new ArrayList<>(); //valt nog even buiten de opdracht
        Date date = new Date();

        // <editor-fold defaultstate="collapsed" desc="setup methods.">
        setupUser("Marco", "marco@esw-kwetter.nl", "Marco Snoek", "test", "1.jpg", "bla bla", "Hilvarenbeek", "");
        setupUser("Piet", "piet@esw-kwetter.nl", "Pietje Puk", "test", "2.jpg", "bla bla", "Hilvarenbeek", "");
        setupUser("Klaas", "klaas@esw-kwetter.nl", "Klaas Janssen", "test", "3.jpg", "bla bla", "Hilvarenbeek", "");
        setupUser("Jan", "jan@esw-kwetter.nl", "Jan van Gorp", "test", "4.jpg", "bla bla", "Hilvarenbeek", "");
        setupUser("Kees", "kees@esw-kwetter.nl", "Kees Klep", "test", "5.jpg", "bla bla", "Hilvarenbeek", "");
        setupUser("Gerrit", "gerrit@esw-kwetter.nl", "Gerrit Gerritsen", "test", "6.jpg", "bla bla", "Hilvarenbeek", "");
        setupUser("Bert", "bert@esw-kwetter.nl", "Bert is Ernie", "test", "7.jpg", "bla bla", "Hilvarenbeek", "");
        setupUser("Martijn", "martijn@esw-kwetter.nl", "Martijn", "test", "8.jpg", "bla bla", "Hilvarenbeek", "");
        setupUser("Henk", "henk@esw-kwetter.nl", "Henk Kraan", "test", "9.jpg", "bla bla", "Hilvarenbeek", "");
        setupUser("Harrie", "harrie@esw-kwetter.nl", "Harrie Nak", "test", "10.jpg", "bla bla", "Hilvarenbeek", "");
        int counter = 0;
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < 10; j++) { //10 kweets per user
                setupKweet(users.get(i).getUserId(), "Kweet " + ++counter, date);
            }
        }

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println(user.toString());
        }

        // </editor-fold>
    }

    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     *
     */
    @Before
    public void setUp() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Validate there are 10 users and 100 kweets (10 per user)
     */
    @Test
    public void validateBaseInfo() {
        assertTrue(users.size() == 10);
        for (int i = 0; i < users.size(); i++) {
            assertTrue(users.get(i).getKweets().size() == 10); //10 kweets per user
        }

    }

    /**
     * Check if first user has 5 friends
     */
    @Test
    public void followingCountTest() {
        setRandomFollowing(1, 10, 5);
        User user = users.get(0);
        user.setFollowing(following);
        assertTrue(user.getFollowing().size() == 5);
    }

    private static int nextUserId;

    private static void setupUser(String username, String name, String emailAddress, String password,
            String profilePicture, String bio, String location, String website) {

        setRandomFollowers(1, 10, 5);
        User u = new User(username, name, emailAddress, password, profilePicture, bio,
                location, website);
        u.setUserId(++nextUserId);
        users.add(u);
    }

    private static int nextKweetId;

    private static void setupKweet(int userId, String content, Date timeStamp) {
        Kweet k = new Kweet(userId, content, timeStamp);
        k.setKweetId(++nextKweetId);
        users.get(userId - 1).addKweet(k);
    }

    private static void setRandomFollowing(int min, int max, int number) {
        following.clear();
        Random rand = new Random();
        for (int i = 0; i < number; i++) {
            following.add(rand.nextInt(max) + min);
        }
    }

    private static void setRandomFollowers(int min, int max, int number) {
        followers.clear();
        Random rand = new Random();
        for (int i = 0; i < number; i++) {
            followers.add(rand.nextInt(max) + min);
        }
    }
}
