package pl.edu.pw.jagiello.rubikscube.model.state;

import pl.edu.pw.jagiello.rubikscube.model.Model;
import pl.edu.pw.jagiello.rubikscube.view.View;
import pl.edu.pw.jagiello.rubikscube.view.events.Event;
import pl.edu.pw.jagiello.rubikscube.view.events.MultipleEvent;

public class FreeState extends State {

  public FreeState(Model model, View view) {
    super(model, view);
  }

  @Override public void handle(StateContext context,
      Event viewEvent) {
    super.handle(context, viewEvent);
    if (viewEvent instanceof MultipleEvent) {
      model.makeMove(viewEvent.getEvent(), ((MultipleEvent) viewEvent).getCount());
    }
  }
}
