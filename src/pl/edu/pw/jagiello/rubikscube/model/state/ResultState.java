package pl.edu.pw.jagiello.rubikscube.model.state;

import pl.edu.pw.jagiello.rubikscube.model.Model;
import pl.edu.pw.jagiello.rubikscube.view.View;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class ResultState extends State {

  private final static String FILENAME = "results.csv";
  private Thread freeStatePostponed;

  public ResultState(final StateContext context, final Model model, final View view) {
    super(model, view);
    long timeDelta = view.stopTimer();

    String result = formatSeconds(timeDelta);
    appendStrToFile(result);
    System.out.println(result);

    goFreeStateAfterPeriod(context, model, view, 1);
  }

  private void goFreeStateAfterPeriod(StateContext context, Model model, View view,
      final int seconds) {
    freeStatePostponed = new Thread(() -> {
      try {
        Thread.sleep(seconds * 1000);
        context.setState(() -> new FreeState(model, view));
      } catch (InterruptedException ignored) {
      }
    });
    freeStatePostponed.start();
  }

  @Override public void close() {
    freeStatePostponed.interrupt();
  }

  private String formatSeconds(long timeLeft) {
    double seconds = TimeUnit.NANOSECONDS.toMillis(timeLeft) / 1000.0;
    DecimalFormat df = new DecimalFormat("0.00");
    return df.format(seconds);
  }

  private void appendStrToFile(String str)
  {
    try {
      BufferedWriter out = new BufferedWriter(
          new FileWriter(FILENAME, true));
      DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
      LocalDateTime now = LocalDateTime.now();
      out.write(String.format("\"%s\",\"%s\"", str, dtf.format(now)));
      out.newLine();
      out.close();
    }
    catch (IOException e) {
      System.out.println("exception occoured" + e);
    }
  }
}
