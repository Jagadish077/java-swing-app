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

public class MobileData extends JFrame implements MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel pane = new JPanel(new BorderLayout());
	private JTable table;
	private JLabel image = new JLabel();
	private Connection con;
	private PreparedStatement pst;
	String[] x={"Name", "Price", "Model", "Quantity", "Ram", "Rom", "Processor","Battery","Color"};
	String[][] y = new String[30][10];
	int i=0,j=0;
	public MobileData(){
		setVisible(true);
		setTitle("Mobile Data");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 200, 1000, 1000);
		
		table = new JTable();
		table.setRowHeight(50);
		table.setRowMargin(30);
		pane.add(table, BorderLayout.CENTER);
		
		String q = "select * from product";
		DatabaseConnection co = new DatabaseConnection();
		con = co.getConnection();
		try {
			this.pst = con.prepareStatement(q);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				y[i][j++] = rs.getString("productName");
				y[i][j++] = rs.getString("productPrice");
				y[i][j++] = rs.getString("productModelNumber");
				y[i][j++] = rs.getString("productQty");
				y[i][j++] = rs.getString("productRam");
				y[i][j++] = rs.getString("productRom");
				y[i][j++] = rs.getString("productProcessor");
				y[i][j++] = rs.getString("batteryDetails");
				y[i][j++] = rs.getString("productColor");
				
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
		image.setPreferredSize(new Dimension(200,200));
		pane.add(image, BorderLayout.LINE_END);
		add(pane, BorderLayout.CENTER);
		}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		try{
			int row = table.getSelectedRow();
			String table_Click = (table.getModel().getValueAt(row, 0).toString());
			System.out.println(table_Click);
			String sql = "Select productImage from product where productName='"+table_Click+"'";
			DatabaseConnection conn = new DatabaseConnection();
			con = conn.getConnection();
			pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				byte []b = rs.getBytes("productImage");
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
		MobileData m = new MobileData();
		m.setVisible(true);
	}

}
