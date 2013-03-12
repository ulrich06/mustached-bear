/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entreprise.sessions;

import java.io.Serializable;
import javax.ejb.Remote;

/**
 *
 * @author Cyril Cecchinel
 */
@Remote
public interface GameSessionRemote extends Serializable {
    
    public void addPlayer(Player p);
    
    public int getPlayerScore();
   
    public int getOpponentScore();
    
    public boolean playerWin();
    
    public boolean opponentWin();
    
    public void play(Choice choice);
    
    
    
}
