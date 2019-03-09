package pl.edu.pw.jagiello.rubikscube.model.state;

import pl.edu.pw.jagiello.rubikscube.model.Model;
import pl.edu.pw.jagiello.rubikscube.view.View;
import pl.edu.pw.jagiello.rubikscube.view.events.AllowedEvents;
import pl.edu.pw.jagiello.rubikscube.view.events.Event;

public class ResultState extends State {

  private Thread freeStatePostponed;

  public ResultState(final StateContext context, final Model model, final View view) {
    super(model, view);
    view.stopTimer();

    freeStatePostponed = new Thread() {
      public void run() {
        try {
          Thread.sleep(3000);
          context.setState(new FreeState(model, view));
        } catch (InterruptedException ignored) {
        }
      }
    };
    freeStatePostponed.start();
  }

  @Override public void handle(StateContext context,
      Event viewEvent) {
    super.handle(context, viewEvent);

    if (viewEvent.getEvent() == AllowedEvents.SCRAMBLE) {
      freeStatePostponed.interrupt();
      this.model.scramble();
      context.setState(new PreinspectionState(context, model, view));
    }
  }
}
