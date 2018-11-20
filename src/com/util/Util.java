package com.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Util {
	public static String classify(int key) {
		//0体育1教育2财经3社会4娱乐5军事6国内7	科技8	互联网9房产10国际11女人12	汽车13游戏
		String str = "";
		switch (key) {
		case 0:
			str="体育";
			break;
		case 1:
			str="教育";
			break;
		case 2:
			str="财经";
			break;
		case 3:
			str="社会";
			break;
		case 4:
			str="娱乐";
			break;
		case 5:
			str="军事";
			break;
		case 6:
			str="国内";
			break;
		case 7:
			str="科技";
			break;
		case 8:
			str="互联网";
			break;
		case 9:
			str="房产";
			break;
		case 10:
			str="国际";
			break;
		case 11:
			str="女人";
			break;
		case 12:
			str="汽车";
			break;
		case 13:
			str="游戏";
			break;
		default:
			break;
		}
		return str;
	}
	
	public static String entity(JSONArray jsonArray) {
		String entity = "";
		String word = "";
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jo = jsonArray.getJSONObject(i);
			entity = jo.get("entity").toString();
			word = jo.get("word").toString();
		}
		net.sf.json.JSONArray obj = net.sf.json.JSONArray.fromObject(entity);
		net.sf.json.JSONArray objWord = net.sf.json.JSONArray.fromObject(word);
		List<Object>  list = new ArrayList<>();
		for (int j = 0; j < obj.size(); j++) {
			list.add(obj.get(j));
		}
		String str = "";
		for (Object object : list) {
			@SuppressWarnings("unchecked")
			List<Object>  list2 = (List<Object>) object;
			str +=list2.get(2)+":"+objWord.get(Integer.parseInt(list2.get(0).toString()))+",";
		}
		return str;
	}
	public static String role(JSONArray jsonArray) {
		String role = "";
		String word = "";
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jo = jsonArray.getJSONObject(i);
			role = jo.get("role").toString();
			word = jo.get("word").toString();
		}
		net.sf.json.JSONArray objrole = net.sf.json.JSONArray.fromObject(role);
		net.sf.json.JSONArray objWord = net.sf.json.JSONArray.fromObject(word);
		String str = "";
		for (int j = 0; j < objrole.size(); j++) {
			str += depparser(objrole.get(j).toString())+":"+objWord.get(j)+"\n";
		}
		return str;
	}
	
	public static String depparser(String key) {
		String str = "";
		switch (key) {
		case "ROOT":
			str="核心词";
			break;
		case "SBJ":
			str="主语成分";
			break;
		case "OBJ":
			str="宾语成分";
			break;
		case "PU":
			str="标点符号";
			break;
		case "TMP":
			str="时间成分	";
			break;
		case "LOC":
			str="位置成分";
			break;
		case "MNR":
			str="方式成分";
			break;
		case "POBJ":
			str="介宾成分";
			break;
		case "PMOD":
			str="介词修饰";
			break;
		case "NMOD":
			str="名词修饰";
			break;
		case "VMOD":
			str="动词修饰";
			break;
		case "VRD":
			str="动结式 （第二动词为第一动词结果）";
			break;
		case "DEG":
			str="连接词“的”结构";
			break;
		case "DEV":
			str="“地”结构";
			break;
		case "LC":
			str="位置词结构";
			break;
		case "M":
			str="量词结构";
			break;
		case "AMOD":
			str="副词修饰";
			break;
		case "PRN":
			str="括号成分";
			break;
		case "VC":
			str="动词“是”修饰";
			break;
		case "COOR":
			str="并列关系";
			break;
		case "CS":
			str="从属连词成分";
			break;
		case "DEC":
			str="关系从句“的”";
			break;
		
		default:
			break;
		}
		return str;
	}
}
