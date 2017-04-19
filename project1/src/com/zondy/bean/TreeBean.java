package com.zondy.bean;

import java.util.List;

public class TreeBean implements Comparable<TreeBean>{
	private String text;
	private String id;
	private List children;
	private String state;
    public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	/*	private int recordsize;
	public int getRecordsize() {
		return recordsize;
	}
	public void setRecordsize(int recordsize) {
		this.recordsize = recordsize;
	}
	*/
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List getChildren() {
		return children;
	}
	public void setChildren(List children) {
		this.children = children;
	}
	@Override
	public int compareTo(TreeBean o) {
		return this.getText().compareTo(o.getText());
	}
}
