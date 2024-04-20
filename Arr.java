import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Arr {
    static String[] board = new String[9];
    static String turn = "X";

    static void printBoard() {
        System.out.println("/---|---|---\\");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("/---|---|---\\");
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
            if (Arrays.asList(board).contains(String.valueOf(a + 1))) {
                break;
            } else if (a == 8)
                return "draw";
        }

        System.out.println(turn + "'s turn; enter a slot number to place " + turn + " in:");
        return null;
    }

    static void playerMove(Scanner scan) {
        String numInput;
        try {
            numInput = scan.next();
            if (!(numInput.length() == 1 && Character.isDigit(numInput.charAt(0)))) {
                System.out.println("Invalid input; re-enter slot number:");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid input; re-enter slot number:");
            return;
        }

        int num = Integer.parseInt(numInput);
        if (!(num > 0 && num <= 9)) {
            System.out.println("Invalid input; re-enter slot number:");
            return;
        }

        if (board[num - 1].equals(String.valueOf(num))) {
            board[num - 1] = turn;
            if (turn.equals("X")) {
                turn = "O";
            } else {
                turn = "X";
            }
            printBoard();
        } else {
            System.out.println("Slot already taken; re-enter slot number:");
        }
    }

    static void machineMove() {
        int num;
        do {
            num = (int) (Math.random() * 9) + 1;
        } while (!board[num - 1].equals(String.valueOf(num)));

        board[num - 1] = turn;
        if (turn.equals("X")) {
            turn = "O";
        } else {
            turn = "X";
        }
        printBoard();
    }

    public static void main(String[] args) {
        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }
        JOptionPane.showMessageDialog(null, "¡Bienvenido a Tic Tac Toe!");
        printBoard();

        board[4] = "X"; // La máquina siempre comienza en la posición 5
        turn = "O";
        printBoard();

        while (true) {
            if (turn.equals("O")) {
                String input = JOptionPane.showInputDialog(
                        "Turno de " + turn + "; ingresa un número de casilla para colocar " + turn + " en:");
                int numInput = Integer.parseInt(input);
                board[numInput - 1] = turn;
                turn = "X";
                printBoard();
            } else {
                machineMove();
            }
            String winner = checkWinner();
            if (winner != null) {
                if (winner.equalsIgnoreCase("draw")) {
                    JOptionPane.showMessageDialog(null, "¡Es un empate! Gracias por jugar.");
                } else {
                    JOptionPane.showMessageDialog(null, "¡Felicidades! " + winner + " ha ganado! Gracias por jugar.");
                }
                break;
            }
        }
    }
}