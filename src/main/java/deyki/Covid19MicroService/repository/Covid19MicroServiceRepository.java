package deyki.Covid19MicroService.repository;

import deyki.Covid19MicroService.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Covid19MicroServiceRepository extends JpaRepository<Country, Long> {

    @Query("SELECT c FROM Country c WHERE c.CountryCode = ?1")
    Optional<Country> findByCountryCode(@Param("countryCode") String countryCode);
}
