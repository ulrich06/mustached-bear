/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entreprise.sessions;

import enterprise.persistence.Game;
import enterprise.persistence.Player;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Cyril Cecchinel
 */
@Remote
public interface GameRoomSessionRemote extends Serializable{
    
    public void enterRoom(Player player);
    
    public void leaveRoom(Player player);
    
    public List listPlayers();
    
    public Game getGame();
    
    
}
