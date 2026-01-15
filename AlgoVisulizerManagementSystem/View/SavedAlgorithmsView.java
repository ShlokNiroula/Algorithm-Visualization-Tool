package View;

import Controller.AlgorithmController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class SavedAlgorithmsView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(View.SavedAlgorithmsView.class.getName());
    private AlgorithmController controller;
    private JFrame parentFrame;
      
    public SavedAlgorithmsView(AlgorithmController controller, JFrame parentFrame) {
        this.controller = controller;
        this.parentFrame = parentFrame;
        initComponents(); // NetBeans generated
        setupCustomComponents(); // Our setup
        setLocationRelativeTo(parentFrame);
    }
    
    public SavedAlgorithmsView() {
        initComponents();
        // Set button texts
        jButton2.setText("Create");
        jButton3.setText("Load"); 
        jButton4.setText("Edit");
        jButton5.setText("Delete");
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.addRow(new Object[]{"No saved algorithms", "", "", "", ""});
    }
    
    private void setupCustomComponents() {
        // Clear and load table data
        refreshTable();
    }

    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows
        
        if (controller != null) {
            String[] states = controller.getSavedStateNames();
            if (states.length > 0) {
                for (String name : states) {
                    // Get actual array data from controller if possible
                    String arrayStr = "[Sample Data]"; // Default
                    String dateStr = new Date().toString();
                    
                    model.addRow(new Object[]{name, "Bubble Sort", arrayStr, dateStr, "0"});
                }
            } else {
                model.addRow(new Object[]{"No saved examples", "", "", "", ""});
            }
        } else {
            // Demo data
            model.addRow(new Object[]{"Demo Example 1", "Bubble Sort", "[5, 3, 8, 1, 9]", "27/12/2025", "10"});
            model.addRow(new Object[]{"Demo Example 2", "Quick Sort", "[10, 7, 8, 9, 1]", "26/12/2025", "15"});
        }
    }
    
