package edu.ezip.ing1.pds.business.server.patient;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.backend.request.Request;
import edu.ezip.ing1.pds.backend.request.Response;
import edu.ezip.ing1.pds.business.dto.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PatientService {

    private enum PatientServiceRequest {
        CREATE,
        LIST,
        DELETE
    }

    private final PatientDao dao = new PatientDao();

    public Response handlePatientRequest(Request request, String serviceRequest) throws IOException {
        PatientServiceRequest patientServiceRequest = PatientServiceRequest.valueOf(serviceRequest);
        switch (patientServiceRequest) {
            case CREATE:
                return create(request);
            case LIST:
                return list(request);
            case DELETE:
                return delete(request);
            default:
                return new Response(request.getRequestId(), "{\"error\":\"Unknown PATIENT request\"}");
        }
    }

    private Response create(Request request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CreatePatientRequest body = mapper.readValue(request.getRequestBody(), CreatePatientRequest.class);

        try {
            int rows = dao.createPatient(body.getPatient());
            return new Response(request.getRequestId(), "{\"rows\":" + rows + "}");
        } catch (SQLException e) {
            return new Response(request.getRequestId(),
                    "{\"error\":\"SQL error on CREATE\",\"message\":\"" + safe(e.getMessage()) + "\"}");
        }
    }

    private Response list(Request request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<PatientDto> patients = dao.listPatients();
            ListPatientsResponse resp = new ListPatientsResponse();
            resp.setPatients(patients);
            return new Response(request.getRequestId(), mapper.writeValueAsString(resp));
        } catch (SQLException e) {
            return new Response(request.getRequestId(),
                    "{\"error\":\"SQL error on LIST\",\"message\":\"" + safe(e.getMessage()) + "\"}");
        }
    }

    private Response delete(Request request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DeletePatientRequest body = mapper.readValue(request.getRequestBody(), DeletePatientRequest.class);

        try {
            int rows = dao.deletePatient(body.getId());
            return new Response(request.getRequestId(), "{\"rows\":" + rows + "}");
        } catch (SQLException e) {
            return new Response(request.getRequestId(),
                    "{\"error\":\"SQL error on DELETE\",\"message\":\"" + safe(e.getMessage()) + "\"}");
        }
    }

    private String safe(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"");
    }
}
