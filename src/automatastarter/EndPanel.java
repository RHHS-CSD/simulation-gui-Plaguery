/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatastarter;

import utils.CardSwitcher;
import javax.swing.JPanel;

/**
 *
 * @author michael.roy-diclemen
 */
public class EndPanel extends javax.swing.JPanel {
        public static final String CARD_NAME = "end";
 CardSwitcher switcher;
    /**
     * Creates new form EndPanel
     */
    public EndPanel(CardSwitcher p) {
        initComponents();
        switcher = p;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        againButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 51, 51));
        setMaximumSize(new java.awt.Dimension(900, 500));
        setMinimumSize(new java.awt.Dimension(900, 500));

        againButton.setText("RETURN TO START SCREEN");
        againButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                againButtonMouseClicked(evt);
            }
        });
        againButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                againButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(359, 359, 359)
                .addComponent(againButton)
                .addContainerGap(369, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(againButton)
                .addContainerGap(267, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void againButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_againButtonMouseClicked
        // TODO add your handling code here:
        //switches to intro when clicked
        switcher.switchToCard(IntroPanel.CARD_NAME);
    }//GEN-LAST:event_againButtonMouseClicked

    private void againButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_againButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_againButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton againButton;
    // End of variables declaration//GEN-END:variables
}
