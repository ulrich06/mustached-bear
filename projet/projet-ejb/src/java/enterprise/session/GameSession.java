/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.session;

import enterprise.entity.Player;
import enterprise.game.Game;
import enterprise.game.PierreFeuilleCiseaux;
import enterprise.game.PierreFeuilleCiseaux.Choice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;

@Stateful
public class GameSession implements Serializable {

    @javax.persistence.PersistenceContext(unitName = "jndi_jeu")
    private EntityManager em;
    private static final int BEST_OF = 3;
    private int playerScore;
    private int computerScore;
    private List<Choice> lastUserChoices = new ArrayList<Choice>();
    private List<Choice> lastComputerChoices = new ArrayList<Choice>();
    private Player player;
    private Game game;

    public GameSession() {
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGame(String game) {
        this.game = em.find(Game.class, Long.parseLong(game));
    }

    public void newGame() {
        this.playerScore = 0;
        this.computerScore = 0;
        this.lastUserChoices.clear();
        this.lastComputerChoices.clear();

  
    }

    public Choice getLastUserChoice() {
        return lastUserChoices.isEmpty() ? null : lastUserChoices.get(lastUserChoices.size() - 1);
    }

    public Choice getLastComputerChoice() {
        return lastComputerChoices.isEmpty() ? null : lastComputerChoices.get(lastComputerChoices.size() - 1);
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public int play(Choice userChoice) {
        Choice computerChoice = PierreFeuilleCiseaux.getRandomChoice();
        int winner = PierreFeuilleCiseaux.getWinner(userChoice, computerChoice);
        this.lastUserChoices.add(userChoice);
        this.lastComputerChoices.add(computerChoice);
        if (winner == 1) {
            this.playerScore++;
        } else if (winner == 2) {
            this.computerScore++;
        }
        if (isFinished() && userWinner()) {
            em.createNamedQuery("incGamesWon").setParameter("game", game).setParameter("player", player).executeUpdate();
        }
        return winner;
    }

    public boolean isFinished() {
        return (playerScore + computerScore) >= BEST_OF;
    }

    public boolean userWinner() {
        return playerScore > computerScore;
    }
}
