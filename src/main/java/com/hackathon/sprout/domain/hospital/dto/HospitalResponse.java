package com.hackathon.sprout.domain.hospital.dto;

import com.hackathon.sprout.domain.hospital.domain.Address;
import com.hackathon.sprout.domain.hospital.domain.Hospital;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class HospitalResponse {
    @Schema(description = "병원 ID", example = "1")
    private final Long hospitalId;

    @Schema(description = "병원 이름", example = "서울대학교병원")
    private final String name;

    @Schema(description = "병원 전화번호", example = "02-1234-5678")
    private final String phoneNumber;

    @Schema(description = "병원 웹사이트 URL", example = "https://www.snuh.org")
    private final String webUrl;

    @Schema(description = "병원 설명", example = "서울대학교병원은 대한민국 서울특별시에 위치한 병원입니다.")
    private final String description;

    @Schema(description = "병원 주소")
    private final Address address;

    public HospitalResponse(Hospital hospital) {
        this.hospitalId = hospital.getHospitalId();
        this.name = hospital.getName();
        this.phoneNumber = hospital.getPhoneNumber();
        this.webUrl = hospital.getWebUrl();
        this.description = hospital.getDescription();
        this.address = hospital.getAddress();
    }
}
