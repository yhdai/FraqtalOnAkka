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
	
	public void onReceive(Object message) {
        if (message instanceof CalculationRequestCmd) {
        	CalculationRequestCmd reqCmd = (CalculationRequestCmd) message;
        	
        	// startup one new quac
        	if(reqCmd.m_quac == "SampleQuac") {
        		ActorRef sampleQuacActor = m_actorSystem.actorOf(Props.create(SampleQuacActor.class, reqCmd), "samplequacactor");
        		
        	}
        }
        else unhandled(message);
    }
}
