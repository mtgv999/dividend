package zerobase.dividend.persist.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.dividend.persist.entity.CompanyEntity;
@Repository public interface CompanyRepository
extends JpaRepository<CompanyEntity,Long> { }