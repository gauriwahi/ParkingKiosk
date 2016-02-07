

import java.awt.*;

import javax.swing.JButton;

public class Button {
	
	 private JButton createButton(final boolean opaque) {
	        JButton but = new JButton("Testing") {

	            /**
	             * @inherited <p>
	             * Overridden to take over background painting with 
	             * transparent color.
	             */
	            @Override
	            protected void paintComponent(Graphics g) {
	                if (!isOpaque() && getBackground().getAlpha() < 255) {
	                    g.setColor(getBackground());
	                    g.fillRect(0, 0, getWidth(), getHeight());
	                }
	                super.paintComponent(g);
	            }

	        };
	        but.setBackground(new Color(0, 0, 0, 100));
	        but.setForeground(new Color(70, 155, 255));
	        but.setOpaque(opaque);
	        return but;
	    }

}
