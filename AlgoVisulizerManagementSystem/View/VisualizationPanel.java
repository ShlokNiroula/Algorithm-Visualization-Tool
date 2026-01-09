package View;

import javax.swing.*;
import java.awt.*;

public class VisualizationPanel extends JPanel {
    private int[] array;
    private int comparingIndex1 = -1;
    private int comparingIndex2 = -1;
    
    public VisualizationPanel() {
        // Start with larger values for visibility
        this.array = new int[]{70, 40, 90, 30, 80, 50, 60, 20, 100, 10};
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 400)); // Fixed size
    }
    
    public void setArray(int[] array) {
        this.array = array;
        repaint(); // Force redraw
    }
    
    public void setComparingIndices(int i, int j) {
        this.comparingIndex1 = i;
        this.comparingIndex2 = j;
        repaint();
    }
   
    public int[] getArray() {
        return array != null ? array.clone() : new int[0];
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (array == null || array.length == 0) {
            // Draw placeholder if no array
            g.setColor(Color.RED);
            g.drawString("No array data", 50, 50);
            return;
        }
        
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        
        // Fixed dimensions for testing
        int drawingWidth = 700;
        int drawingHeight = 300;
        int startX = (panelWidth - drawingWidth) / 2;
        int startY = (panelHeight - drawingHeight) / 2;
        
        // Find max value for scaling
        int maxValue = 0;
        for (int val : array) {
            if (val > maxValue) maxValue = val;
        }
        
        if (maxValue == 0) maxValue = 1; // Avoid division by zero
        
        int barWidth = drawingWidth / array.length;
        int spacing = Math.max(2, barWidth / 4);
        barWidth = barWidth - spacing;
        
        // Draw each bar
        for (int i = 0; i < array.length; i++) {
            // Scale bar height (0 to drawingHeight)
            int barHeight = (int)((array[i] / (double)maxValue) * drawingHeight);
            if (barHeight < 5) barHeight = 5; // Minimum height
            
            int x = startX + i * (barWidth + spacing);
            int y = startY + drawingHeight - barHeight;
            
            // Choose color: red for comparing, blue otherwise
            if (i == comparingIndex1 || i == comparingIndex2) {
                g.setColor(Color.RED);
            } else {
                g.setColor(new Color(30, 144, 255)); // Blue
            }
            
            // Draw the bar
            g.fillRect(x, y, barWidth, barHeight);
            
            // Draw border
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);
            
            // Draw value on top
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            String value = String.valueOf(array[i]);
            int textWidth = g.getFontMetrics().stringWidth(value);
            g.drawString(value, x + (barWidth - textWidth) / 2, y - 5);
            
            // Draw index below
            g.drawString("[" + i + "]", x + (barWidth - 10) / 2, startY + drawingHeight + 15);
        }
        
        // Draw title
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Bubble Sort Visualization", startX, startY - 20);
        
        // Draw scale info
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Max Value: " + maxValue, startX, startY + drawingHeight + 40);
    }
}