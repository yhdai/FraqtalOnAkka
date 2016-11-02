package fraqtalonakkatest;

import java.util.concurrent.TimeUnit;

import com.tr.ap.data.CalculationRequestCmd;
import com.tr.ap.data.ReferenceDataRequestCmd;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Terminated;
import junit.framework.TestCase;

public class FraqtalOnAkkaTest extends TestCase {

	ActorSystem system;
	
	public FraqtalOnAkkaTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		system = ActorSystem.create("Test");
	}

	public void testRequestBrokerActor() {
		CalculationRequestCmd calcReqCmd = new CalculationRequestCmd("AAPL.O", "simplequac", "{\"Fields\":\"TR.SharesOutstanding\",\"Format\":\"Col,|Va,Row|\"}", "");
		//system.actorSelection("akka.tcp://server@127.0.0.1:3000/user/requestbrokeractor").tell(calcReqCmd);
    }
	
	protected void tearDown() throws Exception {
		
		system.terminate();
        Future<Terminated> f = system.whenTerminated();
        Await.result(f, Duration.create(1, TimeUnit.SECONDS));
        
		super.tearDown();
	}

}
