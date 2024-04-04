package zerobase.dividend.scraper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import zerobase.dividend.model.Company;
import zerobase.dividend.model.Dividend;
import zerobase.dividend.model.ScrapedResult;
import zerobase.dividend.model.constants.Month;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class YahooFinanceScraper {
    private static final String STATISTICS_URL=
"https://finance.yahoo.com/quote/O/history?period1=1680444326&period2=1712066726&interval=1mo&filter=history&frequency=1mo&includeAdjustedClose=true";
    private static final String SUMMARY_URL="https://finance.yahoo.com/quote/%s?p=%s";
    public static final long START_TIME=86400;//60*60*24
    public ScrapedResult scrap(Company company){
        var scrapResult=new ScrapedResult();
        scrapResult.setCompany(company);
        try {long now=System.currentTimeMillis()/1000;
            String url = String.format(STATISTICS_URL,
                    company.getTicker(), START_TIME, now);
            Connection connection = Jsoup.connect(url);
            Document document = connection.get();
            Elements parsingDivs = document.getElementsByAttributeValue
                    ("data-test", "historical-prices");
            Element tableEle = parsingDivs.get(0);//table 전체
            Element tbody = tableEle.children().get(1);
            List<Dividend> dividends = new ArrayList<>();
            for (Element e : tbody.children()) {
                String txt = e.text();
                if (!txt.endsWith("Dividend")) {continue;}
                String[] splits = txt.split("");
                int month = Month.strToNumber(splits[0]);
                int day = Integer.valueOf(splits[1].replace(",", ""));
                int year = Integer.valueOf(splits[2]);
                String dividend = splits[3];if(month<0){
            throw new RuntimeException("Unexpected Month enum value->"+splits[0]);}
                dividends.add(Dividend.builder().date(LocalDateTime.of
                        (year,month,day,0,0)).dividend(dividend).build());
                //System.out.println(year + "/" + month + "/" + day + "->" + dividend);
        }scrapResult.setDividends(dividends);}//[1]
        catch (IOException e){//TODO
            e.printStackTrace();}return scrapResult;}
    public Company scrapCompanyByTicker(String ticker){
        String url=String.format(SUMMARY_URL,ticker,ticker);
    try{Document document=Jsoup.connect(url).get();
        Element titleEle=document.getElementsByTag("h1").get(0);
        String title=titleEle.text().split(" - ")[1].trim();
        return Company.builder().ticker(ticker).name(title).build();
    }catch (IOException e){e.printStackTrace();}return null;}
}