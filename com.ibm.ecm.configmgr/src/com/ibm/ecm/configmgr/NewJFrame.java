/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ibm.ecm.configmgr;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
//import javax.swing.text.Document;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ibm.Factory.PropertyFactory;

/**
 *
 * @author nandankn
 */
public class NewJFrame extends javax.swing.JFrame {

    String profileNameSelected = "";
    public static String filePath = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\profiles\\";
    /**
     * Creates new form NewJFrame
     */
    public NewJFrame(String profileName,String console) {
        super("IBM FileNet Configuration Manager for CPE");
        setMaximumSize(new Dimension(400, 200));
        profileNameSelected = profileName;
        initComponents(profileNameSelected);
         if (!profileName.equals(""))
        	TasksTree.setVisible(true);
        else
        	TasksTree.setVisible(false);
        
        //textArea1.setText(console);
         //consoleOP.appendText(console);
         ConsoleOP.appendText(console);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(String profName) {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TasksTree = new javax.swing.JTree();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        //textArea1 = new javax.swing.JTextArea();
        MenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        New = new javax.swing.JMenuItem();
        Open = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        Exit = new javax.swing.JMenuItem();
        EnableTask = new JMenuItem();
        DisableTask = new JMenuItem();
        Upgrade = new JMenuItem();
        Edit = new JMenuItem();
        Close = new JMenuItem();
        WindowMenu = new javax.swing.JMenu();
        HelpMenu = new javax.swing.JMenu();
        popMenu = new JPopupMenu();
        consoleOP = new ConsoleOP();
        System.out.println("App server is "+CMUtil.appServer);
        TasksTree.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), "Tasks"));
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(profName);
        if(CMUtil.scenario.equals("Fresh"))
        {
	        DefaultMutableTreeNode subNode = new DefaultMutableTreeNode("Configure GCD JDBC DataSources");
	        rootNode.add(subNode);
	        subNode = new DefaultMutableTreeNode("Configure Object Store JDBC DataSources");
	        rootNode.add(subNode);
	        if((CMUtil.appServer.equalsIgnoreCase("websphere"))||(CMUtil.appServer.startsWith("WebSphere"))) {
	        subNode = new DefaultMutableTreeNode("Configure Login Modules");
	        rootNode.add(subNode);
	        }
	        subNode = new DefaultMutableTreeNode("Configure LDAP");
	        rootNode.add(subNode);
	        subNode = new DefaultMutableTreeNode("Deploy Application");
	        rootNode.add(subNode);
        }
        else if(CMUtil.scenario.equals("UpgradeWithoutServers"))
        {
        	if((CMUtil.appServer.equalsIgnoreCase("websphere"))||(CMUtil.appServer.startsWith("WebSphere"))) 
        	{
        		DefaultMutableTreeNode subNode = new DefaultMutableTreeNode("Configure Login Modules");
        		rootNode.add(subNode);
        	}
        	DefaultMutableTreeNode subNode = new DefaultMutableTreeNode("Deploy Application");
        	rootNode.add(subNode);
        }
        TasksTree.setModel(new DefaultTreeModel(rootNode));;
        TasksTree.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        TasksTree.setDragEnabled(true);
        TasksTree.setDropMode(DropMode.ON);
        TasksTree.setEditable(true);
        TasksTree.setLargeModel(true);
        TasksTree.setMinimumSize(new java.awt.Dimension(50, 50));
        TasksTree.setShowsRootHandles(true);
        TasksTree.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt) {
                ProfileNameMouseClicked(evt);
            }
        });
        TasksTree.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				ProfileNameMousePressed(e);
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
				
			}
		});
        TasksTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent evt){
                ProfileNameValueChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSplitPane1.setAutoscrolls(true);
        jSplitPane1.setOneTouchExpandable(true);
        //jSplitPane1.setDividerLocation((int)jPanel10.getMinimumSize().getWidth());

        jScrollPane3.setViewportView(TasksTree);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(jPanel10);

        jTabbedPane1.setAutoscrolls(true);
        //jScrollPane5.add(jTabbedPane1);
        jScrollPane5.setViewportView(jTabbedPane1);
        
        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
        );

       /* textArea1.setColumns(20);
        textArea1.setRows(5);
        jScrollPane4.setViewportView(textArea1);*/
        
        //consoleOP.setColumns(20);
        //consoleOP.setRows(5);
        jScrollPane4.setViewportView(consoleOP);

        

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel11);

        EnableTask.setText("Enable Task");
        EnableTask.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String loadedFile = filePath+"\\"+profileNameSelected+"\\deployapplication.xml";
				props.XmlDoc(loadedFile, (String)null);
				Element doc1 = props.getDocElem();
				props.setConfigAttr(doc1, "enabled", "true");
				props.saveXMLDoc(loadedFile);
			}
		});
        popMenu.add(EnableTask);
        
        DisableTask.setText("Disable Task");
        DisableTask.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String loadedFile = filePath+"\\"+profileNameSelected+"\\deployapplication.xml";
				props.XmlDoc(loadedFile, (String)null);
				Element doc1 = props.getDocElem();
				props.setConfigAttr(doc1, "enabled", "false");
				props.saveXMLDoc(loadedFile);
			}
        });
        popMenu.add(DisableTask);
        
        
        
        FileMenu.setText("File");

        New.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        New.setMnemonic('N');
        New.setText("New Installation Profile");
        New.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            	CMUtil.scenario = "Fresh";
                ProfDetails2 pd = new ProfDetails2(CMUtil.scenario);
                pd.setVisible(true);
                pd.setLocationRelativeTo(null);
                dispose();
            }
        }
        );
        FileMenu.add(New);
        
        Upgrade.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Upgrade.setMnemonic('U');
        Upgrade.setText("Upgrade Profile");
        Upgrade.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            	upgradeActionPerformed(evt);
            }
        });
        FileMenu.add(Upgrade);
        
        Edit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Edit.setMnemonic('E');
        Edit.setText("Edit Application Server Properties");
        if(CMUtil.profileName.equals(""))
        {
        	Edit.setEnabled(false);
        }
        Edit.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            	editActionPerformed(evt);
            }
        });
        FileMenu.add(Edit);
        
        Open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Open.setMnemonic('O');
        Open.setText("Open Profile");
        Open.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            	openActionPerformed(evt);
            }
        });
        FileMenu.add(Open);
        
        Close.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK|InputEvent.SHIFT_DOWN_MASK));
        Close.setMnemonic('C');
        Close.setText("Close Profile");
        if(CMUtil.profileName.equals(""))
        {
        	Close.setEnabled(false);
        }
        Close.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            	closeActionPerformed(evt);
            }
        });
        FileMenu.add(Close);
        
        FileMenu.add(jSeparator1);

        Exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Exit.setMnemonic('X');
        Exit.setText("Exit");
        Exit.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                System.exit(0);
            }
        });
        FileMenu.add(Exit);

        MenuBar.add(FileMenu);

        WindowMenu.setText("Window");
        MenuBar.add(WindowMenu);

        HelpMenu.setText("Help");
        MenuBar.add(HelpMenu);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            //.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                //.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
    	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame("","").makeUI();
                setDefaultLookAndFeelDecorated(true);
            }
        });
    }
    public void makeUI() {
        final JFrame frame = new JFrame("Sample Fram") {

            @Override
            public void paint(Graphics g) 
            {
                Dimension d = getSize();
                Dimension m = getMaximumSize();
                boolean resize = d.width > m.width || d.height > m.height;
                d.width = Math.min(m.width, d.width);
                d.height = Math.min(m.height, d.height);
                if (resize) {
                    Point p = getLocation();
                    setVisible(false);
                    setSize(d);
                    setLocation(p);
                    setVisible(true);
                }
                super.paint(g);
            }
        };
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);
        setMaximumSize(new Dimension(1000,1000));
        setMinimumSize(new Dimension(500,500));
        setLocationRelativeTo(null);
        setVisible(true);
	}
    public void ProfileNameMouseClicked(MouseEvent evt)
    {
        try{
        	
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)TasksTree.getSelectionPath().getLastPathComponent();
            int selectedNodeIndex = selectedNode.getParent().getIndex(selectedNode);
            String selectedNodeName = selectedNode.getUserObject().toString();
            System.out.println(selectedNodeName);
            TreeSelectionModel model = TasksTree.getSelectionModel();
            if (model.getSelectionCount() > 0) {
                System.out.println(selectedNodeIndex);
                switch (selectedNodeName) {
                    case "Configure GCD JDBC DataSources":
                    {
                    	ConsoleOP.clearText();
                    	if(panel1IsOpen.equals(false)) {
                    	panel1IsOpen = true;
                        String loadedFile = filePath+"\\"+profileNameSelected+"\\configurejdbcgcd.xml";
                        System.out.println(loadedFile);
                        LoadGCD_JDBC lpd = new LoadGCD_JDBC();
                        jPanel5 = lpd.loadFromFile(new File(loadedFile));
                        jTabbedPane1.addTab(selectedNodeName, jPanel5);
                        closeButton(selectedNodeName);
                    	}
                    	else
                    	{
                    		JOptionPane.showMessageDialog(this, "The task " + selectedNodeName + " is already open. Please close it and then try again to open.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    	}
                        break;
                    }
                    case "Configure Object Store JDBC DataSources":
                    {
                    	ConsoleOP.clearText();
                    	if(panel5IsOpen.equals(false)) {
                    	panel5IsOpen = true;
                        String loadedFile = filePath+"\\"+profileNameSelected+"\\configurejdbcos.xml";
                        LoadGCD_JDBC lpd = new LoadGCD_JDBC();
                        jPanel5 = lpd.loadFromFile(new File(loadedFile));
                        jTabbedPane1.addTab(selectedNodeName, jPanel5);
                        closeButton(selectedNodeName);
                    	}
                    	else
                    	{
                    		JOptionPane.showMessageDialog(this, "The task " + selectedNodeName + " is already open. Please close it and then try again to open.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    	}
                        break;
                    }
                    case "Configure Login Modules":
                    {
                    	ConsoleOP.clearText();
                    	if(panel2IsOpen.equals(false)) {
                        panel2IsOpen = true;
                        String loadedFile = filePath+"\\"+profileNameSelected+"\\configureloginmodules.xml";
                        LoadConfigureLogin lpl = new LoadConfigureLogin();
                        System.out.println(loadedFile);
                        jPanel6 = lpl.loadFromFile(new File(loadedFile));
                        jTabbedPane1.addTab(selectedNodeName, jPanel6);
                        closeButton(selectedNodeName);
                    	}
                    	else
                    	{
                    		JOptionPane.showMessageDialog(this, "The task " + selectedNodeName + " is already open. Please close it and then try again to open.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    	}
                        break;
                    }
                    case "Configure LDAP":
                    {
                    	ConsoleOP.clearText();
                    	if(panel3IsOpen.equals(false)) {
                        panel3IsOpen = true;
                        String loadedFile = filePath+"\\"+profileNameSelected+"\\configureldap.xml";
                    	LoadConfigureLDAP lcdp = new LoadConfigureLDAP();
                    	jPanel7 = lcdp.loadFromFile(new File(loadedFile));
                    	jTabbedPane1.addTab(selectedNodeName, jPanel7);
                    	closeButton(selectedNodeName);
                    	}
                    	else
                    	{
                    		JOptionPane.showMessageDialog(this, "The task " + selectedNodeName + " is already open. Please close it and then try again to open.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    	}
                        break;
                    }
                    case "Deploy Application":
                    {
                    	ConsoleOP.clearText();
                    	if(panel4IsOpen.equals(false)) {
                        panel4IsOpen = true;
                        String loadedFile = filePath+"\\"+profileNameSelected+"\\deployapplication.xml";
                        LoadDeploy ldep = new LoadDeploy();
                    	jPanel8 = ldep.loadFromFile(new File(loadedFile));
                    	jTabbedPane1.addTab(selectedNodeName, jPanel8);
                    	closeButton(selectedNodeName);
                    	}
                    	else
                    	{
                    		JOptionPane.showMessageDialog(this, "The task " + selectedNodeName + " is already open. Please close it and then try again to open.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    	}
                        break;
                    }
                    default :
                    	System.out.println("INvalid");
                }
            }
        }
        catch(Exception ex){
            ex.getMessage();
            JOptionPane.showMessageDialog(rootPane, "Please select one of the below tasks.");
        }
    }

    public void ProfileNameMousePressed(MouseEvent evt)
    {
    	if(SwingUtilities.isRightMouseButton(evt))
    	{
    		popMenu.show(TasksTree, evt.getX(), evt.getY());
    	}
    }
    public void ProfileNameValueChanged(TreeSelectionEvent evt)
    {
        setJTreeDynamically(profileNameSelected);
    }

    public void setJTreeDynamically(String profileName) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(this.getParent());
        Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
        hashtable.put (profileName, new String[]{"Configure GCD JDBC DataSources", "Configure Object Store JDBC DataSources", "Configure Login Modules", "Configure LDAP", "Deploy Application"});
        JTree.DynamicUtilTreeNode.createChildren(root, hashtable);
        TasksTree.setVisible(true);
    }
    public void openActionPerformed(ActionEvent evt)
    {
    	if(CMUtil.profileOpen)
	    {
	   		 String msg = new String("Do you want to open another profile?");
	   		 int result = JOptionPane.showConfirmDialog(this, msg, "Action Required",
	   		               JOptionPane.YES_NO_OPTION,
	   		               JOptionPane.QUESTION_MESSAGE);
	   		 if(result == JOptionPane.YES_OPTION)
	   		 {
	   			 CMUtil.profileOpen = false;
	   		 }
	    }
    	while(!CMUtil.profileOpen)
    	{
             CMUtil.profileOpen = true;
             fileChooser = new JFileChooser();
             fileChooser.setCurrentDirectory(new java.io.File(filePath));
             fileChooser.addChoosableFileFilter(new CFGPFileFilter());
             fileChooser.setDialogTitle("Please select an cfgp file");
	         if(fileChooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
	         {
                 String proName="";
                 System.out.println(fileChooser.getSelectedFile());
                 file1 = fileChooser.getSelectedFile();
         		 props.XmlDoc(file1.toString(), (String)null);
         		 Element doc1 = props.getDocElem();
         		 proName = props.getConfigAttr(doc1, "name");
         		 System.out.println("The profile name is "+proName);
         		 CMUtil.profileName = proName;
                 String file2 = filePath+"\\"+proName+"\\applicationserver.xml";
                 System.out.println(file2);
                 CMUtil.loadAppServer(file2);
             	 dispose();
	             new NewJFrame(proName,"").makeUI();
            } 
    	}
    }
    
    public void editActionPerformed(ActionEvent evt)
    {
    	
    }
    public void upgradeActionPerformed(ActionEvent evt)
    {
    	File file = new File("C:\\Program Files\\IBM\\FileNet\\ContentEngine\\Servers.xml");
    	if(file.exists())
    	{
    		CMUtil.scenario = "UpgradeWithServers";
    		Upgrade_Configuration uc = new Upgrade_Configuration();
        	uc.setVisible(true);
        	uc.setLocationRelativeTo(null);
        	setVisible(false);
    	}
    	else
    	{
    		CMUtil.scenario = "UpgradeWithoutServers";
    		String msg = new String("Configuration Manager did not find information related to your existing Content Engine.\n"
    				+ "You can proceed by verifying and completing all the required fields in the upgrade wizard\n"
    				+ "and then completing the required upgrade tasks. If you are uncertain about what information\n"
    				+ "to enter, refer to the Upgrading and  configuring Content Platform Engine topic in the online\n"
    				+ "documentation : \nhttps://www.ibm.com/docs/en/filenet-p8-platform/5.5.11?topic=p8-upgrading-configuring-\ncontent-platform-engine.\n"
    				+ "\n\nDo you want to continue?");
    		int result = JOptionPane.showConfirmDialog(this, msg, "Action Required",
    	               JOptionPane.YES_NO_OPTION,
    	               JOptionPane.QUESTION_MESSAGE);
    		if(result == JOptionPane.YES_OPTION)
    		{
    			ProfDetails2 pd = new ProfDetails2(CMUtil.scenario);
    			pd.setVisible(true);
    			pd.setLocationRelativeTo(null);
    			setVisible(false);
    			/*UpgradeConfiguration_server ucs = new UpgradeConfiguration_server();
            	ucs.setVisible(true);
            	ucs.setLocationRelativeTo(null);
            	setVisible(false);*/
            }
    		else if (result == JOptionPane.NO_OPTION)
    		{
               
            }
    		else 
    		{
                System.out.println("Wrong selection");
            }
    	}
    }
    public void closeActionPerformed(ActionEvent evt)
    {
    	//btnClose.addActionListener(new MyCloseActionHandler());
    	CMUtil.profileOpen = false;
    	new NewJFrame("","").makeUI();
    	if(panel1IsOpen)
    		panel1IsOpen = false;
    	if(panel2IsOpen)
    		panel2IsOpen = false;
    	if(panel3IsOpen)
    		panel3IsOpen = false;
    	if(panel4IsOpen)
    		panel4IsOpen = false;
    	if(panel5IsOpen)
    		panel5IsOpen = false;
    	dispose();
    }
    public void closeButton(String title)
    {
    	int index = jTabbedPane1.indexOfTab(title);
        JPanel pnlTab = new JPanel(new GridBagLayout());
        pnlTab.setOpaque(false);
        JLabel lblTitle = new JLabel(title);
        btnClose = new JButton("x");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;

        pnlTab.add(lblTitle, gbc);

        gbc.gridx++;
        gbc.weightx = 0;
        pnlTab.add(btnClose, gbc);

        jTabbedPane1.setTabComponentAt(index, pnlTab);

        btnClose.addActionListener(new MyCloseActionHandler());
    }
    public class MyCloseActionHandler implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt) 
        {
        	if(panel1IsOpen)
        		panel1IsOpen = false;
        	if(panel2IsOpen)
        		panel2IsOpen = false;
        	if(panel3IsOpen)
        		panel3IsOpen = false;
        	if(panel4IsOpen)
        		panel4IsOpen = false;
        	if(panel5IsOpen)
        		panel5IsOpen = false;
            Component selected = jTabbedPane1.getSelectedComponent();
            if (selected != null) 
            {
                jTabbedPane1.remove(selected);
                // It would probably be worthwhile getting the source
                // casting it back to a JButton and removing
                // the action handler reference ;)
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenu WindowMenu;
    private javax.swing.JMenu HelpMenu;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem New;
    private javax.swing.JMenuItem Open;
    private javax.swing.JMenuItem Exit;
    private JMenuItem EnableTask;
    private JMenuItem DisableTask;
    private JMenuItem Upgrade;
    private JMenuItem Edit;
    private JMenuItem Close;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private JPopupMenu popMenu;
    private JButton btnClose;
    //private javax.swing.JTextArea textArea1;
    private ConsoleOP consoleOP;
    private javax.swing.JTree TasksTree;
    private JFileChooser fileChooser;
    private File file1;
    PropertyFactory props = new PropertyFactory();
    private String appServer = "";
    public static Boolean panel1IsOpen = false,panel2IsOpen = false,panel3IsOpen = false,panel4IsOpen = false,panel5IsOpen = false;
    // End of variables declaration//GEN-END:variables
}
