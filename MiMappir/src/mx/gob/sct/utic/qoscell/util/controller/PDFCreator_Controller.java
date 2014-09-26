package mx.gob.sct.utic.qoscell.util.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.C_01_04_UA;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.C_03_02_EMPLEADOS;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.C_R_UA;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.DOC_PROC;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.OPT_DOCS;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.OPT_PROC;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.R_PROCESOS;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.SOL_A_EXT;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.S_PROCESOS;
import mx.gob.sct.utic.qoscell.sigtic.services.C_01_04_UA_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.C_03_02_EMPLEADOS_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.C_R_UA_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.DOC_PROC_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.OPT_DOCS_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.OPT_PROC_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.R_PROCESOS_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.SOL_A_EXT_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.SQL_ARG_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.S_PROCESOS_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import weblogic.jdbc.wrapper.PreparedStatement;

@Controller
@RequestMapping("/web")
public class PDFCreator_Controller extends AbstractController {
/*	private R_PROCESOS_Service R_PROCESOS_Service;
	@Autowired
	public void setR_PROCESOS_Service(R_PROCESOS_Service R_PROCESOS_Service) {
		this.R_PROCESOS_Service = R_PROCESOS_Service;
	}
	private S_PROCESOS_Service S_PROCESOS_Service;
	@Autowired
	public void setS_PROCESOS_Service(S_PROCESOS_Service S_PROCESOS_Service) {
		this.S_PROCESOS_Service = S_PROCESOS_Service;
	}
	private OPT_PROC_Service OPT_PROC_Service;
	@Autowired
	public void setOPT_PROC_Service(OPT_PROC_Service OPT_PROC_Service) {
		this.OPT_PROC_Service = OPT_PROC_Service;
	}
	private C_01_04_UA_Service C_01_04_UA_Service;
	@Autowired
	public void setC_01_04_UA_Service(C_01_04_UA_Service C_01_04_UA_Service) {
		this.C_01_04_UA_Service = C_01_04_UA_Service;
	}
	private DOC_PROC_Service DOC_PROC_Service;
	@Autowired
	public void setDOC_PROC_Service(DOC_PROC_Service DOC_PROC_Service) {
		this.DOC_PROC_Service = DOC_PROC_Service;
	}
	private OPT_DOCS_Service OPT_DOCS_Service;
	@Autowired
	public void setOPT_DOCS_Service(OPT_DOCS_Service OPT_DOCS_Service) {
		this.OPT_DOCS_Service = OPT_DOCS_Service;
	}	
	private SQL_ARG_Service SQL_ARG_Service;
	@Autowired
	public void setSQL_ARG_Service(SQL_ARG_Service SQL_ARG_Service) {
		this.SQL_ARG_Service = SQL_ARG_Service;
	}	
	private C_R_UA_Service C_R_UA_Service;
	@Autowired
	public void setC_R_UA_Service(C_R_UA_Service C_R_UA_Service) {
		this.C_R_UA_Service = C_R_UA_Service;
	}	
	private C_03_02_EMPLEADOS_Service C_03_02_EMPLEADOS_Service;
	@Autowired
	public void setC_03_02_EMPLEADOS_Service(C_03_02_EMPLEADOS_Service C_03_02_EMPLEADOS_Service) {
		this.C_03_02_EMPLEADOS_Service = C_03_02_EMPLEADOS_Service;
	}	
	private SOL_A_EXT_Service SOL_A_EXT_Service;
	@Autowired
	public void setSOL_A_EXT_Service(SOL_A_EXT_Service SOL_A_EXT_Service) {
		this.SOL_A_EXT_Service = SOL_A_EXT_Service;
	}		*/
	@RequestMapping(value="/PDF.action")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String output = ServletRequestUtils.getStringParameter(request,
				"output");
				Map<String, String> revenueData = new HashMap<String, String>();
		revenueData.put("1/23/2010", "$400,000");
		revenueData.put("1/24/2010", "$500,000");
		
