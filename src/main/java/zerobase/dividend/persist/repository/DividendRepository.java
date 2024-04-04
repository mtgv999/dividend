package zerobase.dividend.persist.repository;
import zerobase.dividend.persist.entity.DividendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository public interface DividendRepository
extends JpaRepository<DividendEntity,Long> {
    List<DividendEntity>findAllByCompanyId(Long companyId);}