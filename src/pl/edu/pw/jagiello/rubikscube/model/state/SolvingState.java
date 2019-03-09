package pl.edu.pw.jagiello.rubikscube.model.state;

import pl.edu.pw.jagiello.rubikscube.model.Model;
import pl.edu.pw.jagiello.rubikscube.view.View;
import pl.edu.pw.jagiello.rubikscube.view.events.Event;
import pl.edu.pw.jagiello.rubikscube.view.events.CubeEvent;

public class SolvingState extends State {

  public SolvingState(Model model, View view) {
    super(model, view);
    view.startTimer();
  }

  @Override public void handle(StateContext context, Event viewEvent) {
    super.handle(context, viewEvent);

    if (viewEvent instanceof CubeEvent) {
      model.makeMove(viewEvent.getEvent(), ((CubeEvent) viewEvent).getCount());
    }
    if (model.isSolved()) {
      context.setState(() -> new ResultState(context, model, view));
    }
  }

  @Override public void close() {
    view.stopTimer();
  }
}
