import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Perso extends JPanel{

	Vector<String> pers, perso;
	String[] filelist;
	File path;

	public Perso() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		pers=new Vector<String>();
		perso=new Vector<String>();
		path = new File("Perso");
		filelist = path.list();
		for(int i=0; i<filelist.length;i++){
			pers.add("Perso/"+filelist[i]);
			perso.add(filelist[i]);
		}
		JList<String> list= new JList<String>(perso);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				int idx=list.getSelectedIndex();
				if(idx >=0){
					if((path=new File(pers.get(idx))).isDirectory() && idx+1<pers.size() && (pers.get(idx+1).substring(0,pers.get(idx).length()).equals(pers.get(idx)))){
						while(idx+1<pers.size() && pers.get(idx+1).substring(0,pers.get(idx).length()).equals(pers.get(idx))){
							pers.remove(idx+1);
							perso.remove(idx+1);
						}
					}else if(path.isDirectory()){
						filelist=path.list();
						String res="";
						for (int i = pers.get(idx).endsWith("/")? pers.get(idx).split("/").length : (pers.get(idx).split("/").length - 1); i>=0; i--){
							res+="  ";
						}
						for(int i=0;i<filelist.length;i++){
							pers.add(idx+1+i, pers.get(idx)+"/"+filelist[i]);
							perso.add(idx+1+i,res+filelist[i]);
						}
					}else{
						new Feuille(pers.get(idx));
					}
					list.setListData(perso);	
				}
			}
		});
		JScrollPane scroll= new JScrollPane(list);
		Border b = BorderFactory.createTitledBorder("Personnage");
		scroll.setBorder(b);
		scroll.setPreferredSize(new Dimension(150,400));
		add(scroll);

		JPanel pan= new JPanel();
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		JButton ajout=new JButton("Ajoutez un personnage");
		ajout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Feuille();
			}
		});
		pan.add(ajout);

		JPanel panel=new JPanel();
		panel.setLayout(new FlowLayout());
		JButton sup=new JButton("Supprimer");
		panel.add(sup);
		JButton modif=new JButton("Modifier");
		panel.add(modif);
		pan.add(panel);

		JPanel panel1=new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.add(new JLabel("XP"));
		JSpinner spin= new JSpinner(new SpinnerNumberModel(0, 0, 1000, 5));
		panel1.add(spin);
		JButton val=new JButton("V");
		panel1.add(val);
		JLabel xp= new JLabel("0");
		panel1.add(xp);
		pan.add(panel1);

		add(pan);

	}

}
