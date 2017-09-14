/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.marco.kwetter.dao;

import java.util.List;
import nl.marco.kwetter.exceptions.KweetNotFoundException;
import nl.marco.kwetter.model.Kweet;

/**
 *
 * @author Marco
 */
public interface KweetDao {

    /**
     *
     * @param kweetId
     * @return
     * @throws KweetNotFoundException
     */
    Kweet get(int kweetId) throws KweetNotFoundException;
    
    /**
     *
     * @param count
     * @param offset
     * @return
     */
    List<Kweet> get(int count, int offset);
 
    /**
     *
     * @param query
     * @return
     */
    List<Kweet> search(String query);
    
    /**
     *
     * @param kweet
     */
    void create(Kweet kweet);
    
    /**
     *
     * @param kweet
     */
    void delete(Kweet kweet);
    
    /**
     *
     * @param kweet
     * @param userId
     * @throws KweetNotFoundException
     */
    void toggleHeart(Kweet kweet, int userId);
    
}