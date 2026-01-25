package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "listPatientsRequest")
public class ListPatientsRequest {
    // No fields needed for V0 (LIST all)
    public ListPatientsRequest() {}
}
