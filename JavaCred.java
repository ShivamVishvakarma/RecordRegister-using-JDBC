import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaCred {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTextField txtbId;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCred window = new JavaCred();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCred() {
		initialize();
		connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	
	public void connect()
	{
		try
		{
			//load the driver:
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//creating a connection:
			String url = "jdbc:mysql://localhost:3306/JavaCred";
			String username = "root";
			String password = "shivam";
			 con = DriverManager.getConnection(url , username , password );
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void table_load()
	{
		try {
			pstmt = con.prepareStatement("Select * from book");
			rs = pstmt.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception e2)
		{
			e2.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1131, 549);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BookShop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(430, -18, 208, 99);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 63, 475, 341);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("BookName");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(12, 50, 144, 37);
		panel.add(lblNewLabel_1, BorderLayout.NORTH);
		
		txtbname = new JTextField();
		txtbname.setBounds(166, 62, 144, 19);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(12, 119, 144, 37);
		panel.add(lblNewLabel_1_1);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(166, 131, 144, 19);
		panel.add(txtedition);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(12, 189, 144, 37);
		panel.add(lblNewLabel_1_1_1);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(166, 201, 144, 19);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname ,edition , price;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				
				try {
					pstmt = con.prepareStatement("insert into book(name , edition , price) values(? ,?,?)");
					pstmt.setString(1, bname);
					pstmt.setString(2, edition);
					pstmt.setString(3, price);
					
					pstmt.executeUpdate();
					
					JOptionPane.showMessageDialog(null , "Record Added");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					
					txtbname.requestFocus();
					
				}catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(12, 257, 101, 58);
		panel.add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(166, 257, 101, 58);
		panel.add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtbname.setText("");
            	txtedition.setText("");
                txtprice.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBounds(318, 257, 101, 58);
		panel.add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(5, 432, 475, 58);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("BookId");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(21, 10, 89, 39);
		panel_1.add(lblNewLabel_2);
		
		txtbId = new JTextField();
		txtbId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
			          
		            String id = txtbId.getText();

	pstmt = con.prepareStatement("select name,edition,price from book where id = ?");
		                pstmt.setString(1, id);
		                ResultSet rs = pstmt.executeQuery();

		            if(rs.next()==true)
		            {
		              
		                String name = rs.getString(1);
		                String edition = rs.getString(2);
		                String price = rs.getString(3);
		                
		                txtbname.setText(name);
		                txtedition.setText(edition);
		                txtprice.setText(price);
		                
		                
		            }   
		            else
		            {
		            	txtbname.setText("");
		            	txtedition.setText("");
		                txtprice.setText("");
		                 
		            }
				
		            
				
			}catch(Exception e3)
				{
				e3.printStackTrace();
				}
			}
	
		});
		txtbId.setColumns(10);
		txtbId.setBounds(120, 22, 218, 19);
		panel_1.add(txtbId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(540, 58, 482, 347);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname ,edition , price,bid;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid = txtbId.getText();
				
				
				try {
					pstmt = con.prepareStatement("update book set name = ? , edition = ? , price = ? where id = ?");
					pstmt.setString(1, bname);
					pstmt.setString(2, edition);
					pstmt.setString(3, price);
					pstmt.setString(4, bid);
					
					pstmt.executeUpdate();
					
					JOptionPane.showMessageDialog(null , "Record Updated!");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					
					txtbname.requestFocus();
					
				}catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(610, 432, 121, 58);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Delete");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String bid;
				
				bid = txtbId.getText();
				
				
				try {
					pstmt = con.prepareStatement("Delete from book where id = ?");
					
					pstmt.setString(1, bid);
					
					pstmt.executeUpdate();
					
					JOptionPane.showMessageDialog(null , "Record Deleted!!");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					
					txtbname.requestFocus();
					
				}catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1_1.setBounds(804, 432, 121, 58);
		frame.getContentPane().add(btnNewButton_1_1);
	}
}
