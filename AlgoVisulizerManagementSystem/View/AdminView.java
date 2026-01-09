/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.AlgorithmController;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Font;

/**
 *
 * @author Asus
 */
public class AdminView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminView.class.getName());
    private AlgorithmController controller;
    
    
    /**
     * Creates new form AdminView
     */
    public AdminView(AlgorithmController controller) {
        this.controller = controller;
        initComponents();
        loadData(); // Call this after UI is initialized
    }
    
    public AdminView() {
    initComponents();
    loadDemoData(); // Load demo data instead of real data
    }

    private void loadData() {
        if (controller == null) {
            System.err.println("Controller is null!");
            return;
        }
        try {
            // 1. SYSTEM OVERVIEW DATA
            StringBuilder systemOverview = new StringBuilder();
            systemOverview.append("=== SYSTEM STATISTICS ===\n\n");
            systemOverview.append("Total Saved States: ").append(controller.getSavedStatesCount()).append("\n");
            systemOverview.append("Saved State Names: ").append(controller.getSavedStateNames().length).append("\n");
            systemOverview.append("Recent Operations: ").append(controller.getRecentOperations().size()).append("\n");
            systemOverview.append("Undo Stack Size: ").append(controller.getUndoStack().size()).append("\n");
            systemOverview.append("Priority Algorithms: ").append(controller.getPriorityAlgorithms().size()).append("\n");
            systemOverview.append("System Status: ONLINE\n");
            
            // Update system overview panel
            updatePanelText(jPanel3, systemOverview.toString(), "SYSTEM OVERVIEW");
    
            // 2. RECENT ACTIVITIES (Queue)
            StringBuilder recentActivities = new StringBuilder();
            recentActivities.append("=== RECENT ACTIVITIES ===\n\n");
            Queue<String> recentOps = controller.getRecentOperations();
            if (recentOps.isEmpty()) {
                recentActivities.append("No recent activities\n");
            } else {
                int count = 1;
                for (String activity : recentOps) {
                    recentActivities.append(count++).append(". ").append(activity).append("\n");
                }
            }
            
            // Update recent activities panel
            updatePanelText(jPanel4, recentActivities.toString(), "RECENT ACTIVITIES (Queue)");
            
            // 3. POPULAR ALGORITHMS (PriorityQueue)
            StringBuilder popularAlgos = new StringBuilder();
            popularAlgos.append("=== POPULAR ALGORITHMS ===\n\n");
            PriorityQueue<String> priorityAlgos = controller.getPriorityAlgorithms();
            if (priorityAlgos.isEmpty()) {
                popularAlgos.append("No algorithm data\n");
            } else {
                int rank = 1;
                // Need to copy to preserve original queue
                PriorityQueue<String> copy = new PriorityQueue<>(priorityAlgos);
                while (!copy.isEmpty()) {
                    popularAlgos.append(rank++).append(". ").append(copy.poll()).append("\n");
                }
            }
            
            // Update popular algorithms panel
            updatePanelText(jPanel5, popularAlgos.toString(), "POPULAR ALGORITHMS (Priority Queue)");
            
        } catch (Exception e) {
            System.err.println("Error loading admin data: " + e.getMessage());
            loadDemoData();
        }
    }
        
    private void loadDemoData() {
        // Demo data for when controller is null
        String demoSystem = "*** DEMO SYSTEM DATA ***\n\n" +
                           "Total Saved States: 24\n" +
                           "Saved State Names: 5\n" +
                           "Recent Operations: 3\n" +
                           "Undo Stack Size: 2\n" +
                           "Priority Algorithms: 3\n" +
                           "System Status: ONLINE (DEMO)";
        
        String demoActivities = "*** DEMO ACTIVITIES ***\n\n" +
                               "1. User 'student1' completed Bubble Sort\n" +
                               "2. New algorithm 'Quick Sort' added\n" +
                               "3. System backup completed\n" +
                               "4. 5 new users registered\n" +
                               "5. Algorithm 'Binary Search' updated";
        
        String demoAlgorithms = "*** DEMO ALGORITHMS ***\n\n" +
                               "1. Bubble Sort - Priority: 5\n" +
                               "2. Quick Sort - Priority: 4\n" +
                               "3. Binary Search - Priority: 3\n" +
                               "4. Linear Search - Priority: 2\n" +
                               "5. Insertion Sort - Priority: 1";
        
        updatePanelText(jPanel3, demoSystem, "SYSTEM OVERVIEW (DEMO)");
        updatePanelText(jPanel4, demoActivities, "RECENT ACTIVITIES (Queue - DEMO)");
        updatePanelText(jPanel5, demoAlgorithms, "POPULAR ALGORITHMS (Priority Queue - DEMO)");
    }
    
    private void updatePanelText(JPanel panel, String text, String title) {
        // Clear the panel
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        
        // Add title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Add text area
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Monospaced", Font.PLAIN, 12));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Refresh the panel
        panel.revalidate();
        panel.repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setText("ALGORITHM LEARNING SYSTEM -      ADMIN PANEL");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("SYSTEM OVERVIEW");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setText("RECENT ACTIVITIES (Queue)");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setText("POPULAR ALGORITHMAS (Priority Queue)");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setText("QUICK ACTION");

        jButton1.setText("View All Algorithms");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Manage Users");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("System Settings");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Generate Report");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton1)
                        .addGap(41, 41, 41)
                        .addComponent(jButton2)
                        .addGap(38, 38, 38)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton5.setText("Back to Home");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Refresh");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(380, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // View All Algorithms
        try {
            if (controller != null) {
                new SavedAlgorithmsView(controller, this).setVisible(true);
                this.setVisible(false);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Please open Admin Dashboard from Home screen for full functionality.", 
                    "Info", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error: " + e.getMessage(), 
                "Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Manage Users
        javax.swing.JOptionPane.showMessageDialog(this, 
            "User Management Features:\n" +
            "• Create new user accounts\n" +
            "• Edit user profiles\n" +
            "• Delete users\n" +
            "• Set user permissions\n" +
            "• Track user activity", 
            "User Management", 
            javax.swing.JOptionPane.INFORMATION_MESSAGE);// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // Back to Home
        new HomeView().setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // System Settings
        javax.swing.JOptionPane.showMessageDialog(this, 
            "System Settings:\n" +
            "• Algorithm configurations\n" +
            "• System preferences\n" +
            "• Database settings\n" +
            "• Security options", 
            "System Settings", 
            javax.swing.JOptionPane.INFORMATION_MESSAGE);// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Refresh
        if (controller != null) {
            loadData();
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Data refreshed from controller!", 
                "Refresh", 
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            loadDemoData();
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Demo data refreshed!", 
                "Refresh", 
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Generate Report
        StringBuilder report = new StringBuilder();
        report.append("=== SYSTEM REPORT ===\n\n");
        report.append("Generated: ").append(new Date()).append("\n");
        
        if (controller != null) {
            report.append("Total Saved States: ").append(controller.getSavedStatesCount()).append("\n");
            report.append("Recent Operations: ").append(controller.getRecentOperations().size()).append("\n");
            report.append("Undo Stack Size: ").append(controller.getUndoStack().size()).append("\n");
            report.append("Status: CONNECTED\n");
        } else {
            report.append("Total Saved States: 24 (DEMO)\n");
            report.append("Recent Operations: 5 (DEMO)\n");
            report.append("Undo Stack Size: 2 (DEMO)\n");
            report.append("Status: DEMO MODE\n");
        }
        
        javax.swing.JTextArea reportArea = new javax.swing.JTextArea(report.toString());
        reportArea.setEditable(false);
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(reportArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
        
        javax.swing.JOptionPane.showMessageDialog(this, scrollPane, "System Report", 
            javax.swing.JOptionPane.INFORMATION_MESSAGE);// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new AdminView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
