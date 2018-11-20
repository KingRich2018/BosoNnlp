package com.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Util {
	public static String classify(int key) {
		//0����1����2�ƾ�3���4����5����6����7	�Ƽ�8	������9����10����11Ů��12	����13��Ϸ
		String str = "";
		switch (key) {
		case 0:
			str="����";
			break;
		case 1:
			str="����";
			break;
		case 2:
			str="�ƾ�";
			break;
		case 3:
			str="���";
			break;
		case 4:
			str="����";
			break;
		case 5:
			str="����";
			break;
		case 6:
			str="����";
			break;
		case 7:
			str="�Ƽ�";
			break;
		case 8:
			str="������";
			break;
		case 9:
			str="����";
			break;
		case 10:
			str="����";
			break;
		case 11:
			str="Ů��";
			break;
		case 12:
			str="����";
			break;
		case 13:
			str="��Ϸ";
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
			str="���Ĵ�";
			break;
		case "SBJ":
			str="����ɷ�";
			break;
		case "OBJ":
			str="����ɷ�";
			break;
		case "PU":
			str="������";
			break;
		case "TMP":
			str="ʱ��ɷ�	";
			break;
		case "LOC":
			str="λ�óɷ�";
			break;
		case "MNR":
			str="��ʽ�ɷ�";
			break;
		case "POBJ":
			str="����ɷ�";
			break;
		case "PMOD":
			str="�������";
			break;
		case "NMOD":
			str="��������";
			break;
		case "VMOD":
			str="��������";
			break;
		case "VRD":
			str="����ʽ ���ڶ�����Ϊ��һ���ʽ����";
			break;
		case "DEG":
			str="���Ӵʡ��ġ��ṹ";
			break;
		case "DEV":
			str="���ء��ṹ";
			break;
		case "LC":
			str="λ�ôʽṹ";
			break;
		case "M":
			str="���ʽṹ";
			break;
		case "AMOD":
			str="��������";
			break;
		case "PRN":
			str="���ųɷ�";
			break;
		case "VC":
			str="���ʡ��ǡ�����";
			break;
		case "COOR":
			str="���й�ϵ";
			break;
		case "CS":
			str="�������ʳɷ�";
			break;
		case "DEC":
			str="��ϵ�Ӿ䡰�ġ�";
			break;
		
		default:
			break;
		}
		return str;
	}
}
