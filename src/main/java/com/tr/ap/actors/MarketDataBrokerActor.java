package com.tr.ap.actors;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Inbox;

import com.tr.ap.data.MarketData;
import com.tr.ap.data.MarketDataSubscribeCmd;
import com.tr.ap.data.MarketDataUnSubscribeCmd;

public class MarketDataBrokerActor extends UntypedActor {
	private HashMap<String, List<ActorRef>> m_ricSubscriberMap = new HashMap<String, List<ActorRef>>();
	
	
	@Override
    public void preStart() throws Exception {
        System.out.println(getSelf().path());
    }
	
	@Override
    public void onReceive(Object message) {
		System.out.println(message);
		
        if (message instanceof MarketDataSubscribeCmd) {
        	MarketDataSubscribeCmd subCmd = (MarketDataSubscribeCmd) message;
        	List<ActorRef> subscribers = m_ricSubscriberMap.get(subCmd.m_ric);
        	if(subscribers == null) {
        		subscribers = new LinkedList<ActorRef>();
        	}
        	
        	subscribers.add(getSender());
        }
        else if (message instanceof MarketDataUnSubscribeCmd) {
        	MarketDataUnSubscribeCmd unsubCmd = (MarketDataUnSubscribeCmd) message;
        	List<ActorRef> subscribers = m_ricSubscriberMap.get(unsubCmd.m_ric);
        	if(subscribers == null) {
        		// error happened
        	}
        	
        	subscribers.remove(getSender());
        }
        else if (message instanceof MarketData) {
        	MarketData mktData = (MarketData) message;
        	List<ActorRef> subscribers = m_ricSubscriberMap.get(mktData.m_ric);
        	if(subscribers == null) {
        		// error happened
        	}
        	else {
        		subscribers.forEach((subscriber) -> subscriber.tell(mktData, getSelf()));
        	}
        }
        else unhandled(message);
    }
}
