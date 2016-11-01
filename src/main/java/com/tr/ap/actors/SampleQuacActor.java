package com.tr.ap.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Inbox;

import com.tr.ap.data.*;

public class SampleQuacActor extends UntypedActor {
	
	private CalculationRequestCmd m_reqCmd = null;
	public SampleQuacActor(CalculationRequestCmd reqCmd) {
		m_reqCmd = reqCmd;
		Init();
	}
    
	private void Init() {
		// initialize quac
		
		// start to get reference data
		ReferenceDataRequestCmd refDataReqCmd = new ReferenceDataRequestCmd(m_reqCmd.m_ric);
		getContext().actorSelection("/user/referencedatabrokeractor").tell(refDataReqCmd, getSelf());
	}
	
	@Override
    public void preStart() throws Exception {
        System.out.println(getSelf().path());
    }
	
	@Override
    public void onReceive(Object message) {
		System.out.println(message);
        if (message instanceof ReferenceData) {
        	ReferenceData refData = (ReferenceData) message;
        	// receiving reference data
        	// start to subscribe market data
        	MarketDataSubscribeCmd subCmd = new MarketDataSubscribeCmd(m_reqCmd.m_ric);
        	getContext().actorSelection("/user/marketdatabrokeractor").tell(subCmd, getSelf());
        }
        else if (message instanceof MarketData) {
        	MarketData mktData = (MarketData) message;
        	// receiveing market data
        	
        }
        else unhandled(message);
    }
}
