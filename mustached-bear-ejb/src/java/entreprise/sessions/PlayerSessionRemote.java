/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entreprise.sessions;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Cyril Cecchinel
 */
@Remote
public interface PlayerSessionRemote extends Serializable {
    
    public void persist(Object obj);
    
    public boolean login(String login, String password) 
            throws NotAuthorizedException;
    
    public Player searchPlayer(String id);
            throws NotFoundPlayerException;
    
    public boolean enterRoom(Room room);
    
    public String register(Player newPlayer);
    
    public List getAllPlayers();
    
}
