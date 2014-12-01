package com.m.tripcard.model.verify;

public class InputValidate {
	private boolean pass;

	private String error;

	public InputValidate() {
	}

	/**
	 * @param pass
	 * @param error
	 */
	public InputValidate(boolean pass, String error) {
		super();
		this.pass = pass;
		this.error = error;
	}

	/**
	 * @return the pass
	 */
	public boolean isPass() {
		return pass;
	}

	public boolean notPass() {
		return !isPass();
	}

	/**
	 * @param pass
	 *            the pass to set
	 */
	public void setPass(boolean pass) {
		this.pass = pass;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
}
