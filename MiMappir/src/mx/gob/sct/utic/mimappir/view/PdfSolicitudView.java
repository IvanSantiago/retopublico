package mx.gob.sct.utic.mimappir.view;

import java.awt.Color;
import java.net.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.C_01_04_UA;
import mx.gob.sct.utic.mimappir.sigtic.db2.model.C_03_02_EMPLEADOS;
import mx.gob.sct.utic.mimappir.sigtic.db2.model.DOC_PROC;
import mx.gob.sct.utic.mimappir.sigtic.db2.model.OPT_DOCS;
import mx.gob.sct.utic.mimappir.sigtic.db2.model.OPT_PROC;
import mx.gob.sct.utic.mimappir.sigtic.db2.model.R_PROCESOS;
import mx.gob.sct.utic.mimappir.sigtic.db2.model.SOL_A_EXT;
import mx.gob.sct.utic.mimappir.sigtic.db2.model.S_PROCESOS;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.Watermark;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class PdfSolicitudView extends AbstractPdfView {
	@Override
	protected void buildPdfDocument(@SuppressWarnings("rawtypes") Map model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			@SuppressWarnings("unchecked")
			
			/*Map<String, String> modelData = (Map<String, String>) model
					.get("modelData");*/
			Map<String, Object> DATA = (Map<String, Object>) model
			.get("DATA");
			R_PROCESOS process = (R_PROCESOS)DATA.get("process");
			S_PROCESOS processInfo = (S_PROCESOS)DATA.get("processInfo");
			OPT_PROC processType = (OPT_PROC)DATA.get("processType");
			C_01_04_UA processUA = (C_01_04_UA)DATA.get("processUA");
			DOC_PROC PdfDocumentRegister = (DOC_PROC)DATA.get("PdfDocumentRegister");
			List<OPT_DOCS> elementosProceso = (List<OPT_DOCS>)DATA.get("elementosProceso");
			Map<String,List<Object>> infoElementosProceso = (Map<String,List<Object>>)DATA.get("infoElementosProceso");
			C_03_02_EMPLEADOS responsableRIN = (C_03_02_EMPLEADOS)DATA.get("responsableRINProceso");
			C_03_02_EMPLEADOS responsableInterno = (C_03_02_EMPLEADOS)DATA.get("responsableInternoProceso");
			SOL_A_EXT responsableExterno = (SOL_A_EXT)DATA.get("responsableExternoProceso");
				
			PdfContentByte cb = writer.getDirectContent();
			BaseFont bf = null;
			BaseFont bf2 = null;
			Image logo;
			PdfTemplate template;
			
			try {
				bf = BaseFont.createFont("/usr/local/bea/unvr57w.ttf",
						BaseFont.WINANSI, BaseFont.EMBEDDED);
				bf2 = BaseFont.createFont(
						"/usr/local/bea/IDAutomationHC39M_Free.ttf",
						BaseFont.WINANSI, BaseFont.EMBEDDED);
				logo = Image.getInstance("/usr/local/bea/logo_T.wmf");
				template = cb.createTemplate(10, 50);				
			} catch (Exception e) {
				bf = BaseFont.createFont("C:/arial.ttf", BaseFont.WINANSI,
						BaseFont.EMBEDDED);
				bf2 = BaseFont.createFont("C:/arial.ttf", BaseFont.WINANSI,
						BaseFont.EMBEDDED);
				logo = Image.getInstance("C:/logo_sct.wmf");
				template = cb.createTemplate(10, 50);
			}		

			Watermark watermark = new Watermark(Image.getInstance(new URL(
					"http://" + request.getServerName() + ":"
							+ request.getServerPort()
							+ request.getContextPath() + "/images/"
							+ "logo_empresa.png")), 250, 450);
			document.add(watermark);

			HeaderFooter header = new HeaderFooter(
					new Phrase(
							6,
							"\n \n \n \n \n",
							new Font(bf, 10)), false);
			header.setBorder(Rectangle.NO_BORDER);
			document.setHeader(header);
			
			HeaderFooter footer = new HeaderFooter(
					new Phrase(
							6,
							"El usuario no puede copiar, distribuir, transmitir, mostrar, ejecutar, reproducir, publicar, conceder en licencia, "
									+ "crear trabajos derivados, ceder ni vender ninguna información, software, productos o servicios obtenidos del sistema "
									+ "sigtic (en adelante \"Sistema\"). "
									+ "La UTIC se reserva el derecho a denegar o retirar el acceso al Sistema y/o a los Servicios, en cualquier momento y sin "
									+ "necesidad de previo aviso a aquellos Usuarios que incumplan estas Condiciones así como aquellas contempladas en la "
									+ "Normatividad vigente. "
									+ "La UTIC se reserva el derecho de cambiar los términos, condiciones y avisos bajo los que se ofrecen los contenidos "
									+ "y servicios del Sistema.\n"
									// + sigtic
									+ " || DOC STAMP: "	+ PdfDocumentRegister.getVALIDACION()
									+ " || SERVER SIGNATURE: 0x"+ PdfDocumentRegister.getFIRMA(),
							new Font(bf, 5)), false);
			footer.setBorder(Rectangle.NO_BORDER);
			document.setFooter(footer);
			
			/**
			 * Se agrega una pagina vacia para poder cambiar el header y el footer
			 * ya que el documento esta abierto en cuanto lo entrega spring*/
			writer.setPageEmpty(false);
			document.resetPageCount();
			document.newPage();
			// línea arriba
			logo.scaleAbsolute(92, 39);
			logo.setAbsolutePosition(36, 783);
			try {
				cb.addImage(logo);
			} catch (DocumentException lg1) {
				//lg1.printStackTrace();
			}
			/**INICIO HEADER ESTATICO*/
			cb.stroke();
			cb.beginText();
			cb.setFontAndSize(bf, 11);
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,
					"Secretaría de Comunicaciones y Transportes", 165, 814, 0);
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Oficialía Mayor",165, 800, 0);
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"Unidad de Tecnologías de Información y Comunicaciones", 165, 786, 0);
			cb.setFontAndSize(bf2, 12);
			cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "*"
					+ Long.toString(PdfDocumentRegister.getDOC_ID()) + "*", 484, 790, 0);
			cb.endText();
			/**FIN HEADER ESTATICO*/
			
			Table datatable = new Table(1);
			datatable.setPadding(2);
			datatable.setSpacing(0);
			datatable.setBorder(Rectangle.NO_BORDER);
			datatable.setDefaultCellBorderColor(new Color(255, 255, 255));
			int headerwidths[] = { 100 };
			datatable.setWidths(headerwidths);
			datatable.setWidth(100);
			datatable.addCell("");
			datatable.addCell("");
			datatable.addCell("");
			Cell cell = new Cell(
					new Phrase(
							0,
							"FORMATO ÚNICO PARA SOLICITUD DE SERVICIOS Y TRÁMITES DE TIC",
							new Font(bf, 13)));
			cell.setBorder(Rectangle.NO_BORDER);
			datatable.addCell(cell);
			Phrase myPhrase = new Phrase(11);
			myPhrase.add(new Chunk("TRÁMITE O SERVICIO: "
					+ processInfo.getDESC_PROCESO() + " / "
					+ processType.getDESC_OPT(), new Font(bf, 11)));
			myPhrase.add(new Chunk("\nUNIDAD SOLICITANTE: "
					+ processUA.getDESC_UA(), new Font(bf, 11)));
			myPhrase.add(new Chunk("\nFOLIO ÚNICO SIGTIC: "
					+ process.getUID_R_PROCESO(), new Font(bf, 11)));			
			myPhrase.add(new Chunk("\nFECHA: " + PdfDocumentRegister.getVALIDACION(), new Font(bf, 11)));
			datatable.addCell(myPhrase);
			datatable.endHeaders();

			Iterator<OPT_DOCS> ite = elementosProceso.iterator();
			while(ite.hasNext()){
				datatable.addCell("");
				OPT_DOCS registro = ite.next();
				if(registro.getTIPO_ARG().compareTo("QUERY") == 0){
					List<Object> infoElementosRegistro = (List<Object>)infoElementosProceso.get(registro.getMETA_ARG());
					if(registro.getREP_ARG().compareTo("DESP_VERT") == 0){
						datatable.addCell("");
						datatable.addCell(new Phrase(0, registro.getLAB_ARG(), new Font(bf, 10)));
						myPhrase = null;
						myPhrase = new Phrase(13);
						Iterator<Object> rs2 = infoElementosRegistro.iterator();
						while(rs2.hasNext()){
							Object[] registroElementos = (Object[])rs2.next();
							for (int i = 0; i < registroElementos.length; i++) {
								myPhrase.add(new Chunk(registroElementos[i]+"\n", new Font(bf, 8)));
							}				
						}
						datatable.addCell(myPhrase);
						myPhrase = null;
					}
					if(registro.getREP_ARG().compareTo("DESP_GRP") == 0){
						datatable.addCell("");
						datatable.addCell(new Phrase(0, registro.getLAB_ARG(), new Font(bf, 10)));
						myPhrase = null;
						myPhrase = new Phrase(13);
						String GRP = "";
						Iterator<Object> rs2 = infoElementosRegistro.iterator();
						while(rs2.hasNext()){
							Object[] registroElementos = (Object[])rs2.next();
							if(GRP.compareTo((String) registroElementos[0])!=0){
								GRP = (String) registroElementos[0];
								myPhrase.add(new Chunk(GRP+"\n", new Font(bf, 8)));
							}
							myPhrase.add(new Chunk((String) registroElementos[1]+"\n", new Font(bf, 7)));										
						}
						datatable.addCell(myPhrase);
						myPhrase = null;		
					}
					if(registro.getREP_ARG().compareTo("TABLE") == 0){
						/*
						String hw[] = registro.getMETA_ARG().split(",");
						datatable.addCell("");
						datatable.addCell(new Phrase(0, registro.getLAB_ARG(), new Font(bf, 10)));
						String Etiqueta_columna = "";
						Table CHtable = new Table(numCols);
						CHtable.setOffset(-20);					
						//CHtable.setCellspacing(0);
						//CHtable.setCellpadding(1);
						CHtable.setBorder(Rectangle.NO_BORDER);
						CHtable.setDefaultCellBorderColor(new Color(0x00, 0x00, 0x00));
						int CHhw[] = new int[hw.length];
						for(int i = 0; i < hw.length; i++)
							CHhw[i] = Integer.parseInt(hw[i]);
						CHtable.setWidths(CHhw);
						CHtable.setWidth(100);
						Cell cellH = null;	
						for (int i = 1; i <= numCols; i++) {
							Etiqueta_columna =  rsmd.getColumnLabel(i);
							Etiqueta_columna = Etiqueta_columna.replace('_',' ');
							cellH = new Cell(new Phrase(Etiqueta_columna, FontFactory.getFont(FontFactory.HELVETICA, 6, Font.BOLD)));
							cellH.setBackgroundColor(new Color(0xE7, 0xEE, 0xFE));
							CHtable.addCell(cellH);						
						}	
						CHtable.endHeaders();
						int ren = 0;
						while(rs2.next()){
							for (int i = 1; i <= numCols; i++) {
								cellH = new Cell(new Phrase(rs2.getString(i), FontFactory.getFont(FontFactory.HELVETICA, 6))); //BOLD
								if(ren%2==0){
									cellH.setBackgroundColor(new Color(0xFF, 0xFF, 0xFF));
								} else {
									cellH.setBackgroundColor(new Color(0xF7, 0xFA, 0xFF));
								}
								CHtable.addCell(cellH);		
							}
							ren++;				
						}
						cellH = null;
						cellH = new Cell();
						cellH.add(CHtable);
						cellH.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(cellH);
						cellH = null;
						CHtable = null;		*/
					}
				}
			}
			
			/*Aviso legal final*/
			datatable.addCell(new Phrase(20, "AVISO LEGAL", new Font(bf, 12)));
			datatable
					.addCell(new Phrase(
							12,
							"En caso de aceptación de la solicitud por parte de la Unidad Responsable del proceso, el usuario del servicio está aceptando la responsabilidad por el uso del mismo, así como las responsabilidades en que se pudiera incurrir de acuerdo a la Ley y Reglamento de la Administración Pública Federal en materia de responsabilidad de los servidores públicos y confidencialidad de la información, vigente.",
							new Font(bf, 8)));
			datatable.addCell("");
			datatable.addCell("");
			datatable.addCell("");
			//PdfContentByte cb = writer.getDirectContent();
			Image logo2 = Image.getInstance(new URL("http://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/images/logo_empresa.png"));
			logo2.scaleAbsolute(90, 91);
			logo2.setAbsolutePosition(460, 604);
			document.add(logo2);
			try {
				cb.addImage(logo2);
			} catch (DocumentException lg2) {
			}
			cb.setLineWidth(0f);
			cb.moveTo(436, 704);
			cb.lineTo(572, 704);
			cb.lineTo(572, 554);
			cb.lineTo(436, 554);
			cb.lineTo(436, 704);
			cb.moveTo(436, 585);
			cb.lineTo(572, 585);
			cb.moveTo(436, 597);
			cb.lineTo(572, 597);
			cb.stroke();
			cb.beginText();
			cb.setFontAndSize(bf, 5);
			cb.showTextAligned(PdfContentByte.ALIGN_CENTER,
					"Sello (para uso exclusivo de la UTI)", 504, 693, 0);
			cb.showTextAligned(PdfContentByte.ALIGN_CENTER,
					"REVISIÓN: 1            FECHA: Noviembre 2005", 504, 590, 0);
			cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "CÓDIGO", 504, 577,
					0);
			cb.setFontAndSize(bf, 18);
			cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "SCT-FI-11-01",
					504, 560, 0);
			cb.endText();
			// agregar tabla al documento
			datatable.setOffset(0);
			document.add(datatable);
			datatable = null;

			cb.setLineWidth(0f);
			// firmas
			cb.moveTo(40,94);cb.lineTo(163, 94);cb.moveTo(171,94);cb.lineTo(298, 94);
			cb.moveTo(307,94);cb.lineTo(434, 94);	
			cb.stroke();		
			cb.beginText();
				cb.setFontAndSize(bf, 6);	
				cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "UNIDAD ADMINISTRATIVA SOLICITANTE", 254, 138, 0);	
				
				cb.showTextAligned(PdfContentByte.ALIGN_CENTER, responsableInterno.getNOMBRE_COMPLETO(), 99, 85, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "Solicitante Interno", 99, 77, 0);
				
				String nombreEXT = "";
				nombreEXT += responsableExterno.getAP()+" "+responsableExterno.getAM()+" "+responsableExterno.getNOM();
				cb.showTextAligned(PdfContentByte.ALIGN_CENTER, nombreEXT.toUpperCase(), 234, 85, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "Solicitante Externo", 234, 77, 0);
				
				cb.showTextAligned(PdfContentByte.ALIGN_CENTER, responsableRIN.getNOMBRE_COMPLETO(), 370, 85, 0);			
				cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "Enlace Informático de la Unidad", 370, 77, 0);
				
			cb.endText();
			
			
			/**INICIO FOOTER ESTATICO*/						
			cb.setLineWidth(0f);
			int pageN = writer.getPageNumber();
			String text = "Página " + pageN + " de 1" ;
			//String text = "Página " + pageN;
			float len = bf.getWidthPoint(text, 8);
			cb.beginText();
			cb.setFontAndSize(bf, 8);
			cb.setTextMatrix(520, 36);
			cb.showText(text);
			cb.endText();
			cb.addTemplate(template, 520 + len, 36);
			cb.beginText();
			cb.setFontAndSize(bf, 8);
			cb.setTextMatrix(280, 820);
			//cb.showText(act);
			cb.endText();
			/**FIN FOOTER ESTATICO*/
			    
		    writer.setPageEmpty(true);
			PdfAction action = PdfAction.gotoLocalPage(2, new PdfDestination(PdfDestination.XYZ, -1, 10000, 0), writer);
			writer.setOpenAction(action);
						
		} // try
		catch (Exception e) {
			e.printStackTrace();
		} // catch
	}

}