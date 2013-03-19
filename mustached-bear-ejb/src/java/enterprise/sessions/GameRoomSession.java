/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.sessions;

import enterprise.persistence.Game;
import enterprise.persistence.Player;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author Cyril Cecchinel
 */
@Stateless
public class GameRoomSession implements GameRoomSessionRemote{
    
    @javax.persistence.PersistenceContext(unitName="GameDB")
    private EntityManager em ;
    
    @Override
    public void enterRoom(Player player) {       
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void leaveRoom(Player player) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List listPlayers() {
        List players = em.createNamedQuery("findAllPlayers").getResultList();
        return players;
    }

    @Override
    public Game getGame(Object id) {
        Game game = em.createNamedQuery("findGameById").setParameter("id", id).getFirstResult();
        return game;
    }
    
}
