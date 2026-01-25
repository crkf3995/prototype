package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "deletePatientRequest")
public class DeletePatientRequest {
    public int id;

    public DeletePatientRequest() {}

    @JsonProperty("id")
    public int getId() { return id; }

    @JsonProperty("id")
    public void setId(int id) { this.id = id; }
}
