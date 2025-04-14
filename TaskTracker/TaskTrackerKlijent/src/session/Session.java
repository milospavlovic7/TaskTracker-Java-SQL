/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import domain.AbstractDomainObject;
import java.io.IOException;
import java.net.Socket;

/**
 * Singleton klasa koja čuva informacije o trenutno prijavljenom korisniku
 * (bilo da je zaposleni ili menadžer) i upravlja konekcijom ka serveru.
 * 
 * @author USER
 */
public class Session {

    private static Session instance;
    private Socket socket;

    private Session() {
        try {
            socket = new Socket("localhost", 10000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }
}
