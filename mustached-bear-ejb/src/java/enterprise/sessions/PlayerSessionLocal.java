/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.sessions;

import enterprise.exceptions.NotAuthorizedException;
import enterprise.exceptions.NotFoundPlayerException;
import enterprise.persistence.Player;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Cyril Cecchinel
 */
@Local
public interface PlayerSessionLocal extends Serializable {
    
    public void persist(Object obj);
    
    public boolean login(String login, String password) 
            throws NotAuthorizedException;
    
    public Player searchPlayer(String id)
            throws NotFoundPlayerException;

    
    public List getAllPlayers();
    
}
