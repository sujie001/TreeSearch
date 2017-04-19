package com.zondy.web.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zondy.bean.PeopleInfo;
import com.zondy.bean.ResultInfo;
import com.zondy.bean.TreeBean;
import com.zondy.bean.jianghudw;
import com.zondy.service.impl.SearchImpl;
import com.zondy.web.action.BaseAction;

public class SearchAction extends BaseAction{
	/**
	 * 
	 */
	private SearchImpl searchImpl;
	private String dw;
	private String key;
	private String jingzhong;
	private String TreeInfo;
	private int page;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	
	public String getTreeInfo() {
		return TreeInfo;
	}
	public void setTreeInfo(String treeInfo) {
		TreeInfo = treeInfo;
	}
	public SearchImpl getSearchImpl() {
		return searchImpl;
	}
	public void setSearchImpl(SearchImpl searchImpl) {
		this.searchImpl = searchImpl;
	}
	public String getDw() {
		return dw;
	}
	public String getKey() {
		return key;
	}
	public String getJingzhong() {
		return jingzhong;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setJingzhong(String jingzhong) {
		this.jingzhong = jingzhong;
	}
	public String search() throws Exception{
		List<ResultInfo> list=new ArrayList<ResultInfo>();
		try {
			list=this.searchImpl.search(dw, key,jingzhong,page);
			
			int count = this.searchImpl.count(dw, key,jingzhong);
			this.dataMap.put("state", "ok");
			this.dataMap.put("data", list);
			this.dataMap.put("total", count);
		} catch (Exception e){
            this.dataMap.put("state", "exception");
			e.printStackTrace();
		}
		return "map";
	}
	public String putTree(){
		TreeBean map = new TreeBean();
		try{
			map = this.searchImpl.putTree2();
			
			this.Tree.add(map);
		}catch(Exception e){
			this.dataMap.put("state", "exception");
			e.printStackTrace();
		}
		return "tree";
	}
	
	public String putTree2(){
		TreeBean map = new TreeBean();
		try{
			map = this.searchImpl.putTree2();
			this.Tree.add(map);
		}catch(Exception e){
			this.dataMap.put("state", "exception");
			e.printStackTrace();
		}
		return "tree";
	}
	/*public String TreeSearch() throws Exception{
		List<ResultInfo> list=new ArrayList<ResultInfo>();
		try {
			list=this.searchImpl.TreeSearch(TreeInfo);
			this.dataMap.put("state", "ok");
			this.dataMap.put("data", list);
		} catch (Exception e) {
			this.dataMap.put("state", "exception");
			e.printStackTrace();
		}
		return "map";
	}*/

}
