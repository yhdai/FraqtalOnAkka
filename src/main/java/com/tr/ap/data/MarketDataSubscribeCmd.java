package com.tr.ap.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MarketDataSubscribeCmd implements Serializable {
	public final String m_ric;
	public MarketDataSubscribeCmd(String ric) {
		this.m_ric = ric;
	}
}