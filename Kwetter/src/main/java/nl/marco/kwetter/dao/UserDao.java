/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.marco.kwetter.dao;

import java.util.List;
import nl.marco.kwetter.exceptions.UserNotFoundException;
import nl.marco.kwetter.model.User;

/**
 *
 * @author Marco
 */
public interface UserDao {

    /**
     *
     * @param userId
     * @return
     * @throws nl.marco.kwetter.exceptions.UserNotFoundException
     */
    User get(int userId) throws UserNotFoundException;
    
    /**
     *
     * @param count
     * @param offset
     * @return
     */
    List<User> get(int count, int offset);

    /**
     *
     * @param user
     */
    void create(User user);

    /**
     *
     * @param user
     * @throws nl.marco.kwetter.exceptions.UserNotFoundException
     */
    void Update(User user) throws UserNotFoundException;

    /**
     *
     * @param user
     */
    void Delete(User user);
    /**
     *
     * @param user
     * @return List of User
     * @throws nl.marco.kwetter.exceptions.UserNotFoundException
     */
    List<User> getFollowers(User user) throws UserNotFoundException;

    /**
     *
     * @param user
     * @return List of User
     * @throws nl.marco.kwetter.exceptions.UserNotFoundException
     */
    List<User> getFollowing(User user) throws UserNotFoundException;
}
