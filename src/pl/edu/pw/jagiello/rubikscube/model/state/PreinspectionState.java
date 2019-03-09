package pl.edu.pw.jagiello.rubikscube.model.state;

import pl.edu.pw.jagiello.rubikscube.model.Model;
import pl.edu.pw.jagiello.rubikscube.view.events.AllowedEvents;
import pl.edu.pw.jagiello.rubikscube.view.events.Event;
import pl.edu.pw.jagiello.rubikscube.view.events.MultipleEvent;

public class PreinspectionState extends State {

  private Thread solvingStatePostponed;

  public PreinspectionState(final StateContext context, final Model model) {
    super(model);

    solvingStatePostponed = new Thread() {
      public void run() {
        try {
          Thread.sleep(4000);
          context.setState(new SolvingState(model));
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
        context.setState(new SolvingState(model));
        model.makeMove(viewEvent.getEvent(), ((MultipleEvent) viewEvent).getCount());
      } else if (viewEvent.getEvent().isInGroup(AllowedEvents.Group.ROTATE)) {
        model.makeMove(viewEvent.getEvent(), ((MultipleEvent) viewEvent).getCount());
      }
    }
  }
}
