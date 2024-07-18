package com.hackathon.sprout.domain.hospital.dto;

import com.hackathon.sprout.domain.hospital.domain.Address;
import com.hackathon.sprout.domain.hospital.domain.Hospital;
import lombok.Getter;

@Getter
public class HospitalResponse {
    private final Long hospitalId;

    private final String name;

    private final String phoneNumber;

    private final String webUrl;

    private final String description;

    private final Address address;

    public HospitalResponse(Hospital hospital){
        this.hospitalId = hospital.getHospitalId();
        this.name = hospital.getName();
        this.phoneNumber = hospital.getPhoneNumber();
        this.webUrl = hospital.getWebUrl();
        this.description = hospital.getDescription();
        this.address = hospital.getAddress();
    }
}
