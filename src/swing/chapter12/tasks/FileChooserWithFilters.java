package swing.chapter12.tasks;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooserWithFilters {

	private static JFileChooser chooser;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {

			// set up file chooser
			chooser = new JFileChooser();

			JFrame frame = new ImageViewerFrame(chooser);
			frame.setTitle("ImageViewerFrame");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);

			JFrame textFrame = new JavaViewerFrame(chooser);
			textFrame.setTitle("JavaViewerFrame");
			textFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			textFrame.setVisible(true);
		});
	}
}

class ImageViewerFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;
	private JLabel label;
	private JFileChooser chooser;
	private static FileFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "gif", "png");

	public ImageViewerFrame(JFileChooser chooser) {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		this.chooser = chooser;

		// set up menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("File");
		menuBar.add(menu);

		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(event -> {

			// accept all image files ending with .jpg, .jpeg, .gif
			this.chooser.resetChoosableFileFilters();
			this.chooser.setFileFilter(filter);

			this.chooser.setCurrentDirectory(new File("."));

			// show file chooser dialog
			int result = this.chooser.showOpenDialog(ImageViewerFrame.this);

			// if image file accepted, set it as icon of the label
			if (result == JFileChooser.APPROVE_OPTION) {
				String name = this.chooser.getSelectedFile().getPath();
				label.setIcon(new ImageIcon(name));
				pack();
			}
		});

		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(event -> System.exit(0));

		// use a label to display the images
		label = new JLabel();
		add(label);

	}
}

class JavaViewerFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;
	private JLabel label;
	private JFileChooser chooser;
	private static FileFilter filter = new FileNameExtensionFilter("Java files", "java");

	public JavaViewerFrame(JFileChooser chooser) {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		this.chooser = chooser;

		// set up menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("File");
		menuBar.add(menu);

		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(event -> {
			// accept all src files ending with .java
			//this.chooser.resetChoosableFileFilters();
			this.chooser.setFileFilter(filter);

			this.chooser.setCurrentDirectory(new File("."));

			// show file chooser dialog
			int result = this.chooser.showOpenDialog(JavaViewerFrame.this);

		});

		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(event -> System.exit(0));

		// use a label to display the images
		label = new JLabel();
		add(label);

	}
}
