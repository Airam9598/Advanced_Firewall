package view;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Refactor.Bock_port;
import Refactor.Create_list_model;
import Refactor.go_to_url;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.Window.Type;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class advfirewall {

	private JFrame frmAdvancedFirewallV;
	private JTable tabla;
	private Timer timer;
	private TimerTask timerTask;
	private JComboBox<String> comboBox,comboBox_1;
	private JCheckBox chckbxNewCheckBox,chckbxConexionesDelSistema;
	private JPanel panel;
	private Create_list_model clist;

	public advfirewall(String version) {
		clist = new Create_list_model();
		initialize(version);
		frmAdvancedFirewallV.setVisible(true);
	}

	private void initialize(String version) {
		frmAdvancedFirewallV = new JFrame();
		frmAdvancedFirewallV.setResizable(false);
		frmAdvancedFirewallV.setType(Type.POPUP);
		frmAdvancedFirewallV.setTitle(version);
		frmAdvancedFirewallV.setBounds(100, 100, 729, 674);
		frmAdvancedFirewallV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdvancedFirewallV.getContentPane().setLayout(null);
		
		JButton btnUpdate = new JButton("Recargar");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabla.setModel(clist.create(comboBox_1,comboBox,chckbxNewCheckBox,chckbxConexionesDelSistema));
			}
		});
		
		
		
		btnUpdate.setBounds(565, 498, 143, 51);
		frmAdvancedFirewallV.getContentPane().add(btnUpdate);
		JButton btnShow = new JButton("Buscar ip");
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tabla.getSelectedRowCount()==1) {
					new go_to_url().goToURL("https://iplookup.flagfox.net/?ip="+tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
				}
			}
		});
		btnShow.setBounds(10, 585, 136, 51);
		frmAdvancedFirewallV.getContentPane().add(btnShow);
		JLabel lblEncendido = new JLabel("ENCENDIDO");
		lblEncendido.setHorizontalAlignment(SwingConstants.CENTER);
		lblEncendido.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton Parar = new JButton("Parar");
		Parar.setFont(new Font("Tahoma", Font.BOLD, 13));
		Parar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				if(lblEncendido.getText()=="ENCENDIDO") {
					timer.cancel();
					lblEncendido.setText("APAGADO");
					panel.setBackground(new Color(220, 20, 60));
					Parar.setText("Encender");
				}else {
					timerTask = new TimerTask() 
				     { 
				         public void run()  
				         { 
				        	 tabla.setModel(clist.create(comboBox_1,comboBox,chckbxNewCheckBox,chckbxConexionesDelSistema));
				         } 
				     }; 

				      timer = new Timer(); 
				     timer.scheduleAtFixedRate(timerTask, 0, 5000);
					lblEncendido.setText("ENCENDIDO");
					panel.setBackground(new Color(50, 205, 50));
					Parar.setText("Parar");
				}
				
			}
		});
		Parar.setBounds(565, 437, 143, 51);
		frmAdvancedFirewallV.getContentPane().add(Parar);
		
		
		lblEncendido.setBounds(593, 46, 94, 14);
		frmAdvancedFirewallV.getContentPane().add(lblEncendido);
		
		JButton btnBloquearAcceso = new JButton("Bloquear");
		btnBloquearAcceso.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBloquearAcceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {timer.cancel();}catch (Exception e) {}
				Bock_port.block_port(tabla.getValueAt(tabla.getSelectedRow(), 2).toString(),tabla.getValueAt(tabla.getSelectedRow(), 3).toString());
				timer = new Timer(); 
			    timer.scheduleAtFixedRate(timerTask, 0, 10000);
			}
		});
		btnBloquearAcceso.setBounds(337, 437, 107, 51);
		frmAdvancedFirewallV.getContentPane().add(btnBloquearAcceso);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 70, 708, 356);
		frmAdvancedFirewallV.getContentPane().add(scrollPane);
		
		 tabla = new JTable (new DefaultTableModel(
		 	new Object[][] {
		 		{"PID", "Protocolo", "IP Remota", "Puerto Remoto", "Estado","Ejecutable"},
		 	},
		 	new String[] {
		 		"", "", "", "", "", "",
		 	}
		 ));
		 tabla.setModel(new DefaultTableModel(
		 	new Object[][] {
		 		{"PID", "Protocolo", "IP Remota", "Puerto Remoto", "Estado", "Ejecutable"},
		 	},
		 	new String[] {
		 		"", "", "", "", "", ""
		 	}
		 ));
		 tabla.setFont(new Font("Tahoma", Font.BOLD, 12));
		 tabla.setFillsViewportHeight(true);
		 scrollPane.setViewportView(tabla);
		 tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		 JLabel label = new JLabel("C0V3R5UN");
		 label.setHorizontalAlignment(SwingConstants.CENTER);
		 label.setFont(new Font("Segoe Script", Font.BOLD, 15));
		 label.setBounds(570, 618, 128, 18);
		 frmAdvancedFirewallV.getContentPane().add(label);
		 
		  comboBox = new JComboBox<String>();
		  comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		 comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"TODOS", "ESTABLISHED", "TIME_WAIT", "CLOSE_WAIT"}));
		 comboBox.setBounds(406, 34, 113, 20);
		 frmAdvancedFirewallV.getContentPane().add(comboBox);
		 
		 JLabel lblNewLabel = new JLabel("Estado:");
		 lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		 lblNewLabel.setBounds(406, 11, 113, 14);
		 frmAdvancedFirewallV.getContentPane().add(lblNewLabel);
		 
		 JLabel lblProtocolo = new JLabel("Puerto Local:");
		 lblProtocolo.setHorizontalAlignment(SwingConstants.CENTER);
		 lblProtocolo.setFont(new Font("Tahoma", Font.BOLD, 13));
		 lblProtocolo.setBounds(254, 9, 107, 14);
		 frmAdvancedFirewallV.getContentPane().add(lblProtocolo);
		 
		  comboBox_1 = new JComboBox<String>();
		  comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		  comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"TODOS"}));
		 comboBox_1.setBounds(254, 34, 107, 20);
		 frmAdvancedFirewallV.getContentPane().add(comboBox_1);
		 
		  chckbxNewCheckBox = new JCheckBox("Conexiones Locales");
		  chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		 chckbxNewCheckBox.setBounds(6, 9, 152, 23);
		 frmAdvancedFirewallV.getContentPane().add(chckbxNewCheckBox);
		 
		  panel = new JPanel();
		  panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		  panel.setBackground(new Color(50, 205, 50));
		 panel.setBounds(593, 14, 94, 30);
		 frmAdvancedFirewallV.getContentPane().add(panel);
		 
		 JLabel lblIpLocal = new JLabel("IP Local:");
		 lblIpLocal.setHorizontalAlignment(SwingConstants.LEFT);
		 lblIpLocal.setFont(new Font("Tahoma", Font.BOLD, 15));
		 lblIpLocal.setBounds(10, 437, 240, 18);
		 frmAdvancedFirewallV.getContentPane().add(lblIpLocal);
		 
		 JLabel lblPuertoLocal = new JLabel("Puerto Local:");
		 lblPuertoLocal.setHorizontalAlignment(SwingConstants.LEFT);
		 lblPuertoLocal.setFont(new Font("Tahoma", Font.BOLD, 15));
		 lblPuertoLocal.setBounds(10, 465, 240, 18);
		 frmAdvancedFirewallV.getContentPane().add(lblPuertoLocal);
		 
		 JLabel lblEjecutable = new JLabel("Ejecutable:");
		 lblEjecutable.setHorizontalAlignment(SwingConstants.LEFT);
		 lblEjecutable.setFont(new Font("Tahoma", Font.BOLD, 15));
		 lblEjecutable.setBounds(10, 498, 251, 18);
		 frmAdvancedFirewallV.getContentPane().add(lblEjecutable);
		 
		 chckbxConexionesDelSistema = new JCheckBox("Conexiones del Sistema");
		 chckbxConexionesDelSistema.setFont(new Font("Tahoma", Font.BOLD, 13));
		 chckbxConexionesDelSistema.setBounds(6, 36, 196, 23);
		 frmAdvancedFirewallV.getContentPane().add(chckbxConexionesDelSistema);
		 tabla.setModel(clist.create(comboBox_1,comboBox,chckbxNewCheckBox,chckbxConexionesDelSistema));
		timerTask = new TimerTask() 
	     { 
	         public void run()  
	         { 
	        	 tabla.setModel(clist.create(comboBox_1,comboBox,chckbxNewCheckBox,chckbxConexionesDelSistema));
	         } 
	     }; 

	      timer = new Timer(); 
	     timer.scheduleAtFixedRate(timerTask, 0, 5000);
	     TimerTask timerTask2 = new TimerTask() 
	     { 
	         public void run()  
	         { 
	        	 if(tabla.getSelectedRowCount()==1) {
	        		 clist.Calculate(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
	        		 lblIpLocal.setText(clist.get_local());
	    	       	 lblPuertoLocal.setText(clist.get_Local_port());
	    	         lblEjecutable.setText(clist.get_ejecutable());
	        	 } 
	         } 
	     }; 

	     Timer timer2 = new Timer(); 
	     timer2.scheduleAtFixedRate(timerTask2, 0, 500);
	}
}
