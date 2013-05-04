/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.session;

import enterprise.entity.Player;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;

/**
 *
 * @author momo
 */
@Stateless
@LocalBean
public class PlayerSession 
{
    @javax.persistence.PersistenceContext(unitName="jndi_jeu")
    private EntityManager em ;
        
    public void persist(Object obj) {
        em.persist(obj);
    }

    public boolean login(String login, String password) {
        List players = em.createNamedQuery("getPlayer").setParameter("login", login).getResultList();
        //If the DB returns null or the password is incorrect
        if (players.isEmpty() || players.size() != 1) {
            return false;
        }
        return true;
    }

    public Player searchPlayer(String login) {
        List players = em.createNamedQuery("getPlayer").setParameter("login", login).getResultList();
        if (players.isEmpty()){
            throw new BootstrapMethodError();
        }
        players.get(0).toString();
        return (Player)players.get(0);
    }

    public List getAllPlayers() {
        List players = em.createNamedQuery("findAllPlayers").getResultList();
        return players;
    }
}
