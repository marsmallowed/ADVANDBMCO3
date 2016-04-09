package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import controller.db.Controller;

public class ReadPanel extends JPanel {
	private JTable tableResults;
	private JScrollPane tableScroll;
	
	public ReadPanel(Controller controller) {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblTableResults = new JLabel("Table Results");
		add(lblTableResults, BorderLayout.NORTH);
		
		// TODO: set contents of tableResults
		tableResults = new JTable();
		tableScroll = new JScrollPane(tableResults);
		tableScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(tableScroll, BorderLayout.SOUTH);
	}

}
