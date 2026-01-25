package edu.ezip.ing1.pds.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.*;
import edu.ezip.ing1.pds.config.ChatCliConfig;
import edu.ezip.ing1.pds.request.Request;
import edu.ezip.ing1.pds.request.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class PatientService {
    private final Logger logger = LoggerFactory.getLogger(PatientService.class);

    private final ChatCliConfig cfg;
    private Socket socket;

    public PatientService() throws IOException {
        this.cfg = ChatCliConfig.loadProperties();
    }

    private void connect() throws IOException {
        socket = new Socket(cfg.getChatServerHost(), cfg.getChatServerPort());
    }

    private void close() throws IOException {
        if (socket != null) socket.close();
    }

    private <T> Response send(String order, T body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Request req = new Request();
        req.setRequestId(UUID.randomUUID().toString());
        req.setRequestOrder(order);
        req.setRequestContent(mapper.writeValueAsString(body));

        out.println(mapper.writeValueAsString(req));
        out.flush();

        String respLine = in.readLine();
        return mapper.readValue(respLine, Response.class);
    }

    public void listPatients() throws IOException {
        connect();

        Response response = send("PATIENT.LIST", new ListPatientsRequest());
        ObjectMapper mapper = new ObjectMapper();
        ListPatientsResponse resp = mapper.readValue(response.responseBody, ListPatientsResponse.class);

        if (resp.getPatients() == null || resp.getPatients().isEmpty()) {
            System.out.println("No patients.");
        } else {
            for (PatientDto p : resp.getPatients()) {
                System.out.printf("%d | %s | %s | %d | %s | %s%n",
                        p.getId(), p.getNom(), p.getPrenom(), p.getAge(), p.getSexe(), p.getTelephone());
            }
        }

        close();
    }

    public void createPatient(int id, String nom, String prenom, int age, String sexe, String telephone) throws IOException {
        connect();

        PatientDto p = new PatientDto();
        p.setId(id);
        p.setNom(nom);
        p.setPrenom(prenom);
        p.setAge(age);
        p.setSexe(sexe);
        p.setTelephone(telephone);

        CreatePatientRequest req = new CreatePatientRequest();
        req.setPatient(p);

        Response response = send("PATIENT.CREATE", req);
        System.out.println("CREATE => " + response.responseBody);

        close();
    }

    public void deletePatient(int id) throws IOException {
        connect();

        DeletePatientRequest req = new DeletePatientRequest();
        req.setId(id);

        Response response = send("PATIENT.DELETE", req);
        System.out.println("DELETE => " + response.responseBody);

        close();
    }
}
