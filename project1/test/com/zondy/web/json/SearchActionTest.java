package com.zondy.web.json;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SearchActionTest extends StrutsSpringTestCase{


	@Test
	public void testSearch() {
		request.setParameter("dw", "建湖县公安局");
		request.setParameter("key", "派出所");
		request.setParameter("jingzhong", "");
		request.setParameter("page", "2");
		
		String result = "";
		try {
			result = executeAction("/json/Search_search");
			System.out.println(result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testPutTree() {
		String result = "";
		try {
			result = executeAction("/json/Search_putTree");
			System.out.println(result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
   
	/*@Test
	public void testTreeSearch() {
		request.setParameter("TreeInfo", "交通警察");
		String result = "";
		try {
			result = executeAction("/Search_TreeSearch");
			System.out.println(result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}*/

}
