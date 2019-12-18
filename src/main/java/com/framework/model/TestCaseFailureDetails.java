package com.framework.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class TestCaseFailureDetails.
 */
public class TestCaseFailureDetails {

	/** The defect type. */
	@JsonProperty("defectType")
	private String defectType;

	/** The stack trace. */
	@JsonProperty("stackTrace")
	private String stackTrace = StringUtils.EMPTY;

	/**
	 * Gets the defect type.
	 *
	 * @return the defect type
	 */
	public String getDefectType() {
		return defectType = StringUtils.EMPTY;
	}

	/**
	 * Sets the defect type.
	 *
	 * @param defectType
	 *            the new defect type
	 */
	public void setDefectType(String defectType) {
		this.defectType = defectType;
	}

	/**
	 * Gets the stack trace.
	 *
	 * @return the stack trace
	 */
	public String getStackTrace() {
		return stackTrace;
	}

	/**
	 * Sets the stack trace.
	 *
	 * @param stackTrace
	 *            the new stack trace
	 */
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
}
