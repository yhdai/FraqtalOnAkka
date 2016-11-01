package com.tr.ap.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ReferenceDataRequestCmd implements Serializable {
	public final String m_ric;
	public ReferenceDataRequestCmd(String ric) {
		this.m_ric = ric;
	}
}
