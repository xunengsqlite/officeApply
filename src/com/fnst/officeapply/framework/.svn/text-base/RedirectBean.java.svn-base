package com.fnst.officeapply.framework;

import java.util.ArrayList;
import java.util.Iterator;

public class RedirectBean {
	private ArrayList<SimpleEntry> controls = new ArrayList<SimpleEntry>();
	public static final String REDIRECT_BEAN_KEY = "redirectBean";
	public static final String REDIRECT_PREFIX = "org-";
	private String request;
	private String requestWay;
	
	public void addControl(String name, String value) {
		if(name == null){
			return;
		}
		controls.add(new SimpleEntry(name, value));
	}

	public void addControl(String name, String[] values) {
		if(values == null || name == null){
			return;
		}
		for (int i = 0; i < values.length; i++) {
			String value = values[i];
			addControl(name, value);
		}
	}
	
	public class SimpleEntry {

		private String name;
		private String value;
		
		public SimpleEntry() {
		}
		
		public SimpleEntry(String name,String value) {
			this.name = name;
			this.value = value;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public String toString(){
			return name + " : " + value;
		}
	}



	public Iterator<SimpleEntry> iterator() {
		return controls.iterator();
	}

	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}

	public void clear() {
		controls.clear();
	}

	public boolean contains(Object o) {
		return controls.contains(o);
	}

	public boolean isEmpty() {
		return controls.isEmpty();
	}

	public boolean remove(Object o) {
		return controls.remove(o);
	}
	
//	public void remove(String name) {
//		SimpleEntry found = null;
//		for (SimpleEntry entry : controls) {
//			String simpleEntryName = entry.name;
//			if (simpleEntryName != null && simpleEntryName.equals(name)) {
//				found = entry;
//				break;
//			}
//
//		}
//		if (found != null) {
//			controls.remove(found);
//		}
//	}
	

	public void remove(String name) {

		for (int i = controls.size() - 1; i >= 0; i--) {
			SimpleEntry entry = controls.get(i);
			String entryName = entry.name;
			if (entryName != null && entryName.equals(name)) {
				controls.remove(i);
			}
		}
	}

	public int size() {
		return controls.size();
	}

	public String toString() {
		return "request: " + request + controls.toString();
	}

	public void setControls(ArrayList<SimpleEntry> controls) {
		this.controls = controls;
	}

	public ArrayList<SimpleEntry> getControls() {
		return controls;
	}

	public void setRequestWay(String requestWay) {
		this.requestWay = requestWay;
	}

	public String getRequestWay() {
		return requestWay;
	}
}
