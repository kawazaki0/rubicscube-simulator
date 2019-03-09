package pl.edu.pw.jagiello.rubikscube.model.state;

import pl.edu.pw.jagiello.rubikscube.model.Model;
import pl.edu.pw.jagiello.rubikscube.view.View;
import pl.edu.pw.jagiello.rubikscube.view.events.Event;

import java.util.function.Supplier;

public class StateContext {

  private State myState;

  public StateContext(Model model, View view) {
    setState(() -> new FreeState(model, view));
  }

  /**
   * Setter method for the state.
   * Normally only called by classes implementing the State interface.
   * @param newState the new state of this context
   */
  void setState(final Supplier<State> newState) {
    if (myState != null) {
      myState.close();
    }
    myState = newState.get();
    System.out.println(myState);
  }

  public void handle(Event viewEvent) {
    myState.handle(this, viewEvent);
  }
}
