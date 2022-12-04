package sk.fri.uniza.coffeSiTy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fri.uniza.coffeSiTy.repository.DistrictRepo;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepo districtRepo;
}
