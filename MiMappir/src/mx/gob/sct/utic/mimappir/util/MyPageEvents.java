package mx.gob.sct.utic.mimappir.util;

import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class MyPageEvents extends PdfPageEventHelper {
	private PdfContentByte cb;
	private BaseFont bf = null;
	private BaseFont bf2 = null;
	private PdfTemplate template;
	private Image logo;
	private String act = "";
	private long DOC_ID = System.currentTimeMillis();
	private final boolean modoProduccion = true;

	public void onOpenDocument(PdfWriter writer, Document document) {
		try {
			if (!modoProduccion) {
				bf = BaseFont.createFont("C:/arial.ttf", BaseFont.WINANSI,
						BaseFont.EMBEDDED);
				bf2 = BaseFont.createFont("C:/arial.ttf", BaseFont.WINANSI,
						BaseFont.EMBEDDED);
				logo = Image.getInstance("C:/logo_sct.wmf");
			} else { /*VARIABLES PARA PRODUCCIÓN*/
				bf = BaseFont.createFont("/usr/local/bea/unvr57w.ttf",
						BaseFont.WINANSI, BaseFont.EMBEDDED);
				bf2 = BaseFont.createFont(
						"/usr/local/bea/IDAutomationHC39M_Free.ttf",
						BaseFont.WINANSI, BaseFont.EMBEDDED);
				logo = Image.getInstance("/usr/local/bea/logo_T.wmf");
			}
			cb = writer.getDirectContent();
			logo.scaleAbsolute(92, 39);
			logo.setAbsolutePosition(36, 713);
			template = cb.createTemplate(10, 50);
		} catch (DocumentException de) {
		} catch (IOException ioe) {
		}
	}

	public void onEndPage(PdfWriter writer, Document document) {
		cb.setLineWidth(0f);
		int pageN = writer.getPageNumber();
		String text = "Página " + pageN + " de ";
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
		cb.showText(act);
		cb.endText();

		try {
			cb.addImage(logo);
		} catch (DocumentException lg1) {
			//lg1.printStackTrace();
		}
		// línea arriba
		//cb.moveTo(36, 704);cb.lineTo(570, 704);		
		cb.stroke();
		//texto		
		cb.beginText();
		cb.setFontAndSize(bf, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,
				"Secretaría de Comunicaciones y Transportes", 165, 744, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Oficialía Mayor",
				165, 730, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,
				"Unidad de Tecnologías de Información", 165, 716, 0);
		//cb.setFontAndSize(bf, 7);
		//cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "DOC ID:   "+Long.toString(DOC_ID), 484, 720, 0);			
		cb.setFontAndSize(bf2, 12);
		cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "*"
				+ Long.toString(DOC_ID) + "*", 484, 720, 0);
		cb.endText();
	}

	public void onCloseDocument(PdfWriter writer, Document document) {
		template.beginText();
		template.setFontAndSize(bf, 8);
		template.showText(String.valueOf(writer.getPageNumber() - 1));
		template.endText();
	}

	public PdfContentByte getCb() {
		return cb;
	}

	public void setCb(PdfContentByte cb) {
		this.cb = cb;
	}

	public BaseFont getBf() {
		return bf;
	}

	public void setBf(BaseFont bf) {
		this.bf = bf;
	}

	public BaseFont getBf2() {
		return bf2;
	}

	public void setBf2(BaseFont bf2) {
		this.bf2 = bf2;
	}

	public PdfTemplate getTemplate() {
		return template;
	}

	public void setTemplate(PdfTemplate template) {
		this.template = template;
	}

	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public long getDOC_ID() {
		return DOC_ID;
	}

	public void setDOC_ID(long dOC_ID) {
		DOC_ID = dOC_ID;
	}

	public boolean isModoProduccion() {
		return modoProduccion;
	}
	
}
