
package formulario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class formulario4 extends JFrame {

    private Connection conexion;
    private String nombreBase;

    public formulario4(String usuario, String contrasena, String host, String puerto, String nombreBase) {
        this.nombreBase = nombreBase;
        
        // Configurar el título y el tamaño de la ventana
        setTitle("FORMULARIO 4: Crear Tabla en " + nombreBase);
        setSize(400, 200);

        // Crear un panel para organizar los componentes
        JPanel panel = new JPanel(new GridLayout(2, 2)); // Dos filas, dos columnas

        // Etiqueta y caja de texto para el nombre de la tabla
        JLabel lblNombreTabla = new JLabel("Nombre de la tabla:");
        JTextField txtNombreTabla = new JTextField(20);

        // Botón para crear la tabla
        JButton btnCrearTabla = new JButton("Crear Tabla");

        // Agregar componentes al panel
        panel.add(lblNombreTabla);
        panel.add(txtNombreTabla);
        panel.add(btnCrearTabla);

        // Agregar el panel al formulario4
        add(panel);

        // Agregar ActionListener al botón para crear la tabla
        btnCrearTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreTabla = txtNombreTabla.getText();
                if (!nombreTabla.isEmpty()) {
                    crearTabla(nombreTabla);
                } else {
                    JOptionPane.showMessageDialog(formulario4.this, "Por favor, ingrese un nombre para la tabla.");
                }
            }
        });

        // Establecer la conexión a la base de datos
        String url = "jdbc:mysql://" + host + ":" + puerto + "/" + nombreBase;
        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos: " + e.getMessage());
        }

        // Cierra la ventana cuando se hace clic en el botón de cierre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Método para crear una tabla en la base de datos
    private void crearTabla(String nombreTabla) {
        try {
            Statement statement = conexion.createStatement();
            String query = "CREATE TABLE " + nombreTabla + " (id INT PRIMARY KEY, name VARCHAR(255))";
            statement.execute(query);
            JOptionPane.showMessageDialog(this, "Tabla " + nombreTabla + " creada con éxito.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al crear la tabla: " + e.getMessage());
        }
    }
}
