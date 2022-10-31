package sk.fri.uniza.coffeSiTy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sk.fri.uniza.coffeSiTy.entity.City;

import java.util.List;

@Repository
public interface CityRepo extends JpaRepository<City, Long> {
    @Query("select c from City c where c.region.id =?1")
    List<City> findALLByCityByID(Long id);
}
