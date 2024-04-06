package zerobase.dividend.scheduler;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import zerobase.dividend.model.Company;
import zerobase.dividend.model.ScrapedResult;
import zerobase.dividend.persist.entity.CompanyEntity;
import zerobase.dividend.persist.entity.DividendEntity;
import zerobase.dividend.persist.repository.CompanyRepository;
import zerobase.dividend.persist.repository.DividendRepository;
import zerobase.dividend.scraper.Scraper;
import java.time.LocalDateTime;
import java.util.List;
@Component @AllArgsConstructor
public class ScraperScheduler {
    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;
    private final Scraper YahooFinanceScraper;
    //@Scheduled(cron="") public void test(){
    //    System.out.println("now->"+Thread.currentThread()
    //            .getName()+": "+System.currentTimeMillis());}
    /*@Scheduled(fixedDelay = 1000)
    public void test1() throws InterruptedException{
        Thread.sleep(10000);//10초간 일시 정지
        System.out.println("테스트1 : "+ LocalDateTime.now());}
    @Scheduled(fixedDelay = 1000)
    public void test2(){System.out.println
            ("텍스트2 : "+LocalDateTime.now());}*/
    //일정 주기마다 수행
    @CacheEvict(value = "finance",allEntries = true)
    @Scheduled(cron="${scheduler.scrap.yahoo}")
    public void YahooFinanceScheduling(){
        //log.info("scraping scheduler is started");
        //저장된 회사 목록을 조회
        List<CompanyEntity>companies =this.companyRepository.findAll();
        //회사마다 배당금 정보를 새로 스크래핑
        for(var company: companies){log.info("scraping " +
                "scheduler is started->"+company.getName());
    ScrapedResult scrapedResult= this.YahooFinanceScraper.scrap
            (new Company(company.getName(),company.getTicker()));
        //스크래핑한 대방금 정보 중 데이터베이스에 없는 값은 저장
        scrapedResult.getDividends().stream()
                //디비든 모델을 디비든 엔테티로 매핑
                .map(e->new DividendEntity(company.getId(),e))
                //엘리먼트를 하나씩 디비든 레파지토리에 삽입
                .forEach(e->{boolean exists=this.dividendRepository
                    .existsByCompanyIdAndDate(e.getCompanyId(),e.getDate());
                    if(!exists){this.dividendRepository.save(e);
                    log.info("insert new dividend->"+e.toString());}});
        //연속적으로 스크래핑 대상 사이트 서버에 요청을 날리지 않도록 일시정지
            try {Thread.sleep(3000);//3 seconds
            }catch (InterruptedException e){e.printStackTrace();}}}
    }