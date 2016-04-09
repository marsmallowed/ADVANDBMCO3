package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

import controller.db.Controller;

import java.awt.GridLayout;
import javax.swing.JTable;
import java.awt.BorderLayout;

public class ReadWritePanel extends JPanel {
	private JTable beforeTable;
	private JScrollPane beforeScroll;
	private JTable afterTable;
	private JScrollPane afterScroll;
	
	public ReadWritePanel(Controller controller) {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel beforePanel = new JPanel();
		add(beforePanel);
		beforePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblBefore = new JLabel("Before");
		beforePanel.add(lblBefore, BorderLayout.NORTH);
		
		// TODO: set contents of beforeTable (use controller and use query)
		beforeTable = new JTable();
		beforeScroll = new JScrollPane(beforeTable);
		beforeScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		beforeScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		beforePanel.add(beforeScroll);
		
		JPanel afterPanel = new JPanel();
		add(afterPanel);
		afterPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAfter = new JLabel("After");
		afterPanel.add(lblAfter, BorderLayout.NORTH);
		
		// TODO: set contents of afterTable
		afterTable = new JTable();
		afterScroll = new JScrollPane(afterTable);
		afterScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		afterScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		afterPanel.add(afterScroll, BorderLayout.CENTER);
		
	}

}
