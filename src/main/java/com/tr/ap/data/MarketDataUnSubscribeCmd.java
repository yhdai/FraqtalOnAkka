package com.tr.ap.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MarketDataUnSubscribeCmd implements Serializable {
	public final String m_ric;
	public MarketDataUnSubscribeCmd(String ric) {
		this.m_ric = ric;
	}
}