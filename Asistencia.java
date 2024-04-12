import java.util.Scanner;

public class Asistencia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // defino mis variables
        int numEstudiantes = 5;
        int numDias = 5;

        // init de la matriz
        int[][] asistencia = new int[numEstudiantes][numDias];

        // ciclo llenado datos
        for (int i = 0; i < numEstudiantes; i++) {
            for (int j = 0; j < numDias; j++) {
                System.out.println("Ingrese la asistencia del estudiante " + (i + 1) + " en el día " + (j + 1)
                        + " (0 = ausente, 1 = presente):");
                asistencia[i][j] = scanner.nextInt();
            }
        }

        // Calculo asistencia
        for (int i = 0; i < numEstudiantes; i++) {
            int total = 0;
            for (int j = 0; j < numDias; j++) {
                total += asistencia[i][j];
            }
            System.out.println("La asistencia total del estudiante " + (i + 1) + " es: " + total);
        }

        // Calcular la asistencia total por día
        for (int j = 0; j < numDias; j++) {
            int total = 0;
            for (int i = 0; i < numEstudiantes; i++) {
                total += asistencia[i][j];
            }
            System.out.println("La asistencia total del día " + (j + 1) + " es: " + total);
        }

        scanner.close();
    }
}
