package swing.chapter11;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class ActionFrameTest {
		
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			ActionFrameWithButtons firstFrame = new ActionFrameWithButtons();
			firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			firstFrame.setBounds(10, 10, 200, 200);
			firstFrame.setVisible(true);
			
			ActionFrameWithButtons secondFrame = new ActionFrameWithButtons();
			secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			secondFrame.setBounds(400, 10, 200, 200);
			secondFrame.setVisible(true);
		});
	}
}
