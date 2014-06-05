package serverBro.events.networkEvents;



public class MessageEvent extends NetworkEvent {

  public final String message;

  public MessageEvent(String message) {
    super();
    this.message = message;
  }


  @Override
  public void execute() {
    getController().model.addMessage(message);
  }


}
