import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        class Persona implements Serializable {
            private String id;
            private String nombre;
            private int edad;
            private String direccion;
            private String telefono;

            public String getNombre() {
                return nombre;
            }

            public void setId() {
                UUID uuid = UUID.randomUUID();
                String id = uuid.toString();
                this.id = uuid.toString();
            }

            public String getId() {
                return id;
            }

            public void setNombre(String nombre) {
                this.nombre = nombre;
            }

            public int getEdad() {
                return edad;
            }

            public void setEdad(int edad) {
                this.edad = edad;
            }

            public String getDireccion() {
                return direccion;
            }

            public void setDireccion(String direccion) {
                this.direccion = direccion;
            }

            public String getTelefono() {
                return telefono;
            }

            public void setTelefono(String telefono) {
                this.telefono = telefono;
            }

        }

        class GestorPersonas {
            private List<Persona> personas;

            public GestorPersonas() {
                personas = new ArrayList<>();
            }

            public void agregarPersona(Persona persona) {
                personas.add(persona);
            }

            public List<Persona> leerPersonas() {
                return personas;
            }

            public void actualizarPersona(int index, Persona persona) {
                personas.set(index, persona);
            }

            public void eliminarPersona(int index) {
                personas.remove(index);
            }

            public void guardarPersonas(String filename) throws IOException {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                    oos.writeObject(personas);
                }
            }

            public void cargarPersonas(String filename) throws IOException, ClassNotFoundException {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
                    personas = (List<Persona>) ois.readObject();
                }
            }
        }

        GestorPersonas gp = new GestorPersonas();
        String[] opciones = { "Agregar", "Leer", "Actualizar", "Eliminar" };
        String opcion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción", "CRUD Personas",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        switch (opcion) {
            case "Agregar":
                Persona p1 = new Persona();
                p1.setId();
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre");
                p1.setNombre(nombre);
                int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad"));
                p1.setEdad(edad);
                String direccion = JOptionPane.showInputDialog("Ingrese la dirección");
                p1.setDireccion(direccion);
                String telefono = JOptionPane.showInputDialog("Ingrese el teléfono");
                p1.setTelefono(telefono);
                gp.agregarPersona(p1);
                try {
                    FileWriter writer = new FileWriter("output.txt", true);
                    Persona[] personas = gp.leerPersonas().toArray(new Persona[0]);
                    for (Persona p : personas) {
                        writer.write("ID: " + p.getId() + "\n");
                        writer.write("Nombre: " + p.getNombre() + "\n");
                        writer.write("Edad: " + p.getEdad() + "\n");
                        writer.write("Dirección: " + p.getDireccion() + "\n");
                        writer.write("Teléfono: " + p.getTelefono() + "\n");
                        writer.write("\n");
                    }
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Leer":
                // Código para leer personas
                // try {
                // FileReader reader = new FileReader("output.txt");
                // BufferedReader bufferedReader = new BufferedReader(reader);
                // String line;
                // while ((line = bufferedReader.readLine()) != null) {

                // System.out.println(line);
                // }
                // reader.close();
                // } catch (IOException e) {
                // e.printStackTrace();
                // }

                try {
                    String content = new String(Files.readAllBytes(Paths.get("output.txt")));
                    JOptionPane.showMessageDialog(null, content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Actualizar":
                List<String> persons = new ArrayList<>();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        persons.add(line);
                    }
                    reader.close();
                    System.out.println(persons.toString());

                    String[] personArray = persons.toArray(new String[0]);
                    System.out.println(personArray.toString());
                    String[] personIds = new String[personArray.length / 5];
                    String id = (String) JOptionPane.showInputDialog(null,
                            "Seleccione el ID de la persona a actualizar",
                            "Actualizar Persona", JOptionPane.QUESTION_MESSAGE, null, personIds, personIds[0]);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case "Eliminar":
                // Código para eliminar una persona
                break;

        }
    }

    // class Crud {
    // public static void main(String[] args) {
    // // Prueba de las operaciones CRUD
    // GestorPersonas gp = new GestorPersonas();
    // Persona p1 = new Persona();
    // Scanner scanner = new Scanner(System.in);

    // System.out.print("Ingrese el nombre: ");
    // String nombre = scanner.nextLine();
    // p1.setNombre(nombre);

    // System.out.print("Ingrese la edad: ");
    // int edad = scanner.nextInt();
    // p1.setEdad(edad);

    // scanner.nextLine(); // Consume the newline character

    // System.out.print("Ingrese la dirección: ");
    // String direccion = scanner.nextLine();
    // p1.setDireccion(direccion);

    // System.out.print("Ingrese el teléfono: ");
    // String telefono = scanner.nextLine();
    // p1.setTelefono(telefono);

    // gp.agregarPersona(p1);

    // scanner.close();
    // try {
    // FileWriter writer = new FileWriter("output.txt");
    // Persona[] personas = gp.leerPersonas().toArray(new Persona[0]);
    // for (Persona p : personas) {
    // writer.write("Nombre: " + p.getNombre() + "\n");
    // writer.write("Edad: " + p.getEdad() + "\n");
    // writer.write("Dirección: " + p.getDireccion() + "\n");
    // writer.write("Teléfono: " + p.getTelefono() + "\n");
    // writer.write("\n");
    // }
    // writer.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    // }
}