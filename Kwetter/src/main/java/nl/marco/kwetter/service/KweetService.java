/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.marco.kwetter.service;

import java.util.List;
import javax.ejb.Stateless;
import nl.marco.kwetter.dao.KweetDao;
import nl.marco.kwetter.dao.KweetDaoColl;
import nl.marco.kwetter.exceptions.KweetNotFoundException;
import nl.marco.kwetter.model.Kweet;

/**
 *
 * @author Marco
 */
@Stateless
public class KweetService {

    private final KweetDao kweetDao;

    /**
     *
     */
    public KweetService() {
        kweetDao = new KweetDaoColl();
    }

    /**
     *
     * @param kweetId
     * @return
     * @throws KweetNotFoundException
     */
    public Kweet getKweet(int kweetId) throws KweetNotFoundException {
        return kweetDao.get(kweetId);
    }

    /**
     *
     * @param count
     * @param offset
     * @return
     */
    public List<Kweet> getKweets(int count, int offset) {
        return kweetDao.get(count, offset);
    }

    /**
     *
     * @param query
     * @return
     */
    public List<Kweet> searchKweets(String query) {
        return kweetDao.search(query);
    }

    /**
     *
     * @param kweet
     */
    public void createKweet(Kweet kweet) {
        kweetDao.create(kweet);
    }

    /**
     *
     * @param kweet
     */
    public void deleteKweet(Kweet kweet) {
        kweetDao.delete(kweet);
    }

    /**
     *
     * @param kweet
     * @param userId
     */
    public void toggleHeart(Kweet kweet, int userId) {
        kweetDao.toggleHeart(kweet, userId);
    }
}
