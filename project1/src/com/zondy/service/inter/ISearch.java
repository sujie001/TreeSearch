package com.zondy.service.inter;
import java.util.List;
import java.util.Map;

import com.zondy.bean.ResultInfo;
import com.zondy.bean.TreeBean;
import com.zondy.bean.dwInfo;
import com.zondy.bean.jianghudw;

public interface ISearch{
	//输出查询结果
	public List<ResultInfo> search(String type,String seacrhInfo,String jz,int page_now) throws Exception ;
	//输出树状图的信息
	//Map<String,List<Map<String, List<String>>>> putTree() throws Exception;
	public TreeBean putTree2() throws Exception;
	//public int count(String type, String searchInfo,String jz)throws Exception;
}
