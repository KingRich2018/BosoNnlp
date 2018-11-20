package com.boso;
import org.json.JSONArray;
import org.json.JSONObject;

import com.enumBean.TypeBean;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.util.Util;
public class Bosonnlp {
	
	public static String determine(String url,String [] data) throws UnirestException {
		String body = new JSONArray(data).toString();
		System.out.println(body);
	   HttpResponse<JsonNode> jsonResponse = Unirest.post(url)
		            .header(TypeBean.ACCEPT.getCode(), TypeBean.ACCEPT.getDesc())
		            .header(TypeBean.TYPE.getCode(), TypeBean.TYPE.getDesc())
		            .header(TypeBean.TOKEN.getCode(), TypeBean.TOKEN.getDesc())
		            .body(body)
		            .asJson();
		return jsonResponse.getBody().toString();
	    }
	public static String depparser(String [] data) throws UnirestException {
		String body = new JSONArray(data).toString();
		HttpResponse<JsonNode> jsonResponse = Unirest.post(TypeBean.DEPPARSER.getDesc())
				.header(TypeBean.ACCEPT.getCode(), TypeBean.ACCEPT.getDesc())
				.header(TypeBean.TYPE.getCode(), TypeBean.TYPE.getDesc())
				.header(TypeBean.TOKEN.getCode(), TypeBean.TOKEN.getDesc())
				.body(body)
				.asJson();
		JsonNode s = jsonResponse.getBody();
		System.out.println(body);
		JSONArray jsonArray =s.getArray();
		String str = Util.role(jsonArray);
		return str;
	}
	
	public static String ner(String [] data) throws UnirestException {
		String body = new JSONArray(data).toString();
		HttpResponse<JsonNode> jsonResponse = Unirest.post(TypeBean.NER.getDesc())
				.header(TypeBean.ACCEPT.getCode(), TypeBean.ACCEPT.getDesc())
				.header(TypeBean.TYPE.getCode(), TypeBean.TYPE.getDesc())
				.header(TypeBean.TOKEN.getCode(), TypeBean.TOKEN.getDesc())
				.queryString("sensitivity", 1)
				.body(body)
				.asJson();
		JsonNode s = jsonResponse.getBody();
		JSONArray jsonArray =s.getArray();
		String str = Util.entity(jsonArray);
		return str;
	}
	public static String suggest(String url,String  data) throws UnirestException {
		//String body = new JSONArray(data).toString();
		//System.out.println(body);
	   HttpResponse<JsonNode> jsonResponse = Unirest.post(url)
		            .header(TypeBean.ACCEPT.getCode(), TypeBean.ACCEPT.getDesc())
		            .header(TypeBean.TYPE.getCode(), TypeBean.TYPE.getDesc())
		            .header(TypeBean.TOKEN.getCode(), TypeBean.TOKEN.getDesc())
		            .queryString("top_k", 10)
		            .body(data)
		            .asJson();

		return jsonResponse.getBody().toString();
	    }
	public static String time(String time) throws UnirestException {
		   HttpResponse<JsonNode> jsonResponse = Unirest.post(TypeBean.TIME.getDesc())
			            .header(TypeBean.ACCEPT.getCode(), TypeBean.ACCEPT.getDesc())
			            .header(TypeBean.TYPE.getCode(), TypeBean.TYPE.getDesc())
			            .header(TypeBean.TOKEN.getCode(), TypeBean.TOKEN.getDesc())
			            .queryString("pattern", time)
			            .body("")
			            .asJson();
			return jsonResponse.getBody().toString();
		    }
	
	/*public static String summary(String url,JSONObject jsonObject) throws UnirestException {
		
		   HttpResponse<String> jsonResponse = Unirest.post(url)
			            .header(TypeBean.ACCEPT.getCode(), TypeBean.ACCEPT.getDesc())
			            .header(TypeBean.TYPE.getCode(), TypeBean.TYPE.getDesc())
			            .header(TypeBean.TOKEN.getCode(), TypeBean.TOKEN.getDesc())
			            .queryString("not_exceed", 0)
			            .queryString("percentage", 0.1)
			            .body(jsonObject)
			            .asString();
			return jsonResponse.getBody().toString();
		    }*/
}
