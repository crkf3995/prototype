package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.service.ChatService;
import edu.ezip.ing1.pds.service.PatientService;

import java.io.IOException;

public class MainChatCli {

    public enum ServiceRequest {
        HELLO,
        SHUTDOWN,
        PATIENT_CREATE,
        PATIENT_LIST,
        PATIENT_DELETE
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            printUsage();
            return;
        }

        String cmd = args[0].toUpperCase();
        try {
            ServiceRequest serviceRequest = ServiceRequest.valueOf(cmd);

            switch (serviceRequest) {
                case HELLO: {
                    if (args.length != 3) { printUsage(); return; }
                    ChatService chatService = new ChatService();
                    chatService.sendHello(args[1], args[2]);
                    break;
                }
                case SHUTDOWN: {
                    if (args.length != 2) { printUsage(); return; }
                    ChatService chatService = new ChatService();
                    chatService.sendShutdown(args[1]);
                    break;
                }
                case PATIENT_CREATE: {
                    if (args.length != 7) { printUsage(); return; }
                    PatientService patientService = new PatientService();
                    patientService.createPatient(
                            Integer.parseInt(args[1]),
                            args[2], args[3],
                            Integer.parseInt(args[4]),
                            args[5],
                            args[6]
                    );
                    break;
                }
                case PATIENT_LIST: {
                    if (args.length != 1) { printUsage(); return; }
                    PatientService patientService = new PatientService();
                    patientService.listPatients();
                    break;
                }
                case PATIENT_DELETE: {
                    if (args.length != 2) { printUsage(); return; }
                    PatientService patientService = new PatientService();
                    patientService.deletePatient(Integer.parseInt(args[1]));
                    break;
                }
                default:
                    printUsage();
            }
        } catch (IllegalArgumentException e) {
            printUsage();
        }
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("xmart-chat-cli.jar hello <name> <message>");
        System.out.println("xmart-chat-cli.jar shutdown <reason>");
        System.out.println("xmart-chat-cli.jar patient_list");
        System.out.println("xmart-chat-cli.jar patient_create <id> <nom> <prenom> <age> <sexe> <telephone>");
        System.out.println("xmart-chat-cli.jar patient_delete <id>");
    }
}
