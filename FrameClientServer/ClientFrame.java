package FrameClientServer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class ClientFrame {

	private JFrame frame;
	private JTextArea textArea, insertText;
	private JTextField textField;
	private JPanel panel;
	private TitledBorder bordoConTitolo;
	private Border bordoConPadding, bordo, bordoVuoto;
	private boolean checkIndirizzo = false, close = false;
	private String indirizzo;
	private PrintWriter out;

	public ClientFrame()
	{
		frame = new JFrame("Client");
		frame.setLayout(new FlowLayout());
		frame.getContentPane().setBackground(new Color(150,150,150));
		panel = new JPanel(new FlowLayout());
		panel.setBackground(new Color(150,150,150));
		createChooseIPAddress();
		createButtonConnect();
		createChat();
		createInsertText();
		createButtonSend();
		panel.setPreferredSize(new Dimension(600,550));//550,431   550,447
		frame.add(panel);

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(800,450);
		frame.pack();
		frame.setVisible(true);
		//System.out.println(insertText.getWidth()+"  "+insertText.getHeight());
	}

	private void createChat()
	{
		textArea = new JTextArea("", 20, 50);
		textArea.setEditable(false);
		textArea.setFocusable(false);
		textArea.setBackground(new Color(200,200,200));

		bordoVuoto = BorderFactory.createEmptyBorder();
		bordoConTitolo = BorderFactory.createTitledBorder(bordoVuoto, "Testo ricevuto dal Server");
		bordoConPadding = BorderFactory.createEmptyBorder(0,5,0,0);//top,left,bottom,right
		bordo = BorderFactory.createCompoundBorder(bordoConTitolo, bordoConPadding);
		textArea.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scroll.getVerticalScrollBar().setPreferredSize(new Dimension(15,0));
	    scroll.setBackground(new Color(150,150,150));
	    scroll.setBorder(bordo);

		panel.add(scroll);
	}

	private void createInsertText()
	{
		insertText = new JTextArea("", 7, 45); //un rigo = 16px Width
		insertText.setBackground(new Color(200,200,200));

		bordoVuoto = BorderFactory.createEmptyBorder();
		bordoConTitolo = BorderFactory.createTitledBorder(bordoVuoto, "Inserisci testo da inviare al Server");
		bordoConPadding = BorderFactory.createEmptyBorder(0,5,0,0);//top,left,bottom,right
		bordo = BorderFactory.createCompoundBorder(bordoConTitolo, bordoConPadding);
		insertText.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		JScrollPane scroll = new JScrollPane(insertText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scroll.getVerticalScrollBar().setPreferredSize(new Dimension(15,0));
	    scroll.setBackground(new Color(150,150,150));
	    scroll.setBorder(bordo);

	    panel.add(scroll);
	}

	private void createChooseIPAddress()
	{
		textField = new JTextField("localhost", 20);
		textField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JLabel jl = new JLabel("Inserisci l' indirizzo IP del server");
		panel.add(jl);
		panel.add(textField);
	}

	public void createButtonConnect()
	{
		final JButton b = new JButton("Connetti");
		ActionListener aL = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(checkIndirizzo)
				{
					b.setEnabled(false);
					b.validate();
					b.repaint();
				}
				checkIndirizzo = true;
				indirizzo = textField.getText();
			}
		};
		b.addActionListener(aL);
		panel.add(b);
	}

	public void setPrintWriter(PrintWriter out)
	{
		this.out = out;
	}

	public void createButtonSend()
	{
		JButton b = new JButton("Invia");
		ActionListener aL = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s;
				if(!insertText.getText().isEmpty())
				{
					s = insertText.getText();
					if(s.equalsIgnoreCase("/close"))
					{
						System.out.println("qui");
						close = true;
					}
					out.println(s);
					System.out.println(s+" 3");
					insertText.setText("");
				}
			}
		};
		b.addActionListener(aL);
		panel.add(b);
	}

	public boolean getClose()
	{
		return close;
	}

	public boolean getCheckIndirizzo()
	{
		return checkIndirizzo;
	}

	public String getIndirizzo()
	{
		return indirizzo;
	}

	public void write(String s)
	{
		textArea.append(s+"\n");
	}
}
