/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import java.io.InputStream;
import java.util.HashMap;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author felipe
 */
public class PruebaReporte {
    TableModel tableModel = null;
    public void do_test() {
        JasperPrint jasperPrint = null;
        
        TableModelData();
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("reportes/pagos.jrxml");
            JasperReport report = JasperCompileManager.compileReport(in);
            
            jasperPrint = JasperFillManager.fillReport(report, new HashMap(),
                    new JRTableModelDataSource(tableModel));
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {
            System.out.println(ex.toString());
        }
    }

    public void TableModelData() {
        
    }
    
    static public void main(String []args) {
        PruebaReporte test = new PruebaReporte();
        test.do_test();
    }
}
