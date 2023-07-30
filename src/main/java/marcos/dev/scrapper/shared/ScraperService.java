package marcos.dev.scrapper.shared;

import java.io.IOException;

public interface ScraperService<T> {

    T scrape(String value) throws IOException;
}
