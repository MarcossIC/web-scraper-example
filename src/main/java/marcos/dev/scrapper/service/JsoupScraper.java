package marcos.dev.scrapper.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import marcos.dev.scrapper.shared.AbstractScraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
@Service(value = "selenium")
@RequiredArgsConstructor
public class JsoupScraper extends AbstractScraper<Document> {

    private static boolean containsUnwantedText(String url) {
        // Define la expresión regular para buscar "(lenguaje_de_programaci%C3%B3n)" o cualquier otra cadena no deseada
        String regex = "\\([^)]+\\)";

        // Compila la expresión regular en un patrón
        Pattern pattern = Pattern.compile(regex);

        // Busca el patrón en el enlace
        Matcher matcher = pattern.matcher(url);

        // Retorna true si se encuentra la cadena no deseada en el enlace, de lo contrario, retorna false
        return !matcher.find();
    }
    private static boolean containsHttp(String href) {
        return href.contains("http");
    }

    @PostConstruct
    public void postConstruct() throws IOException {
        log.info("JSOUP");
        this.setURL("https://www.google.com/search?q=");
        var result = this.scrape("java");

        var words= result.select("a[href]");
        log.info("LISTA");
        log.info("*************************ATRIB HREF*************************************");
        words.stream().map(x-> x.attr("href"))
                .filter(JsoupScraper::containsHttp)
                .filter(JsoupScraper::containsUnwantedText)
                .forEach(log::info);
        log.info("**************************************************************************");
    }

    @Override
    public Document scrape(final String value) throws IOException {

        return Jsoup.connect(this.URL()+value).get();
    }
}
