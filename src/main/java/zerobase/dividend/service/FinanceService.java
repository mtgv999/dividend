package zerobase.dividend.service;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zerobase.dividend.exception.impl.NoCompanyException;
import zerobase.dividend.model.Company;
import zerobase.dividend.model.Dividend;
import zerobase.dividend.model.ScrapedResult;
import zerobase.dividend.persist.entity.CompanyEntity;
import zerobase.dividend.persist.entity.DividendEntity;
import zerobase.dividend.persist.repository.CompanyRepository;
import zerobase.dividend.persist.repository.DividendRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service @AllArgsConstructor
public class FinanceService {
    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;
    @Cacheable(key="#companyName",value = "finance")
    public ScrapedResult getDividendByCompanyName(String companyName){
        //1. 회사명을 기준으로 회사 정보를 조회
        CompanyEntity company=this.companyRepository.findByName(companyName)
                .orElseThrow(()->new NoCompanyException());
        //2. 조회된 회사 ID로 배당금 정보 조회
        List<DividendEntity>dividendEntities=this.dividendRepository
                .findAllByCompanyId(company.getId());
        //3. 결과 조합 후 반환
        //List<Dividend> dividends=new ArrayList<>();
        //for(var entity: dividendEntities){dividends
        //    .add(Dividend.builder().date(entity.getDate())
        //    .dividend(entity.getDividend()).build());}
        List<Dividend>dividends=dividendEntities.stream()
        .map(e->new Dividend(e.getDate(),e.getDividend()))
                .collect(Collectors.toList());
        return new ScrapedResult(new Company(company
                .getTicker(),company.getName()),dividends);}
}