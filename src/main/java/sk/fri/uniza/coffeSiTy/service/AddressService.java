package sk.fri.uniza.coffeSiTy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fri.uniza.coffeSiTy.entity.City;
import sk.fri.uniza.coffeSiTy.entity.District;
import sk.fri.uniza.coffeSiTy.entity.Region;
import sk.fri.uniza.coffeSiTy.repository.CityRepo;
import sk.fri.uniza.coffeSiTy.repository.DistrictRepo;
import sk.fri.uniza.coffeSiTy.repository.RegionRepo;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private DistrictRepo districtRepo;
    @Autowired
    private RegionRepo regionRepo;
    @Autowired
    private CityRepo cityRepo;


    public List<District> getAllDistricts(){
        return districtRepo.findAll();
    }
    public List<Region> getAllRegionsFromDistrict(Long id){
        return regionRepo.findALLByDistrictID(id);
    }
    public District findDistrictByID(Long id){
        return districtRepo.findDistrictById(id);
    }
    public List<City> getAllCitiesFromRegion(Long id){
        return cityRepo.findALLByCityByID(id);
    }



}

