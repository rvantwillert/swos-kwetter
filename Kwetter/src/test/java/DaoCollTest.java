/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import nl.marco.kwetter.dao.UserDao;
import nl.marco.kwetter.dao.UserDaoColl;
import nl.marco.kwetter.exceptions.UserNotFoundException;
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
public class DaoCollTest {

    private UserDao userDao;
    private List<Integer> following;
    private List<Integer> followers;

    public DaoCollTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        userDao = new UserDaoColl();
        following = new ArrayList<>();
        followers = new ArrayList<>();
    }

    @After
    public void tearDown() {
    }

    /**
     * Tests the UserDaoColl init methods
     */
    @Test
    public void UserDaoCtorTest() {
        assertTrue(userDao.get(0, 10).size() == 10);
    }

    @Test
    public void getUserTest() throws UserNotFoundException {
        User user = userDao.get(1);
        assertNotNull(user);
        assertTrue(user.getName().equals("Marco Snoek"));
    }

    @Test
    public void getUserRangeTest() throws UserNotFoundException {
        List<User> users = new ArrayList<>();
        users = userDao.get(0, 5);
        //simulate paging
        //Page 1/2
        assertTrue(users.size() == 5);
        assertTrue(users.get(0).getName().equals("Marco Snoek"));
        assertTrue(users.get(1).getName().equals("Pietje Puk"));
        assertTrue(users.get(2).getName().equals("Klaas Janssen"));
        assertTrue(users.get(3).getName().equals("Jan van Gorp"));
        assertTrue(users.get(4).getName().equals("Kees Klep"));

        //page 2/2
        users = userDao.get(5, 10);
        assertTrue(users.size() == 5);
        assertTrue(users.get(0).getName().equals("Gerrit Gerritsen"));
        assertTrue(users.get(1).getName().equals("Bert is Ernie"));
        assertTrue(users.get(2).getName().equals("Martijn"));
        assertTrue(users.get(3).getName().equals("Henk Kraan"));
        assertTrue(users.get(4).getName().equals("Harrie Nak"));
    }

    @Test(expected = UserNotFoundException.class)
    public void userNotFoundExceptionTest() throws UserNotFoundException {
        userDao.get(Integer.MAX_VALUE);
    }

    @Test
    public void createTest() throws UserNotFoundException {
        User user = new User("Barrie", "Barrie Batsbak", "barrie@newkids.nl", "test", "11.jpg", "bla bla",
                "Maaskantje", "");
        userDao.create(user);
        assertNotNull(userDao.get(11));
        assertTrue(userDao.get(11).getLocation().equals("Maaskantje"));
    }

    @Test
    public void updateTest() throws UserNotFoundException {
        User user = userDao.get(1);
        user.setPassword("biertje");
        userDao.Update(user);
        user = userDao.get(1);
        assertTrue(user.getPassword().equals("biertje"));

    }

    @Test(expected = UserNotFoundException.class)
    public void deleteUserTest() throws UserNotFoundException {
        User user = userDao.get(4);
        userDao.Delete(user);
        //should throw UserNotFoundException
        userDao.get(4);
    }

}
