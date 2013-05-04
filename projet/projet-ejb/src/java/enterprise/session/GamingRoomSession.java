/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.session;

import enterprise.entity.Player;
import enterprise.entity.Room;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.persistence.EntityManager;

/**
 *
 * @author momo
 */
@Singleton
@LocalBean
public class GamingRoomSession 
{
    @javax.persistence.PersistenceContext(unitName="jndi_jeu")
    private EntityManager em ;
    private Room room;
    private int nbJoueur;

    public GamingRoomSession() {
        room = new Room();
        nbJoueur = 0;
    }

    
    public void enterRoom(Player player) {       
        room.addPlayer(player);
        nbJoueur++;
    }

    public void leaveRoom(Player player) {
        room.removePlayer(player);
        nbJoueur--;
    }

    public List listPlayers() {
        return room.getPlayersList();
    }
    
    public int getNbJoueur(){
        return nbJoueur;
    }
    
    public void logout() {
    System.out.println("Logout user "+this);
    }
    
}
