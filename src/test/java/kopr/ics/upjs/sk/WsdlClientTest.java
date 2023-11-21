package kopr.ics.upjs.sk;

import static org.junit.jupiter.api.Assertions.*;

import ics.upjs.ClientRequester;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WsdlClientTest {

    ClientRequester clientRequester;

    @BeforeAll
    void setUp() {
        clientRequester = new ClientRequester();
    }

    @Test
    public void testForIncorrectValues(){
        assertEquals(clientRequester.requestVisitReport(1,null),"Wrong. DateIdentifier format.");
        assertEquals(clientRequester.requestVisitReport(0,null),"Wrong. PersonId and DateIdentifier format.");

        assertEquals(clientRequester.requestVisitReport(0,"2023-11-11T10:30:00"),"Wrong. PersonId format.");
        assertEquals(clientRequester.requestVisitReport(-1,"2023-11-11T10:30:00"),"Wrong. PersonId format.");

        assertEquals(clientRequester.requestVisitReport(-1,"2023-11-11"),"Wrong. DateIdentifier format.");

        assertEquals(clientRequester.requestVisitReport(Integer.MAX_VALUE,"2023-11-11T10:30:00"),"Wrong. Report not in DB.");
        assertEquals(clientRequester.requestVisitReport(1,"2023-12-24T23:59:59"),"Wrong. Report not in DB.");

        assertEquals(clientRequester.requestVisitReport(1,"NeekedyVPiatokSomMAlVysetrenie"),"Wrong. Report not in DB.");
        assertEquals(clientRequester.requestVisitReport(1,"NeviemAsiVPondelokToBolo??"),"Wrong. Report not in DB.");
    }

    @Test
    public void testForCorrectValues(){
        assertEquals(clientRequester.requestVisitReport(1,"2023-11-10T09:00:00"),"Pacient 1 je ok :)");
        assertEquals(clientRequester.requestVisitReport(1,"2023-11-10T09:30:00"),"Pacient 1 je stale OK");
        assertEquals(clientRequester.requestVisitReport(1,"2023-11-10T11:00:00"),"Po treti raz je Pacient ok");
        assertEquals(clientRequester.requestVisitReport(2,"2023-11-11T10:30:00"),"Novy pacient 2 urcite nieje chory.");
        assertEquals(clientRequester.requestVisitReport(2,"2023-11-11T12:00:00"),"Pacient 2 je mozno chory.");
        assertEquals(clientRequester.requestVisitReport(3,"2023-11-14T13:30:00"),"Novy pacient 3 ma nadchu.");
        assertEquals(clientRequester.requestVisitReport(2,"2023-11-15T09:45:00"),"Pacient 2 je urcite chory.");
        assertEquals(clientRequester.requestVisitReport(3,"2023-11-15T12:00:00"),"Pacient 3 stale nie je chory");
        assertEquals(clientRequester.requestVisitReport(4,"2023-11-16T13:00:00"),"Novy pacient 4 ");
        assertEquals(clientRequester.requestVisitReport(5,"2023-11-17T10:00:00"),"Dna 2023-11-17T10:00:00 prisiel novy pacient cislo 5 a nieje chory");
        assertEquals(clientRequester.requestVisitReport(6,"2023-11-18T11:30:00"),"6 Je ok ");
        assertEquals(clientRequester.requestVisitReport(7,"2023-11-22T11:00:00"),"7 nieje ok :(");
    }
}
