package mx.gob.sct.utic.mimappir.view;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.sct.utic.mimappir.util.MyPageEvents;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.Watermark;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class CopyOfPdfSolicitudView extends AbstractPdfView {
	@Override
	protected void buildPdfDocument(Map model, Document document,
			PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		boolean modoProduccion = false;
		
		try {
			int UID_UA = Integer.parseInt((String) request.getParameter("UID_UA"));
			int UID_R_PROCESO = Integer.parseInt((String) request.getParameter("id"));
			int UID_PROCESO = -1;
			
			int UID_OPTP = -1;
			String DESC_UA = "";
			String NOMBRE_PROCESO = "";
			int c_docs = 0;

			String NOMBRE_EMP = "";
			NumberFormat formatter = new DecimalFormat("000000");
			//String strUID_R_PROCESO = formatter.format(UID_R_PROCESO);
			Timestamp STAMP = new Timestamp(System.currentTimeMillis());
			long DOC_ID = System.currentTimeMillis();
			String FIRMA = Long.toHexString(DOC_ID).toUpperCase();
			// Conexión a la BD
			//javax.naming.InitialContext initctx = new javax.naming.InitialContext();
/*
			getSolicitudInfo(Integer UID_R_PROCESO)			
			PreparedStatement stmt = conn
					.prepareStatement(
							"SELECT A.UID_PROCESO, B.DESC_PROCESO, C.DESC_OPT, D.DESC_C_UA, A.UID_EMPLEADO_SOL " +
							"FROM SCT.R_PROCESOS A " +
							"LEFT OUTER JOIN SCT.S_PROCESOS B ON (A.UID_PROCESO=B.UID_PROCESO) " +
							"LEFT OUTER JOIN SCT.OPT_PROC C ON (A.UID_EMPLEADO_SOL=C.UID_OPTP) " +
							"LEFT OUTER JOIN SCT.C_01_04_UA D ON (A.UID_UA=D.UID_UA) " +
							"WHERE A.UID_R_PROCESO=?",
							ResultSet.TYPE_FORWARD_ONLY,
							ResultSet.CONCUR_READ_ONLY);
			System.out
					.println("SELECT A.UID_PROCESO, B.DESC_PROCESO, C.DESC_OPT, D.DESC_C_UA, A.UID_EMPLEADO_SOL " +
							"FROM SCT.R_PROCESOS A " +
							"LEFT OUTER JOIN SCT.S_PROCESOS B ON (A.UID_PROCESO=B.UID_PROCESO) " +
							"LEFT OUTER JOIN SCT.OPT_PROC C ON (A.UID_EMPLEADO_SOL=C.UID_OPTP) " +
							"LEFT OUTER JOIN SCT.C_01_04_UA D ON (A.UID_UA=D.UID_UA) " +
							"WHERE A.UID_R_PROCESO="
							+ UID_R_PROCESO);
			
			stmt.setInt(1, UID_R_PROCESO);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				UID_PROCESO = rs.getInt(1);
				NOMBRE_PROCESO = rs.getString(2) + " / " + rs.getString(3);
				DESC_UA = rs.getString(4);
				UID_OPTP = rs.getInt(5);
			}
			rs.close();
			stmt.close();

			PreparedStatement stmtu = conn
					.prepareStatement("SELECT VALIDACION, FIRMA, DOC_ID FROM SCT.DOC_PROC WHERE UID_R_PROCESO=?");
			stmtu.setInt(1, UID_R_PROCESO);
			ResultSet rsu = stmtu.executeQuery();
			while (rsu.next()) {
				c_docs++;
				STAMP = rsu.getTimestamp(1);
				FIRMA = rsu.getString(2);
				DOC_ID = rsu.getLong(3);
			}
			rsu.close();
			stmtu.close();
			if (c_docs == 0) {
				PreparedStatement stmtv = conn
						.prepareStatement("INSERT INTO SCT.DOC_PROC (UID_PROCESO, UID_UA, UID_R_PROCESO, VALIDACION, FIRMA, DOC_ID) VALUES (?,?,?,?,?,?)");
				stmtv.setInt(1, UID_PROCESO);
				stmtv.setInt(2, UID_UA);
				stmtv.setInt(3, UID_R_PROCESO);
				stmtv.setTimestamp(4, STAMP);
				stmtv.setString(5, FIRMA);
				stmtv.setLong(6, DOC_ID);
				stmtv.executeUpdate();
				stmtv.close();
			} // if(c_docs>0)
*/
			// PDF Part
			// paso 1: creación del objeto-documento
			//Document document = new Document(PageSize.LETTER);
			// paso 2:
			// creamos un 'writer' que 'escuche' al documento
			// y direccionamos el flujo pdf a un buffer temporal
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//PdfWriter writer = null;
			//writer = PdfWriter.getInstance(document, baos);
			// Se enlaza la instancia del documento a los eventos globales
			MyPageEvents events = new MyPageEvents();
			//events.setDOC_ID(DOC_ID);
			writer.setPageEvent(events);
			// Se agregan metadatos al documento
			/*document.addTitle("FORMATO ÚNICO PARA SOLICITUD DE TRÁMITES Y SERVICIOS DE TIC");
			document.addSubject("SCT ® SIGTIC");
			document.addKeywords("SIGTIC, FORMATO ÚNICO");
			document.addAuthor("ISC J. Antonio Rodríguez Landeros : arlander@sct.gob.mx");
			document.addCreator("® SIGTIC");*/
			Watermark watermark = new Watermark(Image.getInstance(new URL(
					"http://" + request.getServerName() + ":"
							+ request.getServerPort()
							+ request.getContextPath() + "/images/"
							+ "logo_empresa.png")), 308, 554);
			document.add(watermark);
			// Se establece el tipo de letra global al documento
			BaseFont bf = null;
			if (!modoProduccion) {
				bf = BaseFont.createFont("C:/arial.ttf", BaseFont.WINANSI,
						BaseFont.EMBEDDED);
			} else {
				bf = BaseFont.createFont("/usr/local/bea/unvr57w.ttf",
						BaseFont.WINANSI, BaseFont.EMBEDDED);
			}

			// Se establece en pie de página de documento
			/*String sigtic = 
					application.getInitParameter("Sistema") + " "
					+ application.getInitParameter("Version") + " "
					+ application.getInitParameter("Build");*/
			HeaderFooter footer = new HeaderFooter(
					new Phrase(
							6,
							"El usuario no puede copiar, distribuir, transmitir, mostrar, ejecutar, reproducir, publicar, conceder en licencia, crear trabajos derivados, ceder ni vender ninguna información, software, productos o servicios obtenidos del sistema sigtic (en adelante \"Sistema\"). La UTI se reserva el derecho a denegar o retirar el acceso al Sistema y/o a los Servicios, en cualquier momento y sin necesidad de previo aviso a aquellos Usuarios que incumplan estas Condiciones así como aquellas contempladas en la Normatividad vigente. La UTI se reserva el derecho de cambiar los términos, condiciones y avisos bajo los que se ofrecen los contenidos y servicios del Sistema.\n"
									//+ sigtic
									+ " || DOC STAMP: "
									//+ STAMP
									+ " || SERVER SIGNATURE: 0x", //+ FIRMA,
							new Font(bf, 5)), false);
			footer.setBorder(Rectangle.NO_BORDER);
			document.setFooter(footer);
			// paso 3: abrimos el documento
			document.open();
			// paso 4: agregamos contenido al documento
			// tabla principal
			Table datatable = new Table(1);
			datatable.setPadding(2);
			datatable.setSpacing(0);
			datatable.setBorder(Rectangle.NO_BORDER);
			datatable.setDefaultCellBorderColor(new Color(255, 255, 255));
			int headerwidths[] = { 100 };
			datatable.setWidths(headerwidths);
			datatable.setWidth(100);
			// Relleno para el encabezado
			datatable.addCell("");
			datatable.addCell("");
			datatable.addCell("");
			// Título del documento
			Cell cell = new Cell(
					new Phrase(
							0,
							"FORMATO ÚNICO PARA SOLICITUD DE SERVICIOS Y TRÁMITES DE TIC",
							new Font(bf, 14)));
			cell.setBorder(Rectangle.NO_BORDER);
			datatable.addCell(cell);
			Phrase myPhrase = new Phrase(15);
			//myPhrase.add(new Chunk("FOLIO ÚNICO SIGTIC: " + strUID_R_PROCESO,new Font(bf, 12)));
			myPhrase.add(new Chunk("\nTRÁMITE O SERVICIO: "));
					//+ NOMBRE_PROCESO.toUpperCase(), new Font(bf, 12)));
			myPhrase.add(new Chunk("\nUNIDAD SOLICITANTE: ",new Font(bf, 10))); 
					//+ DESC_UA,new Font(bf, 10)));
			myPhrase.add(new Chunk("\nFECHA: ",new Font(bf, 10))); 
					//+ STAMP, new Font(bf, 10)));
			datatable.addCell(myPhrase);
			// datatable.addCell("");
			datatable.endHeaders();
			// fin del encabezado
			// Inicio de cuerpo variable
			/*PreparedStatement stmtX = conn
					.prepareStatement(
							"SELECT ORDEN, LAB_ARG, REP_ARG, META_ARG, TIPO_ARG, SQL_ARG, PSQL_ARG FROM SCT.OPT_DOCS WHERE UID_OPTP="
									+ UID_OPTP + " ORDER BY ORDEN",
							ResultSet.TYPE_FORWARD_ONLY,
							ResultSet.CONCUR_READ_ONLY);
			System.out
					.println("SELECT ORDEN, LAB_ARG, REP_ARG, META_ARG, TIPO_ARG, SQL_ARG, PSQL_ARG FROM SCT.OPT_DOCS WHERE UID_OPTP="
							+ UID_OPTP + " ORDER BY ORDEN");
			// stmtX.setInt(1, UID_OPTP);
			ResultSet rsX = stmtX.executeQuery();
			while (rsX.next()) {
				String ORDEN = rsX.getString(1);
				String LAB_ARG = rsX.getString(2);
				String REP_ARG = rsX.getString(3);
				String META_ARG = rsX.getString(4);
				String TIPO_ARG = rsX.getString(5);
				String SQL_ARG = rsX.getString(6);
				String PSQL_ARG = rsX.getString(7);

				if (TIPO_ARG.compareTo("QUERY") == 0) {
					String REP[] = REP_ARG.split(":");
					if (REP[0].compareTo("DESP_VERT") == 0) {
						String PARAM[] = PSQL_ARG.split(";");
						PreparedStatement stmt2 = conn.prepareStatement(
								SQL_ARG, ResultSet.TYPE_FORWARD_ONLY,
								ResultSet.CONCUR_READ_ONLY);
						for (int i = 0; i < PARAM.length; i++) {
							String DP[] = PARAM[i].split(":");
							String VAL = "";
							// Tipos de Origen
							if (DP[0].compareTo("S") == 0) {
								VAL = (String) session.getAttribute(DP[2]);
							}
							if (DP[0].compareTo("R") == 0) {
								VAL = (String) request.getParameter(DP[2]);
							}
							// Tipos de Datos a Insertar
							if (DP[1].compareTo("I") == 0) {
								stmt2.setInt(i + 1, Integer.parseInt(VAL));
							}
						}
						ResultSet rs2 = stmt2.executeQuery();
						ResultSetMetaData rsmd = rs2.getMetaData();
						int numCols = rsmd.getColumnCount();
						datatable.addCell("");
						datatable.addCell(new Phrase(0, LAB_ARG, new Font(bf,
								10)));
						myPhrase = null;
						myPhrase = new Phrase(13);
						while (rs2.next()) {
							for (int i = 1; i <= numCols; i++) {
								myPhrase.add(new Chunk(rs2.getString(i) + "\n",
										new Font(bf, 8)));
							}
						}
						datatable.addCell(myPhrase);
						myPhrase = null;
						rs2.close();
						stmt2.close();
					} // (REP[0].compareTo("DESP_VERT") == 0)
					if (REP[0].compareTo("DESP_GRP") == 0) {
						String PARAM[] = PSQL_ARG.split(";");
						PreparedStatement stmt2 = conn.prepareStatement(
								SQL_ARG, ResultSet.TYPE_FORWARD_ONLY,
								ResultSet.CONCUR_READ_ONLY);
						for (int i = 0; i < PARAM.length; i++) {
							String DP[] = PARAM[i].split(":");
							String VAL = "";
							// Tipos de Origen
							if (DP[0].compareTo("S") == 0) {
								VAL = (String) session.getAttribute(DP[2]);
							}
							if (DP[0].compareTo("R") == 0) {
								VAL = (String) request.getParameter(DP[2]);
							}
							// Tipos de Datos a Insertar
							if (DP[1].compareTo("I") == 0) {
								stmt2.setInt(i + 1, Integer.parseInt(VAL));
							}
						}
						ResultSet rs2 = stmt2.executeQuery();
						ResultSetMetaData rsmd = rs2.getMetaData();
						int numCols = rsmd.getColumnCount();
						datatable.addCell("");
						datatable.addCell(new Phrase(0, LAB_ARG, new Font(bf,
								10)));
						myPhrase = null;
						myPhrase = new Phrase(13);
						String GRP = "";
						while (rs2.next()) {
							if (GRP.compareTo(rs2.getString(1)) != 0) {
								GRP = rs2.getString(1);
								myPhrase.add(new Chunk(GRP + "\n", new Font(bf,
										8)));
							}
							myPhrase.add(new Chunk(rs2.getString(2) + "\n",
									new Font(bf, 7)));
						}
						datatable.addCell(myPhrase);
						myPhrase = null;
						rs2.close();
						stmt2.close();
					} // (REP[0].compareTo("DESP_GRP") == 0)
					if (REP[0].compareTo("TABLE") == 0) {
						String PARAM[] = PSQL_ARG.split(";");
						String hw[] = META_ARG.split(",");
						PreparedStatement stmt2 = conn.prepareStatement(
								SQL_ARG, ResultSet.TYPE_FORWARD_ONLY,
								ResultSet.CONCUR_READ_ONLY);
						for (int i = 0; i < PARAM.length; i++) {
							String DP[] = PARAM[i].split(":");
							String VAL = "";
							// Tipos de Origen
							if (DP[0].compareTo("S") == 0) {
								VAL = (String) session.getAttribute(DP[2]);
							}
							if (DP[0].compareTo("R") == 0) {
								VAL = (String) request.getParameter(DP[2]);
							}
							// Tipos de Datos a Insertar
							if (DP[1].compareTo("I") == 0) {
								stmt2.setInt(i + 1, Integer.parseInt(VAL));
							}
						}
						ResultSet rs2 = stmt2.executeQuery();
						ResultSetMetaData rsmd = rs2.getMetaData();
						int numCols = rsmd.getColumnCount();
						datatable.addCell("");
						datatable.addCell(new Phrase(0, LAB_ARG, new Font(bf,
								10)));
						String Etiqueta_columna = "";
						Table CHtable = new Table(numCols);
						CHtable.setOffset(-20);
						// CHtable.setCellspacing(0);
						// CHtable.setCellpadding(1);
						CHtable.setBorder(Rectangle.NO_BORDER);
						CHtable.setDefaultCellBorderColor(new Color(0x00, 0x00,
								0x00));
						int CHhw[] = new int[hw.length];
						for (int i = 0; i < hw.length; i++)
							CHhw[i] = Integer.parseInt(hw[i]);
						CHtable.setWidths(CHhw);
						CHtable.setWidth(100);
						Cell cellH = null;
						for (int i = 1; i <= numCols; i++) {
							Etiqueta_columna = rsmd.getColumnLabel(i);
							Etiqueta_columna = Etiqueta_columna.replace('_',
									' ');
							cellH = new Cell(new Phrase(Etiqueta_columna,
									FontFactory.getFont(FontFactory.HELVETICA,
											6, Font.BOLD)));
							cellH.setBackgroundColor(new Color(0xE7, 0xEE, 0xFE));
							CHtable.addCell(cellH);
						}
						CHtable.endHeaders();
						int ren = 0;
						while (rs2.next()) {
							for (int i = 1; i <= numCols; i++) {
								cellH = new Cell(new Phrase(rs2.getString(i),
										FontFactory.getFont(
												FontFactory.HELVETICA, 6))); // BOLD
								if (ren % 2 == 0) {
									cellH.setBackgroundColor(new Color(0xFF,
											0xFF, 0xFF));
								} else {
									cellH.setBackgroundColor(new Color(0xF7,
											0xFA, 0xFF));
								}
								CHtable.addCell(cellH);
							}
							ren++;
						}
						cellH = null;
						rs2.close();
						stmt2.close();
						cellH = new Cell();
						cellH.add(CHtable);
						cellH.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(cellH);
						cellH = null;
						CHtable = null;
					} // (REP[0].compareTo("TABLE") == 0)
				} // (TIPO_ARG.compareTo("QUERY") != 0)

			} // while RSX
			rsX.close();
			stmtX.close();
*/
			// inicia fin de tabla principal
			datatable.addCell(new Phrase(20, "AVISO LEGAL", new Font(bf, 12)));
			datatable
					.addCell(new Phrase(
							12,
							"En caso de aceptación de la solicitud por parte de la Unidad Responsable del proceso, el usuario del servicio está aceptando la responsabilidad por el uso del mismo, así como las responsabilidades en que se pudiera incurrir de acuerdo a la Ley y Reglamento de la Administración Pública Federal en materia de responsabilidad de los servidores públicos y confidencialidad de la información, vigente.",
							new Font(bf, 8)));
			// relleno para firmas
			datatable.addCell("");
			datatable.addCell("");
			datatable.addCell("");

			// inicio gráficos
			PdfContentByte cb = writer.getDirectContent();
			// sello
			Image logo2 = Image.getInstance(new URL("http://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/images/logo_empresa.png"));
					//+ request.getContextPath() + "/img/aguila.wmf"));
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
			//datatable = null;
/*
			PreparedStatement stmt5 = conn
					.prepareStatement("SELECT COALESCE(B.NOMBRE_COMPLETO,'') AS NOM_RI, COALESCE(D.NOMBRE_COMPLETO,'') AS NOM_RU, COALESCE(F.NOMBRE_COMPLETO,'') AS NOM_F FROM SCT.C_R_UA A LEFT OUTER JOIN SCT.C_03_02_EMPLEADOS  B ON (A.RIN_UID=B.UID_EMPLEADO) , SCT.C_R_UA C LEFT OUTER JOIN SCT.C_03_02_EMPLEADOS  D  ON (C.RUA_UID=D.UID_EMPLEADO), SCT.C_R_UA E LEFT OUTER JOIN SCT.C_03_02_EMPLEADOS F ON (E.RAD_UID=F.UID_EMPLEADO)  WHERE A.UID_UA=? AND C.UID_UA=? AND E.UID_UA=?");
			stmt5.setInt(1, UID_UA);
			stmt5.setInt(2, UID_UA);
			stmt5.setInt(3, UID_UA);
			String NOM_RU = "";
			String NOM_RI = "";
			String NOM_F = "";
			ResultSet rs5 = stmt5.executeQuery();
			while (rs5.next()) {
				NOM_RI = rs5.getString(1);
				NOM_RU = rs5.getString(2);
				NOM_F = rs5.getString(3);
			}
			rs5.close();
			stmt5.close();
			conn.close();

			cb.setLineWidth(0f);
			// firmas
			cb.moveTo(40, 94);
			cb.lineTo(163, 94);
			cb.moveTo(171, 94);
			cb.lineTo(298, 94);
			cb.moveTo(307, 94);
			cb.lineTo(434, 94);
			cb.stroke();
			cb.beginText();
			cb.setFontAndSize(bf, 6);
			cb.showTextAligned(PdfContentByte.ALIGN_CENTER,
					"SOLICITANTE INTERNO", 99, 77, 0);

			cb.showTextAligned(PdfContentByte.ALIGN_CENTER,
					"SOLICITANTE EXTERNO", 234, 77, 0);

			cb.showTextAligned(PdfContentByte.ALIGN_CENTER,
					"Enlace Informático de la Unidad", 370, 77, 0);
			cb.showTextAligned(PdfContentByte.ALIGN_CENTER, NOM_RI, 370, 85, 0);

			cb.showTextAligned(PdfContentByte.ALIGN_CENTER,
					"UNIDAD ADMINISTRATIVA SOLICITANTE", 234, 138, 0);
			cb.endText();

			// paso 5: cerramos el documento
			document.close();
			writer.close();
			// int size = baos.size();
			response.reset();
			// response.setContentLength(size);
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename="
					+ DOC_ID + ".pdf");
			// paso 6: le damos salida al 'writer' como un flujo de bytes al la
			// salida de respuesta del usuario
			OutputStream out1 = response.getOutputStream();
			baos.writeTo(out1);
			baos = null;
			writer = null;
			document = null;
			out1.flush();
			out1.close();*/
			
		} // try
		catch (Exception e) {
			e.printStackTrace();
		} // catch
	}

}