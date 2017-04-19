package com.zondy.json.util;

import java.util.List;
public class QueryResult<T> {
    private  List<T>  resultList;
    private long totalCount;
	public QueryResult(){
		
	}
	public QueryResult(List<T> resultList, long totalCount) {
		this.resultList = resultList;
		this.totalCount = totalCount;
	}
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
}
