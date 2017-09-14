/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
import java.util.List;
import nl.marco.kwetter.dao.KweetDao;
import nl.marco.kwetter.dao.KweetDaoColl;
import nl.marco.kwetter.exceptions.KweetNotFoundException;
import nl.marco.kwetter.model.Kweet;
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
public class KweetDaoCollTest {

    private KweetDao kweetDao;

    public KweetDaoCollTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kweetDao = new KweetDaoColl();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void KweetDoaCtorTest() {
        assertTrue(kweetDao.get(0, 10).size() == 10);
    }

    @Test(expected = KweetNotFoundException.class)
    public void kweetNotFoundExceptionTest() throws KweetNotFoundException {
        kweetDao.get(Integer.MAX_VALUE);
    }

    @Test
    public void createTest() throws KweetNotFoundException {
        Kweet kweet = new Kweet(1, "Test Kweet", new Date());
        kweetDao.create(kweet);
        assertNotNull(kweetDao.get(11));
        assertTrue(kweetDao.get(11).getContent().equals("Test Kweet"));
    }

    @Test(expected = KweetNotFoundException.class)
    public void deleteTest() throws KweetNotFoundException {
        Kweet kweet = kweetDao.get(4);
        assertNotNull(kweet);
        kweetDao.delete(kweet);
        //Should throw KweetNotFoundException
        kweetDao.get(4);
    }

    @Test
    public void searchTest() {
        List<Kweet> searchRes = kweetDao.search("This kweet");
        assertTrue(searchRes.size() == 1);
        searchRes = kweetDao.search("should be found");
        assertTrue(searchRes.size() == 1);
    }

    @Test
    public void toggleHeartTest() throws KweetNotFoundException {
        Kweet kweet = kweetDao.get(1);
        assertTrue(kweet.getHearts().isEmpty());
        //add hearts
        kweetDao.toggleHeart(kweet, 1);
        kweetDao.toggleHeart(kweet, 2);
        kweetDao.toggleHeart(kweet, 3);
        kweetDao.toggleHeart(kweet, 4);
        kweet = kweetDao.get(1);
        assertTrue(kweet.getHearts().size() == 4);
        //remove heart
        kweetDao.toggleHeart(kweet, 1);
        kweet = kweetDao.get(1);
        assertTrue(kweet.getHearts().size() == 3);
    }
}
