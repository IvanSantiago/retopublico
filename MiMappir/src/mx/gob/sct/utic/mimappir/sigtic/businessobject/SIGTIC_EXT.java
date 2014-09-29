package mx.gob.sct.utic.mimappir.sigtic.businessobject;
//import gob.mx.sct.Mail.SCTSendMail;
import java.util.Vector;
import java.sql.Timestamp;

public class SIGTIC_EXT {
 /* public StringBuffer ProcesarSolicitud(PageContext pc) throws Exception {
    String Ruta = "http://sigtic.sct.gob.mx/sigtic/";
    StringBuffer SB = new StringBuffer("");
    StringBuffer MSG = new StringBuffer("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"><html><head><title>Mensaje</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\"><link href=\"").append(Ruta).append("estilos/basis.css\" rel=\"stylesheet\" type=\"text/css\"></head><body><table width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"bgerror\"><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td><img src=\"").append(Ruta).append("img/sys_logo.png\" width=\"128\" height=\"128\"></td><td><img src=\"").append(Ruta).append("img/sys_texto.png\" width=\"120\" height=\"60\"></td></tr></table></td></tr><tr><td class=\"separa\">&nbsp;</td></tr><tr><td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"6\"><tr> <td class=\"grande\">NOTIFICACIÓN SIGTIC</td></tr><tr><td>");
    String To = "";
    String Folio = "";
    HttpServletRequest req = (HttpServletRequest)pc.getRequest();
    String _rgi = req.getContextPath()+"/img/";
    Query q = null;
    Vector RS = null;
    Vector Data = null;
    SCTSendMail mail = null;
    Timestamp STAMP = new Timestamp(System.currentTimeMillis());
    try {
      // Iniciar Script
      SB.append("<script>");
      SB.append("_wp=window.parent;_wp.di_ih(\"MAIN_CONTAINER\",\"");
      // Iniciar Tabla
      SB.append("<table border=\\\"0\\\" align=\\\"left\\\" cellpadding=\\\"6\\\" cellspacing=\\\"0\\\">");
      SB.append("<tr><td colspan=\\\"2\\\" class=\\\"ui_tit_secc\\\">Generación de Solicitud</td></tr>");
      // Folio
      q = new Query();
      q.execute("DS_SIGTIC","INSERT INTO SCT.R_PROCESOS (UID_PROCESO,UID_UA,UID_EMPLEADO,STAMP,UID_EMPLEADO_SOL) VALUES (33,?,?,'"+STAMP+"',31)","S:I:UID_UA;S:I:UID_EMPLEADO;", pc);
      SB.append("<tr><td><img src=\\\""+_rgi+"tram/d_5.jpg\\\" width=\\\"24\\\" height=\\\"24\\\"</td><td>El Folio ha sido Generado</td></tr>");
      // Retrieve Folio
      RS = q.query("DS_SIGTIC","SELECT UID_R_PROCESO FROM SCT.R_PROCESOS WHERE STAMP='"+STAMP+"'","",pc);
      Data = (Vector)RS.elementAt(1);
      Folio = (String)Data.elementAt(0);
      // Inbox 
      q.execute("DS_SIGTIC",
                "INSERT INTO SCT.INBOX " +
          "(UID_R_PROCESO,UID_PROCESO,PASO,UID_UA,UID_EMPLEADO,STAMP,STATUS) " +
                "VALUES (" + Folio +
                ",33,6,?," +
                "(SELECT UID_EMPLEADO FROM SCT.C_SI WHERE UID_SI=?),'" +
                STAMP + "',2)", "S:I:UID_UA;R:I:UID_SI", pc);
      SB.append("<tr><td><img src=\\\"" + _rgi + "tram/d_5.jpg\\\" width=\\\"24\\\" height=\\\"24\\\"</td><td>Generando Instrucción de Atención</td></tr>");
      // Sol A Ext
      q.execute("DS_SIGTIC",
                "INSERT INTO SCT.SOL_A_EXT (UID_SI,DS,NE,AP,AM,NOM,RFC,DIR,COL,CP,CD,EDO,EM,TEL,UID_R_PROCESO,CVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                Folio +
                ",?)", "R:I:UID_SI;R:S:DS;R:S:NE;R:S:AP;R:S:AM;R:S:NOM;R:S:RFC;R:S:DIR;R:S:COL;R:S:CP;R:S:CD;R:S:EDO;R:S:EM;R:S:TEL;R:S:CVE",
                pc);
      SB.append("<tr><td><img src=\\\""+_rgi+"tram/d_5.jpg\\\" width=\\\"24\\\" height=\\\"24\\\"</td><td>Creando Referencias en la BD</td></tr>");
      // Mail
      RS = q.query("DS_SIGTIC",
                   "SELECT A.CORREO FROM SCT.EMPLEADOS A " +
          "LEFT OUTER JOIN SCT.C_SI B ON (A.UID_EMPLEADO=B.UID_EMPLEADO) " +
                   "WHERE B.UID_SI=?",
                   "R:I:UID_SI", pc);
      Data = (Vector) RS.elementAt(1);
      To = (String)Data.elementAt(0);
      RS = q.query("DS_SIGTIC","SELECT '<B>FOLIO '||CAST(A.UID_R_PROCESO AS CHAR(6))||'</B><BR><BR><B>PROCESO: </B>'||B.DESC_PROCESO||'<BR><B>SOLICITANTE: </B>'||C.NOMBRE_COMPLETO||'<BR><B>UNIDAD: </B>'||D.DESC_C_UA||'<BR><BR>Proceda a atender la Solicitud en: <br><br><a href=\"http://sigtic.sct.gob.mx\">http://sigtic.sct.gob.mx</a>'  AS BODY FROM SCT.R_PROCESOS A LEFT OUTER JOIN SCT.S_PROCESOS B ON (A.UID_PROCESO=B.UID_PROCESO) LEFT OUTER JOIN SCT.C_03_02_EMPLEADOS C ON (A.UID_EMPLEADO=C.UID_EMPLEADO) LEFT OUTER JOIN SCT.C_01_04_UA D ON (C.UID_UA=D.UID_UA) WHERE A.UID_R_PROCESO="+Folio,"",pc);
      Data = (Vector)RS.elementAt(1);
      MSG.append((String)Data.elementAt(0));
      MSG.append("</td></tr><tr><td>&nbsp;</td></tr></table></td></tr><tr><td class=\"separa\">&nbsp;</td></tr><tr><td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"4\"><tr><td><div align=\"center\"><font color=\"#bbbbbb\" size=\"1\" face=\"Arial, Helvetica, sans-serif\">Secretar&iacute;a de Comunicaciones y Transportes<br>Oficial&iacute;a Mayor<br>Unidad de Tecnolog&iacute;as de la Informaci&oacute;n y Comunicaciones<br>&copy; 2006 Todos los derechos reservados</font></div></td></tr></table></td></tr></table></body></html>");
      mail = new SCTSendMail();
      mail.Send("sigtic_messenger@sct.gob.mx",To,"Proceda a atender la Solicitud de Creación de Cuentas de Acceso al Sistema de la SCT para usuarios Externos",MSG.toString());
      SB.append("<tr><td><img src=\\\""+_rgi+"tram/d_8.jpg\\\" width=\\\"24\\\" height=\\\"24\\\"</td><td>Correo electrónico enviado</td></tr>");
      // Link a Folio
      SB.append("<tr><td id=\\\"RFOLIO\\\" onmouseout=\\\"fx_ce(this.id,'none');\\\" onmouseover=\\\"fx_ce(this.id,'selren');\\\" onclick=\\\"sys_gf('"+Folio+"');\\\"><img src=\\\""+_rgi+"tram/ar.png\\\" width=\\\"64\\\" height=\\\"64\\\"</td><td id=\\\"RFOLIO2\\\" onmouseout=\\\"fx_ce(this.id,'none');\\\" onmouseover=\\\"fx_ce(this.id,'selren');\\\" onclick=\\\"sys_gf('"+Folio+"');\\\">").append("<font class=\\\"grande\\\">Dé clic aquí para obtener el FOLIO "+Folio+"</font>").append("</td></tr>");
      // Cerrar Tabla
      SB.append("<tr><td>&nbsp;</td><td class=\\\"ui_frm_fi\\\"><input type=\\\"button\\\" id=\\\"Button\\\" name=\\\"Button\\\" value=\\\"Finalizar\\\" class=\\\"fbtnoff\\\" onmouseover=\\\"fx_ce(this.id,'fbtnon');\\\" onmouseout=\\\"fx_ce(this.id,'fbtnoff');\\\" onclick=\\\"ui_rl('sys/index.jsp');\\\" /></td></tr></table>");
      // Cerrar Script
      SB.append("\");</script>");
    } catch (Exception e){
      e.printStackTrace();
      SB = new StringBuffer("");
      SB.append("<script>");
      SB.append("_wp=window.parent;_wp.di_ih(\"MAIN_CONTAINER\",\"");
      SB.append("<table border=\\\"0\\\" align=\\\"left\\\" cellpadding=\\\"6\\\" cellspacing=\\\"0\\\">");
      SB.append("<tr><td colspan=\\\"2\\\" class=\\\"ui_tit_secc\\\">Ha ocurrido una Excepción</td></tr>");
      SB.append("<tr><td><img src=\\\""+_rgi+"tram/d_5.jpg\\\" width=\\\"24\\\" height=\\\"24\\\"</td><td>"+e.toString()+"</td></tr>");
      SB.append("</td></tr></table>");
      // Cerrar Script
      SB.append("\");</script>");
      return SB;
    //throw new Exception("<br /><b>Imposible ejecutar Objeto SIGTIC_EXT.ProcesarSolicitud: </b>"+SB.toString(), e);

    }
    Ruta = null;
    mail = null;
    RS = null;
    Data = null;
    To = null;
    q = null;
    req = null;
    MSG = null;
    return SB;
  }
*/
}
