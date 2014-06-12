package serverBro.broShared.events;

import java.io.IOException;
import java.io.Serializable;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SealedObject;

public class SecureEvent extends SealedObject {

  public SecureEvent(Serializable object, Cipher c) throws IOException, IllegalBlockSizeException {
    super(object, c);

    // TODO Auto-generated constructor stub
  }
}
