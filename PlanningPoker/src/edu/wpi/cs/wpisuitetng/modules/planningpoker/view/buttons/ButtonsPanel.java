package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;

/**
 * This is the code for the three buttons on the tool bar. Currently has only one button on it.
 * @author Fangming Ning
 * @contributOr Team romulus
 */
@SuppressWarnings("serial")
public class ButtonsPanel extends ToolbarGroupView{
		private JButton createButton = new JButton("<html>Create<br />Session</html>");
		private final JButton joinButton = new JButton("<html>Join<br />Session</html>");
		private final JButton editButton = new JButton("<html>Edit<br />Session</html>");
		private final JButton viewButton = new JButton("<html>View Old<br />Session</html>");
		private final JPanel contentPanel = new JPanel();
	
	public ButtonsPanel(){
		super("");
		
		this.contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		//change this to 450 when we have three buttons
		this.setPreferredWidth(300);

		this.createButton.setHorizontalAlignment(SwingConstants.CENTER);
		try {
		    Image img = ImageIO.read(getClass().getResource("createSession.png"));
		    this.createButton.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("joinSession.png"));
		    this.joinButton.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("viewSession.png"));
		    this.viewButton.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("createSession.png"));
		    this.editButton.setIcon(new ImageIcon(img));
		    
		} catch (IOException ex) {}
		
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					ViewEventController.getInstance().createSession();
			}
		});		
		
		joinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					ViewEventController.getInstance().createSession();
				}
		});
			
		contentPanel.add(createButton);
		contentPanel.add(joinButton);
		contentPanel.add(viewButton);
		contentPanel.add(editButton);
		contentPanel.setOpaque(false);
		joinButton.setVisible(false);
		viewButton.setVisible(false);

		this.add(contentPanel);
	}
	/**
	 * Method getCreateButton.
	
	 * @return JButton */
	public JButton getCreateButton() {
		return createButton;
	}

	/**
	 * Method getCreateIterationButton.
	
	 * @return JButton */
	public JButton getCreateIterationButton() {
		return createButton;
	}

	
}
