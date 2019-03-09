package pl.edu.pw.jagiello.rubikscube.model.state;

import pl.edu.pw.jagiello.rubikscube.model.Model;
import pl.edu.pw.jagiello.rubikscube.view.View;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class ResultState extends State {

  private Thread freeStatePostponed;

  public ResultState(final StateContext context, final Model model, final View view) {
    super(model, view);
    long timeDelta = view.stopTimer();
    System.out.println(formatSeconds(timeDelta));

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
}
