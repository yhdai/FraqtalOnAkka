package fraqtalonakkatest;

import java.util.concurrent.TimeUnit;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
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

	protected void tearDown() throws Exception {
		
		system.terminate();
        Future<Terminated> f = system.whenTerminated();
        Await.result(f, Duration.create(1, TimeUnit.SECONDS));
        
		super.tearDown();
	}

}
