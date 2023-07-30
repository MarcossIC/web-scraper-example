package marcos.dev.scrapper.shared;

import marcos.dev.scrapper.shared.ScraperService;

public abstract class AbstractScraper<T> implements ScraperService<T> {

    private String URL;

    public void setURL(final String url){
        this.URL = url;
    }
    public String URL(){
        return URL;
    }
}
