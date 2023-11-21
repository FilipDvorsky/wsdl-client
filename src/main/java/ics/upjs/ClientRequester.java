package ics.upjs;

import https.kopr_ics_upjs.VisitReportPortType;
import https.kopr_ics_upjs.VisitReportService;

public class ClientRequester {

    private VisitReportService clientfactory;
    private VisitReportPortType hippokratesWebService;

    public ClientRequester() {
        clientfactory = new VisitReportService();
        hippokratesWebService = clientfactory.getVisitReportPort();
    }


    public  String requestVisitReport(int personId, String dateIdentifier){
        return hippokratesWebService.search(personId, dateIdentifier);
    }


}