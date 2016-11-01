package com.tr.ap.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Inbox;

import com.tr.ap.data.CalculationRequestCmd;

public class RequestBrokerActor extends UntypedActor {
	
	private ActorSystem m_actorSystem = null;
	public RequestBrokerActor(ActorSystem actorSystem) {
		m_actorSystem = actorSystem;
	}
	
	@Override
    public void preStart() throws Exception {
        System.out.println(getSelf().path());
    }
	
	@Override
	public void onReceive(Object message) {
		System.out.println(message);
        if (message instanceof CalculationRequestCmd) {
        	CalculationRequestCmd reqCmd = (CalculationRequestCmd) message;
        	
        	// startup one new quac for the calculation
        	if(reqCmd.m_quac == "SampleQuac") {
        		ActorRef sampleQuacActor = getContext().actorOf(Props.create(SampleQuacActor.class, reqCmd), "samplequacactor");
        		
        	}
        }
        else unhandled(message);
    }
}
