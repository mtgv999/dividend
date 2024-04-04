package zerobase.dividend.persist.repository;
import zerobase.dividend.persist.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository public interface CompanyRepository
extends JpaRepository<CompanyEntity,Long> {
    boolean existsByTicker(String ticker);//[1]
    Optional<CompanyEntity>findByName(String name);}