package Refactor;

import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import model.Win_process;

public class Create_list_model {
	private DefaultTableModel modelo;
	private HashMap<Integer,Win_process> map;
	private Win_process process;
	public Create_list_model() {
		this.modelo = new DefaultTableModel(new Object[][] {
		{"PID", "Protocolo", "IP Remota", "Puerto Remoto", "Estado","Ejecutable"}},
		new String[] {"", "", "", "","",""}
		);
	}
	public DefaultTableModel create(JComboBox<String> report,JComboBox<String> estate,JCheckBox local,JCheckBox system) {
		Add_process(report,estate,local,system);
		return modelo;
	}
	
	public void Calculate(String pid) {
		for(Win_process proc:map.values()) {
			if(proc.getPID().equals(pid)) {
				process=proc;
				break;
			}
		 }
	}
	
	public String get_local() {
		return "IP Local: "+process.getLocalip();
	}

	public String get_Local_port() {
		return "Puerto Local: "+process.getLocalport();
	}
	
	public String get_ejecutable() {
		return "Ejecutable: "+process.getExecutable();
	}
	
	private void Add_process(JComboBox<String> report,JComboBox<String> estate,JCheckBox local,JCheckBox system) {
		HashMap<Integer, Win_process>procesmap=new HashMap<Integer, Win_process>();
		procesmap=new Get_process().get_process();
		map=procesmap;
		if(!procesmap.isEmpty()){
			/*if(modelo.getRowCount()!=1) {
				for(int count = 1; count < modelo.getRowCount(); count++){
					boolean exist=false;
					for(Win_process proc: procesmap.values()) {
						if(modelo.getValueAt(count, 0).toString().contains(proc.getPID())) exist=true; break;
					}
					if(exist==false)comboBox_1.removeItem(modelo.getValueAt(count, 0));
			 	}
			}*/
			//comboBox_1.removeAllItems();
			modelo.setRowCount(1);
			for(Win_process proc: procesmap.values()) {
				boolean exist=false;
				for(int j=0;j<report.getItemCount();j++) {
					if(report.getItemAt(j).toString().contains(proc.getRemoteport())) {
						exist=true; 
						break;
					}
				}
				if(!exist) report.addItem(proc.getRemoteport());
				if(!local.isSelected() && proc.getLocalip().contains("127.0.0.1"))continue;
				if(!system.isSelected() && proc.getPID().equals("0"))continue;
				if(estate.getSelectedItem().toString().contains("TODOS")){
					if(report.getSelectedItem().toString().contains("TODOS")){
						add(proc);
					}else if (report.getSelectedItem().toString().contains(proc.getRemoteport())){
						add(proc);
					}
				}else if (estate.getSelectedItem().toString().contains(proc.getEstado())){
					if(report.getSelectedItem().toString().contains("TODOS")){
						add(proc);
					}else if (report.getSelectedItem().toString().contains(proc.getRemoteport())){
						add(proc);
					}		
				}
			}
		}else {
			System.out.println("sdfs");
		}
	}
	private void add(Win_process proc) {
		String[]datos={proc.getPID(),proc.getProtocolo(),proc.getRemoteip(),proc.getRemoteport(),proc.getEstado(),proc.getExecutable()};
		modelo.addRow(datos);
	}

}
