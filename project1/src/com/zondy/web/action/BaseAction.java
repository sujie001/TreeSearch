package com.zondy.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements SessionAware,
		ServletRequestAware, ServletResponseAware {

	/**
	 * 当需要返回JSON格式数据时，将需要返回到前台的数据加入到dataMap中，
	 * 应用场景： 后台JsonAction中 dataMap.put("data",需要往前台发送的数据)
	 * 则前台获取时  function(data,status) {alert(data.data);//data.data就是后台放入的数据}
	 */
	public List<Object> Tree = new ArrayList<Object>();
	public Map<String,Object> dataMap = new HashMap<String,Object>();
	public Map<String, Object> map = new HashMap<String,Object>();
	public HttpServletRequest request;
	public HttpServletResponse response;
	
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		this.map=map;
	}


	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	/**
	 * 作者
	 */
	protected String author = null;
	
	/***
	 * 
	 * 功能描述：设置作者<br>
	 * 创建作者：邓皓<br>
	 * 创建时间：2014-2-25 下午3:02:15<br>
	 * @param author
	 * void
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取作者
	 * @return
	 */
	public String getAuthor(){
		return author;
	}
}
