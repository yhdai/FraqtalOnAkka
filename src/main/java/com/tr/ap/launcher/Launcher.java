package com.tr.ap.launcher;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.ActorSystem;

import com.tr.ap.actors.*;
import com.tr.ap.data.CalculationRequestCmd;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Launcher {

  private ActorSystem m_actorSystem = null;
  public static void main(String[] args) throws Exception {
    Launcher launcher = new Launcher();
    
    ActorRef requestBrokerActor = launcher.LaunchRequestBroker();
    launcher.LaunchReferenceDataBroker();
    launcher.LaunchMarketDataBroker();
    
    CalculationRequestCmd calcReqCmd = new CalculationRequestCmd("AAPL.O", "samplequac", "{\"Fields\":\"TR.SharesOutstanding\",\"Format\":\"Col,|Va,Row|\"}", "");
    requestBrokerActor.tell(calcReqCmd, null);
  }
  
  public Launcher() {
	  Config config = ConfigFactory.load("application");
	  String systemName = "fraqtal";
      config = config.getConfig(systemName);
      m_actorSystem = ActorSystem.create(systemName, config);
  }
  
  public void Init() {
	  
  }
  
  private ActorRef LaunchRequestBroker() {
	  return m_actorSystem.actorOf(Props.create(RequestBrokerActor.class, m_actorSystem), "requestbrokeractor");
  }
  
  private void LaunchReferenceDataBroker() {
	  ActorRef refDataBrokerActor = m_actorSystem.actorOf(Props.create(ReferenceDataBrokerActor.class), "referencedatabrokeractor");
  }
  
  private void LaunchMarketDataBroker() {
	  ActorRef mktDataBrokerActor = m_actorSystem.actorOf(Props.create(MarketDataBrokerActor.class), "marketdatabrokeractor");
  }
}
