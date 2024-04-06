package zerobase.dividend.persist.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zerobase.dividend.persist.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository public interface CompanyRepository
extends JpaRepository<CompanyEntity,Long> {
    boolean existsByTicker(String ticker);//[1]
    Optional<CompanyEntity>findByName(String name);
    Optional<CompanyEntity>findByTicker(String ticker);
    Page<CompanyEntity> findByNameStartingWithIgnoreCase(String s, Pageable pageable);
}