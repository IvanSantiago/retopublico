package mx.gob.sct.utic.qoscell.sigtic.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.C_SI;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.INBOX;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.R_PROCESOS;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.SOL_A_EXT;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.USUARIOS;
import mx.gob.sct.utic.qoscell.sigtic.services.C_SI_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.INBOX_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.Mail_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.R_PROCESOS_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.SOL_A_EXT_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.SOL_SI_PERF_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.USUARIOS_Service;
import mx.gob.sct.utic.qoscell.util.Mailer;
import mx.gob.sct.utic.qoscell.util.Util;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Controller
@RequestMapping("/web")
public class Solicitudes_Controller {
	private R_PROCESOS_Service R_PROCESOS_Service;
	@Autowired
	public void setR_PROCESOS_Service(R_PROCESOS_Service R_PROCESOS_Service) {
		this.R_PROCESOS_Service = R_PROCESOS_Service;
	}
	
	private USUARIOS_Service USUARIOS_Service;
	@Autowired
	public void setUSUARIOS_Service(USUARIOS_Service USUARIOS_Service) {
		this.USUARIOS_Service = USUARIOS_Service;
	}
	
	private SOL_SI_PERF_Service SOL_SI_PERF_Service;
	@Autowired
	public void setSOL_SI_PERF_Service(SOL_SI_PERF_Service SOL_SI_PERF_Service) {
		this.SOL_SI_PERF_Service = SOL_SI_PERF_Service;
	}
	
	private INBOX_Service INBOX_Service;
	@Autowired
	public void setINBOX_Service(INBOX_Service INBOX_Service) {
		this.INBOX_Service = INBOX_Service;
	}
	
	private SOL_A_EXT_Service SOL_A_EXT_Service;
	@Autowired
	public void setSOL_A_EXT_Service(SOL_A_EXT_Service SOL_A_EXT_Service) {
		this.SOL_A_EXT_Service = SOL_A_EXT_Service;
	}
	
	private C_SI_Service C_SI_Service;
	@Autowired
	public void setC_SI_Service(C_SI_Service C_SI_Service) {
		this.C_SI_Service = C_SI_Service;
	}
	
	private Mail_Service Mail_Service;
	@Autowired
	public void setMail_Service(Mail_Service Mail_Service) {
		this.Mail_Service = Mail_Service;
	}
	/**
	 * Handles and retrieves the common JSP page that everyone can see
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/solicitudAccesoSistemas.action", method = RequestMethod.GET)
    public String getMainPage() {
    	return "pg00000001";
	}
    
	/**
	 * Handles and retrieves the common JSP page that everyone can see
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/solicitudAccesoSistemasExterno.action", method = RequestMethod.POST)
    public @ResponseBody Map<String,? extends Object> procesaSolicitud(@RequestParam(value = "UID_PROCESO", required = true) int UID_PROCESO,
    		@RequestParam(value = "UID_SI", required = true) int UID_SI,
    		@RequestParam(value = "SOL_SI_PERF", required = true) String[] SOL_SI_PERF,
    		@RequestParam(value = "FORM", required = true) Object FORM) throws Exception {
	//public @ResponseBody List<MenuNode> procesaSolicitud() throws Exception {
		try {
			long now = System.currentTimeMillis();
			Timestamp ejecucionSolicitud = new Timestamp(now);
			//Obtenemos el usuario logueado dentro del sistema
			USUARIOS loguedUser = USUARIOS_Service.getNewestUser(SecurityContextHolder.getContext().getAuthentication().getName());
			JSONObject jsonObject = JSONObject.fromObject(FORM);
			SOL_A_EXT solicitudExterna = (SOL_A_EXT) JSONObject.toBean(jsonObject, SOL_A_EXT.class);
			
			//Obtenemos el sistema al que se desea solicitar una cuenta
			C_SI sistema = C_SI_Service.getC_SI(UID_SI);
			
			//CREAMOS LA SOLICITUD
			R_PROCESOS solicitud = new R_PROCESOS();
			solicitud.setUID_PROCESO(UID_PROCESO);//Solicitud de Cuentas de Acceso a Sistemas para Usuarios Externos
			solicitud.setUID_UA(loguedUser.getUID_UA());
			solicitud.setUID_EMPLEADO(loguedUser.getUID_EMPLEADO());
			solicitud.setSTAMP(ejecucionSolicitud);
			solicitud.setUID_EMPLEADO_SOL(31);// ES EL UID_OPTPSIN INFORMACION en la base datos porque es un usuario externo
			solicitud = R_PROCESOS_Service.create(solicitud);
			
			//CREAMOS EL MENSAJE DE INBOX
			INBOX solicitudBandeja = new INBOX();
			solicitudBandeja.setUID_R_PROCESO(solicitud.getUID_R_PROCESO());
			solicitudBandeja.setUID_PROCESO(solicitud.getUID_PROCESO());
			solicitudBandeja.setPASO(6);//Paso de catalodos de la base de datos
			solicitudBandeja.setUID_UA(solicitud.getUID_UA());
			solicitudBandeja.setUID_EMPLEADO(sistema.getUID_EMPLEADO());
			solicitudBandeja.setSTAMP(ejecucionSolicitud);
			solicitudBandeja.setSTATUS(2);//Estatus dentro de la base de datos
			solicitudBandeja = INBOX_Service.create(solicitudBandeja);
			
			//Creamos la solicitud externa
			solicitudExterna.setUID_R_PROCESO(solicitud.getUID_R_PROCESO());
			solicitudExterna.setUID_SI(UID_SI);
			String nombrePerfiles = "PERFILES: ";
			for (String string : SOL_SI_PERF) {
				nombrePerfiles += string;
			}			
			solicitudExterna.setCVE(nombrePerfiles);
			SOL_A_EXT solicitudCreada = SOL_A_EXT_Service.create(solicitudExterna);
			System.out.println(solicitudExterna.getUID_R_PROCESO());
			
			/*
			Mail_Service.sendMail("sigtic_externo@sct.gob.mx",
	    		   "falliv@gmail.com",
	    		   "Testing123", 
	    		   "Testing only \n\n Hello Spring Email Sender");
		   */			
			return getMenuMap(true, "Solicitud creada exitosamente","PDF.action?output=sol&id="+solicitudCreada.getUID_R_PROCESO(),new ArrayList());

		} catch (Exception e) {
			e.printStackTrace();
			return getMenuMap(false, "Error al crear la solicitud","",new ArrayList());
		}
	}
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param boolean isSuccess, String message, String URL, List<?> Registers
	 * @return
	 */
	@SuppressWarnings("unused")
	public Map<String,Object> getMenuMap(boolean isSuccess, String message, String URL, List<?> registros){
		Map<String,Object> modelMap = new HashMap<String,Object>(5);
		modelMap.put("success", isSuccess);
		modelMap.put("msg", message);
		modelMap.put("url", URL);
		modelMap.put("total", registros.size());
		modelMap.put("data", registros);
		return modelMap;
	} 
}
