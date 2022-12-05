package sk.fri.uniza.coffeSiTy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fri.uniza.coffeSiTy.entity.District;
import sk.fri.uniza.coffeSiTy.repository.DistrictRepo;

import java.util.List;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepo districtRepo;
    public List<District> getAllDistricts(){
        return districtRepo.findAll();
    }
    public District findDistrictByID(Long id) {
        return districtRepo.findDistrictById(id);
    }
}
