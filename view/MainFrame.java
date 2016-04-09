package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import controller.db.Controller;
import model.beans.Node;
import model.beans.SelfNode;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame {
	
	private JPanel transactPanel;
	private JLabel lblStatus;
	private JTable resultsTable;
	private JScrollPane tableScroll;
	private JTabbedPane tabbedPane;
	private JPanel queryListPanel;
	private JList<?> queryList;
	private JScrollPane queryListScroll;
	private JPanel readPanel;
	private JPanel writePanel;
	private JLabel lblQueryList;
	private JList listQuery;
	private JPanel buttonsPanel;
	private JButton btnDelete;
	private JButton btnExecute;
	private JPanel displayPanel;
	private JPanel processLogPanel;
	private JTextArea txtProcessLog;
	private JTabbedPane transTabbedPane;
	private JLabel lblProcessLog;
	private JScrollPane logScroll;
	private JLabel lblWrite;
	private JLabel lblRead;
	private JPanel paramPanel;
	private JComboBox readNodeCombo;
	private JComboBox hpq_idCombo;
	private JComboBox isolationCombo;
	private JButton btnAddRead;
	private JLabel lblNode;
	private JLabel lblHpqID;
	private JLabel lblIsolation;
	private JButton btnAddWrite;
	private JPanel paramPanel2;
	private JLabel lblNode2;
	private JComboBox writeNodeCombo;
	private JComboBox hpq_idCombo2;
	private JLabel lblHpqID2;
	private JLabel lblVolume;
	private JTextField txtVolume;
	
	private DefaultListModel listModel;
	private JPanel sendLogPanel;
	private JButton btnSendLog;
	
	public MainFrame(final Controller controller, final SelfNode self) {
		setTitle("ADVANDB MCO3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(757, 604);
		setLocationRelativeTo(null);
		
		lblStatus = new JLabel(self.getNodeName() + " - " + self.getConnectionStatus());
		getContentPane().add(lblStatus, BorderLayout.NORTH);
		
		String[] nodes = new String[self.getFellowNodes().size() + 1];
		nodes[0] = self.getNodeName();
		for (int i=0; i < nodes.length - 1; i++) {
			nodes[i+1] = self.getFellowNodes().get(i).getNodeName();
		}
		
		String[] isolationLevels = {"READ UNCOMMITTED", "READ COMMITTED", "REPEATABLE READ", "SERIALIZABLE"};
		
		transactPanel = new JPanel();
		getContentPane().add(transactPanel, BorderLayout.WEST);
		transactPanel.setLayout(new GridLayout(3, 1, 0, 5));
		
		readPanel = new JPanel();
		readPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		transactPanel.add(readPanel);
		readPanel.setLayout(new BorderLayout(0, 0));
		
		lblRead = new JLabel("Read");
		lblRead.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRead.setHorizontalAlignment(SwingConstants.LEFT);
		readPanel.add(lblRead, BorderLayout.NORTH);
		
		paramPanel = new JPanel();
		readPanel.add(paramPanel, BorderLayout.CENTER);
		paramPanel.setLayout(new GridLayout(0, 2, 0, 5));
		
		lblNode = new JLabel("Node:");
		paramPanel.add(lblNode);
		readNodeCombo = new JComboBox(nodes);
		paramPanel.add(readNodeCombo);
		
		lblHpqID = new JLabel("hpq_id:");
		paramPanel.add(lblHpqID);
		
		hpq_idCombo = new JComboBox(controller.getHpqID());
		paramPanel.add(hpq_idCombo);
		
		lblIsolation = new JLabel("Isolation Level:");
		paramPanel.add(lblIsolation);
		isolationCombo = new JComboBox(isolationLevels);
		paramPanel.add(isolationCombo);
		
		btnAddRead = new JButton("Add");
		readPanel.add(btnAddRead, BorderLayout.SOUTH);
		
		writePanel = new JPanel();
		transactPanel.add(writePanel);
		writePanel.setLayout(new BorderLayout(0, 0));
		
		lblWrite = new JLabel("Write");
		lblWrite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblWrite.setHorizontalAlignment(SwingConstants.LEFT);
		writePanel.add(lblWrite, BorderLayout.NORTH);
		
		btnAddWrite = new JButton("Add");
		writePanel.add(btnAddWrite, BorderLayout.SOUTH);
		
		paramPanel2 = new JPanel();
		writePanel.add(paramPanel2);
		paramPanel2.setLayout(new GridLayout(0, 2, 0, 5));
		
		lblNode2 = new JLabel("Node:");
		paramPanel2.add(lblNode2);
		
		writeNodeCombo = new JComboBox(nodes);
		paramPanel2.add(writeNodeCombo);
		
		lblHpqID2 = new JLabel("hpq_id:");
		paramPanel2.add(lblHpqID2);
		
		hpq_idCombo2 = new JComboBox(controller.getHpqID());
		paramPanel2.add(hpq_idCombo2);
		
		lblVolume = new JLabel("Crop Volume:");
		paramPanel2.add(lblVolume);
		
		txtVolume = new JTextField();
		paramPanel2.add(txtVolume);
		txtVolume.setColumns(10);
		
		// TODO: send log part (also threads)
		sendLogPanel = new JPanel();
		transactPanel.add(sendLogPanel);
		
		btnSendLog = new JButton("Send Log");
		sendLogPanel.add(btnSendLog);
		
		transTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(transTabbedPane, BorderLayout.CENTER);
		transTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		displayPanel = new JPanel();
		getContentPane().add(displayPanel, BorderLayout.SOUTH);
		displayPanel.setLayout(new GridLayout(0, 2, 5, 0));
		
		queryListPanel = new JPanel();
		displayPanel.add(queryListPanel);
		queryListPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		queryListPanel.setLayout(new BorderLayout(0, 0));
		
		lblQueryList = new JLabel("Query List:");
		queryListPanel.add(lblQueryList, BorderLayout.NORTH);
		
		queryListScroll = new JScrollPane();
		listQuery = new JList();
		queryListScroll.setViewportView(listQuery);
		queryListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		queryListPanel.add(queryListScroll, BorderLayout.CENTER);
		
		buttonsPanel = new JPanel();
		queryListPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		btnDelete = new JButton("Delete");
		buttonsPanel.add(btnDelete);
		
		btnExecute = new JButton("Execute");
		buttonsPanel.add(btnExecute);
		
		processLogPanel = new JPanel();
		displayPanel.add(processLogPanel);
		processLogPanel.setLayout(new BorderLayout(0, 0));
		
		lblProcessLog = new JLabel("Process Log:");
		processLogPanel.add(lblProcessLog, BorderLayout.NORTH);
		
		txtProcessLog = new JTextArea();
		txtProcessLog.setEditable(false);
		logScroll = new JScrollPane(txtProcessLog);
		logScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		logScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		processLogPanel.add(logScroll, BorderLayout.CENTER);
		
		listModel = new DefaultListModel();
		
		/** ACTION LISTENERS HERE */
		
		txtVolume.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
			      if (!((c >= '0') && (c <= '9') ||
			         (c == KeyEvent.VK_BACK_SPACE) ||
			         (c == KeyEvent.VK_DELETE))) {
			        getToolkit().beep();
			        e.consume();
			      }
			}
		});
		
		btnAddRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String queryTitle = readNodeCombo.getSelectedItem() + " - " 
									+ isolationCombo.getSelectedItem() + " - "
									+ hpq_idCombo.getSelectedItem();
				listModel.addElement(queryTitle);
				listQuery = new JList(listModel);
				queryListScroll.setViewportView(listQuery);
				queryListScroll.repaint();
				
				// TODO: Set a query list and add these into the ? parameters.
			}
		});
		
		btnAddWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtVolume.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid input.");
				} else {
					String queryTitle = readNodeCombo.getSelectedItem() 
							+ " - UPDATE - "
							+ txtVolume.getText().toString();
					
					listModel.addElement(queryTitle);
					listQuery = new JList(listModel);
					queryListScroll.setViewportView(listQuery);
					queryListScroll.repaint();
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] toDelete = listQuery.getSelectedIndices();
				
				for (int i = 0; i < toDelete.length; i++) {
					listModel.remove(i);
					listQuery = new JList(listModel);
					queryListScroll.setViewportView(listQuery);
				}
				
				queryListScroll.repaint();
			}
		});
		
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean modeWrite = false;
				
				ArrayList<String> listContents = new ArrayList<String>();
				for (int i = 0; i < listModel.getSize(); i++) {
					listContents.add(listModel.getElementAt(i).toString());
					if (listModel.getElementAt(i).toString().contains("UPDATE"))
						modeWrite = true;
				}
				
				if (modeWrite)
					transTabbedPane.addTab("Transaction " + transTabbedPane.getTabCount()+1, new ReadWritePanel(controller));
				else
					transTabbedPane.addTab("Transaction " + transTabbedPane.getTabCount()+1, new ReadPanel(controller));
				
				// TODO: implement live progress log displayed in txtProcessLog 
				
				repaint();
				revalidate();
			}
		});
	}
}
