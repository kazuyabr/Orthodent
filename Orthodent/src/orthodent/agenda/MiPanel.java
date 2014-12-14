/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.agenda;

import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.Scrollable;

/**
 *
 * @author Lucy
 */
public class MiPanel extends JPanel implements Scrollable {

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return this.getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle rctngl, int i, int i1) {
        return 5;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle rctngl, int i, int i1) {
        return 5;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return true;
    }

    public MiPanel() {
    }
    
    
    
}
