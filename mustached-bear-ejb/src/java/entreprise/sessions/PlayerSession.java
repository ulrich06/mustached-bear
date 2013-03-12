/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entreprise.sessions;

import enterprise.persistence.Player;
import enterprise.persistence.Room;
import entreprise.exceptions.NotAuthorizedException;
import entreprise.exceptions.NotFoundPlayerException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author Cyril Cecchinel
 */
@Stateless
public class PlayerSession implements PlayerSessionRemote{
    
    @javax.persistence.PersistenceContext(unitName="GameDB")
    private EntityManager em ;
        
    @Override
    public void persist(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean login(String login, String password) throws NotAuthorizedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Player searchPlayer(String id) throws NotFoundPlayerException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean enterRoom(Room room) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String register(Player newPlayer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List getAllPlayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
