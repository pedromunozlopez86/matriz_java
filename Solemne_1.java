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
        presentaJugada();
        boolean firstTurn = true;
        while (true) {
            if (turn.equals("X")) {
                int numInput;
                if (firstTurn) {
                    numInput = 5;
                    firstTurn = false;
                } else {
                    do {
                        numInput = random.nextInt(9) + 1;
                    } while (board[numInput - 1].equals("X") || board[numInput - 1].equals("Y"));
                }
                board[numInput - 1] = turn;
                presentaJugada();
            } else {
                String input = JOptionPane.showInputDialog(
                        "Es tu turno, por favor ingresa un número de casilla para colocar |Y| en:");
                int numInput = Integer.parseInt(input);
                if (!board[numInput - 1].equals("X") && !board[numInput - 1].equals("Y")) {
                    board[numInput - 1] = turn;
                    presentaJugada();
                } else {
                    System.out.println("Esta casilla ya está ocupada. Por favor, elige otra.");
                    continue;
                }

            }
            String ganador = hayUnGanador();
            if (ganador != null) {
                if (ganador.equalsIgnoreCase("draw")) {
                    System.out.println("¡Es un empate! Gracias por jugar.");
                } else if (ganador.equalsIgnoreCase("X")) 
                {
                    System.out.println("Lo siento has perdido el juego! Gracias por jugar.");
                } else {
                    System.out.println("¡Felicidades! has ganado! Gracias por jugar.");
                }
                break;

            }
            turn = turn.equals("X") ? "Y" : "X";
        }

    }

    static void presentaJugada() {
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

    static String hayUnGanador() {
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
            } else if (line.equals("YYY")) {
                return "Y";
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