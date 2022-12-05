package sk.fri.uniza.coffeSiTy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fri.uniza.coffeSiTy.entity.Region;
import sk.fri.uniza.coffeSiTy.repository.RegionRepo;

import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepo regionRepo;

    public List<Region> getAllRegionsFromDistrict(Long districtId){
        return regionRepo.findALLByDistrictID(districtId);
    }
}
