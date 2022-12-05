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
    private AddressRepo addressRepo;

    public Address saveAdress(AddressDto addressDto) {
        Address address = new Address();
        address.setPsc(addressDto.getPsc());
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        return addressRepo.save(address);
    }
}

