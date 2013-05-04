/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author momo
 */
@Entity
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enterprise.entity.Room[ id=" + id + " ]";
    }
    
    private String name;
    private List playersList;

    public Room(){
        playersList = new ArrayList();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List playersList) {
        this.playersList = playersList;
    }
    
    public void addPlayer(Player player) {
        this.playersList.add(player);
    }
    
    public void removePlayer(Player player) {
        this.playersList.remove(player);
    }
}
