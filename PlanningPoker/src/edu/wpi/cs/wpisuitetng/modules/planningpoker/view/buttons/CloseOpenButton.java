/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * Description
 * 
 * @author Sean Halloran
 * @version Apr 22, 2014
 */
public class CloseOpenButton extends JButton {

    private Image closeIcon;
    private Image openIcon;
    
    public CloseOpenButton() {
        setHorizontalAlignment(SwingConstants.CENTER);

        this.setText("<html>Close<br />Session</html>");
        
        try {
            closeIcon = ImageIO.read(getClass().getResource("close-button.png"));
            openIcon = ImageIO.read(getClass().getResource("close-button.png"));
            setIcon(new ImageIcon(closeIcon));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
