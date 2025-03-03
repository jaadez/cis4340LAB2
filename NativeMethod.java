// Rule 00. Input Validation and Data Sanitization (IDS)

if (loginSuccessful) {
    logger.severe("User login succeeded for: " + username);
  } else {
    logger.severe("User login failed for: " + username);
  }

// Rule 02. Expressions (EXP)
public void deleteFile(){
 
    File someFile = new File("someFileName.txt");
    // Do something with someFile
    someFile.delete();
   
  }


// Rule 03. Numeric Types and Operations (NUM)

public static int getInteger(DataInputStream is) throws IOException {
    return is.readInt(); 
  }

// Rule 04. Characters and Strings (STR)
BigInteger x = new BigInteger("530500452766");
byte[] byteArray = x.toByteArray();
String s = new String(byteArray);
byteArray = s.getBytes();
x = new BigInteger(byteArray);

// Rule 06. Methods (MET)

public static int getAbsAdd(int x, int y) {
    assert x != Integer.MIN_VALUE;
    assert y != Integer.MIN_VALUE;
    int absX = Math.abs(x);
    int absY = Math.abs(y);
    assert (absX <= Integer.MAX_VALUE - absY);
    return absX + absY;
  }

//   USER CHOSEN VIOLATIONS


// Rule 11. Thread Pools (TPS)

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

final class PoolService {
    private final ExecutorService pool = Executors.newFixedThreadPool(10);
   
    public void doSomething() {
      pool.execute(new Task());
    }
  }
   
  final class Task implements Runnable {
    @Override public void run() {
      // ...
      throw new NullPointerException();
      // ...
    }
  }

// Rule 17. Java Native Interface (JNI)
  public final class NativeMethod {
 
    // Public native method
    public native void nativeOperation(byte[] data, int offset, int len);
   
    // Wrapper method that lacks security checks and input validation
    public void doOperation(byte[] data, int offset, int len) {
      nativeOperation(data, offset, len);
    }
     
    static {
      // Load native library in static initializer of class
      System.loadLibrary("NativeMethodLib");
    }
  }

// Rule 09. Locking (LCK)
private int count = 0;
private final Integer Lock = count; // Boxed primitive Lock is shared
 
public void doSomething() {
  synchronized (Lock) {
    count++;
    // ...
  }
}

// Rule 05. Object Orientation (OBJ)
public class Widget {
  public int total; // Number of elements
 
  void add() {
    if (total < Integer.MAX_VALUE) {     
      total++;
      // ...
    } else {
      throw new ArithmeticException("Overflow");
    }
  }
 
  void remove() { 
    if (total > 0) {     
      total--;
      // ...
    } else {
      throw new ArithmeticException("Overflow");
    }
  }
}

// Rule 15. Platform Security (SEC)
private void privilegedMethod(final String filename)
                              throws FileNotFoundException {
  try {
    FileInputStream fis =
        (FileInputStream) AccessController.doPrivileged(
          new PrivilegedExceptionAction() {
        public FileInputStream run() throws FileNotFoundException {
          return new FileInputStream(filename);
        }
      }
    );
    // Do something with the file and then close it
  } catch (PrivilegedActionException e) {
    // Forward to handler
  }
}