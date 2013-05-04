/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.sessions;

import enterprise.persistence.Game;
import enterprise.persistence.Player;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Cyril Cecchinel
 */
@Local
public interface GameRoomSessionLocal extends Serializable{
    
    public void enterRoom(Player player);
    
    public void leaveRoom(Player player);
    
    public List listPlayers();
    
    public Game getGame(Object id);
    
    
}
