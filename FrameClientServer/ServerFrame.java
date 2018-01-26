package FrameClientServer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class ServerFrame {

	private JFrame frame;
	private JPanel panel;
	private JTextArea textArea;
	private TitledBorder bordoConTitolo;
	private Border bordoConPadding, bordo, bordoVuoto;;
	
	public ServerFrame()
	{
		frame = new JFrame("Server");
		frame.setLayout(new FlowLayout());
		frame.getContentPane().setBackground(new Color(150,150,150));
		panel = new JPanel(new FlowLayout());
		panel.setBackground(new Color(150,150,150));
		createChat();
		frame.add(panel);

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(800,450);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void createChat()
	{
		textArea = new JTextArea(""/*"sdfa\nsfsf\nesfsfd\nsdfsdf\nsdfsdg\nsdfsdf\na\ns\ns\ns\ns\ns\ns\ns\ns\ns\ns\ns\n\n\n\n\n\n\n\nfsdfs\n\nada\n\nasdas"*/, 20, 50);
		textArea.setEditable(false);
		textArea.setFocusable(false);
		textArea.setBackground(new Color(200,200,200));
		
		bordoVuoto = BorderFactory.createEmptyBorder();
		bordoConTitolo = BorderFactory.createTitledBorder(bordoVuoto, "Testo ricevuto dal Client");
		bordoConPadding = BorderFactory.createEmptyBorder(0,5,0,0);//top,left,bottom,right
		bordo = BorderFactory.createCompoundBorder(bordoConTitolo, bordoConPadding);
		textArea.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scroll.getVerticalScrollBar().setPreferredSize(new Dimension(15,0));
	    scroll.setBackground(new Color(150,150,150));
	    scroll.setBorder(bordo);
	    
		panel.add(scroll);
	}
	
	public void write(String s)
	{
		textArea.append(s+"\n");
	}
}
