/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.reportes;

import java.awt.Desktop;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import orthodent.db.DbConnection;
import  java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felipe
 */
public class PruebaReporte {

    public void do_test() {
        JasperPrint jasperPrint = null;

        try {
            /* Asi se usa, (ID de presupuesto, DB connection) */
            jasperPrint = Reportes.generarPresupuesto(5, new DbConnection());
            
            /* Asi se genera un PDF */
            File tempPdf;
            try {
                tempPdf = File.createTempFile("orthodent_reporte", ".pdf");
                JasperExportManager.exportReportToPdfFile(jasperPrint, tempPdf.getPath());
                System.out.println("PDF en " + tempPdf.getPath());
                /* Asi se visualiza */
                Desktop.getDesktop().open(tempPdf);
            } catch (IOException ex) {
                Logger.getLogger(PruebaReporte.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JRException ex) {
            System.out.println(ex.toString());
        }
    }
    
    static public void main(String []args) {
        PruebaReporte test = new PruebaReporte();
        test.do_test();
    }
}
