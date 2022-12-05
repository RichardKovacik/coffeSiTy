package sk.fri.uniza.coffeSiTy.service;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fri.uniza.coffeSiTy.entity.City;
import sk.fri.uniza.coffeSiTy.repository.CityRepo;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepo cityRepo;

    public List<City> getAllCitiesFromRegion(Long id){
        return cityRepo.findALLByCityByID(id);
    }
    public City getCityById(Long id){
        return cityRepo.getCityById(id);
    }
}
