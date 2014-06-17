package serverBro.broShared.misc;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

import serverBro.broShared.events.external.NetworkEvent;

public class CryptoManager {

  private static CryptoManager instance;

  private final Key key;

  private CryptoManager() {
    key = Config.getInstance().getSecretKey();
  }

  public synchronized static CryptoManager getInstance() {
    if (instance == null) {
      instance = new CryptoManager();
    }
    return instance;
  }

  private SecretKey generateKey() {
    SecretKey key = null;
    try {
      KeyGenerator generator = KeyGenerator.getInstance("DES");
      generator.init(new SecureRandom());
      key = generator.generateKey();
    } catch (NoSuchAlgorithmException e) {
      Logger.error("Unable to generate secretKey", e);
    }
    return key;
  }

  private Cipher generateCipher() {
    Cipher cipher = null;
    try {
      cipher = Cipher.getInstance("DES");
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      Logger.error("Unable to generate cipher", e);
    }
    return cipher;
  }

  public synchronized Serializable decrypt(Key key, SealedObject object) {
    Serializable decrypted = null;
    try {
      Cipher cipher = generateCipher();
      cipher.init(Cipher.DECRYPT_MODE, key);
      decrypted = (Serializable) object.getObject(cipher);
      Logger.log("decrypted: " + decrypted);
      Logger.log("object:" + object);
    } catch (ClassNotFoundException | ClassCastException | IllegalBlockSizeException | BadPaddingException | IOException | InvalidKeyException e) {
      Logger.error("Unable to decrypt object", e);
    } finally {
      if (decrypted == null) {
        System.out.println();
      }
    }
    return decrypted;
  }

  public synchronized SealedObject encrypt(Key key, Serializable object) {
    SealedObject encrypted = null;
    try {
      Cipher cipher = generateCipher();
      cipher.init(Cipher.ENCRYPT_MODE, key);
      encrypted = new SealedObject(object, cipher);
    } catch (IllegalBlockSizeException | IOException | InvalidKeyException e) {
      Logger.error("Unable to encrypt object", e);
    }
    return encrypted;
  }

  public static void main(String[] args) {
    CryptoManager cm = new CryptoManager();
    // Key key = cm.generateKey();
    Key key = cm.generateKey();
    Serializable msg = "dicks";

    System.out.println(msg.toString());
    msg = cm.encrypt(key, msg);
    Serializer.getInstance().serialize(msg, "derp");
    msg = (Serializable) Serializer.getInstance().deserialize("derp");
    msg = cm.decrypt(key, (SealedObject) msg);
    System.out.println(msg.toString());
  }

  public NetworkEvent decryptNetworkEvent(Serializable sealed) {
    NetworkEvent event = null;
    System.out.println(sealed);
    event = (NetworkEvent) decrypt(key, (SealedObject) sealed);
    return event;
  }

  public Serializable encryptNetworkEvent(Serializable event) {
    return encrypt(key, event);
  }
}
