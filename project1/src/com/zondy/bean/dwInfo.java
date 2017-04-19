package com.zondy.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dwInfo {
	private Map<String,List<String>> parentNode = new HashMap<String,List<String>>();
	private Map<String,List<String>> chirldNode = new HashMap<String,List<String>>();
	
	public Map<String, List<String>> getParentNode() {
		return parentNode;
	}
	public void setParentNode(Map<String, List<String>> parentNode) {
		this.parentNode = parentNode;
	}
	public Map<String, List<String>> getChirldNode() {
		return chirldNode;
	}
	public void setChirldNode(Map<String, List<String>> chirldNode) {
		this.chirldNode = chirldNode;
	}
	

}
