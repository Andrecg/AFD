package AFD;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Gui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table_3;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
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
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{327, 97, 0};
		gbl_contentPane.rowHeights = new int[]{20, 231, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		textField = new JTextField();
		
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.NORTH;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {"WORD","TYPE"
			}
		));
		GridBagConstraints gbc_table_3 = new GridBagConstraints();
		gbc_table_3.gridwidth = 2;
		gbc_table_3.insets = new Insets(0, 0, 0, 5);
		gbc_table_3.fill = GridBagConstraints.BOTH;
		gbc_table_3.gridx = 0;
		gbc_table_3.gridy = 1;
		contentPane.add(table_3, gbc_table_3);
		JScrollPane scrollPane = new JScrollPane(table_3);
	    contentPane.add(scrollPane, gbc_table_3);
	     
		JButton btnNewButton = new JButton("Choose file...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "ALG", "alg");
				chooser.setFileFilter(filter);
				chooser.setCurrentDirectory(new File("Visualg Files\\"));
				int returnVal = chooser.showOpenDialog(btnNewButton.getParent());
				if(returnVal == JFileChooser.APPROVE_OPTION) {
				     textField.setText(chooser.getSelectedFile().getName());
				     Automata aut = new Automata(chooser.getSelectedFile().getPath());				    
				     aut.find();
				     String model[] = {"WORD","TYPE"};
				     table_3.setModel(new DefaultTableModel(model,(int)aut.getType().size()) {
				    	 @Override
				    	 public boolean isCellEditable(int row, int column) {
				    		 return false;
				    	 }
				     });
				     for(int i = 0; i < aut.getWord().size(); i++) {
				    	 table_3.setValueAt(aut.getWord().toArray()[i], i, 0);
				    	 table_3.setValueAt(aut.getType().toArray()[i], i, 1);
				     }
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
	}
}
