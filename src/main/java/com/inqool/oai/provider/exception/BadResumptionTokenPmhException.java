package com.inqool.oai.provider.exception;

import org.openarchives.oai._2.OAIPMHerrorcodeType;

public class BadResumptionTokenPmhException extends PmhException {

	public BadResumptionTokenPmhException() {
		super(OAIPMHerrorcodeType.BAD_RESUMPTION_TOKEN, "Token invalid or expired!");
	}

}