		if (output == null || "".equals(output)) {
			// return normal view
			return new ModelAndView("RevenueSummary", "revenueData",
					revenueData);

		} else if ("PDF".equals(output.toUpperCase())) {
			// return excel view
			return new ModelAndView("PdfRevenueSummary", "revenueData",
					revenueData);

		} /*else if ("SOL".equals(output.toUpperCase())) {
			long doc_id = System.currentTimeMillis();
			Timestamp stamp = new Timestamp(doc_id);
			String firma = Long.toHexString(doc_id).toUpperCase();
			//String UID_R_PROCESO = ServletRequestUtils.getStringParameter(request,"UID_R_PROCESO");
			String UID_R_PROCESO = ServletRequestUtils.getStringParameter(request,"id");
			R_PROCESOS process = (R_PROCESOS) R_PROCESOS_Service.getR_PROCESOS(Integer.parseInt(UID_R_PROCESO));
			S_PROCESOS processInfo = (S_PROCESOS)S_PROCESOS_Service.getS_PROCESOS(process.getUID_PROCESO());
			OPT_PROC processType = (OPT_PROC)OPT_PROC_Service.getOPT_PROC(process.getUID_EMPLEADO_SOL());
			C_01_04_UA processUA = C_01_04_UA_Service.getC_01_04_UA(process.getUID_UA());
			DOC_PROC PdfDocumentRegister = DOC_PROC_Service.getDOC_PROCofR_UID_PROCESOS(Integer.parseInt(UID_R_PROCESO));
			if(PdfDocumentRegister == null){
				PdfDocumentRegister = new DOC_PROC();
				PdfDocumentRegister.setDOC_ID(new Integer((int) doc_id));
				PdfDocumentRegister.setFIRMA(firma);
				PdfDocumentRegister.setUID_R_PROCESO(process.getUID_R_PROCESO());
				PdfDocumentRegister.setUID_PROCESO(process.getUID_PROCESO());
				PdfDocumentRegister.setUID_UA(process.getUID_UA());
				PdfDocumentRegister.setVALIDACION(stamp);
				PdfDocumentRegister = DOC_PROC_Service.create(PdfDocumentRegister);
			}
			List<OPT_DOCS> elementosProceso = OPT_DOCS_Service.getOPT_DOCSOfUID_OPTP(processType.getUID_OPTP());
			Iterator<OPT_DOCS> ite = elementosProceso.iterator();
			
			Map<String,List<Object>> infoElementosProceso = new HashMap<String,List<Object>>();
			while(ite.hasNext()){
				OPT_DOCS registro = ite.next();
				List<Object> infoElement = new ArrayList<Object>();
				if(registro.getTIPO_ARG().compareTo("QUERY") == 0){
					infoElement = SQL_ARG_Service.getList(registro.getSQL_ARG(), registro.getPSQL_ARG().split(";"), request);				
				}
				infoElementosProceso.put(registro.getMETA_ARG(), infoElement);
			}
			
			C_R_UA responsables = C_R_UA_Service.getC_R_UAofUA(process.getUID_UA());
			C_03_02_EMPLEADOS responsableRIN = C_03_02_EMPLEADOS_Service.getC_03_02_EMPLEADOSofUID_EMPLEADO(responsables.getRIN_UID());
			C_03_02_EMPLEADOS responsableInterno = C_03_02_EMPLEADOS_Service.getC_03_02_EMPLEADOSofUID_EMPLEADO(process.getUID_EMPLEADO());
			SOL_A_EXT responsableExterno = SOL_A_EXT_Service.getSOL_A_EXTofUID_R_PROCESO(process.getUID_R_PROCESO());
			
			Map<String, Object> DATA = new HashMap<String, Object>();
			DATA.put("process",process);
			DATA.put("processInfo",processInfo);
			DATA.put("processType",processType);
			DATA.put("processUA",processUA);
			DATA.put("PdfDocumentRegister",PdfDocumentRegister);
			DATA.put("elementosProceso",elementosProceso);
			DATA.put("infoElementosProceso",infoElementosProceso);
			DATA.put("responsableInternoProceso",responsableInterno);
			DATA.put("responsableExternoProceso",responsableExterno);
			DATA.put("responsableRINProceso",responsableRIN);
			
			return new ModelAndView("PdfSolicitud", "DATA",
					DATA);
		} */else{
			// return normal view
			return new ModelAndView("RevenueSummary", "revenueData",
					revenueData);

		}
	}
}
