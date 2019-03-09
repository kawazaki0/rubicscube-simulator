package pl.edu.pw.jagiello.rubikscube.view.timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class TimerPanel extends JPanel {

  private JLabel label;
  private Timer timer;
  private long timeDelta;

  public TimerPanel() {
    label = new JLabel("Free mode", SwingConstants.LEFT);
    setBackground(Color.BLACK);
    label.setForeground(Color.WHITE);
    label.setPreferredSize(new Dimension(150, 50));
    add(label);
  }

  public void start() {
    startTimer(new MillisTimer(), 50);
  }

  public void countDown(int seconds) {
    startTimer(new CountDownTimer(seconds), 100);
  }

  public long stop() {
    timer.stop();
    return timeDelta;
  }

  private void startTimer(ActionListener timer, int delay) {
    if (this.timer != null) {
      this.timer.stop();
    }
    this.timer = new Timer(delay, timer);
    this.timer.setInitialDelay(0);
    this.timer.start();
  }


  class CountDownTimer implements ActionListener {

    private int seconds;
    private long startTime = -1;

    CountDownTimer(int seconds) {
      this.seconds = seconds;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (startTime == -1) {
        startTime = System.nanoTime();
      } else {
        long endTime = startTime + TimeUnit.SECONDS.toNanos(seconds);
        long time = System.nanoTime();

        if (time < endTime) {
          long timeLeft = (endTime - time);
          timeDelta = timeLeft;
          label.setText("Preinspection: " + TimeUnit.NANOSECONDS.toSeconds(timeLeft) + "s");
        } else {
          ((Timer) e.getSource()).stop();
        }
        revalidate();
        repaint();
      }
    }
  }


  class MillisTimer implements ActionListener {

    private long startTime = -1;

    @Override
    public void actionPerformed(ActionEvent e) {
      if (startTime == -1) {
        startTime = System.nanoTime();
      } else {
        long time = System.nanoTime();
        timeDelta = time - startTime;
        label.setText(formatSeconds(timeDelta) + " seconds");
        revalidate();
        repaint();
      }
    }

    private String formatSeconds(long timeLeft) {
      double seconds = TimeUnit.NANOSECONDS.toMillis(timeLeft) / 1000.0;
      DecimalFormat df = new DecimalFormat("0.0");
      return df.format(seconds);
    }
  }
}
