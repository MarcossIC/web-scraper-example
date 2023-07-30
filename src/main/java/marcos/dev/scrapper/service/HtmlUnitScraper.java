package marcos.dev.scrapper.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import marcos.dev.scrapper.shared.AbstractScraper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service(value = "htmlUnit")
public class HtmlUnitScraper extends AbstractScraper<HtmlPage> {

    //@PostConstruct
    public void scraper() throws IOException, InterruptedException {
        log.info("HTML UNIT");
        this.setURL("https://relatedwords.org/relatedto/");
        var result= scrape("card");
        DomElement wordsDiv = result.getFirstByXPath("//div[@class='words']");
        var itemList = wordsDiv.querySelectorAll(".item");

        log.info("LISTA");
        log.info("*************************TEXT CONTENT*************************************");
        itemList.stream().map(DomNode::getTextContent).forEach(log::info);
        log.info("**************************************************************************");
    }

    @Override
    public HtmlPage scrape(String value) throws IOException {
        final WebClient client = new WebClient();
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.getOptions().setJavaScriptEnabled(true);
        return client.getPage(this.URL()+value);
    }

}
