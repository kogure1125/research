import org.w3c.dom.*;

public interface MainManager {
  void start();
  void speechDetected(Document doc);
  void camerarelay(boolean _cameraflag);
}
