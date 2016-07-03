import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Feuille {
	JFrame f= new JFrame("Fiche Perso");
	ArrayList<Integer> xplvl, pv, mod,caracpricipal;
	ArrayList<JSpinner> caracpricipalbase,caracpricipalact;
	ArrayList<JTextField> caracmod;
	JComboBox<String> classe,race;
	JTextField nom,xp,cheveux,yeux,pfrest,actuels,basepv,pvtotal,modphy,modmal,modpoi,modmys,modpsy,fatbase,pvniveau,totalphy,totalmal,totalpoi,totalmys,totalpsy,pres,fattotal,
				fatmalus,acttour,regenbase,regentotal,aurepos,sansrepos,reductionmalus,regenspe;
	JSpinner niveau,age,taille,tail,poids,app,modpv,ptrept,regenspecial;
	JRadioButton homme, femme, ti, basebutt, actbutt,avfatigue1,avfatigue2,avfatigue3,desavfatigue1,avregen1,avregen2,avregen3;
	int[][] tp,costclasse;
	JRadioButton[][] avdes;
	String[][] regentab;
	int lvl,exp,t,i;
	Font title, soustitle;
	boolean taillefemme,tailledaimah,taillejayan;

	public Feuille(){
		f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		init();

		JScrollPane general = new JScrollPane();	
		general(general);
		onglets.addTab("General", general);	

		JPanel combat = new JPanel();
		onglets.addTab("Combat", combat);		 	 
		onglets.setOpaque(true);

		f.setPreferredSize(new Dimension(1500, 700));
		f.getContentPane().add(onglets);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setDefaultLookAndFeelDecorated(true);
		f.setExtendedState(f.MAXIMIZED_BOTH);
		f.setVisible(true);
	}

	public Feuille(String path) {
		f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		init();

		JScrollPane general = new JScrollPane();
		general(general);
		onglets.addTab("General", general);	

		JPanel combat = new JPanel();
		onglets.addTab("Combat", combat);		 	 
		onglets.setOpaque(true);

		try {
			BufferedReader fiche= new BufferedReader(new FileReader(path));
			String ligne=fiche.readLine();
			nom.setText(ligne.substring(0, ligne.indexOf(';')));
			ligne=ligne.substring(ligne.indexOf(';')+1);
			classe.setSelectedIndex(Integer.parseInt(ligne.substring(0, ligne.indexOf(';'))));
			ligne=ligne.substring(ligne.indexOf(';')+1);
			race.setSelectedIndex(Integer.parseInt(ligne.substring(0, ligne.indexOf(';'))));
			ligne=ligne.substring(ligne.indexOf(';')+1);
			if(ligne.substring(0, ligne.indexOf(';')).equals("H")){
				homme.setSelected(true);
				femme.setSelected(false);
			}else{
				homme.setSelected(false);
				femme.setSelected(true);
			}
			ligne=ligne.substring(ligne.indexOf(';')+1);
			age.setValue(Integer.parseInt(ligne.substring(0, ligne.indexOf(';'))));
			ligne=ligne.substring(ligne.indexOf(';')+1);
			cheveux.setText(ligne.substring(0, ligne.indexOf(';')));
			ligne=ligne.substring(ligne.indexOf(';')+1);
			yeux.setText(ligne.substring(0, ligne.indexOf(';')));
			ligne=ligne.substring(ligne.indexOf(';')+1);
			taille.setValue(Integer.parseInt(ligne.substring(0, ligne.indexOf(';'))));
			ligne=ligne.substring(ligne.indexOf(';')+1);
			ti.setSelected(ligne.substring(0, ligne.indexOf(';')).equals('T'));
			ligne=ligne.substring(ligne.indexOf(';')+1);
			tail.setValue(Integer.parseInt(ligne.substring(0, ligne.indexOf(';'))));
			ligne=ligne.substring(ligne.indexOf(';')+1);			
			poids.setValue(Integer.parseInt(ligne.substring(0, ligne.indexOf(';'))));
			ligne=ligne.substring(ligne.indexOf(';')+1);
			app.setValue(Integer.parseInt(ligne.substring(0, ligne.indexOf(';'))));
			ligne=ligne.substring(ligne.indexOf(';')+1);
			niveau.setValue(Integer.parseInt(ligne.substring(0, ligne.indexOf(';'))));
			ligne=ligne.substring(ligne.indexOf(';')+1);
			xp.setText(ligne.substring(0, ligne.indexOf(';')));
			ligne=ligne.substring(ligne.indexOf(';')+1);
			pfrest.setText(ligne.substring(0, ligne.indexOf(';')));

			ligne=fiche.readLine();

			fiche.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		f.setPreferredSize(new Dimension(1500, 700));
		f.getContentPane().add(onglets);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setDefaultLookAndFeelDecorated(true);
		f.setExtendedState(f.MAXIMIZED_BOTH);
		f.setVisible(true);
	}	

	public void general(JScrollPane general){
		general.setLayout(null);
		JPanel description= new JPanel();
		title=new Font("Segoe Print",Font.BOLD,20);
		soustitle=new Font("Segoe Script",Font.BOLD,15);
		description.setLayout(new BoxLayout(description, BoxLayout.Y_AXIS));
		JLabel joueur=new JLabel("Joueur");
		joueur.setFont(title);
		description.add(joueur);
		description.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
		JPanel pan1= new JPanel(new FlowLayout());
		pan1.add(new JLabel("Nom"));
		nom=new JTextField();
		nom.setPreferredSize(new Dimension(200, 20));
		pan1.add(nom);
		pan1.add(new JLabel("Classe"));
		pan1.add(classe);
		pan1.add(new JLabel("Race"));
		pan1.add(race);
		pan1.add(new JLabel("Sexe"));
		ButtonGroup sexe =new ButtonGroup(); 				
		homme = new JRadioButton("♂");
		femme = new JRadioButton("♀");
		sexe.add(homme);
		sexe.add(femme);
		pan1.add(homme);
		pan1.add(femme);


		JPanel pan2= new JPanel(new FlowLayout());
		pan2.add(new JLabel("Âge"));
		age= new JSpinner();
		age.addChangeListener(new ChangeListener() {					
			@Override
			public void stateChanged(ChangeEvent e) {
				if((int)age.getValue()<0){
					age.setValue(0);
				}
			}
		});
		age.setPreferredSize(new Dimension(40,20));
		pan2.add(age);
		pan2.add(new JLabel("Cheveux"));
		cheveux=new JTextField();
		cheveux.setPreferredSize(new Dimension(80, 20));
		pan2.add(cheveux);
		pan2.add(new JLabel("Yeux"));
		yeux=new JTextField();
		yeux.setPreferredSize(new Dimension(80, 20));
		pan2.add(yeux);
		pan2.add(new JLabel("Taille"));
		taille= new JSpinner();
		taille.setValue(caracpricipal.get(1)+caracpricipal.get(3));
		taille.setPreferredSize(new Dimension(40,20));
		ti= new JRadioButton();
		ti.setBackground(Color.GREEN);
		JSpinner tispinn=new JSpinner();
		tispinn.setEnabled(false);
		taille.setEnabled(ti.isSelected());
		ti.addActionListener(new ActionListener() {					
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!ti.isSelected())
					tispinn.setValue(0);
				tispinn.setEnabled(ti.isSelected());
			}
		});
		t=(int)taille.getValue()+(int)tispinn.getValue();
		if(femme.isSelected())t--;
		if(race.getSelectedIndex()==4)t--;
		SpinnerNumberModel tailmodel= new SpinnerNumberModel((tp[t-2][0]+tp[t-2][1])/2, tp[t-2][0], tp[t-2][1] , 1);
		tail=new JSpinner();
		tail.setValue(tailmodel.getValue());
		tail.addChangeListener(new ChangeListener() {					
			@Override
			public void stateChanged(ChangeEvent e) {
				if((int)tail.getValue()>(int)tailmodel.getMaximum()){
					tail.setValue(tailmodel.getMaximum());
				}else if((int)tail.getValue()<(int)tailmodel.getMinimum()){
					tail.setValue(tailmodel.getMinimum());
				}
			}
		});
		tail.setPreferredSize(new Dimension(40,20));				
		SpinnerNumberModel poidsmodel= new SpinnerNumberModel((tp[t-2][2]+tp[t-2][3])/2, tp[t-2][2], tp[t-2][3] , 1);
		poids=new JSpinner();
		poids.setValue(poidsmodel.getValue());
		poids.addChangeListener(new ChangeListener() {					
			@Override
			public void stateChanged(ChangeEvent e) {
				if((int)poids.getValue()>(int)poidsmodel.getMaximum()){
					poids.setValue(poidsmodel.getMaximum());
				}else if((int)poids.getValue()<(int)poidsmodel.getMinimum()){
					poids.setValue(poidsmodel.getMinimum());
				}
			}
		});
		poids.setPreferredSize(new Dimension(40,20));				
		taille.addChangeListener(new ChangeListener() {					
			@Override
			public void stateChanged(ChangeEvent e) {
				t=(int)taille.getValue()+(int)tispinn.getValue();
				if(t<20){						
					tailmodel.setMaximum(tp[t-2][1]);						
					tailmodel.setMinimum(tp[t-2][0]);						
					tailmodel.setValue((tp[t-2][0]+tp[t-2][1])/2);
					tail.setValue(tailmodel.getValue());
					poidsmodel.setMaximum(tp[t-2][3]);
					poidsmodel.setMinimum(tp[t-2][2]);
					poidsmodel.setValue((tp[t-2][3]+tp[t-2][2])/2);
					poids.setValue(poidsmodel.getValue());
				}else{
					tailmodel.setMaximum(12500);
					tailmodel.setMinimum(250);
					tailmodel.setValue(250);
					tail.setValue(tailmodel.getValue());
					poidsmodel.setMaximum(1400);
					poidsmodel.setMinimum(400);
					poidsmodel.setValue(400);
					poids.setValue(poidsmodel.getValue());
				}
			}
		});
		tispinn.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if((int)tispinn.getValue()<-5)
					tispinn.setValue(-5);
				else if((int)tispinn.getValue()>5)
					tispinn.setValue(5);
				t=(int)taille.getValue()+(int)tispinn.getValue();
				if(t<20){						
					tailmodel.setMaximum(tp[t-2][1]);						
					tailmodel.setMinimum(tp[t-2][0]);						
					tailmodel.setValue((tp[t-2][0]+tp[t-2][1])/2);
					tail.setValue(tailmodel.getValue());
					poidsmodel.setMaximum(tp[t-2][3]);
					poidsmodel.setMinimum(tp[t-2][2]);
					poidsmodel.setValue((tp[t-2][3]+tp[t-2][2])/2);
					poids.setValue(poidsmodel.getValue());
				}else{
					tailmodel.setMaximum(12500);
					tailmodel.setMinimum(250);
					tailmodel.setValue(250);
					tail.setValue(tailmodel.getValue());
					poidsmodel.setMaximum(1400);
					poidsmodel.setMinimum(400);
					poidsmodel.setValue(400);
					poids.setValue(poidsmodel.getValue());
				}
			}
		});
		taillefemme=false;
		tailledaimah=false;
		taillejayan=false;
		femme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(femme.isSelected() && !taillefemme){
					taille.setValue((int)taille.getValue()-1);
					taillefemme=true;
				}
			}
		});
		homme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(homme.isSelected() && taillefemme){
					taille.setValue((int)taille.getValue()+1);
					taillefemme=false;
				}
			}
		});
		pan2.add(taille);
		pan2.add(ti);
		pan2.add(tispinn);
		pan2.add(new JLabel("Taille"));
		pan2.add(tail);
		pan2.add(new JLabel("Poids"));
		pan2.add(poids);
		pan2.add(new JLabel("App"));
		app=new JSpinner();
		app.setPreferredSize(new Dimension(40, 20));
		app.setValue(5);
		app.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if ((int)app.getValue()<0){
					app.setValue(0);
				}
				if ((int)app.getValue()>10){
					app.setValue(10);
				}
			}
		});
		pan2.add(app);

		JPanel pan3=new JPanel(new FlowLayout());
		pan3.add(new JLabel("Niveau"));
		niveau=new JSpinner();
		niveau.setValue(0);
		niveau.setPreferredSize(new Dimension(40,20));
		lvl=(int)niveau.getValue();
		JTextField pf= new JTextField();
		niveau.addChangeListener(new ChangeListener() {					
			@Override
			public void stateChanged(ChangeEvent e) {
				if((int)niveau.getValue()>50 || (int)niveau.getValue()<0 ){
					niveau.setValue(lvl);
				}
				lvl=(int)niveau.getValue();
				pf.setText(lvl==0? ""+400: ""+(lvl*100+500));
			}
		});
		pan3.add(niveau);
		pan3.add(new JLabel("Experience"));
		xp =new JTextField();
		xp.setPreferredSize(new Dimension(60, 20));
		pan3.add(xp);
		pan3.add(new JLabel("Niveau Suivant"));
		JTextField ns =new JTextField(race.getSelectedItem().equals("Tuan Dalyr")? 
				lvl+2<=xplvl.size()-1? ""+xplvl.get(lvl+2): ""+((450*(lvl-14+2))+xplvl.get(xplvl.size()-1)):
					lvl<=xplvl.size()-1? ""+xplvl.get(lvl): ""+((450*(lvl-14))+xplvl.get(xplvl.size()-1)));
		race.addActionListener(new ActionListener() {					
			@Override
			public void actionPerformed(ActionEvent e) {
				ns.setText(race.getSelectedItem().equals("Tuan Dalyr")? 
						lvl+2<=xplvl.size()-1? ""+xplvl.get(lvl+2): ""+((450*(lvl-14+2))+xplvl.get(xplvl.size()-1)):
							lvl<=xplvl.size()-1? ""+xplvl.get(lvl): ""+((450*(lvl-14))+xplvl.get(xplvl.size()-1)));	

				if(race.getSelectedIndex()==5 && !tailledaimah){
					taille.setValue((int)taille.getValue()-1);
					tailledaimah=true;
				}
				if(race.getSelectedIndex()==2 && !taillejayan){
					taille.setValue((int)taille.getValue()+1);
					taillejayan=true;
				}
				if(race.getSelectedIndex()!=5 && tailledaimah){
					taille.setValue((int)taille.getValue()+1);
					tailledaimah=false;
				}
				if(race.getSelectedIndex()!=2 && taillejayan){
					taille.setValue((int)taille.getValue()-1);
					taillejayan=false;
				}
			}
		});
		ns.setEditable(false);
		ns.setPreferredSize(new Dimension(60, 20));
		pan3.add(ns);
		pan3.add(new JLabel("Point de Formation"));
		pf.setPreferredSize(new Dimension(60,20));
		pf.setEditable(false);
		pf.setText(lvl==0? ""+400: ""+(lvl*100+500));
		pfrest= new JTextField();
		pfrest.setPreferredSize(new Dimension(60,20));
		pfrest.setText(pf.getText());
		pfrest.setEditable(false);
		pan3.add(pf);
		pan3.add(new JLabel("a  Depenser"));
		pan3.add(pfrest);

		description.add(pan1);
		description.add(pan2);
		description.add(pan3);


		JPanel pdv =new JPanel();
		pdv.setLayout(new BoxLayout(pdv, BoxLayout.Y_AXIS));
		JPanel pan=new JPanel(new FlowLayout());
		JLabel pointdevie=new JLabel("Point de Vie");
		pointdevie.setFont(title);
		pan.add(pointdevie);
		pdv.add(pan);
		pdv.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
		JPanel pan4=new JPanel(new GridLayout(6, 2));
		pan4.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
		JLabel l1=new JLabel("Cout Multiplcateur");
		l1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		pan4.add(l1);
		JTextField cost=new JTextField();
		cost.setEditable(false);
		cost.setText(costclasse[classe.getSelectedIndex()][0]+"");
		cost.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		pan4.add(cost);
		pan4.add(new JLabel("Base"));
		basepv=new JTextField();
		basepv.setEditable(false);
		basepv.setText(""+pv.get(caracpricipal.get(1)-1));
		pan4.add(basepv);
		pan4.add(new JLabel("Classe"));
		pvniveau=new JTextField();
		pvniveau.setEditable(false);
		pvniveau.setText(costclasse[classe.getSelectedIndex()][1]+"");
		pan4.add(pvniveau);
		pan4.add(new JLabel("Multiplicateur"));
		modpv=new JSpinner();
		modpv.setValue(0);
		modpv.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if((int)modpv.getValue()<0){
					modpv.setValue(0);
				}
			}
		});
		pan4.add(modpv);
		pan4.add(new JLabel("Total"));
		pvtotal =new JTextField();
		pvtotal.setEditable(false);
		pvtotal.setText(""+(Integer.parseInt(basepv.getText())+Integer.parseInt(pvniveau.getText())+(int)modpv.getValue()*caracpricipal.get(1)));
		pan4.add(pvtotal);
		pdv.add(pan4);
		JLabel l2=new JLabel("Actuels");
		l2.setFont(soustitle);
		l2.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
		pan4.add(l2);
		actuels=new JTextField();
		actuels.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
		pan4.add(actuels);
		pdv.add(pan4);

		JPanel resistance= new JPanel();
		resistance.setLayout(new BoxLayout(resistance, BoxLayout.X_AXIS));
		resistance.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
		JPanel resistext= new JPanel(new GridLayout(7, 1));
		JPanel resisbutton= new JPanel(new GridLayout(7, 1));
		JLabel labresi=new JLabel("Resistance");
		labresi.setFont(title);
		labresi.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		resistext.add(labresi);
		resistext.add(new JLabel("Presence"));
		resistext.add(new JLabel("Physique"));
		resistext.add(new JLabel("Maladie"));
		resistext.add(new JLabel("Poisons"));
		resistext.add(new JLabel("Mystique"));	
		resistext.add(new JLabel("Psychique"));
		JPanel resisnbr= new JPanel(new GridLayout(7, 4));
		JPanel pannbase=new JPanel(new FlowLayout());
		JLabel labbase=new JLabel("Base");
		labbase.setFont(soustitle);
		pannbase.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		pannbase.add(labbase);
		resisnbr.add(pannbase);
		JPanel panncarac=new JPanel(new FlowLayout());
		JLabel labcarac=new JLabel("Carac");
		labcarac.setFont(soustitle);
		panncarac.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		panncarac.add(labcarac);
		resisnbr.add(panncarac);
		JPanel pannmod=new JPanel(new FlowLayout());
		JLabel labmod=new JLabel("Mod");
		labmod.setFont(soustitle);
		pannmod.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		pannmod.add(labmod);
		resisnbr.add(pannmod);
		JPanel panntotal=new JPanel(new FlowLayout());
		JLabel labtotal=new JLabel("Total");
		labtotal.setFont(soustitle);
		panntotal.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		panntotal.add(labtotal);
		resisnbr.add(panntotal);
		JPanel pannspe=new JPanel(new FlowLayout());
		JLabel labspe=new JLabel("Spe");
		labspe.setFont(soustitle);
		pannspe.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		pannspe.add(labspe);
		resisbutton.add(pannspe);

		JPanel[] panavdes= new JPanel[6];
		avdes= new JRadioButton[6][3];
		for(int i=0; i<avdes.length; i++){
			panavdes[i]=new JPanel(new FlowLayout());
			for(int j=0; j<avdes[0].length; j++){
				avdes[i][j]=new JRadioButton();
				if(i==0 || (i==5 && j==2)){avdes[i][j].setBackground(Color.GRAY);avdes[i][j].setEnabled(false);}
				else if(j==2)avdes[i][j].setBackground(Color.RED);
				else avdes[i][j].setBackground(Color.GREEN);
				panavdes[i].add(avdes[i][j]);
			}
			resisbutton.add(panavdes[i]);
		}

		pres= new JTextField();
		pres.setEditable(false);
		pres.setText(""+(30+5*(int)niveau.getValue()));
		pres.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(pres);
		JTextField carac1= new JTextField();
		carac1.setEditable(false);
		carac1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		carac1.setBackground(Color.BLACK);
		carac1.setOpaque(true);
		resisnbr.add(carac1);
		JTextField mod1= new JTextField();
		mod1.setEditable(false);
		mod1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		mod1.setBackground(Color.BLACK);
		mod1.setOpaque(true);
		resisnbr.add(mod1);
		JTextField total1=new JTextField(pres.getText());
		total1.setEditable(false);
		total1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(total1);

		JTextField phy= new JTextField();
		phy.setEditable(false);
		phy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		phy.setText(""+total1.getText());
		resisnbr.add(phy);
		JTextField caracphy= new JTextField();
		caracphy.setEditable(false);
		caracphy.setText("CON");
		caracphy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(caracphy);
		modphy= new JTextField();
		modphy.setEditable(false);
		modphy.setText(""+mod.get(caracpricipal.get(1)-1));
		modphy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(modphy);
		totalphy=new JTextField(""+(Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText())));
		totalphy.setEditable(false);
		totalphy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(totalphy);

		JTextField mal= new JTextField();
		mal.setEditable(false);
		mal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		mal.setText(""+total1.getText());
		resisnbr.add(mal);
		JTextField caracmal= new JTextField();
		caracmal.setEditable(false);
		caracmal.setText("CON");
		caracmal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(caracmal);
		modmal= new JTextField();
		modmal.setEditable(false);
		modmal.setText(""+mod.get(caracpricipal.get(1)-1));
		modmal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(modmal);
		totalmal=new JTextField(""+(Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText())));
		totalmal.setEditable(false);
		totalmal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(totalmal);

		JTextField poi= new JTextField();
		poi.setEditable(false);
		poi.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		poi.setText(""+total1.getText());
		resisnbr.add(poi);
		JTextField caracpoi= new JTextField();
		caracpoi.setEditable(false);
		caracpoi.setText("CON");
		caracpoi.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(caracpoi);
		modpoi= new JTextField();
		modpoi.setEditable(false);
		modpoi.setText(""+mod.get(caracpricipal.get(1)-1));
		modpoi.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(modpoi);
		totalpoi=new JTextField(""+(Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText())));
		totalpoi.setEditable(false);
		totalpoi.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(totalpoi);

		JTextField mys= new JTextField();
		mys.setEditable(false);
		mys.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		mys.setText(""+total1.getText());
		resisnbr.add(mys);
		JTextField caracmys= new JTextField();
		caracmys.setEditable(false);
		caracmys.setText("POU");
		caracmys.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(caracmys);
		modmys= new JTextField();
		modmys.setEditable(false);
		modmys.setText(""+mod.get(caracpricipal.get(6)-1));
		modmys.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(modmys);
		totalmys=new JTextField(""+(Integer.parseInt(mys.getText())+Integer.parseInt(modmys.getText())));
		totalmys.setEditable(false);
		totalmys.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(totalmys);

		JTextField psy= new JTextField();
		psy.setEditable(false);
		psy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		psy.setText(""+total1.getText());
		resisnbr.add(psy);
		JTextField caracpsy= new JTextField();
		caracpsy.setEditable(false);
		caracpsy.setText("VOL");
		caracpsy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(caracpsy);
		modpsy= new JTextField();
		modpsy.setEditable(false);
		modpsy.setText(""+mod.get(caracpricipal.get(7)-1));
		modpsy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(modpsy);
		totalpsy=new JTextField(""+(Integer.parseInt(psy.getText())+Integer.parseInt(modpsy.getText())));
		totalpsy.setEditable(false);
		totalpsy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		resisnbr.add(totalpsy);

		//avantage un physique
		avdes[1][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(avdes[1][0].isSelected()){
					if(avdes[1][1].isSelected()){
						avdes[1][1].setSelected(false);
						avdes[2][1].setSelected(false);
						avdes[3][1].setSelected(false);
						totalphy.setText(Integer.parseInt(totalphy.getText())-50+"");
						totalmal.setText(Integer.parseInt(totalmal.getText())-50+"");
						totalpoi.setText(Integer.parseInt(totalpoi.getText())-50+"");
					}
					avdes[2][0].setSelected(true);
					avdes[3][0].setSelected(true);
					if(avdes[1][2].isSelected())
						totalphy.setText(((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+25)/2+"");
					else
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+25+"");
					if(avdes[2][2].isSelected())
						totalmal.setText(((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+25)/2+"");
					else
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+25+"");
					if(avdes[3][2].isSelected())
						totalpoi.setText(((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+25)/2+"");
					else
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+25+"");
				}else{
					avdes[2][0].setSelected(false);
					avdes[3][0].setSelected(false);
					if(avdes[1][2].isSelected())
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))/2+"");
					else
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+"");
					if(avdes[2][2].isSelected())
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))/2+"");
					else
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+"");
					if(avdes[3][2].isSelected())
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))/2+"");
					else
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+"");
				}
			}
		});

		//avantage un maladie
		avdes[2][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(avdes[2][0].isSelected()){
					if(avdes[2][1].isSelected()){
						avdes[1][1].setSelected(false);
						avdes[2][1].setSelected(false);
						avdes[3][1].setSelected(false);
						totalphy.setText(Integer.parseInt(totalphy.getText())-50+"");
						totalmal.setText(Integer.parseInt(totalmal.getText())-50+"");
						totalpoi.setText(Integer.parseInt(totalpoi.getText())-50+"");
					}
					avdes[1][0].setSelected(true);
					avdes[3][0].setSelected(true);
					if(avdes[1][2].isSelected())
						totalphy.setText(((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+25)/2+"");
					else
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+25+"");
					if(avdes[2][2].isSelected())
						totalmal.setText(((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+25)/2+"");
					else
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+25+"");
					if(avdes[3][2].isSelected())
						totalpoi.setText(((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+25)/2+"");
					else
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+25+"");
				}else{
					avdes[1][0].setSelected(false);
					avdes[3][0].setSelected(false);
					if(avdes[1][2].isSelected())
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))/2+"");
					else
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+"");
					if(avdes[2][2].isSelected())
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))/2+"");
					else
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+"");
					if(avdes[3][2].isSelected())
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))/2+"");
					else
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+"");
				}
			}
		});

		//avantage un poisson
		avdes[3][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(avdes[3][0].isSelected()){
					if(avdes[3][1].isSelected()){
						avdes[1][1].setSelected(false);
						avdes[2][1].setSelected(false);
						avdes[3][1].setSelected(false);
						totalphy.setText(Integer.parseInt(totalphy.getText())-50+"");
						totalmal.setText(Integer.parseInt(totalmal.getText())-50+"");
						totalpoi.setText(Integer.parseInt(totalpoi.getText())-50+"");
					}
					avdes[1][0].setSelected(true);
					avdes[2][0].setSelected(true);
					if(avdes[1][2].isSelected())
						totalphy.setText(((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+25)/2+"");
					else
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+25+"");
					if(avdes[2][2].isSelected())
						totalmal.setText(((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+25)/2+"");
					else
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+25+"");
					if(avdes[3][2].isSelected())
						totalpoi.setText(((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+25)/2+"");
					else
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+25+"");
				}else{
					avdes[1][0].setSelected(false);
					avdes[2][0].setSelected(false);
					if(avdes[1][2].isSelected())
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))/2+"");
					else
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+"");
					if(avdes[2][2].isSelected())
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))/2+"");
					else
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+"");
					if(avdes[3][2].isSelected())
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))/2+"");
					else
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+"");
				}
			}
		});

		//avantage un mystique
		avdes[4][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(avdes[4][0].isSelected()){
					if(avdes[4][1].isSelected()){
						avdes[4][1].setSelected(false);
					}
					if(avdes[4][2].isSelected()){
						avdes[4][2].setSelected(false);
					}
					totalmys.setText((Integer.parseInt(mys.getText())+Integer.parseInt(modmys.getText()))+25+"");
				}else{
					if(avdes[4][2].isSelected())
						totalmys.setText(Integer.parseInt(mys.getText())+Integer.parseInt(modmys.getText())/2+"");
					else
						totalmys.setText(Integer.parseInt(mys.getText())+Integer.parseInt(modmys.getText())+"");
				}
			}
		});

		//avantage un psychique
		avdes[5][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(avdes[5][0].isSelected()){
					if(avdes[5][1].isSelected()){
						avdes[5][1].setSelected(false);
						totalmys.setText(Integer.parseInt(totalpsy.getText())-50+"");
					}
					totalmys.setText(Integer.parseInt(totalpsy.getText())+25+"");
				}else{
					totalmys.setText(Integer.parseInt(totalpsy.getText())-25+"");
				}
			}
		});



		//avantage deux physique
		avdes[1][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(avdes[1][1].isSelected()){
					if(avdes[1][0].isSelected()){
						avdes[1][0].setSelected(false);
						avdes[2][0].setSelected(false);
						avdes[3][0].setSelected(false);
						totalphy.setText(Integer.parseInt(totalphy.getText())-25+"");
						totalmal.setText(Integer.parseInt(totalmal.getText())-25+"");
						totalpoi.setText(Integer.parseInt(totalpoi.getText())-25+"");
					}
					avdes[2][1].setSelected(true);
					avdes[3][1].setSelected(true);
					if(avdes[1][2].isSelected())
						totalphy.setText(((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+50)/2+"");
					else
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+50+"");
					if(avdes[2][2].isSelected())
						totalmal.setText(((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+50)/2+"");
					else
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+50+"");
					if(avdes[3][2].isSelected())
						totalpoi.setText(((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+50)/2+"");
					else
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+50+"");
				}else{
					avdes[2][1].setSelected(false);
					avdes[3][1].setSelected(false);
					if(avdes[1][2].isSelected())
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))/2+"");
					else
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+"");
					if(avdes[2][2].isSelected())
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))/2+"");
					else
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+"");
					if(avdes[3][2].isSelected())
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))/2+"");
					else
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+"");
				}
			}
		});

		//avantage deux maladie
		avdes[2][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(avdes[2][1].isSelected()){
					if(avdes[2][0].isSelected()){
						avdes[1][0].setSelected(false);
						avdes[2][0].setSelected(false);
						avdes[3][0].setSelected(false);
						totalphy.setText(Integer.parseInt(totalphy.getText())-25+"");
						totalmal.setText(Integer.parseInt(totalmal.getText())-25+"");
						totalpoi.setText(Integer.parseInt(totalpoi.getText())-25+"");
					}
					avdes[1][1].setSelected(true);
					avdes[3][1].setSelected(true);
					if(avdes[1][2].isSelected())
						totalphy.setText(((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+50)/2+"");
					else
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+50+"");
					if(avdes[2][2].isSelected())
						totalmal.setText(((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+50)/2+"");
					else
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+50+"");
					if(avdes[3][2].isSelected())
						totalpoi.setText(((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+50)/2+"");
					else
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+50+"");
				}else{
					avdes[1][1].setSelected(false);
					avdes[3][1].setSelected(false);
					if(avdes[1][2].isSelected())
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))/2+"");
					else
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+"");
					if(avdes[2][2].isSelected())
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))/2+"");
					else
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+"");
					if(avdes[3][2].isSelected())
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))/2+"");
					else
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+"");
				}
			}
		});

		//avantage deux poisson
		avdes[3][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(avdes[3][1].isSelected()){
					if(avdes[3][0].isSelected()){
						avdes[1][0].setSelected(false);
						avdes[2][0].setSelected(false);
						avdes[3][0].setSelected(false);
						totalphy.setText(Integer.parseInt(totalphy.getText())-25+"");
						totalmal.setText(Integer.parseInt(totalmal.getText())-25+"");
						totalpoi.setText(Integer.parseInt(totalpoi.getText())-25+"");
					}
					avdes[1][1].setSelected(true);
					avdes[2][1].setSelected(true);
					if(avdes[1][2].isSelected())
						totalphy.setText(((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+50)/2+"");
					else
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+50+"");
					if(avdes[2][2].isSelected())
						totalmal.setText(((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+50)/2+"");
					else
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+50+"");
					if(avdes[3][2].isSelected())
						totalpoi.setText(((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+50)/2+"");
					else
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+50+"");
				}else{
					avdes[1][1].setSelected(false);
					avdes[2][1].setSelected(false);
					if(avdes[1][2].isSelected())
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))/2+"");
					else
						totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText()))+"");
					if(avdes[2][2].isSelected())
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))/2+"");
					else
						totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText()))+"");
					if(avdes[3][2].isSelected())
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))/2+"");
					else
						totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText()))+"");
				}
			}
		});

		//avantage deux mystique
		avdes[4][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(avdes[4][1].isSelected()){
					if(avdes[4][0].isSelected()){
						avdes[4][0].setSelected(false);
						totalmys.setText(Integer.parseInt(totalmys.getText())-25+"");
					}
					if(avdes[4][2].isSelected()){
						avdes[4][2].setSelected(false);
						totalmys.setText(Integer.parseInt(totalmys.getText())/2+"");
					}
					totalmys.setText(Integer.parseInt(totalmys.getText())+50+"");
				}else{
					if(avdes[4][2].isSelected())
						totalmys.setText(Integer.parseInt(totalmys.getText())-25+"");
					else
						totalmys.setText(Integer.parseInt(totalmys.getText())-50+"");

				}
			}
		});

		//avantage deux psychique
		avdes[5][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(avdes[5][1].isSelected()){
					if(avdes[5][0].isSelected()){
						avdes[5][0].setSelected(false);
						totalmys.setText(Integer.parseInt(totalpsy.getText())-25+"");
					}
					totalmys.setText(Integer.parseInt(totalpsy.getText())+50+"");
				}else{
					totalmys.setText(Integer.parseInt(totalpsy.getText())-50+"");
				}
			}
		});



		//desavantage physique
		avdes[1][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(avdes[1][2].isSelected()){
					totalphy.setText(Integer.parseInt(totalphy.getText())/2+"");
				}else{
					if(avdes[1][0].isSelected()){
						totalphy.setText(Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText())+25+"");
					}else if(avdes[1][1].isSelected()){
						totalphy.setText(Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText())+50+"");
					}else{
						totalphy.setText(Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText())+"");
					}
				}
			}
		});

		//desavantage maladie
		avdes[2][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(avdes[2][2].isSelected()){
					totalmal.setText(Integer.parseInt(totalmal.getText())/2+"");
				}else{
					if(avdes[2][0].isSelected()){
						totalmal.setText(Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText())+25+"");
					}else if(avdes[2][1].isSelected()){
						totalmal.setText(Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText())+50+"");
					}else{
						totalmal.setText(Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText())+"");
					}
				}
			}
		});

		//desavantage poisson
		avdes[3][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(avdes[3][2].isSelected()){
					totalpoi.setText(Integer.parseInt(totalpoi.getText())/2+"");
				}else{
					if(avdes[3][0].isSelected()){
						totalpoi.setText(Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText())+25+"");
					}else if(avdes[3][1].isSelected()){
						totalpoi.setText(Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText())+50+"");
					}else{
						totalpoi.setText(Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText())+"");
					}
				}
			}
		});

		//desavantage poisson
		avdes[4][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(avdes[4][2].isSelected()){
					totalmys.setText(Integer.parseInt(totalmys.getText())/2+"");
				}else{
					if(avdes[4][0].isSelected()){
						totalmys.setText(Integer.parseInt(mys.getText())+Integer.parseInt(modmys.getText())+25+"");
					}else if(avdes[4][1].isSelected()){
						totalmys.setText(Integer.parseInt(mys.getText())+Integer.parseInt(modmys.getText())+50+"");
					}else{
						totalmys.setText(Integer.parseInt(mys.getText())+Integer.parseInt(modmys.getText())+"");
					}
				}
			}
		});

		niveau.addChangeListener(new ChangeListener() {					
			@Override
			public void stateChanged(ChangeEvent e) {
				ns.setText(race.getSelectedItem().equals("Tuan Dalyr")? 
						lvl+2<=xplvl.size()-1? ""+xplvl.get(lvl+2): ""+((450*(lvl-14+2))+xplvl.get(xplvl.size()-1)):
							lvl<=xplvl.size()-1? ""+xplvl.get(lvl): ""+((450*(lvl-14))+xplvl.get(xplvl.size()-1)));

				pres.setText(""+(25+5*(int)niveau.getValue()));
				phy.setText(pres.getText());
				mal.setText(pres.getText());
				poi.setText(pres.getText());
				mys.setText(pres.getText());
				psy.setText(pres.getText());
				total1.setText(pres.getText());
				totalphy.setText((Integer.parseInt(phy.getText())+Integer.parseInt(modphy.getText())+
						(avdes[1][0].isSelected()? 25:0) +
						(avdes[1][1].isSelected()? 50:0)) /
						(avdes[1][2].isSelected()? 2:1 )+"");
				totalmal.setText((Integer.parseInt(mal.getText())+Integer.parseInt(modmal.getText())+
						(avdes[2][0].isSelected()? 25:0) +
						(avdes[2][1].isSelected()? 50:0)) /
						(avdes[2][2].isSelected()? 2:1 )+"");
				totalpoi.setText((Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText())+
						(avdes[3][0].isSelected()? 25:0) +
						(avdes[3][1].isSelected()? 50:0)) /
						(avdes[3][2].isSelected()? 2:1 )+"");
				totalmys.setText((Integer.parseInt(mys.getText())+Integer.parseInt(modmys.getText())+
						(avdes[4][0].isSelected()? 25:0) +
						(avdes[4][1].isSelected()? 50:0)) /
						(avdes[4][2].isSelected()? 2:1 )+"");
				totalpsy.setText((Integer.parseInt(psy.getText())+Integer.parseInt(modpsy.getText())+
						(avdes[5][0].isSelected()? 25:0) +
						(avdes[5][1].isSelected()? 50:0)) /
						(avdes[5][2].isSelected()? 2:1 )+"");
			}
		});

		resistance.add(resistext);
		resistance.add(resisnbr);
		resistance.add(resisbutton);	


		JPanel carac=new JPanel(new GridLayout(9, 4));
		carac.setBorder(BorderFactory.createMatteBorder(2, 3, 3, 3, Color.BLACK));
		ptrept=new JSpinner();
		ptrept.setValue((int)niveau.getValue()/2);
		carac.add(ptrept);
		ButtonGroup caracgroupbutt=new ButtonGroup();
		JPanel basepan=new JPanel(new FlowLayout());		
		basepan.add(new JLabel("Base"));
		basebutt.setSelected(true);
		caracgroupbutt.add(basebutt);
		basepan.add(basebutt);
		carac.add(basepan);
		JPanel actpan=new JPanel(new FlowLayout());		
		actpan.add(new JLabel("Act"));
		caracgroupbutt.add(actbutt);
		actpan.add(actbutt);
		carac.add(actpan);
		carac.add(new JLabel("Mod"));
		for(int i=0; i<8;i++){
			if(i==0)carac.add(new JLabel("Agi"));
			if(i==1)carac.add(new JLabel("Con"));
			if(i==2)carac.add(new JLabel("Dex"));
			if(i==3)carac.add(new JLabel("For"));
			if(i==4)carac.add(new JLabel("Int"));
			if(i==5)carac.add(new JLabel("Per"));
			if(i==6)carac.add(new JLabel("Pou"));
			if(i==7)carac.add(new JLabel("Vol"));
			caracmod.get(i).setEditable(false);
			carac.add(caracpricipalbase.get(i));
			carac.add(caracpricipalact.get(i));
			caracmod.get(i).setBorder(BorderFactory.createMatteBorder( 0, 1, 1, 0, Color.BLACK));
			carac.add(caracmod.get(i));
		}
		caracmod.get(0).setBorder(BorderFactory.createMatteBorder( 1, 1, 1, 0, Color.BLACK));

		JPanel fatigue=new JPanel();
		fatigue.setBorder(BorderFactory.createMatteBorder( 3, 3, 3, 3, Color.BLACK));
		fatigue.setLayout(new BoxLayout(fatigue, BoxLayout.Y_AXIS));
		ArrayList<JPanel> panfatigue=new ArrayList<JPanel>();
		for(int i=0;i<7;i++){
			panfatigue.add(new JPanel(new FlowLayout()));
			panfatigue.get(i).setBorder(BorderFactory.createMatteBorder( 0, 0, 1, 0, Color.BLACK));
			fatigue.add(panfatigue.get(i));
		}
		JLabel l3=new JLabel("Fatigue");
		panfatigue.get(0).setBorder(BorderFactory.createMatteBorder( 0, 0, 2, 0, Color.BLACK));
		panfatigue.get(6).setBorder(BorderFactory.createMatteBorder( 2, 0, 0, 0, Color.BLACK));
		l3.setFont(title);
		panfatigue.get(0).add(l3);
		panfatigue.get(1).add(new JLabel("Base"));
		fatbase=new JTextField();
		fatbase.setPreferredSize(new Dimension(30, 20));
		fatbase.setText(caracpricipal.get(1)+"");
		fatbase.setEditable(false);
		panfatigue.get(1).add(fatbase);	
		avfatigue1=new JRadioButton();
		avfatigue1.setBackground(Color.GREEN);
		panfatigue.get(2).add(avfatigue1);
		avfatigue2=new JRadioButton();
		avfatigue2.setBackground(Color.GREEN);
		panfatigue.get(2).add(avfatigue2);
		avfatigue3=new JRadioButton();
		avfatigue3.setBackground(Color.GREEN);
		panfatigue.get(2).add(avfatigue3);
		desavfatigue1=new JRadioButton();
		desavfatigue1.setBackground(Color.RED);
		panfatigue.get(2).add(desavfatigue1);
		panfatigue.get(3).add(new JLabel("Total"));
		fattotal=new JTextField();
		fattotal.setPreferredSize(new Dimension(30, 20));
		fattotal.setText((Integer.parseInt(fatbase.getText())+(avfatigue1.isSelected()? 3:0)+(avfatigue2.isSelected()? 6:0)+(avfatigue3.isSelected()? 9:0))+"");
		fatmalus=new JTextField();
		fatmalus.setEditable(false);
		fatmalus.setPreferredSize(new Dimension(30, 20));

		avfatigue1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(avfatigue1.isSelected()){
					avfatigue2.setSelected(false);
					avfatigue3.setSelected(false);
					desavfatigue1.setSelected(false);
					fattotal.setText((Integer.parseInt(fatbase.getText())+3)+"");
				}else{
					fattotal.setText(fatbase.getText());
				}
			}
		});
		avfatigue2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(avfatigue2.isSelected()){
					avfatigue1.setSelected(false);
					avfatigue3.setSelected(false);
					desavfatigue1.setSelected(false);
					fattotal.setText((Integer.parseInt(fatbase.getText())+6)+"");
				}else{
					fattotal.setText(fatbase.getText());
				}
			}
		});
		avfatigue3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(avfatigue3.isSelected()){
					avfatigue1.setSelected(false);
					avfatigue2.setSelected(false);
					desavfatigue1.setSelected(false);
					fattotal.setText((Integer.parseInt(fatbase.getText())+9)+"");
				}else{
					fattotal.setText(fatbase.getText());
				}
			}
		});
		desavfatigue1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(desavfatigue1.isSelected()){
					avfatigue1.setSelected(false);
					avfatigue2.setSelected(false);
					avfatigue3.setSelected(false);
					fattotal.setText((Integer.parseInt(fatbase.getText())-1)+"");
					fatmalus.setText((Integer.parseInt(fatmalus.getText())*2)+"");
				}else{
					fattotal.setText((Integer.parseInt(fatbase.getText()))+"");
				}
			}
		});
		panfatigue.get(3).add(fattotal);
		JLabel l4=new JLabel("Actuel");
		l4.setFont(soustitle);
		panfatigue.get(4).add(l4);
		JSpinner fatactuel=new JSpinner();
		fatactuel.setValue(Integer.parseInt(fattotal.getText()));
		fatactuel.setPreferredSize(new Dimension(30, 20));
		panfatigue.get(4).add(fatactuel);
		fatactuel.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if((int)fatactuel.getValue()>Integer.parseInt(fattotal.getText()))
					fatactuel.setValue(fattotal);
				else if((int)fatactuel.getValue()<0)
					fatactuel.setValue(0);
				if((int)fatactuel.getValue()==0){
					fatmalus.setText("-120");
				}else if((int)fatactuel.getValue()==1){
					fatmalus.setText("-80");
				}else if((int)fatactuel.getValue()==2){
					fatmalus.setText("-40");
				}else if((int)fatactuel.getValue()==3){
					fatmalus.setText("-20");
				}else if((int)fatactuel.getValue()==4){
					fatmalus.setText("-10");
				}else{
					fatmalus.setText("0");
				}
			}
		});
		panfatigue.get(5).add(new JLabel("Malus"));
		panfatigue.get(5).add(fatmalus);
		JLabel l5=new JLabel("A/Tour");
		l5.setFont(soustitle);
		panfatigue.get(6).add(l5);
		acttour=new JTextField();
		acttour.setEditable(false);
		acttour.setPreferredSize(new Dimension(30, 20));
		panfatigue.get(6).add(acttour);
		
		JPanel regen=new JPanel();
		regen.setBorder(BorderFactory.createMatteBorder( 3, 3, 3, 3, Color.BLACK));
		regen.setLayout(new BoxLayout(regen, BoxLayout.Y_AXIS));
		JPanel panregen1=new JPanel(new FlowLayout());
		JPanel panregen2=new JPanel(new FlowLayout());
		JLabel l6=new JLabel("Regeneration");
		l6.setFont(title);
		panregen1.add(l6);
		regenbase=new JTextField();
		regenbase.setEditable(false);
		regenbase.setText(""+(caracpricipal.get(1)<3? 0:caracpricipal.get(1)<8? 1:caracpricipal.get(1)<10? 2:caracpricipal.get(1)>18? 12:caracpricipal.get(1)-7));
		regenbase.setPreferredSize(new Dimension(30, 20));
		regenspecial=new JSpinner();
		regenspecial.setValue(0);
		regenspecial.setPreferredSize(new Dimension(40, 20));
		regentotal=new JTextField();
		regentotal.setEditable(false);
		regentotal.setPreferredSize(new Dimension(30, 20));
		regentotal.setText((Integer.parseInt(regenbase.getText())+(int)regenspecial.getValue())+"");
		panregen1.add(regenbase);
		avregen1=new JRadioButton();
		avregen1.setBackground(Color.GREEN);
		panregen1.add(avregen1);
		avregen2=new JRadioButton();
		avregen2.setBackground(Color.GREEN);
		panregen1.add(avregen2);
		avregen3=new JRadioButton();
		avregen3.setBackground(Color.GREEN);
		panregen1.add(avregen3);
		panregen1.add(new JLabel("spe"));
		panregen1.add(regenspecial);
		panregen1.add(regentotal);
		aurepos=new JTextField();
		aurepos.setEditable(false);
		aurepos.setText(regenbase.getText().equals("0")? 0+"":regentab[0][Integer.parseInt(regenbase.getText())-1]);
		sansrepos=new JTextField();
		sansrepos.setEditable(false);
		sansrepos.setText(regenbase.getText().equals("0")? 0+"":regentab[1][Integer.parseInt(regenbase.getText())-1]);
		reductionmalus=new JTextField();
		reductionmalus.setEditable(false);
		reductionmalus.setText(regenbase.getText().equals("0")? 0+"":regentab[2][Integer.parseInt(regenbase.getText())-1]);
		regenspe=new JTextField();
		regenspe.setEditable(false);
		regenspe.setText(regenbase.getText().equals("0")? 0+"":regentab[3][Integer.parseInt(regenbase.getText())-1]);
		
		regenspecial.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				regentotal.setText((Integer.parseInt(regenbase.getText())+(int)regenspecial.getValue())+"");
				if((Integer.parseInt(regentotal.getText()))>20)regentotal.setText("20");
				aurepos.setText(regentotal.getText().equals("0")? 0+"":regentab[0][Integer.parseInt(regentotal.getText())-1]);
				sansrepos.setText(regentotal.getText().equals("0")? 0+"":regentab[1][Integer.parseInt(regentotal.getText())-1]);
				reductionmalus.setText(regentotal.getText().equals("0")? 0+"":regentab[2][Integer.parseInt(regentotal.getText())-1]);
				regenspe.setText(regentotal.getText().equals("0")? 0+"":regentab[3][Integer.parseInt(regentotal.getText())-1]);
			}
		});
		avregen1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(avregen1.isSelected()){
					avregen2.setSelected(false);
					avregen3.setSelected(false);
					regentotal.setText((Integer.parseInt(regenbase.getText())+(int)regenspecial.getValue()+2)+"");
					if((Integer.parseInt(regentotal.getText()))>20)regentotal.setText("20");
					aurepos.setText(regentotal.getText().equals("0")? 0+"":regentab[0][Integer.parseInt(regentotal.getText())-1]);
					sansrepos.setText(regentotal.getText().equals("0")? 0+"":regentab[1][Integer.parseInt(regentotal.getText())-1]);
					reductionmalus.setText(regentotal.getText().equals("0")? 0+"":regentab[2][Integer.parseInt(regentotal.getText())-1]);
					regenspe.setText(regentotal.getText().equals("0")? 0+"":regentab[3][Integer.parseInt(regentotal.getText())-1]);
				}else{
					regentotal.setText((Integer.parseInt(regenbase.getText())+(int)regenspecial.getValue())+"");
					if((Integer.parseInt(regentotal.getText()))>20)regentotal.setText("20");
					aurepos.setText(regentotal.getText().equals("0")? 0+"":regentab[0][Integer.parseInt(regentotal.getText())-1]);
					sansrepos.setText(regentotal.getText().equals("0")? 0+"":regentab[1][Integer.parseInt(regentotal.getText())-1]);
					reductionmalus.setText(regentotal.getText().equals("0")? 0+"":regentab[2][Integer.parseInt(regentotal.getText())-1]);
					regenspe.setText(regentotal.getText().equals("0")? 0+"":regentab[3][Integer.parseInt(regentotal.getText())-1]);
				}
			}
		});
		avregen2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(avregen2.isSelected()){
					avregen1.setSelected(false);
					avregen3.setSelected(false);
					regentotal.setText((Integer.parseInt(regenbase.getText())+(int)regenspecial.getValue()+4)+"");
					if((Integer.parseInt(regentotal.getText()))>20)regentotal.setText("20");
					aurepos.setText(regentotal.getText().equals("0")? 0+"":regentab[0][Integer.parseInt(regentotal.getText())-1]);
					sansrepos.setText(regentotal.getText().equals("0")? 0+"":regentab[1][Integer.parseInt(regentotal.getText())-1]);
					reductionmalus.setText(regentotal.getText().equals("0")? 0+"":regentab[2][Integer.parseInt(regentotal.getText())-1]);
					regenspe.setText(regentotal.getText().equals("0")? 0+"":regentab[3][Integer.parseInt(regentotal.getText())-1]);
				}else{
					regentotal.setText((Integer.parseInt(regenbase.getText())+(int)regenspecial.getValue())+"");
					if((Integer.parseInt(regentotal.getText()))>20)regentotal.setText("20");
					aurepos.setText(regentotal.getText().equals("0")? 0+"":regentab[0][Integer.parseInt(regentotal.getText())-1]);
					sansrepos.setText(regentotal.getText().equals("0")? 0+"":regentab[1][Integer.parseInt(regentotal.getText())-1]);
					reductionmalus.setText(regentotal.getText().equals("0")? 0+"":regentab[2][Integer.parseInt(regentotal.getText())-1]);
					regenspe.setText(regentotal.getText().equals("0")? 0+"":regentab[3][Integer.parseInt(regentotal.getText())-1]);
				}
			}
		});
		avregen3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(avregen3.isSelected()){
					avregen1.setSelected(false);
					avregen2.setSelected(false);
					regentotal.setText((Integer.parseInt(regenbase.getText())+(int)regenspecial.getValue()+6)+"");
					if((Integer.parseInt(regentotal.getText()))>20)regentotal.setText("20");
					aurepos.setText(regentotal.getText().equals("0")? 0+"":regentab[0][Integer.parseInt(regentotal.getText())-1]);
					sansrepos.setText(regentotal.getText().equals("0")? 0+"":regentab[1][Integer.parseInt(regentotal.getText())-1]);
					reductionmalus.setText(regentotal.getText().equals("0")? 0+"":regentab[2][Integer.parseInt(regentotal.getText())-1]);
					regenspe.setText(regentotal.getText().equals("0")? 0+"":regentab[3][Integer.parseInt(regentotal.getText())-1]);
				}else{
					regentotal.setText((Integer.parseInt(regenbase.getText())+(int)regenspecial.getValue())+"");
					if((Integer.parseInt(regentotal.getText()))>20)regentotal.setText("20");
					aurepos.setText(regentotal.getText().equals("0")? 0+"":regentab[0][Integer.parseInt(regentotal.getText())-1]);
					sansrepos.setText(regentotal.getText().equals("0")? 0+"":regentab[1][Integer.parseInt(regentotal.getText())-1]);
					reductionmalus.setText(regentotal.getText().equals("0")? 0+"":regentab[2][Integer.parseInt(regentotal.getText())-1]);
					regenspe.setText(regentotal.getText().equals("0")? 0+"":regentab[3][Integer.parseInt(regentotal.getText())-1]);
				}
			}
		});
		
		panregen2.add(new JLabel("Au Repos"));
		panregen2.add(aurepos);
		panregen2.add(new JLabel("Sans Repos"));
		panregen2.add(sansrepos);
		panregen2.add(new JLabel("Reduction malus"));
		panregen2.add(reductionmalus);
		regen.add(panregen1);
		regen.add(panregen2);
		regen.add(regenspe);
		
		
		
		basebutt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(basebutt.isSelected()){
					for(int i=0; i<8;i++){
						caracpricipal.set(i,(int)caracpricipalbase.get(i).getValue());
						caracmod.get(i).setText(""+mod.get((int)caracpricipalbase.get(i).getValue()-1));
						caracpricipalbase.get(i).setValue((int)caracpricipalbase.get(i).getValue()-1);
						caracpricipalbase.get(i).setValue((int)caracpricipalbase.get(i).getValue()+1);
					}
				}else{
					for(int i=0; i<8;i++){
						caracpricipal.set(i,(int)caracpricipalact.get(i).getValue());;
						caracmod.get(i).setText(""+mod.get((int)caracpricipalact.get(i).getValue()-1));
						caracpricipalact.get(i).setValue((int)caracpricipalact.get(i).getValue()-1);
						caracpricipalact.get(i).setValue((int)caracpricipalact.get(i).getValue()+1);
					}
				}
			}
		});
		actbutt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(basebutt.isSelected()){
					for(int i=0; i<8;i++){
						caracpricipal.set(i,(int)caracpricipalbase.get(i).getValue());
						caracmod.get(i).setText(""+mod.get((int)caracpricipalbase.get(i).getValue()-1));
						caracpricipalbase.get(i).setValue((int)caracpricipalbase.get(i).getValue()-1);
						caracpricipalbase.get(i).setValue((int)caracpricipalbase.get(i).getValue()+1);
					}
				}else{
					for(int i=0; i<8;i++){
						caracpricipal.set(i,(int)caracpricipalact.get(i).getValue());
						caracmod.get(i).setText(""+mod.get((int)caracpricipalact.get(i).getValue()-1));
						caracpricipalact.get(i).setValue((int)caracpricipalact.get(i).getValue()-1);
						caracpricipalact.get(i).setValue((int)caracpricipalact.get(i).getValue()+1);
					}
				}
			}
		});

		caracpricipalbase.get(0).addChangeListener(new MyChangeListenerAgilite(this));
		caracpricipalbase.get(1).addChangeListener(new MyChangeListenerConstitution(this));
		caracpricipalbase.get(2).addChangeListener(new MyChangeListenerDexterite(this));
		caracpricipalbase.get(3).addChangeListener(new MyChangeListenerForce(this));
		caracpricipalbase.get(4).addChangeListener(new MyChangeListenerIntelligence(this));
		caracpricipalbase.get(5).addChangeListener(new MyChangeListenerPerception(this));
		caracpricipalbase.get(6).addChangeListener(new MyChangeListenerPouvoir(this));
		caracpricipalbase.get(7).addChangeListener(new MyChangeListenerVolonte(this));
		caracpricipalact.get(0).addChangeListener(new MyChangeListenerAgilite(this));
		caracpricipalact.get(1).addChangeListener(new MyChangeListenerConstitution(this));
		caracpricipalact.get(2).addChangeListener(new MyChangeListenerDexterite(this));
		caracpricipalact.get(3).addChangeListener(new MyChangeListenerForce(this));
		caracpricipalact.get(4).addChangeListener(new MyChangeListenerIntelligence(this));
		caracpricipalact.get(5).addChangeListener(new MyChangeListenerPerception(this));
		caracpricipalact.get(6).addChangeListener(new MyChangeListenerPouvoir(this));
		caracpricipalact.get(7).addChangeListener(new MyChangeListenerVolonte(this));

		general.add(description);
		description.setBounds(0, 0, 730, 150);
		general.add(pdv);
		pdv.setBounds(730-3, 0, 170, 227);
		general.add(resistance);
		resistance.setBounds(730-3+170-3, 0, 425, 200);
		JPanel panl3=new JPanel(new FlowLayout());
		JLabel labelcarac=new JLabel("Caracteristique");
		labelcarac.setFont(title);
		panl3.setBorder(BorderFactory.createMatteBorder(3, 3, 0, 3, Color.BLACK));
		panl3.add(labelcarac);
		general.add(panl3);
		panl3.setBounds(0, 150-3, 250, 50);
		general.add(carac);	
		carac.setBounds(0, 150-3+50, 250, 210);
		general.add(fatigue);
		fatigue.setBounds(250-3, 150-3, 110, 260);
		general.add(regen);
		regen.setBounds(250-3+110, 227-3, 500, 100);
	}

	public void init(){
		xplvl= new ArrayList<Integer>();
		xplvl.add(0);
		xplvl.add(100);
		xplvl.add(225);
		xplvl.add(375);
		xplvl.add(550);	
		xplvl.add(750);
		xplvl.add(975);
		xplvl.add(1225);
		xplvl.add(1500);
		xplvl.add(1800);
		xplvl.add(2125);
		xplvl.add(2475);
		xplvl.add(2850);
		xplvl.add(3250);
		xplvl.add(3675);


		classe= new JComboBox<String>();
		classe.addItem("Guerrier");
		classe.addItem("Guerrier Acrobate");
		classe.addItem("Paladin");
		classe.addItem("Paladin Noir");
		classe.addItem("Maitre d'Arme");
		classe.addItem("Virtuose Martial");
		classe.addItem("Tao");
		classe.addItem("Explorateur");
		classe.addItem("Ombre");
		classe.addItem("Voleur");		
		classe.addItem("Assassin");
		classe.addItem("Sorcier");
		classe.addItem("Mage de Bataille");
		classe.addItem("Illusionniste");
		classe.addItem("Sorcier Mentaliste");
		classe.addItem("Convocateur");
		classe.addItem("Guerrier Convocateur");
		classe.addItem("Mentaliste");
		classe.addItem("Guerrier Mentaliste");
		classe.addItem("Touche-a-Tout");	


		race= new JComboBox<String>();
		race.addItem("Humain");
		race.addItem("Sylvain");
		race.addItem("Jayan");
		race.addItem("D'Anjayni");
		race.addItem("Ebudan");
		race.addItem("Daimah");
		race.addItem("Duk'Zarist");
		race.addItem("Devas");
		race.addItem("Vetala");
		race.addItem("Tuan Dalyr");
		race.addItem("Turak");	


		tp=new int[20][4];
		tp[0][0]=20;
		tp[0][1]=60;
		tp[0][2]=5;
		tp[0][3]=15;
		tp[1][0]=40;
		tp[1][1]=60;
		tp[1][2]=10;
		tp[1][3]=20;
		tp[2][0]=60;
		tp[2][1]=100;
		tp[2][2]=20;
		tp[2][3]=30;
		tp[3][0]=80;
		tp[3][1]=120;
		tp[3][2]=20;
		tp[3][3]=50;
		tp[4][0]=100;
		tp[4][1]=140;
		tp[4][2]=30;
		tp[4][3]=50;
		tp[5][0]=110;
		tp[5][1]=150;
		tp[5][2]=30;
		tp[5][3]=60;
		tp[6][0]=120;
		tp[6][1]=160;
		tp[6][2]=35;
		tp[6][3]=70;
		tp[7][0]=130;
		tp[7][1]=160;
		tp[7][2]=40;
		tp[7][3]=80;		
		tp[8][0]=140;
		tp[8][1]=170;
		tp[8][2]=40;
		tp[8][3]=90;
		tp[9][0]=140;
		tp[9][1]=180;
		tp[9][2]=50;
		tp[9][3]=100;
		tp[10][0]=150;
		tp[10][1]=180;
		tp[10][2]=50;
		tp[10][3]=120;
		tp[11][0]=150;
		tp[11][1]=180;
		tp[11][2]=50;
		tp[11][3]=140;
		tp[12][0]=160;
		tp[12][1]=190;
		tp[12][2]=50;
		tp[12][3]=150;
		tp[13][0]=160;
		tp[13][1]=200;
		tp[13][2]=60;
		tp[13][3]=180;
		tp[14][0]=170;
		tp[14][1]=210;
		tp[14][2]=70;
		tp[14][3]=220;
		tp[15][0]=170;
		tp[15][1]=210;
		tp[15][2]=80;
		tp[15][3]=240;
		tp[16][0]=180;
		tp[16][1]=220;
		tp[16][2]=90;
		tp[16][3]=260;
		tp[17][0]=190;
		tp[17][1]=230;
		tp[17][2]=100;
		tp[17][3]=280;
		tp[18][0]=200;
		tp[18][1]=240;
		tp[18][2]=110;
		tp[18][3]=320;
		tp[19][0]=210;
		tp[19][1]=260;
		tp[19][2]=120;
		tp[19][3]=450;

		pv=new ArrayList<Integer>();
		pv.add(5);
		pv.add(20);
		pv.add(40);
		pv.add(55);
		pv.add(70);
		pv.add(85);
		pv.add(95);
		pv.add(110);
		pv.add(120);
		pv.add(135);
		pv.add(150);
		pv.add(160);
		pv.add(175);
		pv.add(185);
		pv.add(200);
		pv.add(215);
		pv.add(225);
		pv.add(240);
		pv.add(250);
		pv.add(265);

		mod=new ArrayList<Integer>();
		mod.add(-30);
		mod.add(-20);
		mod.add(-10);
		mod.add(-5);
		mod.add(0);
		mod.add(5);
		mod.add(5);
		mod.add(10);
		mod.add(10);
		mod.add(15);
		mod.add(20);
		mod.add(20);
		mod.add(25);
		mod.add(25);
		mod.add(30);
		mod.add(35);
		mod.add(35);
		mod.add(40);
		mod.add(40);
		mod.add(45);

		caracmod=new ArrayList<JTextField>();
		caracpricipal=new ArrayList<Integer>();
		caracpricipalact=new ArrayList<JSpinner>();
		caracpricipalbase=new ArrayList<JSpinner>();
		basebutt=new JRadioButton();
		actbutt=new JRadioButton();
		for(i=0; i<8;i++){

			caracmod.add(new JTextField("0"));
			caracpricipalbase.add(new JSpinner());
			caracpricipalbase.get(i).setValue(5);
			caracpricipal.add((int)caracpricipalbase.get(i).getValue());
			caracpricipalact.add(new JSpinner());
			caracpricipalact.get(i).setValue(5);
			caracpricipal.add((int)caracpricipalact.get(i).getValue());
		}	
		
		regentab= new String [4][20];
		regentab[0][0]="10 par jour";
		regentab[0][1]="20 par jour";
		regentab[0][2]="30 par jour";
		regentab[0][3]="40 par jour";
		regentab[0][4]="50 par jour";
		regentab[0][5]="75 par jour";
		regentab[0][6]="100 par jour";
		regentab[0][7]="250 par jour";
		regentab[0][8]="500 par jour";
		regentab[0][9]="1 par minute";
		regentab[0][10]="2 par minute";
		regentab[0][11]="5 par minute";	
		regentab[0][12]="10 par minute";
		regentab[0][13]="1 par round";
		regentab[0][14]="5 par round";
		regentab[0][15]="10 par round";
		regentab[0][16]="25 par round";
		regentab[0][17]="50 par round";
		regentab[0][18]="100 par round";
		regentab[0][19]="250 par round";
		regentab[1][0]="5 par jour";
		regentab[1][1]="10 par jour";
		regentab[1][2]="15 par jour";
		regentab[1][3]="20 par jour";
		regentab[1][4]="25 par jour";
		regentab[1][5]="30 par jour";
		regentab[1][6]="50 par jour";
		regentab[1][7]="100 par jour";
		regentab[1][8]="200 par jour";
		regentab[1][9]="1 par minute";
		regentab[1][10]="2 par minute";
		regentab[1][11]="5 par minute";	
		regentab[1][12]="10 par minute";
		regentab[1][13]="1 par round";
		regentab[1][14]="5 par round";
		regentab[1][15]="10 par round";
		regentab[1][16]="25 par round";
		regentab[1][17]="50 par round";
		regentab[1][18]="100 par round";
		regentab[1][19]="250 par round";
		regentab[2][0]="-5 par jour";
		regentab[2][1]="-5 par jour";
		regentab[2][2]="-5 par jour";
		regentab[2][3]="-10 par jour";
		regentab[2][4]="-10 par jour";
		regentab[2][5]="-15 par jour";
		regentab[2][6]="-20 par jour";
		regentab[2][7]="-25 par jour";
		regentab[2][8]="-30 par jour";
		regentab[2][9]="-40 par jour";
		regentab[2][10]="-50 par jour";
		regentab[2][11]="-5 par heure";	
		regentab[2][12]="-10 par heure";
		regentab[2][13]="-15 par heure";
		regentab[2][14]="-20 par heure";
		regentab[2][15]="-10 par minute";
		regentab[2][16]="-10 par round";
		regentab[2][17]="-25 par round";
		regentab[2][18]="Tous";
		regentab[2][19]="Tous";
		regentab[3][0]="";
		regentab[3][1]="";
		regentab[3][2]="";
		regentab[3][3]="";
		regentab[3][4]="Les blessure ne laisse pas de cicatrices.";
		regentab[3][5]="Le personnages ne souffre pas des effets des hemoragie.";
		regentab[3][6]="Les membres emputes proprement se recollent en une semaine.";
		regentab[3][7]="Les membres emputes proprement se recollent en 5 jours.";
		regentab[3][8]="Les membres emputes proprement se recollent en 3 jours.";
		regentab[3][9]="Les membres emputes proprement se recollent en 1 jours.";
		regentab[3][10]="Tout membres emputes se recollent en une semaine.";
		regentab[3][11]="Tout membres emputes se recollent en 3 jours.";	
		regentab[3][12]="Tout membres emputes se recollent en 1jours.";
		regentab[3][13]="Tout membres emputes se recollent en quelque heures.";
		regentab[3][14]="Tout membres emputes se recollent en un jour et repousse en une semaine.";
		regentab[3][15]="Tout membres emputes repousse en un jour.";
		regentab[3][16]="Tout membres emputes repousse en quelque minutes.";
		regentab[3][17]="Tout membres emputes repousse en quelque tours.";
		regentab[3][18]="Tout membres emputes repousse en un round (même la tête).";
		regentab[3][19]="Tous les Critiques physiques sont annulés";
		
		

		BufferedReader costclass;
		try {
			costclass = new BufferedReader(new FileReader("src/costclass"));
			costclasse=new int[20][30];
			String ligne=costclass.readLine();
			String l;
			for(int i=0; i<costclasse.length; i++){
				l=ligne;
				for(int j=0; j<costclasse[i].length;j++){
					costclasse[i][j]=Integer.parseInt(ligne.substring(0, ligne.indexOf(";")));
					l=l.substring(ligne.indexOf(';'));
				}
			}
			costclass.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}


class MyChangeListenerAgilite implements ChangeListener{

	Feuille f;

	public MyChangeListenerAgilite(Feuille f){
		this.f=f;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(f.basebutt.isSelected()){
			if ((int)f.caracpricipalbase.get(0).getValue()<1){
				f.caracpricipalbase.get(0).setValue(1);
			}else if((int) f.caracpricipalbase.get(0).getValue()>20){
				f.caracpricipalbase.get(0).setValue(20);
			}
			f.caracpricipal.set(0,(int)f.caracpricipalbase.get(0).getValue());
			f.caracmod.get(0).setText(""+f.mod.get((int)f.caracpricipalbase.get(0).getValue()-1));
		}else{
			if ((int)f.caracpricipalact.get(0).getValue()<1){
				f.caracpricipalact.get(0).setValue(1);
			}else if((int) f.caracpricipalact.get(0).getValue()>20){
				f.caracpricipalact.get(0).setValue(20);
			}
			f.caracpricipal.set(0,(int)f.caracpricipalact.get(0).getValue());
			f.caracmod.get(0).setText(""+f.mod.get((int)f.caracpricipalact.get(0).getValue()-1));
		}
		int dexagi=f.caracpricipal.get(0)+f.caracpricipal.get(2);
		f.acttour.setText((dexagi<=10? 1: dexagi<=14? 2: dexagi<=19? 3: dexagi<=22? 4: dexagi<=25? 5: dexagi<=28? 6: dexagi<=31? 8:10)+"");
	}

}


class MyChangeListenerConstitution implements ChangeListener{

	Feuille f;

	public MyChangeListenerConstitution(Feuille f){
		this.f=f;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(f.basebutt.isSelected()){
			if ((int)f.caracpricipalbase.get(1).getValue()<1){
				f.caracpricipalbase.get(1).setValue(1);
			}else if((int) f.caracpricipalbase.get(1).getValue()>20){
				f.caracpricipalbase.get(1).setValue(20);
			}
			f.taille.setValue(((int)f.caracpricipalbase.get(1).getValue()-f.caracpricipal.get(1))+(int)f.taille.getValue());
			f.caracpricipal.set(1,(int)f.caracpricipalbase.get(1).getValue());
		}else{
			if ((int)f.caracpricipalact.get(1).getValue()<1){
				f.caracpricipalact.get(1).setValue(1);
			}else if((int) f.caracpricipalact.get(1).getValue()>20){
				f.caracpricipalact.get(1).setValue(20);
			}
			f.taille.setValue(((int)f.caracpricipalact.get(1).getValue()-f.caracpricipal.get(1))+(int)f.taille.getValue());
			f.caracpricipal.set(1,(int)f.caracpricipalact.get(1).getValue());
		}
		f.caracmod.get(1).setText(""+f.mod.get((int)f.caracpricipal.get(1)-1));
		f.basepv.setText(""+f.pv.get(f.caracpricipal.get(1)-1));
		f.pvtotal.setText(""+(Integer.parseInt(f.basepv.getText())+Integer.parseInt(f.pvniveau.getText())+(int)f.modpv.getValue()*f.caracpricipal.get(1)));
		f.modphy.setText(""+f.mod.get(f.caracpricipal.get(1)-1));
		f.modmal.setText(""+f.mod.get(f.caracpricipal.get(1)-1));
		f.modpoi.setText(""+f.mod.get(f.caracpricipal.get(1)-1));
		f.fatbase.setText((int)f.caracpricipalbase.get(1).getValue()+"");
		f.fattotal.setText((Integer.parseInt(f.fatbase.getText())+(f.avfatigue1.isSelected()? 3:0)+(f.avfatigue2.isSelected()? 6:0)+(f.avfatigue3.isSelected()? 9:0))+"");


		f.totalphy.setText((Integer.parseInt(f.pres.getText())+Integer.parseInt(f.modphy.getText())+
				(f.avdes[1][0].isSelected()? 25:0) +
				(f.avdes[1][1].isSelected()? 50:0)) /
				(f.avdes[1][2].isSelected()? 2:1 )+"");
		f.totalmal.setText((Integer.parseInt(f.pres.getText())+Integer.parseInt(f.modmal.getText())+
				(f.avdes[2][0].isSelected()? 25:0) +
				(f.avdes[2][1].isSelected()? 50:0)) /
				(f.avdes[2][2].isSelected()? 2:1 )+"");
		f.totalpoi.setText((Integer.parseInt(f.pres.getText())+Integer.parseInt(f.modpoi.getText())+
				(f.avdes[3][0].isSelected()? 25:0) +
				(f.avdes[3][1].isSelected()? 50:0)) /
				(f.avdes[3][2].isSelected()? 2:1 )+"");
		
		f.regenbase.setText(""+((f.caracpricipal.get(1)<3? 0:f.caracpricipal.get(1)<8? 1:f.caracpricipal.get(1)<10? 2:f.caracpricipal.get(1)>18? 12:f.caracpricipal.get(1)-7)+(f.avregen1.isSelected()? 2:f.avregen2.isSelected()? 4:f.avregen3.isSelected()? 6:0)));
		f.regentotal.setText((Integer.parseInt(f.regenbase.getText())+(int)f.regenspecial.getValue())+"");
		if((Integer.parseInt(f.regentotal.getText()))>20)f.regentotal.setText("20");
		f.aurepos.setText(f.regentotal.getText().equals("0")? 0+"":f.regentab[0][Integer.parseInt(f.regentotal.getText())-1]);
		f.sansrepos.setText(f.regentotal.getText().equals("0")? 0+"":f.regentab[1][Integer.parseInt(f.regentotal.getText())-1]);
		f.reductionmalus.setText(f.regentotal.getText().equals("0")? 0+"":f.regentab[2][Integer.parseInt(f.regentotal.getText())-1]);
		f.regenspe.setText(f.regentotal.getText().equals("0")? 0+"":f.regentab[3][Integer.parseInt(f.regentotal.getText())-1]);
	}
}



class MyChangeListenerDexterite implements ChangeListener{

	Feuille f;

	public MyChangeListenerDexterite(Feuille f){
		this.f=f;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(f.basebutt.isSelected()){
			if ((int)f.caracpricipalbase.get(2).getValue()<1){
				f.caracpricipalbase.get(2).setValue(1);
			}else if((int) f.caracpricipalbase.get(2).getValue()>20){
				f.caracpricipalbase.get(2).setValue(20);
			}
			f.caracpricipal.set(2,(int)f.caracpricipalbase.get(2).getValue());
			f.caracmod.get(2).setText(""+f.mod.get((int)f.caracpricipalbase.get(2).getValue()-1));
		}else{
			if ((int)f.caracpricipalact.get(2).getValue()<1){
				f.caracpricipalact.get(2).setValue(1);
			}else if((int) f.caracpricipalact.get(2).getValue()>20){
				f.caracpricipalact.get(2).setValue(20);
			}
			f.caracpricipal.set(2,(int)f.caracpricipalact.get(2).getValue());
			f.caracmod.get(2).setText(""+f.mod.get((int)f.caracpricipalact.get(2).getValue()-1));
		}
		int dexagi=f.caracpricipal.get(0)+f.caracpricipal.get(2);
		f.acttour.setText((dexagi<=10? 1: dexagi<=14? 2: dexagi<=19? 3: dexagi<=22? 4: dexagi<=25? 5: dexagi<=28? 6: dexagi<=31? 8:10)+"");
	}

}

class MyChangeListenerForce implements ChangeListener{

	Feuille f;

	public MyChangeListenerForce(Feuille f){
		this.f=f;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(f.basebutt.isSelected()){
			if ((int)f.caracpricipalbase.get(3).getValue()<1){
				f.caracpricipalbase.get(3).setValue(1);
			}else if((int) f.caracpricipalbase.get(3).getValue()>20){
				f.caracpricipalbase.get(3).setValue(20);
			}
			f.taille.setValue(((int)f.caracpricipalbase.get(3).getValue()-f.caracpricipal.get(3))+(int)f.taille.getValue());
			f.caracpricipal.set(3,(int)f.caracpricipalbase.get(3).getValue());
			f.caracmod.get(3).setText(""+f.mod.get((int)f.caracpricipalbase.get(3).getValue()-1));
		}else{
			if ((int)f.caracpricipalact.get(3).getValue()<1){
				f.caracpricipalact.get(3).setValue(1);
			}else if((int) f.caracpricipalact.get(3).getValue()>20){
				f.caracpricipalact.get(3).setValue(20);
			}
			f.taille.setValue(((int)f.caracpricipalact.get(3).getValue()-f.caracpricipal.get(3))+(int)f.taille.getValue());
			f.caracpricipal.set(3,(int)f.caracpricipalact.get(3).getValue());
			f.caracmod.get(3).setText(""+f.mod.get((int)f.caracpricipalact.get(3).getValue()-1));
		}
	}

}

class MyChangeListenerIntelligence implements ChangeListener{

	Feuille f;

	public MyChangeListenerIntelligence(Feuille f){
		this.f=f;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(f.basebutt.isSelected()){
			if ((int)f.caracpricipalbase.get(4).getValue()<1){
				f.caracpricipalbase.get(4).setValue(1);
			}else if((int) f.caracpricipalbase.get(4).getValue()>20){
				f.caracpricipalbase.get(4).setValue(20);
			}
			f.caracpricipal.set(4,(int)f.caracpricipalbase.get(4).getValue());
			f.caracmod.get(4).setText(""+f.mod.get((int)f.caracpricipalbase.get(4).getValue()-1));
		}else{
			if ((int)f.caracpricipalact.get(4).getValue()<1){
				f.caracpricipalact.get(4).setValue(1);
			}else if((int) f.caracpricipalact.get(4).getValue()>20){
				f.caracpricipalact.get(4).setValue(20);
			}
			f.caracpricipal.set(4,(int)f.caracpricipalact.get(4).getValue());
			f.caracmod.get(4).setText(""+f.mod.get((int)f.caracpricipalact.get(4).getValue()-1));
		}
	}

}

class MyChangeListenerPerception implements ChangeListener{

	Feuille f;

	public MyChangeListenerPerception(Feuille f){
		this.f=f;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(f.basebutt.isSelected()){
			if ((int)f.caracpricipalbase.get(5).getValue()<1){
				f.caracpricipalbase.get(5).setValue(1);
			}else if((int) f.caracpricipalbase.get(5).getValue()>20){
				f.caracpricipalbase.get(5).setValue(20);
			}
			f.caracpricipal.set(5,(int)f.caracpricipalbase.get(5).getValue());
			f.caracmod.get(5).setText(""+f.mod.get((int)f.caracpricipalbase.get(5).getValue()-1));
		}else{
			if ((int)f.caracpricipalact.get(5).getValue()<1){
				f.caracpricipalact.get(5).setValue(1);
			}else if((int) f.caracpricipalact.get(5).getValue()>20){
				f.caracpricipalact.get(5).setValue(20);
			}
			f.caracpricipal.set(5,(int)f.caracpricipalact.get(5).getValue());
			f.caracmod.get(5).setText(""+f.mod.get((int)f.caracpricipalact.get(5).getValue()-1));
		}
	}

}

class MyChangeListenerPouvoir implements ChangeListener{

	Feuille f;

	public MyChangeListenerPouvoir(Feuille f){
		this.f=f;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(f.basebutt.isSelected()){
			if ((int)f.caracpricipalbase.get(6).getValue()<1){
				f.caracpricipalbase.get(6).setValue(1);
			}else if((int) f.caracpricipalbase.get(6).getValue()>20){
				f.caracpricipalbase.get(6).setValue(20);
			}
			f.caracpricipal.set(6,(int)f.caracpricipalbase.get(6).getValue());
			f.caracmod.get(6).setText(""+f.mod.get((int)f.caracpricipalbase.get(6).getValue()-1));
		}else{
			if ((int)f.caracpricipalact.get(6).getValue()<1){
				f.caracpricipalact.get(6).setValue(1);
			}else if((int) f.caracpricipalact.get(6).getValue()>20){
				f.caracpricipalact.get(6).setValue(20);
			}
			f.caracpricipal.set(6,(int)f.caracpricipalact.get(6).getValue());
			f.caracmod.get(6).setText(""+f.mod.get((int)f.caracpricipalact.get(6).getValue()-1));
		}
		f.modmys.setText(""+f.mod.get(f.caracpricipal.get(6)-1));
		f.totalmys.setText((Integer.parseInt(f.pres.getText())+Integer.parseInt(f.modmys.getText())+
				(f.avdes[4][0].isSelected()? 25:0) +
				(f.avdes[4][1].isSelected()? 50:0)) /
				(f.avdes[4][2].isSelected()? 2:1 )+"");
	}

}

class MyChangeListenerVolonte implements ChangeListener{

	Feuille f;

	public MyChangeListenerVolonte(Feuille f){
		this.f=f;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(f.basebutt.isSelected()){
			if ((int)f.caracpricipalbase.get(7).getValue()<1){
				f.caracpricipalbase.get(7).setValue(1);
			}else if((int) f.caracpricipalbase.get(7).getValue()>20){
				f.caracpricipalbase.get(7).setValue(20);
			}
			f.caracpricipal.set(7,(int)f.caracpricipalbase.get(7).getValue());
			f.caracmod.get(7).setText(""+f.mod.get((int)f.caracpricipalbase.get(7).getValue()-1));
		}else{
			if ((int)f.caracpricipalact.get(7).getValue()<1){
				f.caracpricipalact.get(7).setValue(1);
			}else if((int) f.caracpricipalact.get(7).getValue()>20){
				f.caracpricipalact.get(7).setValue(20);
			}
			f.caracpricipal.set(7,(int)f.caracpricipalact.get(7).getValue());
			f.caracmod.get(7).setText(""+f.mod.get((int)f.caracpricipalact.get(7).getValue()-1));
		}
		f.modpsy.setText(""+f.mod.get(f.caracpricipal.get(7)-1));
		f.totalpsy.setText((Integer.parseInt(f.pres.getText())+Integer.parseInt(f.modpsy.getText())+
				(f.avdes[5][0].isSelected()? 25:0) +
				(f.avdes[5][1].isSelected()? 50:0)) /
				(f.avdes[5][2].isSelected()? 2:1 )+"");
	}

}


