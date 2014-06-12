package serverBro.broShared.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Serializer class giving the application the option to cache its current data to disk, in order to
 * resume its business when restarted
 * 
 * @author Patrick
 * 
 */
public class Serializer {

  private static Serializer instance;

  private Serializer() {};

  public synchronized static Serializer getInstance() {
    if (instance == null)
      instance = new Serializer();
    return instance;
  }

  public void serialize(Object obj, String path) {
    try {
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(path)));
      out.writeObject(obj);
      out.close();
    } catch (IOException e) {
      Logger.error("Unable to serialize to disk", e);
    }
  }

  public Object deserialize(String path) {
    InputStream in = null;
    try {
      in = new FileInputStream(new File(path));
    } catch (IOException e) {
      Logger.error("Unable to deserialize from disk", e);
    }
    return deserialize(in);
  }

  public Object deserialize(InputStream in) {
    Object obj = null;
    try {
      obj = new ObjectInputStream(in).readObject();
      in.close();
    } catch (ClassNotFoundException | IOException e) {
      Logger.error("Couldn't open objectInputStream", e);
    }
    return obj;
  }
}
