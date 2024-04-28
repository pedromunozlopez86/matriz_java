package Trabajo_Crud;

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

            public void generateUuid() {
                UUID uuid = UUID.randomUUID();
                this.id = uuid.toString();
            }

            public void setUuid(String id) {
                this.id = id;
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

            public String toString() {
                return "ID: " + this.id + ", Nombre: " + this.nombre + ", Edad: " + this.edad + ", Dirección: "
                        + this.direccion + ", Teléfono: " + this.telefono;
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
            };
        }

        GestorPersonas gp = new GestorPersonas();
        String[] opciones = { "Agregar", "Ver Datos Persona", "Actualizar", "Eliminar", "Ver Todos" };
        String opcion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción", "CRUD Personas",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        switch (opcion) {
            case "Agregar":
                Persona p1 = new Persona();
                p1.generateUuid();
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
                    FileWriter writer = new FileWriter("Trabajo_Crud/output.txt", true);
                    Persona[] personas = gp.leerPersonas().toArray(new Persona[0]);
                    for (Persona p : personas) {
                        writer.write("ID: " + p.getId() + ", ");
                        writer.write("Nombre: " + p.getNombre() + ", ");
                        writer.write("Edad: " + p.getEdad() + ", ");
                        writer.write("Dirección: " + p.getDireccion() + ", ");
                        writer.write("Teléfono: " + p.getTelefono() + "; ");
                        writer.write("\n");
                    }
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Ver Todos":
                try {
                    String content = new String(Files.readAllBytes(Paths.get("Trabajo_Crud/output.txt")));
                    JOptionPane.showMessageDialog(null, content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Actualizar":
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("Trabajo_Crud/output.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("; ")) {
                            String[] parts = line.split(", ");
                            Persona persona = new Persona();
                            String[] idParts = parts[0].split(": ");
                            persona.setUuid(idParts[1]);
                            String[] nombreParts = parts[1].split(": ");
                            persona.setNombre(nombreParts[1]);
                            String[] edadParts = parts[2].split(": ");
                            persona.setEdad(Integer.parseInt(edadParts[1]));
                            String[] direccionParts = parts[3].split(": ");
                            persona.setDireccion(direccionParts[1]);
                            String[] telefonoParts = parts[4].split(": ");
                            persona.setTelefono(telefonoParts[1]);
                            gp.agregarPersona(persona);
                        }
                    }
                    reader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                String[] ids = new String[gp.personas.size()];
                ids = gp.personas.stream().map(p -> p.getId()).toArray(String[]::new);

                String idToUpdate = (String) JOptionPane.showInputDialog(null,
                        "Seleccione el ID de la persona a actualizar", "Actualizar Persona",
                        JOptionPane.QUESTION_MESSAGE, null, ids, ids[0]);

                Persona personaToUpdate = gp.personas.stream().filter(p -> p.getId().equals(idToUpdate)).findFirst()
                        .orElse(null);
                System.out.println("personatoUpdt: " + personaToUpdate.toString());

                if (personaToUpdate != null) {
                    String nombrePersonaString = JOptionPane.showInputDialog("Ingrese el nuevo nombre",
                            personaToUpdate.getNombre());
                    personaToUpdate.setNombre(nombrePersonaString);
                    int edadPersonaInt = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva edad",
                            personaToUpdate.getEdad()));
                    personaToUpdate.setEdad(edadPersonaInt);
                    String direccionPersona = JOptionPane.showInputDialog("Ingrese la nueva dirección",
                            personaToUpdate.getDireccion());
                    personaToUpdate.setDireccion(direccionPersona);
                    String telefonoPersona = JOptionPane.showInputDialog("Ingrese el nuevo teléfono",
                            personaToUpdate.getTelefono());
                    personaToUpdate.setTelefono(telefonoPersona);
                    gp.actualizarPersona(gp.leerPersonas().indexOf(personaToUpdate), personaToUpdate);
                    try {
                        FileWriter writer = new FileWriter("Trabajo_Crud/output.txt", false);
                        for (Persona persona : gp.leerPersonas()) {
                            writer.write("ID: " + persona.getId() + ", ");
                            writer.write("Nombre: " + persona.getNombre() + ", ");
                            writer.write("Edad: " + persona.getEdad() + ", ");
                            writer.write("Dirección: " + persona.getDireccion() + ", ");
                            writer.write("Teléfono: " + persona.getTelefono() + "; ");
                            writer.write("\n");
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case "Eliminar":
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("Trabajo_Crud/output.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("; ")) {
                            String[] parts = line.split(", ");
                            Persona persona = new Persona();
                            String[] idParts = parts[0].split(": ");
                            persona.setUuid(idParts[1]);
                            String[] nombreParts = parts[1].split(": ");
                            persona.setNombre(nombreParts[1]);
                            String[] edadParts = parts[2].split(": ");
                            persona.setEdad(Integer.parseInt(edadParts[1]));
                            String[] direccionParts = parts[3].split(": ");
                            persona.setDireccion(direccionParts[1]);
                            String[] telefonoParts = parts[4].split(": ");
                            persona.setTelefono(telefonoParts[1]);
                            gp.agregarPersona(persona);
                        }
                    }
                    reader.close();
                    ids = gp.personas.stream().map(p -> p.getId()).toArray(String[]::new);

                    String idToDelete = (String) JOptionPane.showInputDialog(null,
                            "Seleccione el ID de la persona a actualizar", "Actualizar Persona",
                            JOptionPane.QUESTION_MESSAGE, null, ids, ids[0]);
                    gp.eliminarPersona(gp.leerPersonas().indexOf(
                            gp.personas.stream().filter(p -> p.getId().equals(idToDelete)).findFirst().orElse(null)));
                    try {
                        System.out.println("gp.leerPersonas()delete " + gp.leerPersonas());
                        FileWriter writer = new FileWriter("Trabajo_Crud/output.txt", false);
                        for (Persona persona : gp.leerPersonas()) {
                            writer.write("ID: " + persona.getId() + ", ");
                            writer.write("Nombre: " + persona.getNombre() + ", ");
                            writer.write("Edad: " + persona.getEdad() + ", ");
                            writer.write("Dirección: " + persona.getDireccion() + ", ");
                            writer.write("Teléfono: " + persona.getTelefono());
                            writer.write("\n");
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "Ver Datos Persona":
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("Trabajo_Crud/output.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("; ")) {
                            String[] parts = line.split(", ");
                            Persona persona = new Persona();
                            String[] idParts = parts[0].split(": ");
                            persona.setUuid(idParts[1]);
                            String[] nombreParts = parts[1].split(": ");
                            persona.setNombre(nombreParts[1]);
                            String[] edadParts = parts[2].split(": ");
                            persona.setEdad(Integer.parseInt(edadParts[1]));
                            String[] direccionParts = parts[3].split(": ");
                            persona.setDireccion(direccionParts[1]);
                            String[] telefonoParts = parts[4].split(": ");
                            persona.setTelefono(telefonoParts[1]);
                            gp.agregarPersona(persona);
                        }
                    }
                    reader.close();
                    ids = gp.personas.stream().map(p -> p.getId()).toArray(String[]::new);

                    String idToView = (String) JOptionPane.showInputDialog(null,
                            "Seleccione el ID de la persona a actualizar", "Actualizar Persona",
                            JOptionPane.QUESTION_MESSAGE, null, ids, ids[0]);
                    Persona personaToView = gp.personas.stream().filter(p -> p.getId().equals(idToView)).findFirst()
                            .orElse(null);
                    JOptionPane.showMessageDialog(null, personaToView.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}