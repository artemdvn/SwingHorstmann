package swing.chapter11;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class ActionFrameWithButtons extends JFrame {
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;

	public ActionFrameWithButtons() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		buttonPanel = new JPanel();

		Action redAction = new ColorAction("Red", new ImageIcon("car.png"), Color.RED);
		Action yellowAction = new ColorAction("Yellow", new ImageIcon("bomb.png"), Color.YELLOW);
		buttonPanel.add(new JButton(redAction));
		buttonPanel.add(new JButton(yellowAction));
		add(buttonPanel);

		InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");
		imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");

		ActionMap amap = buttonPanel.getActionMap();
		amap.put("panel.red", redAction);
		amap.put("panel.yellow", yellowAction);
	}

	public class ColorAction extends AbstractAction {
		/**
		 * Constructs color changing action
		 * 
		 * @param name
		 *            button label
		 * @param icon
		 *            button icon
		 * @param ñ
		 *            background color
		 */
		public ColorAction(String name, Icon icon, Color c) {
			putValue(Action.NAME, name);
			putValue(Action.SMALL_ICON, icon);
			putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
			putValue("color", c);
		}

		public void actionPerformed(ActionEvent event) {
			Color c = (Color) getValue("color");
			buttonPanel.setBackground(c);

		}
	}

}
