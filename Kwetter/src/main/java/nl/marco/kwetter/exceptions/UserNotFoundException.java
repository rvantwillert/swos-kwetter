/*
 * To cha nge this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.marco.kwetter.exceptions;

/**
 *
 * @author Marco
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }
}
