/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.sessions;

import enterprise.persistence.Player;
import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author Cyril Cecchinel
 */
@Local
public interface GameSessionLocal extends Serializable {
    
    public void addPlayer(Player p);
    
    public int getPlayerScore();
   
    public int getOpponentScore();
    
    public boolean playerWin();
    
    public boolean opponentWin();
    
    public void play(int choice);
    
    
    
}
