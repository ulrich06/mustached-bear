/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.sessions;

import enterprise.persistence.Player;
import enterprise.persistence.Room;
import enterprise.exceptions.NotAuthorizedException;
import enterprise.exceptions.NotFoundPlayerException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author Cyril Cecchinel
 */
@Stateless
public class PlayerSession implements PlayerSessionLocal{
    
    @javax.persistence.PersistenceContext(unitName="GameDB")
    private EntityManager em ;
        
    @Override
    public void persist(Object obj) {
        em.persist(obj);
    }

    @Override
    public boolean login(String login, String password) throws NotAuthorizedException {
        List players = em.createNamedQuery("getPlayer").setParameter("login", login).setParameter("password", password).getResultList();
        //If the DB returns null or the password is incorrect
        if (players.isEmpty() || players.size() != 1) {
            return false;
        }
        return true;
    }

    @Override
    public Player searchPlayer(String id) throws NotFoundPlayerException {
        return (Player)em.find(Player.class, id);
    }

    @Override
    public List getAllPlayers() {
        List players = em.createNamedQuery("findAllPlayers").getResultList();
        return players;
    }

    
}
