/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.marco.kwetter.service;

import java.util.List;
import javax.ejb.Stateless;
import nl.marco.kwetter.dao.UserDao;
import nl.marco.kwetter.dao.UserDaoColl;
import nl.marco.kwetter.exceptions.UserNotFoundException;
import nl.marco.kwetter.model.User;

/**
 *
 * @author Marco
 */
@Stateless
public class UserService {

    private final UserDao userDao;

    /**
     *
     */
    public UserService() {
        userDao = new UserDaoColl();
    }

    /**
     *
     * @param userId
     * @return
     * @throws UserNotFoundException
     */
    public User getUser(int userId) throws UserNotFoundException {
        return userDao.get(userId);
    }

    /**
     *
     * @param count
     * @param offset
     * @return
     */
    public List<User> getUsers(int count, int offset) {
        return userDao.get(count, offset);
    }

    /**
     *
     * @param user
     * @return
     * @throws UserNotFoundException
     */
    public List<User> getFollowers(User user) throws UserNotFoundException {
        return userDao.getFollowers(user);
    }

    /**
     *
     * @param user
     * @return
     * @throws UserNotFoundException
     */
    public List<User> getFollowing(User user) throws UserNotFoundException {
        return userDao.getFollowing(user);
    }

    /**
     *
     * @param user
     */
    public void createUser(User user) {
        userDao.create(user);
    }

    public void updateUser(User user) throws UserNotFoundException {
        userDao.Update(user);
    }
}
