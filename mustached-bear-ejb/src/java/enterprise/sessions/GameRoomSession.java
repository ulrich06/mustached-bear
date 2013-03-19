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

    public GameRoomSession() {
        room = new Room();
    }

    
    @Override
    public void enterRoom(Player player) {       
        room.addPlayer(player); 
    }

    @Override
    public void leaveRoom(Player player) {
        room.removePlayer(player);
    }

    @Override
    public List listPlayers() {
        return room.getPlayersList();
    }

    @Override
    public Game getGame(Object id) {
        return room.getGame();
    }
    
}
