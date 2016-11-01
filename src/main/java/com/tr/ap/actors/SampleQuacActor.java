package com.tr.ap.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Inbox;

import com.tr.ap.data.*;

public class SampleQuacActor extends UntypedActor {
	
	private CalculationRequestCmd m_reqCmd = null;
	private ActorSystem m_actorSystem = null;
	public SampleQuacActor(CalculationRequestCmd reqCmd, ActorSystem actorSystem) {
		m_reqCmd = reqCmd;
		m_actorSystem = actorSystem;
		Init();
	}
    
	private void Init() {
		// initialize quac
		
		// start to get reference data
		ReferenceDataRequestCmd refDataReqCmd = new ReferenceDataRequestCmd(m_reqCmd.m_ric);
		m_actorSystem.actorSelection("/user/referencedatabrokeractor").tell(refDataReqCmd, getSelf());
	}
	
    public void onReceive(Object message) {
        if (message instanceof ReferenceData) {
        	ReferenceData refData = (ReferenceData) message;
        	// receiving reference data
        	// start to subscribe market data
        	MarketDataSubscribeCmd subCmd = new MarketDataSubscribeCmd(m_reqCmd.m_ric);
        	m_actorSystem.actorSelection("/user/marketdatabrokeractor").tell(subCmd, getSelf());
        }
        else if (message instanceof MarketData) {
        	MarketData mktData = (MarketData) message;
        	// receiveing market data
        	
        }
        else unhandled(message);
    }
}
