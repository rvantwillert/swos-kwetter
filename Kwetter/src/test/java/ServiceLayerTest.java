/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
import java.util.List;
import nl.marco.kwetter.exceptions.KweetNotFoundException;
import nl.marco.kwetter.exceptions.UserNotFoundException;
import nl.marco.kwetter.model.Kweet;
import nl.marco.kwetter.model.User;
import nl.marco.kwetter.service.*;
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
public class ServiceLayerTest {

    private UserService userService;
    private KweetService kweetService;

    public ServiceLayerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        userService = new UserService();
        kweetService = new KweetService();
    }

    @After
    public void tearDown() {
    }

    /**
     * Simple test to check all services are running
     *
     * @throws UserNotFoundException
     * @throws KweetNotFoundException
     */
    @Test
    public void serviceInitTest() throws UserNotFoundException, KweetNotFoundException {
        User user = userService.getUser(1);
        assertNotNull(user);
        Kweet kweet = kweetService.getKweet(1);
        assertNotNull(kweet);
    }

    @Test
    public void newKweetTest() throws UserNotFoundException, KweetNotFoundException {
        User user = userService.getUser(1);
        assertNotNull(user);
        kweetService.createKweet(new Kweet(user.getUserId(), "Dit is een kweet", new Date()));
        assertNotNull(kweetService.getKweet(11));
    }

    @Test
    public void searchKweetTest() {
        List<Kweet> searchRes = kweetService.searchKweets("This kweet");
        assertTrue(searchRes.size() == 1);
        searchRes = kweetService.searchKweets("should be found");
        assertTrue(searchRes.size() == 1);
    }

    @Test
    public void createKweetTest() throws KweetNotFoundException {
        Kweet kweet = new Kweet(1, "Test Kweet", new Date());
        kweetService.createKweet(kweet);
        assertNotNull(kweetService.getKweet(11));
        assertTrue(kweetService.getKweet(11).getContent().equals("Test Kweet"));
    }

    @Test(expected = KweetNotFoundException.class)
    public void deleteKweetTest() throws KweetNotFoundException {
        Kweet kweet = kweetService.getKweet(4);
        assertNotNull(kweet);
        kweetService.deleteKweet(kweet);
        //Should throw KweetNotFoundException
        kweetService.getKweet(4);
    }

    @Test
    public void toggleHeartTest() throws KweetNotFoundException {
        Kweet kweet = kweetService.getKweet(1);
        assertTrue(kweet.getHearts().isEmpty());
        //add hearts
        kweetService.toggleHeart(kweet, 1);
        kweetService.toggleHeart(kweet, 2);
        kweetService.toggleHeart(kweet, 3);
        kweetService.toggleHeart(kweet, 4);
        kweet = kweetService.getKweet(1);
        assertTrue(kweet.getHearts().size() == 4);
        //remove heart
        kweetService.toggleHeart(kweet, 1);
        kweet = kweetService.getKweet(1);
        assertTrue(kweet.getHearts().size() == 3);
    }

    @Test
    public void newUserTest() throws UserNotFoundException {
        userService.createUser(new User("pietjepuk", "Pietje puk", "pietje@puk.nl", "geheim", "", "", "", ""));
        assertNotNull(userService.getUser(11));
    }

    @Test
    public void getUsersTest() {
        List<User> users = userService.getUsers(0, 10);
        assertTrue(users.size() > 1);
    }

    @Test
    public void updateUserTest() throws UserNotFoundException {
        User user = userService.getUser(1);
        assertNotNull(user);
        user.setLocation("Tilburg");
        userService.updateUser(user);
        User user2 = userService.getUser(1);
        assertTrue(user2.getLocation().equals("Tilburg"));
    }
}
