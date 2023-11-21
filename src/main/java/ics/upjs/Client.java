package ics.upjs;

public class Client {
    public static void main(String[] args) {
        ClientRequester clientRunner = new ClientRequester();
        try {
            int personId = 2;
            String dateIdentifier = "2023-11-11T10:30:00";
            System.out.println(clientRunner.requestVisitReport(personId, dateIdentifier));

            personId = 2;
            dateIdentifier = "2023-11-11T21:30:00";
            System.out.println(clientRunner.requestVisitReport(personId, dateIdentifier));

            personId = 3;
            dateIdentifier = "2023-11-11T10:30:00";
            System.out.println(clientRunner.requestVisitReport(personId, dateIdentifier));

            personId = Integer.MAX_VALUE;
            dateIdentifier = "2023-11-11T10:30:00";
            System.out.println(clientRunner.requestVisitReport(personId, dateIdentifier));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
