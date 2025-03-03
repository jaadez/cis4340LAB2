// Rule 00. Input Validation and Data Sanitization (IDS)
Logger sanLogger = new SanitizedTextLogger(logger);
 
if (loginSuccessful) {
  sanLogger.severe("User login succeeded for: " + username);
} else {
  sanLogger.severe("User login failed for: " + username);
}
// Rule 02. Expressions (EXP)
public void deleteFile(){
 
  File someFile = new File("someFileName.txt");
  // Do something with someFile
  if (!someFile.delete()) {
    // Handle failure to delete the file
  }
 
}


// Rule 03. Numeric Types and Operations (NUM)
public static long getInteger(DataInputStream is) throws IOException {
  return is.readInt() & 0xFFFFFFFFL; // Mask with 32 one-bits
}

// Rule 04. Characters and Strings (STR)
BigInteger x = new BigInteger("530500452766");
String s = x.toString();  // Valid character data
byte[] byteArray = s.getBytes();
String ns = new String(byteArray); 
x = new BigInteger(ns);


// Rule 06. Methods (MET)

public static int getAbsAdd(int x, int y) {
  if (x == Integer.MIN_VALUE || y == Integer.MIN_VALUE) {
    throw new IllegalArgumentException();
  }
  int absX = Math.abs(x);
  int absY = Math.abs(y);
  if (absX > Integer.MAX_VALUE - absY) {
    throw new IllegalArgumentException();
  }
  return absX + absY;
}

//   USER CHOSEN VIOLATIONS


// Rule 11. Thread Pools (TPS)

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class PoolService {
  // The values have been hard-coded for brevity
  ExecutorService pool = new CustomThreadPoolExecutor(
      10, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
  // ...
}
 
class CustomThreadPoolExecutor extends ThreadPoolExecutor {
  // ... Constructor ...
  public CustomThreadPoolExecutor(
      int corePoolSize, int maximumPoolSize, long keepAliveTime,
      TimeUnit unit, BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
  }
 
 
  @Override
  public void afterExecute(Runnable r, Throwable t) {
    super.afterExecute(r, t);
    if (t != null) {
      // Exception occurred, forward to handler
    }
    // ... Perform task-specific cleanup actions
  }
 
  @Override
  public void terminated() {
    super.terminated();
    // ... Perform final clean-up actions
  }
}

// Rule 17. Java Native Interface (JNI)
  public final class NativeMethodWrapper {
 
  // Private native method
  private native void nativeOperation(byte[] data, int offset, int len);
 
  // Wrapper method performs SecurityManager and input validation checks
  public void doOperation(byte[] data, int offset, int len) {
    // Permission needed to invoke native method
    securityManagerCheck();
 
    if (data == null) {
      throw new NullPointerException();
    }
 
    // Copy mutable input
    data = data.clone();
 
    // Validate input
    if ((offset < 0) || (len < 0) || (offset > (data.length - len))) {
      throw new IllegalArgumentException();
    }
 
    nativeOperation(data, offset, len);
  }
 
  static {
    // Load native library in static initializer of class
    System.loadLibrary("NativeMethodLib");
  }
}

// Rule 09. Locking (LCK)
private int count = 0;
private final Integer Lock = new Integer(count);
 
public void doSomething() {
  synchronized (Lock) {
    count++;
    // ...
  }
}

// Rule 05. Object Orientation (OBJ)
public class Widget {
  private int total; // Declared private
 
  public int getTotal () {
    return total;
  }
 
  // Definitions for add() and remove() remain the same
}

// Rule 15. Platform Security (SEC)
private void privilegedMethod(final String filename)
                              throws FileNotFoundException {
  final String cleanFilename;
  try {
    cleanFilename = cleanAFilenameAndPath(filename);
  } catch (/* exception as per spec of cleanAFileNameAndPath */) {
    // Log or forward to handler as appropriate based on specification
    // of cleanAFilenameAndPath
  }
  try {
    FileInputStream fis =
        (FileInputStream) AccessController.doPrivileged(
          new PrivilegedExceptionAction() {
        public FileInputStream run() throws FileNotFoundException {
          return new FileInputStream(cleanFilename);
        }
      }
    );
    // Do something with the file and then close it
  } catch (PrivilegedActionException e) {
    // Forward to handler
  }
}