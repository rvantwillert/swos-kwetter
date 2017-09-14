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
import nl.marco.kwetter.exceptions.KweetNotFoundException;
import nl.marco.kwetter.model.Kweet;

/**
 *
 * @author Marco
 */
public class KweetDaoColl implements KweetDao {

    List<Kweet> kweets = new ArrayList<>();
    private int nextId;

    public KweetDaoColl() {
        Date date = new Date();
        int counter = 0;
        for (int j = 0; j < 9; j++) {
            setupKweet(1, "Kweet " + ++counter, date);
        }
        setupKweet(1, "This kweet should be found by test", date);
        nextId = 11;
    }

    private void setupKweet(int userId, String content, Date timeStamp) {
        Kweet k = new Kweet(userId, content, timeStamp);
        k.setKweetId(nextId++);
        kweets.add(k);
    }

    @Override
    public Kweet get(int kweetId) throws KweetNotFoundException {
        for (Kweet kweet : kweets) {
            if (kweet.getKweetId() == kweetId) {
                return kweet;
            }
        }
        throw new KweetNotFoundException("Kweet with ID " + kweetId + " does not expist");
    }

    @Override
    public List<Kweet> get(int count, int offset) {
        return kweets.subList(count, offset);
    }

    @Override
    public List<Kweet> search(String query) {
        List<Kweet> retList = new ArrayList<>();
        for (Kweet kweet : kweets) {
            if (kweet.getContent().toLowerCase().contains(query.toLowerCase())) {
                retList.add(kweet);
            }
        }
        return retList;
    }

    @Override
    public void create(Kweet kweet) {
        kweet.setKweetId(nextId++);
        kweets.add(kweet);
    }

    @Override
    public void delete(Kweet kweet) {
        Iterator<Kweet> kweetIterator = kweets.iterator();
        while (kweetIterator.hasNext()) {
            Kweet k = kweetIterator.next();
            if (k.equals(kweet)) {
                kweets.remove(kweet);
                return;
            }
        }
    }

    @Override
    public void toggleHeart(Kweet kweet, int userId) {
        List<Integer> currentHearts = kweet.getHearts();
        //gebruiker heeft al een hartje, verwijder het
        Iterator<Integer> kweetIterator = kweet.getHearts().iterator();
        while (kweetIterator.hasNext()) {
            int heartUserId = kweetIterator.next();
            if (heartUserId == userId) {
                int i = currentHearts.indexOf(heartUserId);
                currentHearts.remove(i); //remove based on index
                kweet.setHearts(currentHearts);
                return;
            }
        }
        //gebruiker heeft nog geen hartje, voeg het toe
        currentHearts.add(userId);
        kweet.setHearts(currentHearts);
    }
}
