/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatastarter;

import java.awt.Color;
import utils.CardSwitcher;
import utils.ImageUtil;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author michael.roy-diclemen
 */
public class GamePanel extends javax.swing.JPanel implements MouseListener {

    public static final String CARD_NAME = "game";

    CardSwitcher switcher; // This is the parent panel
    Timer animTimer;
    // Image img1 = Toolkit.getDefaultToolkit().getImage("yourFile.jpg");
    BufferedImage img1;
    //variables to control your animation elements

    int x = 3;
    int y = 3;
    int panelHeight;
    int panelWidth;
    int interval;

    int gridRows = 20;
    int gridColumns = 20;

    int preyNum = 50;
    int predNum = 5;

    int step = 0;
    int preyVal = 21;
    boolean simStarted;
    automataSim sim;

    /**
     * Creates new form GamePanel
     */
    public GamePanel(CardSwitcher p) {

        //initializes program
        initComponents();
        sim = new automataSim(gridRows, gridColumns, preyNum, predNum, preyVal);

        this.setFocusable(true);

        // tell the program we want to listen to the mouse
        addMouseListener(this);
        //tells us the panel that controls this one
        switcher = p;
        //create and start a Timer for animation
        animTimer = new Timer(1000, new AnimTimerTick());
        animTimer.start();
        predStats.setText("Predators: " + predNum);
        preyStats.setText("Prey: " + preyNum);

        //set up the key bindings
        setupKeys();

    }

