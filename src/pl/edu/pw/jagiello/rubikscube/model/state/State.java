package pl.edu.pw.jagiello.rubikscube.model.state;

import pl.edu.pw.jagiello.rubikscube.model.Model;
import pl.edu.pw.jagiello.rubikscube.view.events.AllowedEvents;
import pl.edu.pw.jagiello.rubikscube.view.events.Event;

public abstract class State {

  protected final Model model;

  public State(Model model) {
    this.model = model;
  }

  public void handle(StateContext context, Event viewEvent) {
    // obsluz akcje zamkniecia
    if (viewEvent.getEvent() == AllowedEvents.EXIT) {
      System.exit(0);
    }
  }


}


