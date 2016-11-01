package com.tr.ap.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ReferenceData implements Serializable {
	public final String m_ric;
	public final String m_payload;
	
	public ReferenceData(String ric, String payload) {
		this.m_ric = ric;
		this.m_payload = payload;
	}
}