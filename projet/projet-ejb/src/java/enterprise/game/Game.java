package enterprise.game;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries(
    value={@NamedQuery(name="getGames", query="select object(g) from Game g")}
)

public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;

    public Game() {
        String title = "Pierre Feuille Ciseau";
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof Game && ((Game) o).getId().equals(id);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
}
