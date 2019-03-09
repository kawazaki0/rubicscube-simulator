package pl.edu.pw.jagiello.rubikscube.model.state;

import pl.edu.pw.jagiello.rubikscube.model.Model;
import pl.edu.pw.jagiello.rubikscube.view.events.Event;

public class StateContext {

  private State myState;

  public StateContext(Model model) {
    setState(new FreeState(model));
  }

  /**
   * Setter method for the state.
   * Normally only called by classes implementing the State interface.
   * @param newState the new state of this context
   */
  void setState(final State newState) {
    myState = newState;
    System.out.println(myState);
  }

  public void handle(Event viewEvent) {
    myState.handle(this, viewEvent);
  }
}
