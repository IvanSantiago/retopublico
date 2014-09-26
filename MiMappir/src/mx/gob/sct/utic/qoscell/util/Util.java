package mx.gob.sct.utic.qoscell.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.C_SI;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * Util class. Contains some common methods that can be used
 * for any class
 * 
 * @author Ivan Santiago
 *
 */@Component
public class Util {
	
	/**
	 * Get list of Contacts from request.
	 * @param data - json data from request 
	 * @return list of Contacts
	 */
	public List<C_SI> getContactsFromRequest(Object data){

		List<C_SI> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListContactsFromJSON(data);

		} else { //it is only one object - cast to object/bean

			C_SI contact = getContactFromJSON(data);

			list = new ArrayList<C_SI>();
			list.add(contact);
		}

		return list;
	}

	/**
	 * Transform json data format into Contact object
	 * @param data - json data from request
	 * @return 
	 */
	private C_SI getContactFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		C_SI newContact = (C_SI) JSONObject.toBean(jsonObject, C_SI.class);
		return newContact;
	}

	/**
	 * Transform json data format into list of Contact objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<C_SI> getListContactsFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<C_SI> newContacts = (List<C_SI>) JSONArray.toCollection(jsonArray,C_SI.class);
		return newContacts;
	}

	/**
	 * Tranform array of numbers in json data format into
	 * list of Integer
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListIdFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<Integer> idContacts = (List<Integer>) JSONArray.toCollection(jsonArray,Integer.class);
		return idContacts;
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param registros
	 * @return
	 */
	@SuppressWarnings("unused")
	public Map<String,Object> getMap(List<?> registros){		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", registros.size());
		modelMap.put("data", registros);
		modelMap.put("success", true);		
		return modelMap;
	}
	
	/**
	 * Generates modelMap to return in the modelAndView in case
	 * of exception
	 * @param msg message
	 * @return
	 */
	@SuppressWarnings("unused")
	public Map<String,Object> getModelMapError(String msg){
		Map<String,Object> modelMap = new HashMap<String,Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", false);
		return modelMap;
	} 
}
