package edu.ezip.ing1.pds.business.server;

import edu.ezip.ing1.pds.backend.request.Request;
import edu.ezip.ing1.pds.backend.request.Response;
import edu.ezip.ing1.pds.business.server.chat.ChatService;
import edu.ezip.ing1.pds.business.server.patient.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RequestService {
    private final Logger logger = LoggerFactory.getLogger(RequestService.class);

    public static RequestService singleton = null;
    public static final RequestService getInstance()  {
        if(singleton == null) {
            singleton = new RequestService();
        }
        return singleton;
    }

    private final ChatService chatService;
    private final PatientService patientService;

    private RequestService() {
        chatService = new ChatService();
        patientService = new PatientService();
    }

    private enum ServiceRequest {
        CHAT,
        PATIENT
    }

    public final Response handleRequest(final Request request) throws IOException {
        Response response = null;

        String[] requestOrder = request.getRequestOrder().split("\\.");
        ServiceRequest serviceRequest = ServiceRequest.valueOf(requestOrder[0]);
        logger.debug("Handle request {}", serviceRequest);

        switch(serviceRequest){
            case CHAT:
                response = chatService.handleChatRequest(request, requestOrder[1]);
                break;
            case PATIENT:
                response = patientService.handlePatientRequest(request, requestOrder[1]);
                break;
            default:
                break;
        }

        return response;
    }
}
