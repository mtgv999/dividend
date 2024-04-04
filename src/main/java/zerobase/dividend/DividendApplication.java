package zerobase.dividend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zerobase.dividend.model.Company;
import zerobase.dividend.scraper.NaverFinanceScraper;
import zerobase.dividend.scraper.Scraper;
import zerobase.dividend.scraper.YahooFinanceScraper;
@SpringBootApplication
public class DividendApplication {
    public static void main(String[] args) {
        SpringApplication.run(DividendApplication.class, args);}}
        /* Trie trie=new PatriciaTrie();
        AutoComplete autoComplete=new AutoComplete();
        AutoComplete autoComplete1=new AutoComplete();
        autoComplete.add("hello");
        autoComplete1.add("hello");
        System.out.println(autoComplete.get("hello"));
        System.out.println(autoComplete1.get("hello"));*/

        /* Scraper scraper=new NaverFinanceScraper();
        var result=scraper.scrap(Company.builder().ticker("O").build());
        var result=scraper.scrapCompanyByTicker("MMM");
        System.out.println(result);*/