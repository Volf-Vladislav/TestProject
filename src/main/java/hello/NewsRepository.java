package hello;

import java.io.IOException;
import java.util.List;

public interface NewsRepository {

    List<News> readNews() throws IOException;
}
