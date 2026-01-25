package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName(value = "listPatientsResponse")
public class ListPatientsResponse {

    private List<PatientDto> patients;

    public ListPatientsResponse() {}

    @JsonProperty("patients")
    public List<PatientDto> getPatients() {
        return patients;
    }

    @JsonProperty("patients")
    public void setPatients(List<PatientDto> patients) {
        this.patients = patients;
    }
}
