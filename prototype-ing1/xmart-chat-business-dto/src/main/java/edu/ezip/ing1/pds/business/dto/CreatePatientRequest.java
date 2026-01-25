package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "createPatientRequest")
public class CreatePatientRequest {
    public PatientDto patient;

    public CreatePatientRequest() {}

    @JsonProperty("patient")
    public PatientDto getPatient() { return patient; }

    @JsonProperty("patient")
    public void setPatient(PatientDto patient) { this.patient = patient; }
}
