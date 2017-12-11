import java.util.Random;

public class Game
{
    private Player[] player;
    private int currentPlayer;

    public Game()
    {
        player = new Player[2];
        player[0] = new Player('X');
        player[1] = new Player('O');
    }

    public void play()
    {
        Random rand = new Random();
        boolean bool = rand.nextBoolean();
        if(bool == true) {
            currentPlayer = 1;
        }
        else {
            currentPlayer = 0;
        }
    }

    public char updateGame()
    {
        if(currentPlayer == 0) {
            currentPlayer = 1;
        } else {
            currentPlayer = 0;
        }
        return player[currentPlayer].getLetter();
    }
    
    public char playerTurn()
    {
        return player[currentPlayer].getLetter();
    }
}