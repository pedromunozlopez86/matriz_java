import java.util.Scanner;

public class Determinante {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ingresar los valores de la matriz
        int[][] matriz = new int[3][3];
        System.out.println("Ingrese los valores de la matriz:");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Ingrese el valor para la posición [" + i + "][" + j + "]: ");
                matriz[i][j] = scanner.nextInt();
            }
        }

        // Calcular el determinante
        int determinante = calcularDeterminante(matriz);

        // Mostrar el resultado
        System.out.println("El determinante de la matriz es: " + determinante);

        scanner.close();
    }

    public static int calcularDeterminante(int[][] matriz) {
        int determinante = 0;

        determinante += matriz[0][0] * (matriz[1][1] * matriz[2][2] - matriz[1][2] * matriz[2][1]);
        determinante -= matriz[0][1] * (matriz[1][0] * matriz[2][2] - matriz[1][2] * matriz[2][0]);
        determinante += matriz[0][2] * (matriz[1][0] * matriz[2][1] - matriz[1][1] * matriz[2][0]);

        return determinante;
    }
}

