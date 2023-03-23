package com.ibm.ecm.configmgr;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

public class WAS_FreshProfile extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_4;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_2;
	public static String cellValue="";
	private static String appServer="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WAS_FreshProfile frame = new WAS_FreshProfile(appServer);
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
	public WAS_FreshProfile(String appServer) {
		this.appServer = appServer;
		setBounds(100, 100, 638, 656);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Create New Installation Profile");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(254, 255, 255));
		
		JLabel lblNewLabel = new JLabel("Set Properties for WebSphere Application Server");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		final JLabel lblNewLabel_1 = new JLabel("*Please fill all the required fields");
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblNewLabel_2 = new JLabel("Application server version* :");
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8.5.5", "9.0" }));
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String wasVersion = comboBox.getSelectedItem().toString();
				CMUtil.wasVersion = wasVersion;
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("Application server installation directory* :");
		
		textField = new JTextField();
		textField.setText("C:\\Program Files IBM\\WebSphere\\AppServer");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser profPath = new JFileChooser();
		        profPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        profPath.setCurrentDirectory(new java.io.File("."));
		        profPath.showOpenDialog(null);
		        File profDir = profPath.getSelectedFile();
		        if (!(profDir.isDirectory() && profDir.exists())) {
		            JOptionPane.showMessageDialog(rootPane, "The Application Server directory does not exist. \n Please provide the correct application directory path.");
		        }
		        String profAPath = profDir.getAbsolutePath();
		        textField.setText(profAPath);
			}
		});
		CMUtil.appServerInstallFolder = textField.getText();
		
		JLabel lblNewLabel_4 = new JLabel("Application server administration user name* :");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Application server administrator password* :");
		
		passwordField = new JPasswordField();
		
		JLabel lblNewLabel_6 = new JLabel("Confirm* :");
		
		passwordField_1 = new JPasswordField();
		
		JLabel lblNewLabel_7 = new JLabel("Application server SOAP port* :");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("SSL enabled");
		
		JLabel lblNewLabel_8 = new JLabel("Application server host* :");
		
		textField_3 = new JTextField();
		textField_3.setText("localhost");
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Application server cell* :");
		
		JLabel lblNewLabel_10 = new JLabel("Security Domain* :");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel<>(new String[] {"default"}));
		CMUtil.securityDomain = "default";
		
		JLabel lblNewLabel_11 = new JLabel("Application server transaction timeout* :");
		
		textField_5 = new JTextField();
		textField_5.setText("180");
		textField_5.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Test Connection");
		
		JSeparator separator = new JSeparator();
		
		JButton btnNewButton_3 = new JButton("Cancel");
		
		JButton btnNewButton_4 = new JButton("Finish");
		btnNewButton_4.setEnabled(false);
		
		JButton btnNewButton_5 = new JButton("Next >");
		btnNewButton_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nextPerformed(e);
			}
		});
		
		JButton btnNewButton_6 = new JButton("< Back");
		btnNewButton_6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				backPerformed(e);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 588, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblNewLabel_1))
		);
		panel.setLayout(gl_panel);
		
		comboBox_2 = new JComboBox();
				//new DefaultComboBoxModel<>(new String[] {"NandanCMUIInstallDev1Node01Cell"}));
		comboBox_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
		    		//String scriptName = "fetchcell.py", licenseValue = "";
		    		//cellValue = WebsphereUtil.runScript(scriptName,licenseValue);//"RohitNode04Cell";
		    		//System.out.println("CellName = "+ cellValue);
		    		
		    		//System.out.println(cellValue);
		    		CMUtil.appServerCell = comboBox_2.getSelectedItem().toString();
		    		System.out.println(CMUtil.appServerCell);
		    		//comboBox_2.setSelectedItem(cellValue);
		        } catch(Exception ex) {
		            //catch these exceptions - throws MalformedObjectNameException, NullPointerException, InstanceNotFoundException, MBeanException, ReflectionException, IOException, AttributeNotFoundException
		            ex.printStackTrace();
		        }
			}
		});
		
		comboBox_2.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel_1.setText("*Fetching the application server cell name ");
				String scriptName = "fetchcell.py", licenseValue = "";
	    		cellValue = WebsphereUtil.runScript(scriptName,licenseValue);
	    		lblNewLabel_1.setText("*Server cell fetched successfully");
	    		comboBox_2.setModel(new DefaultComboBoxModel<>(new String[] {cellValue}));
	    		System.out.println(cellValue);
			}
		});
		
		
		JLabel lblNewLabel_12 = new JLabel("Application server profile directory* :");
		
		textField_4 = new JTextField();
		textField_4.setText("C:\\Program Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv01");
		textField_4.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Browse");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser profPath = new JFileChooser();
		        profPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        profPath.setCurrentDirectory(new java.io.File("."));
		        profPath.showOpenDialog(null);
		        File profDir = profPath.getSelectedFile();
		        if (!(profDir.isDirectory() && profDir.exists())) {
		            JOptionPane.showMessageDialog(rootPane, "The Application Server directory does not exist. \n Please provide the correct application directory path.");
		        }
		        String profAPath = profDir.getAbsolutePath();
		        textField_4.setText(profAPath);
			}
		});
		CMUtil.appServerProfile = textField_4.getText();
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(153)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(153)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(326)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 590, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(237)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(87)
							.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
					.addGap(4)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(88)
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_2))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_3))
						.addComponent(btnNewButton)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(3)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_12))
						.addComponent(btnNewButton_1)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_7))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(chckbxNewCheckBox)))
					.addGap(16)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_8))
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_9))
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_11))
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_10))
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addComponent(btnNewButton_2)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 626, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 626, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 527, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);
	}
	public void nextPerformed(ActionEvent ev)
	{
		
		new ChooseTasksPanel(CMUtil.appServer).setVisible(true);
		String file2 = NewJFrame.filePath+"\\"+CMUtil.profileName+"\\applicationserver.xml";
		CMUtil.appServerInstallFolder = textField.getText();
		CMUtil.appServerAdminPassword = passwordField.getText();
		CMUtil.appServerAdminUser = textField_1.getText();
		CMUtil.appServerSOAP = textField_2.getText();
		CMUtil.appServerTimeout = textField_5.getText();
		CMUtil.appServerHostName = textField_3.getText();
		CMUtil.appServerDomain = textField_4.getText();
		String res = "false";
		/*if(chckbxNewCheckBox.isSelected())
			  res = "true";
		  CMUtil.SSLEnabled = res;*/
        this.dispose();
	}
	public void backPerformed(ActionEvent ev)
	{
		new ProfDetails2().setVisible(true);
		this.dispose();
	}
}

