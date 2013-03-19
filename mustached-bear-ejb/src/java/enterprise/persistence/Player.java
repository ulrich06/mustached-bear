/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author momo
 */
@Entity
@NamedQueries(
    value={@NamedQuery(name="findAllPlayers", query="select object(c) from Player c"),
    @NamedQuery(name="getPlayer", query="select object(p) from Player p where p.login= :login and p.password= :password")}
        )
public class Player implements Serializable {
    
    
    String login;
    String password;
    String email;
    
    public Player()
    {
        this("Anonyme", "abcde", "admin@jeu.fr");
    }
    
    public Player(String log, String pwd, String mail)
    {
        this.login = log;
        this.password = pwd;
        this.email = mail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
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
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enterprise.persistence.Player[ id=" + id + " ]";
    }
    
    
    
}
