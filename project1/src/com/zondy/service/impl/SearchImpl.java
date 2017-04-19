package com.zondy.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.lucene.search.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.zondy.bean.ResultInfo;
import com.zondy.bean.TreeBean;
import com.zondy.property.PropertiesConfig;
import com.zondy.service.inter.ISearch;

public class SearchImpl implements ISearch{
	private JdbcTemplate jdbctmp;
	
	private PropertiesConfig property;
	
	public JdbcTemplate getJdbctmp() {
		return jdbctmp;
	}

	public void setJdbctmp(JdbcTemplate jdbctmp) {
		this.jdbctmp = jdbctmp;
	}
	
	public PropertiesConfig getProperty() {
		return property;
	}

	public void setProperty(PropertiesConfig property) {
		this.property = property;
	}
	
	//根据type，searchInfo，jz，页数查找记录
	@Override
	public List<ResultInfo> search(String type, String searchInfo,String jz,int page)
			throws Exception {
		int pageNum = Integer.valueOf(this.property.getInitParameter("pageNum"));
		int page_start = (page-1)*pageNum +1;
		int page_end = page*pageNum;
		List<ResultInfo> list = new ArrayList<ResultInfo>();
		
		String subStr = " 1=1 ";
		if(searchInfo!=null && !"null".equals(searchInfo) && !"".equals(searchInfo)){
			subStr += " and con like '%"+searchInfo+"%'";
		}
		if(type!=null && !"null".equals(type) && !"".equals(type)){
			subStr += " and MC like '%"+type+"%'";
		}
		if(jz!=null && !"null".equals(jz) && !"".equals(jz)){
			//subStr += " and jz like '%"+jz+"%'";
		}
		String sql = "select XM, JH, SFZHM, MC from "
				+ "(select XM, JH, SFZHM, MC, con,rownum r from "
				+ "(select XM, JH, SFZHM, MC, XM || JH || SFZHM || MC con from JH_USER, ZD_DIC where ZZJGDM = MC)"
				+ " where  "+subStr+")"
				+ " where r>= "+page_start+"and r<="+page_end;
		
	
		list=this.jdbctmp.query(sql, new RowMapper<ResultInfo>(){
			@Override
			public ResultInfo mapRow(ResultSet rs, int i)
					throws SQLException {
				ResultInfo bean=new ResultInfo();
				bean.setXm(rs.getString(1));
				bean.setJh(rs.getString(2));
				bean.setSfzhm(rs.getString(3));
				bean.setMc(rs.getString(4));
				return bean;
			}
		});
		return list;
	}
	
	
	/*public List<ResultInfo> TreeSearch(String TreeInfo) throws Exception {
		List<ResultInfo> list = new ArrayList<ResultInfo>();
		//参数key数据库中到不到对应的参数
		String sql = "select JH_USER.XM,JH_USER.JH,JH_USER.SFZHM,ZD_DIC.MC from JH_USER join ZD_DIC on JH_USER.ZZJGDM=ZD_DIC.MC where ZD_DIC.MC LIKE '%建湖县%' AND ZD_DIC.MC like '%"+TreeInfo+"%'";
		list=this.jdbctmp.query(sql, new RowMapper<ResultInfo>(){
			@Override
			public ResultInfo mapRow(ResultSet rs, int i)
					throws SQLException {
				ResultInfo bean=new ResultInfo();
				bean.setXm(rs.getString(1));
				bean.setJh(rs.getString(2));
				bean.setSfzhm(rs.getString(3));
				bean.setMc(rs.getString(4));
				return bean;
			}
		});
		return list;
	}*/
	//返回查询符合查询条件记录的总数
	public int count(String type, String searchInfo, String jz)
			throws Exception {
        int count = 0;
        String subStr = " 1=1 ";
		if(searchInfo!=null && !"null".equals(searchInfo) && !"".equals(searchInfo)){
			subStr += " and con like '%"+searchInfo+"%'";
		}
		if(type!=null && !"null".equals(type) && !"".equals(type)){
			subStr += " and MC like '%"+type+"%'";
		}
		if(jz!=null && !"null".equals(jz) && !"".equals(jz)){
			//subStr += " and jz like '%"+jz+"%'";
		}
		String sql = "select count(*) from (select XM, JH, SFZHM, MC, XM || JH || SFZHM || MC con from JH_USER, ZD_DIC where ZZJGDM = MC) where "+subStr;
		try {
			count=this.jdbctmp.queryForInt(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return count;
	}


	/*@Override
	public Map<String,List<Map<String, List<String>>>> putTree() throws Exception {
		//获取建湖县的所有信息，相当于得到二级节点
		String sql1 = "select MC from ZD_DIC where MC like '%建湖县%' and BM like '%0000' and bm not like '%000000'";
		List<String> strings = this.jdbctmp.query(sql1,
				new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int i)
							throws SQLException {
						return rs.getString(1);
					}
				});
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<Map<String, List<String>>> list2 = new ArrayList<Map<String, List<String>>>();
		//将三级节点的信息放入二级节点中
		for (String third : strings) {
			String sql2 = "select MC from ZD_DIC where MC like '"+third+"%'";
			
			List<String> list = this.jdbctmp.query(sql2,
					new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int i)
								throws SQLException {
							return rs.getString(1);
						}
					});
			map.put(third, list);
			list2.add(map);
		}
		//将所有信息放入一级节点“建湖县公安局中”
//		for(Entry<String, List<String>> entry : map.entrySet()){
//			list2.add((Map<String, List<String>>) entry);
//		}
		Map<String,List<Map<String, List<String>>>> map2 = new HashMap<String,List<Map<String, List<String>>>>();
		map2.put("建湖县公安局", list2);
		return map2;
	}*/
	@Override
	public TreeBean putTree2() {
		List<TreeBean> list= new ArrayList<TreeBean>();
		String sql1 = "select bm, mc from ZD_DIC where bm like '320925__0000'  and bm not like '%000000'";
		list=this.jdbctmp.query(sql1, new RowMapper<TreeBean>(){
			@Override
			public TreeBean mapRow(ResultSet rs, int i)
					throws SQLException {
				TreeBean bean=new TreeBean();
				bean.setId(rs.getString(1));
				bean.setText(rs.getString(2).substring(6,rs.getString(2).length()));
				int a =getChirldren1(bean.getId());
				if(a>0){
					bean.setState("closed");
				}
				return bean;
			}
		});
		for (TreeBean third : list){
			String sql2 = "select bm,mc from ZD_DIC where bm like '"+third.getId().substring(0,8)+"__00' and bm<>'"+third.getId()+"'";
			List<TreeBean> chirldren = this.jdbctmp.query(sql2,
					new RowMapper<TreeBean>() {
						@Override
						public TreeBean mapRow(ResultSet rs, int i)
								throws SQLException {
							TreeBean bean = new TreeBean();
							bean.setId(rs.getString(1));
							bean.setText(rs.getString(2).substring(9,rs.getString(2).length()));
							List<TreeBean> clist=getChirldren(bean.getId());
							if(clist.size()>0){
								bean.setChildren(clist);
								bean.setState("closed");
							}
							return bean;
						}
					});
			third.setChildren(chirldren);
		}
		
		TreeBean bean=new TreeBean();
		bean.setChildren(list);
		bean.setId("320925000000");
		bean.setText("建湖县公安局");
		return bean;

	}

	public List<TreeBean> getChirldren(String bm) {
		String sql3 = "select bm,mc from ZD_DIC where bm like '"+bm.substring(0,10)+"__' and bm<> '"+bm+"'";
		List<TreeBean> clist = this.jdbctmp.query(sql3,
				new RowMapper<TreeBean>() {
					@Override
					public TreeBean mapRow(ResultSet rs, int i)
							throws SQLException {
						TreeBean bean = new TreeBean();
						bean.setId(rs.getString(1));
						bean.setText(rs.getString(2).substring(6,rs.getString(2).length()));
	//					int a = count(rs.getString(2));
						return bean;
					}
				});
		return clist;
	}
	
	private int getChirldren1(String id) {
		int count=0;
		String sql = "select count(*) from (select bm,mc from ZD_DIC where bm like '"+id.substring(0,8)+"__00' and bm<>'"+id+"')";
		try {
			count=this.jdbctmp.queryForInt(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return count;
	}
/*	private int count(String string) {
		int count = 0;
        String subStr = " 1=1 ";
		if(string!=null && !"null".equals(string) && !"".equals(string)){
			subStr += " and con like '%"+string+"%'";
		}
		String sql = "select count(*) from (select XM, JH, SFZHM, MC, XM || JH || SFZHM || MC con from JH_USER, ZD_DIC where ZZJGDM = MC) where "+subStr;
		try {
			count=this.jdbctmp.queryForInt(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(count);
        return count;
	}*/
}
