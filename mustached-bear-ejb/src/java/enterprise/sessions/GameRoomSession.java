/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.sessions;

import enterprise.persistence.Game;
import enterprise.persistence.Player;
import enterprise.persistence.Room;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author Cyril Cecchinel
 */
@Stateless
public class GameRoomSession implements GameRoomSessionLocal{
    
    @javax.persistence.PersistenceContext(unitName="GameDB")
    private EntityManager em ;
    private Room room;

    
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
        return room.getPlayersList();
    }

    @Override
    public Game getGame(Object id) {
        Game game = em.find(Game.class, id);
        return game;
    }
    
}
