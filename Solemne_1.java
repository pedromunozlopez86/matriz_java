import javax.swing.JOptionPane;
import java.util.Random;

public class Solemne_1 {
    static String[] board = new String[9];
    static String turn = "X";
    static Random random = new Random();

    public static void main(String[] args) {
        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }
        printBoard();

        while (true) {
            if (turn.equals("X")) {
                String input = JOptionPane.showInputDialog(
                        "Turno de " + turn + "; ingresa un número de casilla para colocar " + turn + " en:");
                int numInput = Integer.parseInt(input);
                if (!board[numInput - 1].equals("X") && !board[numInput - 1].equals("O")) {
                    board[numInput - 1] = turn;
                    printBoard();
                } else {
                    System.out.println("Esta casilla ya está ocupada. Por favor, elige otra.");
                    continue;
                }
            } else {
                int numInput;
                do {
                    numInput = random.nextInt(9);
                } while (board[numInput].equals("X") || board[numInput].equals("O"));
                board[numInput] = turn;
                printBoard();
            }
            String winner = checkWinner();
            if (winner != null) {
                if (winner.equalsIgnoreCase("draw")) {
                    System.out.println("¡Es un empate! Gracias por jugar.");
                } else {
                    System.out.println("¡Felicidades! " + winner + " ha ganado! Gracias por jugar.");
                }
                break;
            }
            turn = turn.equals("X") ? "O" : "X";
        }
    }

    static void printBoard() {
        System.out.println("+---+---+---+");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("+---+---+---+");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("+---+---+---+");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("+---+---+---+");
        System.out.println();
        System.out.println();

    }

    static String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }
            if (line.equals("XXX")) {
                return "X";
            } else if (line.equals("OOO")) {
                return "O";
            }
        }
        for (int a = 0; a < 9; a++) {
            if (board[a].equals(String.valueOf(a + 1))) {
                return null;
            }
        }
        return "draw";
    }
}