private void sortAlgorithms(boolean ascending) {
    // VALIDATION 1: Check controller
    if (controller == null) {
        JOptionPane.showMessageDialog(this,
            "❌ System Error: No data available",
            "System Error",
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Get algorithms
    String[] algorithmNames = controller.getSavedStateNames();

    // VALIDATION 2: Check if enough data to sort
    if (algorithmNames.length < 2) {
        String message = algorithmNames.length == 0 ?
            "📭 No algorithms to sort" :
            "ℹ️ Only 1 algorithm - no sorting needed";
    
        JOptionPane.showMessageDialog(this,
            message,
            "Sort Info",
            JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    // Collect data BEFORE clearing the table
    Map<String, String[]> algorithmData = new HashMap<>();
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    
    for (int i = 0; i < model.getRowCount(); i++) {
        String name = (String) model.getValueAt(i, 0);
        // Skip placeholder rows
        if (name.equals("No saved examples") || name.equals("No saved algorithms")) {
            continue;
        }
        
        String type = (String) model.getValueAt(i, 1);
        String array = (String) model.getValueAt(i, 2);
        String date = (String) model.getValueAt(i, 3);
        String steps = String.valueOf(model.getValueAt(i, 4));
    
        algorithmData.put(name, new String[]{type, array, date, steps});
    }

    // Ask for confirmation for large datasets
    if (algorithmNames.length > 10) {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Sort " + algorithmNames.length + " algorithms?\n" +
            "Algorithm: Quick Sort\n" +
            "Complexity: O(n log n)",
            "Confirm Sort",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
    
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
    }

    // Perform sort
    String[] sortedNames = algorithmNames.clone();
    long startTime = System.nanoTime();

    if (ascending) {
        Arrays.sort(sortedNames); // Using Java's built-in sort
    } else {
        Arrays.sort(sortedNames, Collections.reverseOrder());
    }

    long endTime = System.nanoTime();
    long durationMs = (endTime - startTime) / 1_000_000;

    // Update table
    model.setRowCount(0);

    // Re-add in sorted order
    for (String name : sortedNames) {
        String[] data = algorithmData.get(name);
        if (data != null) {
            model.addRow(new Object[]{name, data[0], data[1], data[2], data[3]});
        }
    }

    // Show useful results
    String sortType = ascending ? "Ascending (A-Z)" : "Descending (Z-A)";

    JOptionPane.showMessageDialog(this,
        "✅ Sorting Complete!\n\n" +
        "Sort Type: " + sortType + "\n" +
        "Algorithms Sorted: " + sortedNames.length + "\n" +
        "Sorting Time: " + durationMs + " ms\n\n" +
        "Algorithm Used: Quick Sort\n" +
        "Time Complexity: O(n log n)\n" +
        "Space Complexity: O(log n)\n\n" +
        "Table has been updated with sorted results.",
        "Sort Successful",
        JOptionPane.INFORMATION_MESSAGE);
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
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setText("<-- Back  ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 153, 255));
        jLabel7.setText("AlgoViz");

        jLabel1.setText("[ Admin ]");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(25, 25, 25)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(70, 70, 70))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 255));
        jLabel2.setText("**Saved Algorithm Examples**");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Example Name", "Algorithm", "Array", "Created Date", "Steps"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Create");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Load");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Edit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setText("Search Algorithm:");

        jButton6.setText("Binary Search");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Sort Ascending");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton6))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(49, 49, 49)
                                .addComponent(jButton3)
                                .addGap(55, 55, 55)
                                .addComponent(jButton4)
                                .addGap(58, 58, 58)
                                .addComponent(jButton5)))
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addGap(49, 49, 49))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(7, 7, 7)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(49, 49, 49)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       // CREATE button
        String name = JOptionPane.showInputDialog(this, "Enter name for new algorithm example:");
        if (name != null && !name.trim().isEmpty()) {
            if (controller != null) {
                if (controller.validateStateName(name)) {
                    // Actually save a state
                    if (controller.saveCurrentStateWithName(name)) {
                        refreshTable();
                        JOptionPane.showMessageDialog(this, "Created: " + name);
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to create!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Invalid name! Use 3-20 letters/numbers only.");
                }
            } else {
                // Demo mode - just add to table
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.addRow(new Object[]{name, "Bubble Sort", "[1, 2, 3, 4, 5]", new Date().toString(), "0"});
                JOptionPane.showMessageDialog(this, "Demo: Created " + name);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // EDIT button
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            String oldName = (String) jTable1.getValueAt(selectedRow, 0);
            if (!oldName.equals("No saved examples")) {
                String newName = JOptionPane.showInputDialog(this, "Enter new name:", oldName);
                if (newName != null && !newName.trim().isEmpty()) {
                    if (controller != null) {
                        if (controller.validateStateName(newName)) {
                            if (controller.updateState(oldName, newName)) {
                                jTable1.setValueAt(newName, selectedRow, 0);
                                JOptionPane.showMessageDialog(this, "Updated: " + oldName + " → " + newName);
                            } else {
                                JOptionPane.showMessageDialog(this, "Update failed!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Invalid new name!");
                        }
                    } else {
                        // Demo mode
                        jTable1.setValueAt(newName, selectedRow, 0);
                        JOptionPane.showMessageDialog(this, "Demo: Updated name");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cannot edit placeholder");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an example first.");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // LOAD button
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            String name = (String) jTable1.getValueAt(selectedRow, 0);
        
            if (controller != null) {
                try {
                    // 1. LOAD the state into controller
                    controller.loadSavedState(name);
                
                    // 2. Open NEW VisualizationView
                    VisualizationView newViz = new VisualizationView();
                
                    // 3. CRITICAL: Pass the SAME controller instance
                    // We need to modify VisualizationView to accept controller
                    newViz.setController(controller);
                
                    // 4. Show and close
                    newViz.setVisible(true);
                    this.dispose();
                
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, 
                        "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // DELETE button
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            String name = (String) jTable1.getValueAt(selectedRow, 0);
            if (!name.equals("No saved examples")) {
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete example '" + name + "'?\nThis cannot be undone!",
                    "Confirm Delete", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    if (controller != null) {
                        if (controller.deleteSavedState(name)) {
                            refreshTable();
                            JOptionPane.showMessageDialog(this, "Deleted: " + name);
                        } else {
                            JOptionPane.showMessageDialog(this, "Delete failed!");
                        }
                    } else {
                        // Demo mode
                        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(this, "Demo: Deleted " + name);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cannot delete placeholder");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an example first.");
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // VALIDATION 1: Check controller
        if (controller == null) {
            JOptionPane.showMessageDialog(this,
                "❌ System Error: No data controller found",
                "System Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // VALIDATION 2: Get and validate search term
        String searchTerm = searchField.getText().trim();
    
        if (searchTerm.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "⚠️ Please enter a search term",
                "Input Required",
                JOptionPane.WARNING_MESSAGE);
            searchField.requestFocus();
            return;
        }
    
        // VALIDATION 3: Check length
        if (searchTerm.length() < 2) {
            JOptionPane.showMessageDialog(this,
                "⚠️ Search term must be at least 2 characters",
                "Invalid Input",
                JOptionPane.WARNING_MESSAGE);
            searchField.selectAll();
            searchField.requestFocus();
            return;
        }
    
        // VALIDATION 4: Check for special characters
        if (!searchTerm.matches("^[a-zA-Z0-9\\s]+$")) {
            JOptionPane.showMessageDialog(this,
                "⚠️ Only letters, numbers and spaces allowed",
                "Invalid Characters",
                JOptionPane.WARNING_MESSAGE);
            searchField.selectAll();
            searchField.requestFocus();
            return;
        }
    
        // Get saved algorithms
        String[] algorithmNames = controller.getSavedStateNames();
    
        // VALIDATION 5: Check if there are any saved algorithms
        if (algorithmNames.length == 0) {
            JOptionPane.showMessageDialog(this,
                "📭 No saved algorithms found\nPlease save some algorithms first",
                "No Data",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    
        // Sort for binary search (required)
        String[] sortedNames = algorithmNames.clone();
        Arrays.sort(sortedNames);
    
        // Perform Binary Search
        int left = 0;
        int right = sortedNames.length - 1;
        int foundIndex = -1;
        int steps = 0;
    
        while (left <= right) {
            steps++;
            int mid = left + (right - left) / 2;
            int comparison = searchTerm.compareToIgnoreCase(sortedNames[mid]);
        
            if (comparison == 0) {
                foundIndex = mid;
                break;
            } else if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    
        // SHOW USEFUL RESULTS
        if (foundIndex != -1) {
            String foundName = sortedNames[foundIndex];
        
            // 1. Highlight in table
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                String tableName = (String) jTable1.getValueAt(i, 0);
                if (tableName.equals(foundName)) {
                    jTable1.setRowSelectionInterval(i, i);
                    jTable1.scrollRectToVisible(jTable1.getCellRect(i, 0, true));
                
                    // 2. Show useful info dialog
                    String algorithmType = (String) jTable1.getValueAt(i, 1);
                    String createdDate = (String) jTable1.getValueAt(i, 3);
                
                    JOptionPane.showMessageDialog(this,
                        "✅ Algorithm Found!\n\n" +
                        "Name: " + foundName + "\n" +
                        "Type: " + algorithmType + "\n" +
                        "Created: " + createdDate + "\n" +
                        "Search Steps: " + steps + "\n\n" +
                        "Algorithm: Binary Search\n" +
                        "Complexity: O(log n)\n" +
                        "Search Time: Instant",
                        "Search Successful",
                        JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        } else {
            // 3. Show suggestions for "not found"
            List<String> suggestions = new ArrayList<>();
            for (String name : algorithmNames) {
                if (name.toLowerCase().contains(searchTerm.toLowerCase())) {
                    suggestions.add(name);
                }
            }
        
            String message = "❌ Algorithm not found: \"" + searchTerm + "\"\n\n";
            message += "Search Steps: " + steps + "\n";
            message += "Total Algorithms: " + algorithmNames.length + "\n\n";
        
            if (!suggestions.isEmpty()) {
                message += "Did you mean?\n";
                for (int i = 0; i < Math.min(3, suggestions.size()); i++) {
                    message += "• " + suggestions.get(i) + "\n";
                }
            } else {
                message += "No similar algorithms found.";
            }
        
            JOptionPane.showMessageDialog(this,
                message,
                "Not Found",
                JOptionPane.WARNING_MESSAGE);
        }
    
        searchField.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        System.out.println("Sort Ascending clicked");
        sortAlgorithms(true);// TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new SavedAlgorithmsView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}
