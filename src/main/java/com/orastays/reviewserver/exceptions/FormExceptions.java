package com.orastays.reviewserver.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormExceptions extends Exception {

	private static final long serialVersionUID = -7440375263654788223L;

	private Map<String, Exception> exceptions = new HashMap<String, Exception>();

	private List<String> exe = new ArrayList<String>();

	public FormExceptions(Map<String, Exception> exceptions) {
		super();
		this.exceptions = exceptions;
	}

	public FormExceptions(List<String> exe) {
		super();
		this.exe = exe;
	}

	public Map<String, Exception> getExceptions() {
		return exceptions;
	}

	public void setExceptions(Map<String, Exception> exceptions) {
		this.exceptions = exceptions;
	}

	public List<String> getExe() {
		return exe;
	}

	public void setExe(List<String> exe) {
		this.exe = exe;
	}

}