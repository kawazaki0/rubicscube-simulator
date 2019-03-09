package pl.edu.pw.jagiello.rubikscube.model.state;

import pl.edu.pw.jagiello.rubikscube.model.Model;
import pl.edu.pw.jagiello.rubikscube.view.View;
import pl.edu.pw.jagiello.rubikscube.view.events.AllowedEvents;
import pl.edu.pw.jagiello.rubikscube.view.events.Event;
import pl.edu.pw.jagiello.rubikscube.view.events.MultipleEvent;

public class PreinspectionState extends State {

  private Thread solvingStatePostponed;

  public PreinspectionState(final StateContext context, final Model model, final View view) {
    super(model, view);
    final int seconds = 4;
    view.countDownPreinspectionTimer(seconds);
    solvingStatePostponed = new Thread() {
      public void run() {
        try {
          Thread.sleep(seconds * 1000);
          context.setState(new SolvingState(model, view));
        } catch (InterruptedException ignored) {
        }
      }
    };
    solvingStatePostponed.start();
  }

  @Override public void handle(final StateContext context, Event viewEvent) {
    super.handle(context, viewEvent);
    if (viewEvent instanceof MultipleEvent) {
      if (viewEvent.getEvent().isInGroup(AllowedEvents.Group.MOVE)) {
        solvingStatePostponed.interrupt();
        context.setState(new SolvingState(model, view));

        model.makeMove(viewEvent.getEvent(), ((MultipleEvent) viewEvent).getCount());
      } else if (viewEvent.getEvent().isInGroup(AllowedEvents.Group.ROTATE)) {
        model.makeMove(viewEvent.getEvent(), ((MultipleEvent) viewEvent).getCount());
      }
    } else if (viewEvent.getEvent() == AllowedEvents.SCRAMBLE) {
      solvingStatePostponed.interrupt();
      this.model.scramble();
      context.setState(new PreinspectionState(context, model, view));
    }
  }
}