    private void setupKeys() {
        //these lines map a physical key, to a name, and then a name to an 'action'.  You will change the key, name and action to suit your needs
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftKey");
        this.getActionMap().put("leftKey", new Move("LEFT"));

        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "wKey");
        this.getActionMap().put("wKey", new Move("w"));

        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "dKey");
        this.getActionMap().put("dKey", new Move("d"));

        this.getInputMap().put(KeyStroke.getKeyStroke("X"), "xKey");
        this.getActionMap().put("xKey", new Move("x"));
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        //draws the grid
        for (int i = 0; i < gridRows; i++) {
            for (int j = 0; j < gridColumns; j++) {
                int stat = sim.statusCheck(i, j);
                switch (stat) {
                    case 0 ->
                        g.setColor(Color.white);
                    case 1 ->
                        g.setColor(Color.red);
                    case 2 ->
                        g.setColor(Color.BLACK);
                }
                g.fillRect(j * interval, i * interval, interval, interval);
            }

        }


    }
    
   

    /**
     * When the mountain is pressed
     *
     * @param me
     */
    public void mousePressed(MouseEvent me) {
        System.out.println("Press: " + me.getX() + ":" + me.getY());
    }

    /**
     * When the mouse button is released
     *
     * @param me
     */
    public void mouseReleased(MouseEvent me) {
        System.out.println("Release: " + me.getX() + ":" + me.getY());
    }

    /**
     * When the mouse enters the area
     *
     * @param me
     */
    public void mouseEntered(MouseEvent me) {
        System.out.println("Enter: " + me.getX() + ":" + me.getY());
    }

    /**
     * When the mouse exits the panel
     *
     * @param me
     */
    public void mouseExited(MouseEvent me) {
        System.out.println("Exit: " + me.getX() + ":" + me.getY());
    }

    /**
     * Everything inside here happens when you click on a captured key.
     */
    private class Move extends AbstractAction {

        String key;

        public Move(String akey) {
            key = akey;
        }

        public void actionPerformed(ActionEvent ae) {
            // here you decide what you want to happen if a particular key is pressed
            System.out.println("llll" + key);
            switch(key){
                case "d": x+=2; break;
                case "x": animTimer.stop(); switcher.switchToCard(EndPanel.CARD_NAME); break;
            }
            if (key.equals("d")) {
                x = x + 2;
            }
            
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        speedSlider = new javax.swing.JSlider();
        startButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        layoutPicker = new java.awt.Choice();
        predStats = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        preyStats = new javax.swing.JLabel();
        stepStat = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 204));
        setMaximumSize(new java.awt.Dimension(900, 500));
        setMinimumSize(new java.awt.Dimension(900, 500));
        setPreferredSize(new java.awt.Dimension(900, 500));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        speedSlider.setMajorTickSpacing(5);
        speedSlider.setMaximum(20);
        speedSlider.setMinimum(1);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                speedSliderStateChanged(evt);
            }
        });
        speedSlider.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                speedSliderPropertyChange(evt);
            }
        });

        startButton.setText("START");
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startButtonMouseClicked(evt);
            }
        });
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        stopButton.setText("STOP");
        stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stopButtonMouseClicked(evt);
            }
        });
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        resetButton.setText("RESET");
        resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetButtonMouseClicked(evt);
            }
        });

        layoutPicker.setName("Pattern Select"); // NOI18N

        predStats.setText("jLabel2");

        submitButton.setText("SUBMIT");
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitButtonMouseClicked(evt);
            }
        });

        preyStats.setText("pre");

        stepStat.setText("STEP: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(672, 672, 672)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(predStats, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(preyStats, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(layoutPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stepStat, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(resetButton)
                    .addComponent(startButton)
                    .addComponent(stopButton))
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(startButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stopButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resetButton)
                .addGap(49, 49, 49)
                .addComponent(layoutPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(predStats, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(preyStats, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stepStat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
        );

        layoutPicker.add("Alternating");
        layoutPicker.add("Lines");
        layoutPicker.add("Four Corners");
    }// </editor-fold>//GEN-END:initComponents

    //inits the frame
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        panelHeight = getSize().height;
        panelWidth = getSize().width - 200;
        interval = Math.min((panelHeight / gridRows), (panelWidth / gridColumns));


    }//GEN-LAST:event_formComponentShown

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stopButtonActionPerformed

    private void resetButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetButtonMouseClicked
        // TODO add your handling code here:
        
        //creates new sim and resets values
        sim = new automataSim(gridRows, gridColumns, preyNum, predNum, preyVal);
        step = 0;
        stepStat.setText("Step: " + step);
        predStats.setText("Predators: " + predNum);
        preyStats.setText("Prey: " + preyNum);
        repaint();
    }//GEN-LAST:event_resetButtonMouseClicked

    private void startButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startButtonMouseClicked
        // TODO add your handling code here:
        simStarted = true;
    }//GEN-LAST:event_startButtonMouseClicked

    private void stopButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopButtonMouseClicked
        // TODO add your handling code here:
        simStarted = false;
    }//GEN-LAST:event_stopButtonMouseClicked

    private void speedSliderPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_speedSliderPropertyChange
        // TODO add your handling code here:


    }//GEN-LAST:event_speedSliderPropertyChange

    private void submitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMouseClicked
        // TODO add your handling code here:
        
        //creates new sim and resets values
        int choice = layoutPicker.getSelectedIndex();

        sim = new automataSim(gridRows, gridColumns, preyVal, choice);
        step = 0;
        stepStat.setText("Step: " + step);
        predStats.setText("Predators: " + sim.numPred);
        preyStats.setText("Prey: " + sim.numPrey);
        repaint();
        
        

    }//GEN-LAST:event_submitButtonMouseClicked

    private void speedSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speedSliderStateChanged
        // updates speed of timer
        animTimer.setDelay(speedSlider.getValue() * 100);
        System.out.println("Slider value: " + speedSlider.getValue());
    }//GEN-LAST:event_speedSliderStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice layoutPicker;
    private javax.swing.JLabel predStats;
    private javax.swing.JLabel preyStats;
    private javax.swing.JButton resetButton;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel stepStat;
    private javax.swing.JButton stopButton;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables

    /**
     * This event captures a click which is defined as pressing and releasing in
     * the same area if it is within the grid, changes the status of the square
     * it is on
     * changes value of square which is clicked on
     * @param me
     */
    public void mouseClicked(MouseEvent me) {
        

        
        //gets x and y value of click  (in terms of pos in sim array)
        if (me.getX() < (interval * gridColumns) && me.getY() < (interval * gridRows)) {
            int yValue = (int) Math.floor(me.getX() / interval);
            int xValue = (int) Math.floor(me.getY() / interval);
            switch (sim.statusCheck(xValue, yValue)) {
                //if click on empty, becomes pred
                case 0:
                    sim.simulationGrid[xValue][yValue] = preyVal - 1;
                    sim.numPred ++;
                    predStats.setText("Predators: " + sim.numPred);
                    
                    break;
                    
                  //click on pred, becomes prey
                case 1:
                    sim.simulationGrid[xValue][yValue] = preyVal;
                    sim.numPred--;
                    sim.numPrey++;
                    predStats.setText("Predators: " + (sim.numPred));
                    preyStats.setText("Prey: " + (sim.numPrey));
                    
                    break;
                    
                  //click on prey, becomes emptyx
                case 2:
                    sim.simulationGrid[xValue][yValue] = 0;
                    sim.numPrey--;
                    preyStats.setText("Prey: " + sim.numPrey);
                    
                    break;
                default:
                    break;
            }
            repaint();

        }

    }



    /**
     * Everything inside here happens when you click on a captured key.
     */


    /**
     * Everything inside this actionPerformed will happen every time the
     * animation timer clicks.
     * takes another step in simulation and updates displays to match (if sim has been started)
     */
    private class AnimTimerTick implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            //updates simulation (if simulation has been started) and redraws each tick
            if (simStarted) {
                sim.step();
                predStats.setText("Predators: " + sim.numPred);
                preyStats.setText("Prey: " + sim.numPrey);
                stepStat.setText("Step: " + step);
                step++;

            }

            //force redraw
            repaint();
        }
    }
}
