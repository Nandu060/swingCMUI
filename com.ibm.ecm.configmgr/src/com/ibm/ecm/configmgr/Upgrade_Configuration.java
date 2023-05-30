package com.ibm.ecm.configmgr;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.Element;

import com.ibm.Factory.PropertyFactory;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Upgrade_Configuration extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane1;
	JScrollPane pane;
	JButton nextBtn;
	JTable table;
	String[] col;
	Object[][] data;
	private JTable table_1;
	PropertyFactory props = new PropertyFactory();
	String serverFilePath = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\Servers.xml";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Upgrade_Configuration frame = new Upgrade_Configuration();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Upgrade_Configuration() {
		//setBounds(100, 100, 515, 515);
		setBounds(100, 100, 520, 515);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Upgrade Configuration Profile");
		setAlwaysOnTop(true);
		setResizable(false);
		
		contentPane = new JPanel();
		//contentPane1 = new UpgradeConfiguration_server().getContentPane();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		//setBounds(x, y, width, height);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(254, 255, 255));
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblNewLabel = new JLabel("Upgrade Configuration Profile Creation Summary");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JLabel lblNewLabel_2 = new JLabel("Select the server to upgrade by selecting its row in the table ");
		
		JSeparator separator = new JSeparator();
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CMUtil.profileName="";
				CMUtil.appServer="";
				dispose();
				new NewJFrame("", "").makeUI();
			}
		});
		
		JButton finishBtn = new JButton("Finish");
		finishBtn.setEnabled(false);
		
		nextBtn = new JButton("Next>");
		nextBtn.setEnabled(false);
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nextPerformed(e);
			}
		});
		
		JButton backBtn = new JButton("<Back");
		backBtn.setEnabled(false);
		
		col = new String[] {"Server name","CE Version","App Server Type"};
		data = getData();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblNewLabel_2))
		);
		panel.setLayout(gl_panel);
		
		table_1 = new JTable(data,col);
		//table_1.setCellSelectionEnabled(true);
		table_1.setBounds(35, 26, 420, 255);
		table_1.setShowGrid(true);
		table_1.setShowHorizontalLines(true);
		table_1.setShowVerticalLines(true);
		//panel_1.add(table_1);
		table_1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				nextBtn.setEnabled(true);
				serverClicked(e);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table_1);
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(35)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(156)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(250)
							.addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(170)
							.addComponent(finishBtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(80)
							.addComponent(nextBtn, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(26)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(cancelBtn)
						.addComponent(backBtn)
						.addComponent(finishBtn)
						.addComponent(nextBtn)))
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 488, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 488, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);
		
	}
	
	Object[][] getData()
	{
		Object[][] rows = null;
		try
		{
			String version = "";
			props.XmlDoc(serverFilePath.toString(), (String)null);
			Element doc1 = props.getDocElem();
			String[] names = props.getElementNamesByTagName(doc1, "server");
			ArrayList<String> list = new ArrayList<>();
			for(int i=0;i<names.length;i++)
			{
				version = props.getAttribute(doc1, "server", names[i], "version");
				list.add(names[i]+"/"+version);
			}
			System.out.println(list);
			rows = new Object[list.size()][3];
			for(int i=0;i<list.size();i++)
			{
				rows[i][0] = list.get(i).split("/")[1];
				rows[i][1] = list.get(i).split("/")[2];
				if((list.get(i).split("/")[0]).equals("WL"))
					rows[i][2] = "Oracle WebLogic Server";
				else
					rows[i][2] = "WebSphere Application Server";
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return rows;
	}
	public void serverClicked(MouseEvent evt)
	{
		int selectedRowIndex = table_1.getSelectedRow();
		CMUtil.upgradeProfile = data[selectedRowIndex][0].toString();
		CMUtil.upgradeAppServer = data[selectedRowIndex][2].toString();
		System.out.println(CMUtil.upgradeProfile);
		CMUtil.profileName = CMUtil.upgradeProfile;
		CMUtil.appServer = CMUtil.upgradeAppServer;
	}
	public void nextPerformed(ActionEvent evt)
	{
		/*String msg = new String("Is the server you want to upgrade running a version earlier than 5.5.1?");
		int result = JOptionPane.showConfirmDialog(this, msg, "Action Required",
	               JOptionPane.YES_NO_OPTION,
	               JOptionPane.QUESTION_MESSAGE);
		if(result == JOptionPane.YES_OPTION)
		{
			//another panel to be opened showing the entire info of the selected profile
			CMUtil.serverEarlier551 = true;
        }
		else if (result == JOptionPane.NO_OPTION)
		{
           //same here
			CMUtil.serverEarlier551 = false;
        }
		else 
		{
            System.out.println("Wrong selection");
        }*/
		//dispose();
		setVisible(false);
		UpgradeConfiguration_server ucs = new UpgradeConfiguration_server();
		ucs.setVisible(true);
		ucs.setLocationRelativeTo(null);
		//this.toBack();
		//new UpgradeConfiguration_server().setVisible(true);
		//new UpgradeConfiguration_server().toFront();
		//setVisible(false);
		//setContentPane(new UpgradeConfiguration_server().getContentPane());
		//remove(contentPane);
	}
	public void close()
	{
		WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSED);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
}
