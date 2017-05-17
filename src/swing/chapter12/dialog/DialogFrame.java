package swing.chapter12.dialog;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * A frame with a menu whose File->About action shows a dialog.
 */
public class DialogFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private AboutDialog dialog;

	public DialogFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		// Construct a File menu.

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		// Add action listener for Open About
		ActionListener openAboutListener = event -> {
			if (dialog == null) {
				// first time
				dialog = new AboutDialog(DialogFrame.this);
			}
			System.out.println("AboutDialog id: " + AboutDialog.getId());
			dialog.setVisible(true); // pop up dialog
		};
		
		// Add action listener for Close about
		ActionListener closeAboutListener = event -> {
			if (dialog != null) {
				System.out.println("AboutDialog id: " + AboutDialog.getId());
				dialog.setVisible(false);
			}			
		};

		// Add About and Exit menu items.

		// The Open about item shows the About dialog.
		JMenuItem openAboutItem = new JMenuItem("Open about");
		openAboutItem.addActionListener(openAboutListener);
		fileMenu.add(openAboutItem);
		
		// The Close about item closes the About dialog.
		JMenuItem closeAboutItem = new JMenuItem("Close about");
		closeAboutItem.addActionListener(closeAboutListener);
		fileMenu.add(closeAboutItem);

		// The Exit item exits the program.

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(event -> System.exit(0));
		fileMenu.add(exitItem);
	}
}
