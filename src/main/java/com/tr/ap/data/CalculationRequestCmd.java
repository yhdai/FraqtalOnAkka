package com.tr.ap.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CalculationRequestCmd implements Serializable {
	public final String m_ric;
	public final String m_quac;
	public final String m_option;
	public final String m_body;
	public CalculationRequestCmd(String ric, String quac, String option, String body) {
		this.m_ric = ric;
		this.m_quac = quac;
		this.m_option = option;
		this.m_body = body;
	}
}