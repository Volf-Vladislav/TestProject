package hello;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class FileNewsRepository implements NewsRepository {

    private InputStream source;

    public FileNewsRepository(InputStream source) {
        this.source = source;
    }

    @Override
    public List<News> readNews() throws IOException {
        List<News> news = new ArrayList<>();

        LineNumberReader reader = new LineNumberReader(new InputStreamReader(source));

        String string = reader.readLine();

        News oneNews = null;
        while (string != null) {
            if(string.isEmpty()) {
                if(oneNews != null) {
                    news.add(oneNews);
                    oneNews = null;
                }
            } else if(oneNews == null) {
                oneNews = new News(string);
            } else {
                oneNews.addLine(string);
            }
            string = reader.readLine();
        }

        if(oneNews != null)
            news.add(oneNews);

        String str = oneNews != null ? "not null" : "null";
        if(oneNews != null) {
            str = "not null";
        } else {
            str = "null";
        }

        return news;
    }
}
