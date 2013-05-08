/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.entity;

import enterprise.session.PlayerSession;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author momo
 */
@Entity
@NamedQueries(
    value={@NamedQuery(name="findAllPlayers", query="select object(c) from Player c"),
    @NamedQuery(name="getPlayer", query="select object(p) from Player p where p.login = :login")}
        )
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return login + "|" + password + "|" + email;
    }
    
    String login;
    String password;
    String email;
    transient boolean playing;
    transient boolean waiting;
    transient Player adv;
    transient boolean defied;
    
    public Player()
    {
        this("Anonyme", "abcde", "admin@jeu.fr");
    }
    
    public Player(String log, String pwd, String mail)
    {
        this.login = log;
        this.password = pwd;
        this.email = mail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Player getAdv() {
        return this.adv;
    }
    
    public void defier(Player p) {
        this.adv = p;
        defied = true;
    }

    private Message createJMSMessageForjmsDefierMessage(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToDefierMessage(Object messageData) throws NamingException, JMSException {
        Context c = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) c.lookup("java:comp/env/jms/defierMessageFactory");
        Connection conn = null;
        Session s = null;
        try {
            conn = cf.createConnection();
            s = conn.createSession(false, s.AUTO_ACKNOWLEDGE);
            Destination destination = (Destination) c.lookup("java:comp/env/jms/defierMessage");
            MessageProducer mp = s.createProducer(destination);
            mp.send(createJMSMessageForjmsDefierMessage(s, messageData));
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    
}
