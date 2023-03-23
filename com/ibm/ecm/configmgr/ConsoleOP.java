package com.ibm.ecm.configmgr;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsoleOP extends JPanel
{
	private static JTextArea textArea;
	
	public ConsoleOP()
	{
		textArea = new JTextArea();
		setLayout(new BorderLayout());
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
	
	public static void appendText(String text)
	{
		textArea.append(text);
	}
	public static void clearText()
	{
		textArea.setText("");
	}

}
