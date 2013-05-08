
package enterprise.game;

import java.util.Random;

public class PierreFeuilleCiseaux {
    
    public enum Choice { PIERRE, FEUILLE, CISEAUX };
    
    public static int getWinner(Choice player1, Choice player2) {
        if (player1 == player2) {
            return 0; // Draw
        }
        if (player1 == Choice.PIERRE) {
            return player2 == Choice.FEUILLE ? 2 : 1;
        } else if (player1 == Choice.FEUILLE) {
            return player2 == Choice.PIERRE ? 1 : 2;
        } else if (player1 == Choice.CISEAUX) {
            return player2 == Choice.PIERRE ? 2 : 1;
        } else {
            return 0; // Unknown case
        }
    }
    
    public static Choice getRandomChoice() {
        return Choice.values()[(new Random()).nextInt(Choice.values().length)];
    }
}
