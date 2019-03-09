package pl.edu.pw.jagiello.rubikscube.model.state;

import pl.edu.pw.jagiello.rubikscube.model.Model;
import pl.edu.pw.jagiello.rubikscube.view.View;
import pl.edu.pw.jagiello.rubikscube.view.events.AllowedEvents;
import pl.edu.pw.jagiello.rubikscube.view.events.Event;
import pl.edu.pw.jagiello.rubikscube.view.events.CubeEvent;

public class PreinspectionState extends State {

  private Thread solvingStatePostponed;

  public PreinspectionState(final StateContext context, final Model model, final View view) {
    super(model, view);
    final int seconds = 12;
    view.countDownPreinspectionTimer(seconds);
    solvingStatePostponed = new Thread() {
      public void run() {
        try {
          Thread.sleep(seconds * 1000);
          context.setState(() -> new SolvingState(model, view));
        } catch (InterruptedException ignored) {
        }
      }
    };
    solvingStatePostponed.start();
  }

  @Override public void handle(final StateContext context, Event viewEvent) {
    super.handle(context, viewEvent);
    if (viewEvent instanceof CubeEvent) {
      if (viewEvent.getEvent().isInGroup(AllowedEvents.Group.MOVE)) {
        context.setState(() -> new SolvingState(model, view));
        model.makeMove(viewEvent.getEvent(), ((CubeEvent) viewEvent).getCount());
      } else if (viewEvent.getEvent().isInGroup(AllowedEvents.Group.ROTATE)) {
        model.makeMove(viewEvent.getEvent(), ((CubeEvent) viewEvent).getCount());
      }
    }
  }

  @Override public void close() {
    view.stopTimer();
    solvingStatePostponed.interrupt();
  }
}
