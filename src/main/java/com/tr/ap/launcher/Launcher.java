package com.tr.ap.launcher;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.ActorSystem;

import com.tr.ap.actors.*;

public class Launcher {

  private ActorSystem m_actorSystem = null;
  public static void main(String[] args) throws Exception {
    Launcher launcher = new Launcher();
    
    launcher.LaunchRequestBroker();
    launcher.LaunchReferenceDataBroker();
    launcher.LaunchMarketDataBroker();
    
  }
  
  public Launcher() {
	  m_actorSystem = ActorSystem.create("fraqtal");
  }
  
  public void Init() {
	  
  }
  
  private void LaunchRequestBroker() {
	  ActorRef requestBrokerActor = m_actorSystem.actorOf(Props.create(RequestBrokerActor.class, m_actorSystem), "requestbrokeractor");
  }
  
  private void LaunchReferenceDataBroker() {
	  ActorRef refDataBrokerActor = m_actorSystem.actorOf(Props.create(ReferenceDataBrokerActor.class), "referencedatabrokeractor");
  }
  
  private void LaunchMarketDataBroker() {
	  ActorRef mktDataBrokerActor = m_actorSystem.actorOf(Props.create(MarketDataBrokerActor.class), "marketdatabrokeractor");
  }
}
