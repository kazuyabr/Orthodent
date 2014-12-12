/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.reportes;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
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
    static JasperPrint generarPresupuesto(int id_presupuesto, DbConnection db) throws JRException {
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
}
