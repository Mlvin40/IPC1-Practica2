/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.Archivos;
import Backend.Ordenamiento;
import Backend.jugador.Jugador;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Phoenix
 */
public class VentanaReportes extends javax.swing.JFrame {

    /**
     * Creates new form Reportes
     */
    static JFrame frameAnterior;
    Archivos archivos;
    Ordenamiento ordenamiento;

    public VentanaReportes(JFrame frameAnterior) {
        archivos = new Archivos();
        ordenamiento = new Ordenamiento();
        this.frameAnterior = frameAnterior;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHistorial = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaNombres = new javax.swing.JTable();
        btnOrdenarMayor = new javax.swing.JButton();
        btnMejorJugador = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHistorial.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        btnHistorial.setText("HISTORIAL DE GANADORES");
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });
        getContentPane().add(btnHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, -1, -1));

        tablaNombres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ganador", "Punteo", "HoraDeJuego", "Perdedor"
            }
        ));
        jScrollPane2.setViewportView(tablaNombres);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 480, -1));

        btnOrdenarMayor.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        btnOrdenarMayor.setText("OrdenarMayorAMenor");
        btnOrdenarMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarMayorActionPerformed(evt);
            }
        });
        getContentPane().add(btnOrdenarMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 150, 20));

        btnMejorJugador.setText("MejorJugador");
        btnMejorJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMejorJugadorActionPerformed(evt);
            }
        });
        getContentPane().add(btnMejorJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoReportes.jpg"))); // NOI18N
        lblFondo.setText("jLabel1");
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed

        Jugador[] jugadores = archivos.leerArchivo();

        // Crear el modelo de datos del JTable
        DefaultTableModel model = (DefaultTableModel) tablaNombres.getModel();

        model.setRowCount(0);

        // Agregar los jugadores a la tabla 
        for (Jugador jugador : jugadores) {
            Object[] fila = {jugador.getNombre(), jugador.getPunteo(), jugador.getHoraDeJuego(), jugador.getNombrePerdedor()};
            model.addRow(fila);
        }
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
        frameAnterior.setVisible(true);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnOrdenarMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarMayorActionPerformed

        Jugador[] jugadores = archivos.leerArchivo();
        ordenamiento.ordenarPorPuntajeDescendente(jugadores);

        DefaultTableModel model = (DefaultTableModel) tablaNombres.getModel();

        model.setRowCount(0); //limpiar lo que hay

        // Agregar los jugadores a la tabla
        for (Jugador jugador : jugadores) {
            Object[] fila = {jugador.getNombre(), jugador.getPunteo(), jugador.getHoraDeJuego(), jugador.getNombrePerdedor()};
            model.addRow(fila);
        }
    }//GEN-LAST:event_btnOrdenarMayorActionPerformed

    private void btnMejorJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMejorJugadorActionPerformed
        Jugador[] jugadores = archivos.leerArchivo();
        ordenamiento.ordenarPorPuntajeDescendente(jugadores);
        jugadores[0].mostrarDatos();
    }//GEN-LAST:event_btnMejorJugadorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaReportes(frameAnterior).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnMejorJugador;
    private javax.swing.JButton btnOrdenarMayor;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JTable tablaNombres;
    // End of variables declaration//GEN-END:variables
}
