package it.unibo.oop.reactivegui03;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Third experiment with reactive gui.
 */
public final class AnotherConcurrentGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final double WIDTH_PERC = 0.2;
    private static final double HEIGHT_PERC = 0.1;
    private static final String DEFAULT = "0";
    private final JLabel display = new JLabel();
    private final JButton stop = new JButton("stop");
    private final JButton up = new JButton("up");
    private final JButton down = new JButton("down");

    public AnotherConcurrentGUI() {
        super();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        panel.add(display);
        display.setText(DEFAULT);
        panel.add(up);
        panel.add(down);
        panel.add(stop);
        this.getContentPane().add(panel);
        this.setVisible(true);

        final Agent agent = new Agent();
        new Thread(agent).start();
        stop.addActionListener((e) -> agent.stopCounting());
        up.addActionListener((e) -> agent.increaseCounting());
        down.addActionListener((e) -> agent.decreaseCounting());

        new Thread(() -> {try {
            Thread.sleep(10000);
            SwingUtilities.invokeAndWait(() -> agent.stopCounting());
        } catch (InterruptedException | InvocationTargetException e1) {
            e1.printStackTrace();
        }}).start();
    }

   

    private class Agent implements Runnable {
        private volatile boolean stop;
        private volatile boolean isEncreasing = true;
        private int counter;

        @Override
        public void run() {
            while (!this.stop) {
                try {
                    // The EDT doesn't access `counter` anymore, it doesn't need to be volatile 
                    final var nextText = Integer.toString(this.counter);
                    SwingUtilities.invokeAndWait(() -> AnotherConcurrentGUI.this.display.setText(nextText));
                    if(isEncreasing){
                        this.counter++;
                    } else {
                        this.counter--;
                    }
                    Thread.sleep(100);
                } catch (InvocationTargetException | InterruptedException ex) {
                    /*
                     * This is just a stack trace print, in a real program there
                     * should be some logging and decent error reporting
                     */
                    ex.printStackTrace();
                }
            }
        }
        public void stopCounting() {
            this.stop = true;
            up.setEnabled(false);
            down.setEnabled(false);
            AnotherConcurrentGUI.this.stop.setEnabled(false);
        }
        public void increaseCounting() {
            this.isEncreasing = true;
        }
        public void decreaseCounting() {
            this.isEncreasing = false;
        }
        
    }
}
