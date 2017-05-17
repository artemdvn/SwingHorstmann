package swing.chapter12.tasks;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ModalityTypes {

	// First document (red): frame, modeless dialog box,
	// document-modal dialog box
	private static Frame f1;
	private static Dialog d11;
	private static Dialog d12;

	// Second document (blue): frame, modeless dialog box,
	// application-modal dialog box
	private static Frame f2;
	private static Dialog d21;
	private static Dialog d22;

	// Third document (green): modal application excluded frame
	private static Frame f3;

	// Fourth document (grey): frame, file dialog box
	// application-modal dialog box
	private static Frame f4;
	private static FileDialog fd4;
	
	// Fifth document (yellow): modal toolkit excluded frame
	private static Frame f5;
	
	// Sixth document (white): frame, modeless dialog box,
	// toolkit-modal dialog box
	private static Frame f6;
	private static Dialog d61;
	private static Dialog d62;

	private static WindowListener closeWindow = new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			e.getWindow().dispose();
		}
	};

	public static void main(String[] args) {

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = gd.getDefaultConfiguration();
		int sw = gc.getBounds().width - 64;
		int sh = gc.getBounds().height - 64;

		Label l;
		Button b;
		MenuBar mb;
		Menu m;
		MenuItem mi;

		Font labelFont = new Font("Tahoma", 24, Font.PLAIN);

		// First document

		f1 = new Frame("Parent Frame");
		f1.setBounds(32, 32, 300, 200);
		f1.addWindowListener(closeWindow);
		mb = new MenuBar();
		m = new Menu("Test");
		mi = new MenuItem("Show modeless");
		mi.addActionListener(e -> {
			d11.setVisible(true);
		});
		m.add(mi);
		mb.add(m);
		f1.setMenuBar(mb);
		f1.setLayout(new BorderLayout());
		l = new Label("FRAME");
		l.setAlignment(Label.CENTER);
		l.setFont(labelFont);
		l.setBackground(Color.RED);
		f1.add(l, BorderLayout.CENTER);
		f1.setVisible(true);

		d11 = new Dialog(f1, "Modeless Dialog");

		// An old constructor. Because the flag "modal" is
		// missed, the dialog box is modeless.

		d11.setBounds(132, 132, 300, 200);
		d11.addWindowListener(closeWindow);
		d11.setLayout(new BorderLayout());
		l = new Label("MODELESS");
		l.setAlignment(Label.CENTER);
		l.setFont(labelFont);
		l.setBackground(Color.RED);
		d11.add(l, BorderLayout.CENTER);
		b = new Button("Show document-modal");
		b.addActionListener(e -> {
			d12.setVisible(true);
		});
		d11.add(b, BorderLayout.SOUTH);

		d12 = new Dialog((Window) d11, "Document-modal Dialog", Dialog.ModalityType.DOCUMENT_MODAL);

		// New constructor with parameter for its modality type
		// Parameter type is enum Dialog.ModalityType.

		d12.setBounds(232, 232, 300, 200);
		d12.addWindowListener(closeWindow);
		d12.setLayout(new BorderLayout());
		l = new Label("DOCUMENT_MODAL");
		l.setAlignment(Label.CENTER);
		l.setFont(labelFont);
		l.setBackground(Color.RED);
		d12.add(l, BorderLayout.CENTER);

		// Second document

		f2 = new Frame("Parent Frame");
		f2.setBounds(sw - 300 + 32, 32, 300, 200);
		f2.addWindowListener(closeWindow);
		mb = new MenuBar();
		m = new Menu("Test");
		mi = new MenuItem("Show modeless");
		mi.addActionListener(e -> {
			d21.setVisible(true);
		});
		m.add(mi);
		mb.add(m);
		f2.setMenuBar(mb);
		f2.setLayout(new BorderLayout());
		l = new Label("FRAME");
		l.setBackground(Color.BLUE);
		l.setAlignment(Label.CENTER);
		l.setFont(labelFont);
		f2.add(l, BorderLayout.CENTER);
		f2.setVisible(true);

		d21 = new Dialog(f2, "Modeless Dialog");
		d21.setBounds(sw - 400 + 32, 132, 300, 200);
		d21.addWindowListener(closeWindow);
		d21.setLayout(new BorderLayout());
		l = new Label("MODELESS");
		l.setBackground(Color.BLUE);
		l.setAlignment(Label.CENTER);
		l.setFont(labelFont);
		d21.add(l, BorderLayout.CENTER);
		b = new Button("Show application-modal");
		b.addActionListener(e -> {
			d22.setVisible(true);
		});
		d21.add(b, BorderLayout.SOUTH);

		d22 = new Dialog((Window) d21, "Application-modal Dialog", Dialog.ModalityType.APPLICATION_MODAL);
		d22.setBounds(sw - 500 + 32, 232, 300, 200);
		d22.addWindowListener(closeWindow);
		d22.setLayout(new BorderLayout());
		l = new Label("APPLICATION_MODAL");
		l.setBackground(Color.BLUE);
		l.setAlignment(Label.CENTER);
		l.setFont(labelFont);
		d22.add(l, BorderLayout.CENTER);

		// Third document

		f3 = new Frame("Application Excluded Frame");

		f3.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);

		f3.setBounds(32, sh - 600 + 32, 300, 200);
		f3.addWindowListener(closeWindow);
		f3.setLayout(new BorderLayout());
		l = new Label("APPLICATION EXCLUDED FRAME");
		l.setBackground(Color.GREEN);
		l.setAlignment(Label.CENTER);
		l.setFont(labelFont);
		f3.add(l, BorderLayout.CENTER);
		b = new Button("I'm alive!");
		f3.add(b, BorderLayout.SOUTH);
		f3.setVisible(true);

		// Fourth document

		f4 = new Frame("Parent Frame");
		f4.setBounds(sw - 300 + 32, sh - 200 + 32, 300, 200);
		f4.addWindowListener(closeWindow);
		f4.setLayout(new BorderLayout());
		l = new Label("FRAME");
		l.setBackground(Color.GRAY);
		l.setAlignment(Label.CENTER);
		l.setFont(labelFont);
		b = new Button("Show file dialog");
		b.addActionListener(e -> {
			fd4.setVisible(true);
		});
		f4.add(b, BorderLayout.SOUTH);
		f4.setVisible(true);

		fd4 = new FileDialog(f4, "File Dialog", FileDialog.LOAD);
		fd4.setBounds(sw - 400 + 32, sh - 300 + 32, 300, 200);
		
		// Fifth document

		f5 = new Frame("Toolkit Excluded Frame");

		f5.setModalExclusionType(Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);

		f5.setBounds(32, sh - 200 + 32, 300, 200);
		f5.addWindowListener(closeWindow);
		f5.setLayout(new BorderLayout());
		l = new Label("TOOLKIT EXCLUDED FRAME");
		l.setBackground(Color.YELLOW);
		l.setAlignment(Label.CENTER);
		l.setFont(labelFont);
		f5.add(l, BorderLayout.CENTER);
		b = new Button("I'm alive!");
		f5.add(b, BorderLayout.SOUTH);
		f5.setVisible(true);
		
		
		// Sixth document

		f6 = new Frame("Parent Frame");
		f6.setBounds(sw - 300 + 32, sh - 600 + 32, 300, 200);
		f6.addWindowListener(closeWindow);
		mb = new MenuBar();
		m = new Menu("Test");
		mi = new MenuItem("Show modeless");
		mi.addActionListener(e -> {
			d61.setVisible(true);
		});
		m.add(mi);
		mb.add(m);
		f6.setMenuBar(mb);
		f6.setLayout(new BorderLayout());
		l = new Label("FRAME");
		l.setBackground(Color.WHITE);
		l.setAlignment(Label.CENTER);
		l.setFont(labelFont);
		f6.add(l, BorderLayout.CENTER);
		f6.setVisible(true);

		d61 = new Dialog(f6, "Modeless Dialog");
		d61.setBounds(sw - 400 + 32, sh - 500 + 32, 300, 200);
		d61.addWindowListener(closeWindow);
		d61.setLayout(new BorderLayout());
		l = new Label("MODELESS");
		l.setBackground(Color.WHITE);
		l.setAlignment(Label.CENTER);
		l.setFont(labelFont);
		d61.add(l, BorderLayout.CENTER);
		b = new Button("Show toolkit-modal");
		b.addActionListener(e -> {
			d62.setVisible(true);
		});
		d61.add(b, BorderLayout.SOUTH);

		d62 = new Dialog((Window) d61, "Toolkit-modal Dialog", Dialog.ModalityType.TOOLKIT_MODAL);
		d62.setBounds(sw - 500 + 32, sh - 400 + 32, 300, 200);
		d62.addWindowListener(closeWindow);
		d62.setLayout(new BorderLayout());
		l = new Label("TOOLKIT_MODAL");
		l.setBackground(Color.WHITE);
		l.setAlignment(Label.CENTER);
		l.setFont(labelFont);
		d62.add(l, BorderLayout.CENTER);
				
	}
}
