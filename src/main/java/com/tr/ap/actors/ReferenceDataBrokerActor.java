package com.tr.ap.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Inbox;

import com.tr.ap.data.ReferenceData;
import com.tr.ap.data.ReferenceDataRequestCmd;

public class ReferenceDataBrokerActor extends UntypedActor {
	
	@Override
    public void preStart() throws Exception {
        System.out.println(getSelf().path());
    }
	
	@Override
    public void onReceive(Object message) {
		System.out.println(message);
        if (message instanceof ReferenceDataRequestCmd) {
        	ReferenceDataRequestCmd reqCmd = (ReferenceDataRequestCmd) message;
        	
        	// request from data cloud
        	ReferenceData refData = new ReferenceData(reqCmd.m_ric, "reference data from data cloud");
        	getSender().tell(refData, getSelf());
        }
        else unhandled(message);
    }
}
