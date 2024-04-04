package zerobase.dividend;
import zerobase.dividend.model.Company;
import zerobase.dividend.scraper.YahooFinanceScraper;
//@SpringBootApplication
public class DividendApplication {
    public static void main(String[] args) {
        //SpringApplication.run(DividendApplication.class, args);
        YahooFinanceScraper scraper=new YahooFinanceScraper();
        var result=scraper.scrap(Company.builder().ticker("0").build());
        System.out.println(result);}}