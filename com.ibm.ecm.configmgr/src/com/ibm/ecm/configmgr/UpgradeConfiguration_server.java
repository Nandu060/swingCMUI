package com.ibm.ecm.configmgr;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.Element;

import com.ibm.Factory.PropertyFactory;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class UpgradeConfiguration_server extends JFrame {

	private JPanel contentPane;
	PropertyFactory props = new PropertyFactory();
	String file = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\Servers.xml";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpgradeConfiguration_server frame = new UpgradeConfiguration_server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpgradeConfiguration_server() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 515);
		setTitle("Upgrade Configuration Profile");
		setAlwaysOnTop(true);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		setContentPane(contentPane);
		
		props.XmlDoc(file.toString(), (String)null);
		Element doc1 = props.getDocElem();
		//String[] names = props.getElementNamesByTagName(doc1, "server");
		String version = props.getAttribute(doc1, "server", CMUtil.upgradeProfile, "version");
		String ear_path = props.getAttribute(doc1, "server", CMUtil.upgradeProfile, "ear");
		String deploy = props.getAttribute(doc1, "server", CMUtil.upgradeProfile, "deployment");
		String profile_path = props.getAttribute(doc1, "server", CMUtil.upgradeProfile, "profilePath");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(254, 255, 255));
		
		JLabel lblNewLabel = new JLabel("Upgrade Configuration Profile Creation Summary");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JLabel lblNewLabel_1 = new JLabel("You are viewing information for an existing server from servers.xml. ");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 585, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_2 = new JLabel("Application Server:");
		
		JLabel lblNewLabel_3 = new JLabel("Server name:");
		
		JLabel lblNewLabel_4 = new JLabel("Installed Content Platform Engine version:  ");
		
		JLabel lblNewLabel_5 = new JLabel("EAR file path:");
		
		JLabel lblNewLabel_6 = new JLabel("Deployment type:");
		
		JLabel lblNewLabel_7 = new JLabel("Profile path:");
		
		JLabel lblNewLabel_8 = new JLabel(CMUtil.upgradeAppServer);
		
		JLabel lblNewLabel_9 = new JLabel(CMUtil.upgradeProfile);
		
		JLabel lblNewLabel_10 = new JLabel(version);
		
		JLabel lblNewLabel_11 = new JLabel(ear_path);
		
		JLabel lblNewLabel_12 = new JLabel(deploy);
		
		JLabel lblNewLabel_13 = new JLabel(profile_path);
		
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
		
		JButton nextBtn = new JButton("Next>");
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nextPerformed(e);
			}
		});
		
		JButton backBtn = new JButton("<Back");
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				backPerformed(e);
			}
		});
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 880, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(488)
					.addComponent(backBtn)
					.addGap(1)
					.addComponent(nextBtn, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(finishBtn, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_8))
					.addGap(17)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_9))
					.addGap(15)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_10))
					.addGap(16)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_11))
					.addGap(14)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_6)
						.addComponent(lblNewLabel_12))
					.addGap(17)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_7)
						.addComponent(lblNewLabel_13))
					.addGap(112)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn)
						.addComponent(nextBtn)
						.addComponent(finishBtn)
						.addComponent(cancelBtn)))
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 888, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 888, GroupLayout.PREFERRED_SIZE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void backPerformed(ActionEvent evt)
	{
		//dispose();
		setVisible(false);
		Upgrade_Configuration uc = new Upgrade_Configuration();
		uc.setVisible(true);
		uc.setLocationRelativeTo(null);
		//this.toBack();
		//setVisible(false);
		//new Upgrade_Configuration().setVisible(true);
		//new Upgrade_Configuration().setState(NORMAL);
		//setContentPane(new Upgrade_Configuration().getContentPane());
		//remove(contentPane);
	}
	
	public void nextPerformed(ActionEvent evt)
	{
		if(CMUtil.upgradeAppServer.contains("WebSphere"))
		{
			setVisible(false);
			WAS_FreshProfile wsf = new WAS_FreshProfile("UpgradeWithServers");
			wsf.setVisible(true);
			wsf.setLocationRelativeTo(null);
		}
		else
		{
			setVisible(false);
			WL_FreshProfile wlf = new WL_FreshProfile("UpgradeWithServers");
			wlf.setVisible(true);
			wlf.setLocationRelativeTo(null);
		}
	}
}

