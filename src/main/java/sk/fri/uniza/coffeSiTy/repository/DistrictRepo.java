package sk.fri.uniza.coffeSiTy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.fri.uniza.coffeSiTy.entity.District;

import java.util.List;

@Repository
public interface DistrictRepo extends JpaRepository<District, Long> {
    @Override
    List<District> findAll();
}
