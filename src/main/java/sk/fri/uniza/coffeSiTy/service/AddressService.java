package sk.fri.uniza.coffeSiTy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fri.uniza.coffeSiTy.entity.District;
import sk.fri.uniza.coffeSiTy.entity.Region;
import sk.fri.uniza.coffeSiTy.repository.DistrictRepo;
import sk.fri.uniza.coffeSiTy.repository.RegionRepo;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    DistrictRepo districtRepo;

    @Autowired
    RegionRepo regionRepo;

    public List<District> getAllDistricts(){
        return districtRepo.findAll();
    }
    public List<Region> getAllRegionsFromDistrict(Long id){
        return regionRepo.findALLByDistrictID(id);
    }



}

