package mainApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dbutilities.DatabaseConnection;

public class UserData extends JFrame implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel pane = new JPanel(new BorderLayout());
	private JTable table;
	private JLabel image = new JLabel();
	private Connection con;
	private PreparedStatement pst;
	String[] x={"Username", "Password", "Division", "Gender"};
	String[][] y = new String[30][5];
	int i=0,j=0;
	public UserData(){
		setVisible(true);
		setTitle("Employee Data");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 200, 800, 400);
		
		table = new JTable();
		table.setRowHeight(40);
		table.setRowMargin(20);
		pane.add(table, BorderLayout.CENTER);
		
		String q = "select * from User";
		DatabaseConnection co = new DatabaseConnection();
		con = co.getConnection();
		try {
			this.pst = con.prepareStatement(q);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				y[i][j++] = rs.getString("username");
				y[i][j++] = rs.getString("password");
				y[i][j++] = rs.getString("division");
				y[i][j++] = rs.getString("gender");
				i++;
				j=0;		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}finally{
			try{
				con.close();
				pst.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		DefaultTableModel model = new DefaultTableModel(y, x);
		table.setModel(model);
		JScrollPane s = new JScrollPane(table);
		s.setPreferredSize(new Dimension(480, 300));
		
		table.addMouseListener(this);
		pane.add(s, BorderLayout.CENTER);
		image.setPreferredSize(new Dimension(0,0));
		pane.add(image, BorderLayout.LINE_END);
		add(pane, BorderLayout.CENTER);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		try{
			int row = table.getSelectedRow();
			String table_Click = (table.getModel().getValueAt(row, 0).toString());
			String sql = "Select productImage from User where username='"+table_Click+"'";
			DatabaseConnection conn = new DatabaseConnection();
			con = conn.getConnection();
			pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				byte []b = rs.getBytes("pimage");
				ImageIcon i = new ImageIcon(b);
				image.setIcon(i);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				pst.close();
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("clicked");
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
	public static void main(String[] args) {
		new UserData().setVisible(true);
	}
}
