/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.reportes;

import java.io.InputStream;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import orthodent.db.DbConnection;

/**
 *
 * @author felipe
 */
public class Reportes {
    public static JasperPrint generarPresupuesto(int id_presupuesto, DbConnection db) throws JRException {
            InputStream in;
            
            in = Reportes.class.getClassLoader().getResourceAsStream("orthodent/reportes/presupuesto.jrxml");     
            
            JasperReport report;
            report = JasperCompileManager.compileReport(in);
            
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("ID_PRESUPUESTO", id_presupuesto);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters,
                db.getConnection());
            return jasperPrint;
    }
    public static JasperPrint generarPagos(String profIds, String fecha1, String fecha2, DbConnection db) throws JRException {
        InputStream in;

        in = Reportes.class.getClassLoader().getResourceAsStream("orthodent/reportes/pagos.jrxml");

        JasperReport report;
        report = JasperCompileManager.compileReport(in);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ID_PROFESIONAL_LIST", profIds);
        parameters.put("FECHA1", fecha1);
        parameters.put("FECHA2", fecha2);

        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters,
                db.getConnection());
        
        // HACK para eliminar paginas en blanco
        jasperPrint.removePage(jasperPrint.getPages().size() - 1);
        List<JRPrintPage> pages = jasperPrint.getPages();
       
        for (int i = 0; i < jasperPrint.getPages().size();) {
            JRPrintPage page = jasperPrint.getPages().get(i);
            List<JRPrintElement> elements = page.getElements();

            System.out.println("height=" + elements.get(4).getHeight());
            if (elements.get(4).getHeight() == 0) {
                jasperPrint.removePage(i);
            } else
                i++;
        }

            return jasperPrint;
    }
}
