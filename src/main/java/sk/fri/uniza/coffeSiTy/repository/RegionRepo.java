package sk.fri.uniza.coffeSiTy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sk.fri.uniza.coffeSiTy.entity.Region;

import java.util.List;

@Repository
public interface RegionRepo extends JpaRepository<Region, Long> {

    @Query("select r from Region r where r.district.id =?1")
    List<Region> findALLByDistrictID(Long id);
}
