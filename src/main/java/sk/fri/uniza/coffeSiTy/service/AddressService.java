package sk.fri.uniza.coffeSiTy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fri.uniza.coffeSiTy.dto.AddressDto;
import sk.fri.uniza.coffeSiTy.entity.Address;
import sk.fri.uniza.coffeSiTy.entity.City;
import sk.fri.uniza.coffeSiTy.entity.District;
import sk.fri.uniza.coffeSiTy.entity.Region;
import sk.fri.uniza.coffeSiTy.repository.AddressRepo;
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

    @Autowired
    private AddressRepo addressRepo;


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
    //todo: do bucuna spravit dto obejkty pre kominkiaciu s klinetom a nasledne naplnenie entit
    public Address saveAdress(AddressDto addressDto, Long cityId) {
        Address address = new Address();
        address.setPsc(addressDto.getPsc());
        address.setStreet(addressDto.getStreet());
        City city = cityRepo.getCityById(cityId);
        if (city != null) {
            address.setCity(city);
        }
        return addressRepo.save(address);
    }



}

