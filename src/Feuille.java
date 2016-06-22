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
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Feuille {
	JFrame f= new JFrame("Fiche Perso");
	ArrayList<Integer> xplvl, pv, mod, caracprim;
	ArrayList<JSpinner> caracprimbase,caracprimact,caracmod;
	JComboBox<String> classe,race;
	JTextField nom,xp,cheveux,yeux,pfrest,actuels;
	JSpinner niveau,age,taille,tail,poids,app,modpv,ptrept;
	JRadioButton homme, femme, ti;
	int[][] tp,costclasse;
	int lvl,exp,t,i;
	Font title, soustitle;

	public Feuille(){
		f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		init();

		JPanel general = new JPanel();
		general.setLayout(new GridLayout(2, 2));	
		general(general);
		onglets.addTab("Général", general);	

		JPanel combat = new JPanel();
		onglets.addTab("Combat", combat);		 	 
		onglets.setOpaque(true);

		f.getContentPane().add(onglets);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}

	public Feuille(String path) {
		f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		init();

		JPanel general = new JPanel();
		general.setLayout(new GridLayout(2, 2));	
		general(general);
		onglets.addTab("Général", general);	

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
		f.getContentPane().add(onglets);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}	

	public void general(JPanel general){			
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
		homme = new JRadioButton("Homme");
		femme = new JRadioButton("Femme");
		sexe.add(homme);
		sexe.add(femme);
		pan1.add(homme);
		pan1.add(femme);


		JPanel pan2= new JPanel(new FlowLayout());
		pan2.add(new JLabel("âge"));
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
		SpinnerNumberModel taillemodel= new SpinnerNumberModel();
		taillemodel.setMinimum(2);
		taillemodel.setMinimum(30);
		taille= new JSpinner();
		taille.setValue(caracprim.get(1)+caracprim.get(3));
		taille.setPreferredSize(new Dimension(40,20));
		ti= new JRadioButton();
		taille.setEnabled(ti.isSelected());
		ti.addActionListener(new ActionListener() {					
			@Override
			public void actionPerformed(ActionEvent e) {
				taille.setEnabled(ti.isSelected());
				taillemodel.setMinimum(t-5<2? 2:t-5);
				taillemodel.setMaximum(t+5);
			}
		});
		t=(int)taille.getValue();
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
				if((int)taille.getValue()<(int)taillemodel.getMinimum() ||  (int)taille.getValue()>(int)taillemodel.getMaximum()){
					taille.setValue(t);
				}
				t=(int)taille.getValue();
				if(t<20){						
					tailmodel.setMaximum(tp[t-2][1]);						
					tailmodel.setMinimum(tp[t-2][0]);						
					tailmodel.setValue((tp[t-2][0]+tp[t-2][1])/2);						tail.setValue(tailmodel.getValue());						poidsmodel.setMaximum(tp[t-2][1]);						poidsmodel.setMinimum(tp[t-2][0]);						poidsmodel.setValue((tp[t-2][0]+tp[t-2][1])/2);						poids.setValue(poidsmodel.getValue());
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
		pan2.add(taille);
		pan2.add(ti);
		pan2.add(new JLabel("Taille"));
		pan2.add(tail);
		pan2.add(new JLabel("Poids"));
		pan2.add(poids);
		pan2.add(new JLabel("Apparence"));
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
		niveau.addChangeListener(new ChangeListener() {					
			@Override
			public void stateChanged(ChangeEvent e) {
				ns.setText(race.getSelectedItem().equals("Tuan Dalyr")? 
						lvl+2<=xplvl.size()-1? ""+xplvl.get(lvl+2): ""+((450*(lvl-14+2))+xplvl.get(xplvl.size()-1)):
							lvl<=xplvl.size()-1? ""+xplvl.get(lvl): ""+((450*(lvl-14))+xplvl.get(xplvl.size()-1)));
			}
		});
		race.addActionListener(new ActionListener() {					
			@Override
			public void actionPerformed(ActionEvent e) {
				ns.setText(race.getSelectedItem().equals("Tuan Dalyr")? 
						lvl+2<=xplvl.size()-1? ""+xplvl.get(lvl+2): ""+((450*(lvl-14+2))+xplvl.get(xplvl.size()-1)):
							lvl<=xplvl.size()-1? ""+xplvl.get(lvl): ""+((450*(lvl-14))+xplvl.get(xplvl.size()-1)));			
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
		pan3.add(new JLabel("à Dépenser"));
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
		JPanel pan4=new JPanel(new GridLayout(5, 3));
		pan4.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
		JLabel l1=new JLabel("Coût Multiplcateur");
		l1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		pan4.add(l1);
		JTextField cost=new JTextField();
		cost.setEditable(false);
		cost.setText(costclasse[classe.getSelectedIndex()][0]+"");
		cost.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		pan4.add(cost);
		pan4.add(new JLabel("Base"));
		JTextField basepv=new JTextField();
		basepv.setEditable(false);
		basepv.setText(""+pv.get(caracprim.get(1)-1));
		pan4.add(basepv);
		pan4.add(new JLabel("Classe"));
		JTextField pvniveau=new JTextField();
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
		JTextField pvtotal=new JTextField();
		pvtotal.setEditable(false);
		pvtotal.setText(""+(Integer.parseInt(basepv.getText())+Integer.parseInt(pvniveau.getText())+(int)modpv.getValue()*caracprim.get(1)));
		pan4.add(pvtotal);
		pdv.add(pan4);
		JPanel pann=new JPanel(new FlowLayout());
		JLabel l2=new JLabel("Actuels");
		l2.setFont(soustitle);
		pann.add(l2);
		pdv.add(pann);
		actuels=new JTextField();
		pdv.add(actuels);
		
		JPanel résistance= new JPanel();
		résistance.setLayout(new BoxLayout(résistance, BoxLayout.X_AXIS));
		résistance.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
		JPanel résistext= new JPanel(new GridLayout(7, 1));
		JPanel résisbutton= new JPanel(new GridLayout(7, 1));
		JLabel labresi=new JLabel("Résistance");
		labresi.setFont(title);
		labresi.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		résistext.add(labresi);
		résistext.add(new JLabel("Présence"));
		résistext.add(new JLabel("Physique"));
		résistext.add(new JLabel("Maladie"));
		résistext.add(new JLabel("Poisons"));
		résistext.add(new JLabel("Mystique"));	
		résistext.add(new JLabel("Psychique"));
		JPanel résisnbr= new JPanel(new GridLayout(7, 4));
		JPanel pannbase=new JPanel(new FlowLayout());
		JLabel labbase=new JLabel("Base");
		labbase.setFont(soustitle);
		pannbase.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		pannbase.add(labbase);
		résisnbr.add(pannbase);
		JPanel panncarac=new JPanel(new FlowLayout());
		JLabel labcarac=new JLabel("Carac");
		labcarac.setFont(soustitle);
		panncarac.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		panncarac.add(labcarac);
		résisnbr.add(panncarac);
		JPanel pannmod=new JPanel(new FlowLayout());
		JLabel labmod=new JLabel("Mod");
		labmod.setFont(soustitle);
		pannmod.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		pannmod.add(labmod);
		résisnbr.add(pannmod);
		JPanel panntotal=new JPanel(new FlowLayout());
		JLabel labtotal=new JLabel("Total");
		labtotal.setFont(soustitle);
		panntotal.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		panntotal.add(labtotal);
		résisnbr.add(panntotal);
		JPanel pannspé=new JPanel(new FlowLayout());
		JLabel labspé=new JLabel("Spé");
		labspé.setFont(soustitle);
		pannspé.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		pannspé.add(labspé);
		résisbutton.add(pannspé);
		JTextField pres= new JTextField();
		pres.setEditable(false);
		pres.setText(""+(30+5*(int)niveau.getValue()));
		pres.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(pres);
		JTextField carac1= new JTextField();
		carac1.setEditable(false);
		carac1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		carac1.setBackground(Color.BLACK);
		carac1.setOpaque(true);
		résisnbr.add(carac1);
		JTextField mod1= new JTextField();
		mod1.setEditable(false);
		mod1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		mod1.setBackground(Color.BLACK);
		mod1.setOpaque(true);
		résisnbr.add(mod1);
		JTextField total1=new JTextField(pres.getText());
		total1.setEditable(false);
		total1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(total1);
		JRadioButton avun1=new JRadioButton();
		avun1.setEnabled(false);
		avun1.setBackground(Color.GRAY);
		JRadioButton avdeux1=new JRadioButton();
		avdeux1.setEnabled(false);
		avdeux1.setBackground(Color.GRAY);
		JRadioButton desavun1=new JRadioButton();
		desavun1.setEnabled(false);
		desavun1.setBackground(Color.GRAY);
		JPanel pann1=new JPanel(new FlowLayout());
		pann1.add(avun1);
		pann1.add(avdeux1);
		pann1.add(desavun1);
		pann1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisbutton.add(pann1);
		
		JTextField phy= new JTextField();
		phy.setEditable(false);
		phy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		phy.setText(""+total1.getText());
		résisnbr.add(phy);
		JTextField caracphy= new JTextField();
		caracphy.setEditable(false);
		caracphy.setText("CON");
		caracphy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(caracphy);
		JTextField modphy= new JTextField();
		modphy.setEditable(false);
		modphy.setText(""+mod.get(caracprim.get(1)));
		modphy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(modphy);
		JRadioButton avunphy=new JRadioButton();
		avunphy.setBackground(Color.GREEN);
		JRadioButton avdeuxphy=new JRadioButton();
		avdeuxphy.setBackground(Color.GREEN);
		JRadioButton desavunphy=new JRadioButton();
		desavunphy.setBackground(Color.RED);
		JPanel pannphy=new JPanel(new FlowLayout());
		JTextField totalphy=new JTextField(""+(
									Integer.parseInt(phy.getText())+
									Integer.parseInt(modphy.getText()+
									(avunphy.isSelected()? 25:
										(avdeuxphy.isSelected()? 50:
											(desavunphy.isSelected()? +0/2:+0))))));
		totalphy.setEditable(false);
		totalphy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(totalphy);
		pannphy.add(avunphy);
		pannphy.add(avdeuxphy);
		pannphy.add(desavunphy);
		pannphy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisbutton.add(pannphy);
		
		JTextField mal= new JTextField();
		mal.setEditable(false);
		mal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		mal.setText(""+total1.getText());
		résisnbr.add(mal);
		JTextField caracmal= new JTextField();
		caracmal.setEditable(false);
		caracmal.setText("CON");
		caracmal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(caracmal);
		JTextField modmal= new JTextField();
		modmal.setEditable(false);
		modmal.setText(""+mod.get(caracprim.get(1)));
		modmal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(modmal);JRadioButton avunmal=new JRadioButton();
		avunmal.setBackground(Color.GREEN);
		JRadioButton avdeuxmal=new JRadioButton();
		avdeuxmal.setBackground(Color.GREEN);
		JRadioButton desavunmal=new JRadioButton();
		desavunmal.setBackground(Color.RED);
		JPanel pannmal=new JPanel(new FlowLayout());
		JTextField totalmal=new JTextField(""+(
									Integer.parseInt(mal.getText())+
									Integer.parseInt(modmal.getText()+
									(avunmal.isSelected()? 25:
										(avdeuxmal.isSelected()? 50:
											(desavunmal.isSelected()? +0/2:+0))))));
		totalmal.setEditable(false);
		totalmal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(totalmal);
		pannmal.add(avunmal);
		pannmal.add(avdeuxmal);
		pannmal.add(desavunmal);
		pannmal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisbutton.add(pannmal);
		
		JTextField poi= new JTextField();
		poi.setEditable(false);
		poi.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		poi.setText(""+total1.getText());
		résisnbr.add(poi);
		JTextField caracpoi= new JTextField();
		caracpoi.setEditable(false);
		caracpoi.setText("CON");
		caracpoi.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(caracpoi);
		JTextField modpoi= new JTextField();
		modpoi.setEditable(false);
		modpoi.setText(""+mod.get(caracprim.get(1)));
		modpoi.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(modpoi);
		JTextField totalpoi=new JTextField(""+(Integer.parseInt(poi.getText())+Integer.parseInt(modpoi.getText())));
		totalpoi.setEditable(false);
		totalpoi.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(totalpoi);
		
		JRadioButton avunpoi=new JRadioButton();
		avunpoi.setBackground(Color.GREEN);
		JRadioButton avdeuxpoi=new JRadioButton();
		avdeuxpoi.setBackground(Color.GREEN);
		JRadioButton desavunpoi=new JRadioButton();
		desavunpoi.setBackground(Color.RED);
		JPanel pannpoi=new JPanel(new FlowLayout());
		pannpoi.add(avunpoi);
		pannpoi.add(avdeuxpoi);
		pannpoi.add(desavunpoi);
		pannpoi.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisbutton.add(pannpoi);
		
		JTextField mys= new JTextField();
		mys.setEditable(false);
		mys.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		mys.setText(""+total1.getText());
		résisnbr.add(mys);
		JTextField caracmys= new JTextField();
		caracmys.setEditable(false);
		caracmys.setText("POU");
		caracmys.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(caracmys);
		JTextField modmys= new JTextField();
		modmys.setEditable(false);
		modmys.setText(""+mod.get(caracprim.get(6)));
		modmys.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(modmys);
		JTextField totalmys=new JTextField(""+(Integer.parseInt(mys.getText())+Integer.parseInt(modmys.getText())));
		totalmys.setEditable(false);
		totalmys.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(totalmys);
		
		JRadioButton avunmys=new JRadioButton();
		avunmys.setBackground(Color.GREEN);
		JRadioButton avdeuxmys=new JRadioButton();
		avdeuxmys.setBackground(Color.GREEN);
		JRadioButton desavunmys=new JRadioButton();
		desavunmys.setBackground(Color.GRAY);
		desavunmys.setEnabled(false);
		JPanel pannmys=new JPanel(new FlowLayout());
		pannmys.add(avunmys);
		pannmys.add(avdeuxmys);
		pannmys.add(desavunmys);
		pannmys.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisbutton.add(pannmys);
		
		JTextField psy= new JTextField();
		psy.setEditable(false);
		psy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		psy.setText(""+total1.getText());
		résisnbr.add(psy);
		JTextField caracpsy= new JTextField();
		caracpsy.setEditable(false);
		caracpsy.setText("VOL");
		caracpsy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(caracpsy);
		JTextField modpsy= new JTextField();
		modpsy.setEditable(false);
		modpsy.setText(""+mod.get(caracprim.get(7)));
		modpsy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(modpsy);
		JTextField totalpsy=new JTextField(""+
					(Integer.parseInt(psy.getText())+
										Integer.parseInt(modpsy.getText())));
		totalpsy.setEditable(false);
		totalpsy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisnbr.add(totalpsy);
		
		JRadioButton avunpsy=new JRadioButton();
		avunpsy.setBackground(Color.GREEN);
		JRadioButton avdeuxpsy=new JRadioButton();
		avdeuxpsy.setBackground(Color.GREEN);
		JRadioButton desavunpsy=new JRadioButton();
		desavunpsy.setBackground(Color.RED);
		JPanel pannpsy=new JPanel(new FlowLayout());
		pannpsy.add(avunpsy);
		pannpsy.add(avdeuxpsy);
		pannpsy.add(desavunpsy);
		pannpsy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		résisbutton.add(pannpsy);
		
		avunphy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(avdeuxphy.isSelected()){
					avdeuxphy.setSelected(false);
				}else if(desavunphy.isSelected()){
					desavunphy.setSelected(false);
				}
				if(avunmal.isSelected()|| avunpoi.isSelected()){
					avunphy.setSelected(true);
				}
			}
		});
		avdeuxphy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(avunphy.isSelected()){
					avunphy.setSelected(false);
				}else if(desavunphy.isSelected()){
					desavunphy.setSelected(false);
				}
				if(avdeuxmal.isSelected()|| avdeuxpoi.isSelected()){
					avdeuxphy.setSelected(true);
				}
			}
		});
		desavunphy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(avdeuxphy.isSelected()){
					avdeuxphy.setSelected(false);
				}else if(avunphy.isSelected()){
					desavunphy.setSelected(false);
				}
				if(avunmal.isSelected()|| avunpoi.isSelected()){
					avunphy.setSelected(true);
				}
			}
		});
		
		avunmal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(avdeuxmal.isSelected()){
					avdeuxmal.setSelected(false);
				}else if(desavunmal.isSelected()){
					desavunmal.setSelected(false);
				}
				if(avunphy.isSelected()|| avunpoi.isSelected()){
					avunmal.setSelected(true);
				}
			}
		});
		avdeuxmal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(avunmal.isSelected()){
					avunmal.setSelected(false);
				}else if(desavunmal.isSelected()){
					desavunmal.setSelected(false);
				}
				if(avdeuxphy.isSelected()|| avdeuxpoi.isSelected()){
					avdeuxmal.setSelected(true);
				}
			}
		});
		desavunmal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(avdeuxmal.isSelected()){
					avdeuxmal.setSelected(false);
				}else if(avunmal.isSelected()){
					desavunmal.setSelected(false);
				}
			}
		});
		
		avunpoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(avdeuxpoi.isSelected()){
					avdeuxpoi.setSelected(false);
				}else if(desavunpoi.isSelected()){
					desavunpoi.setSelected(false);
				}
				if(avunphy.isSelected()|| avunmal.isSelected()){
					avunpoi.setSelected(true);
				}
			}
		});
		avdeuxpoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(avunpoi.isSelected()){
					avunpoi.setSelected(false);
				}else if(desavunpoi.isSelected()){
					desavunpoi.setSelected(false);
				}
				if(avdeuxphy.isSelected()|| avdeuxmal.isSelected()){
					avdeuxpoi.setSelected(true);
				}
			}
		});
		desavunpoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(avdeuxpoi.isSelected()){
					avdeuxpoi.setSelected(false);
				}else if(avunpoi.isSelected()){
					desavunpoi.setSelected(false);
				}
			}
		});
		
		avunmys.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(avdeuxmys.isSelected()){
					avdeuxmys.setSelected(false);
				}else if(desavunmys.isSelected()){
					desavunmys.setSelected(false);
				}
			}
		});
		avdeuxmys.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(avunmys.isSelected()){
					avunmys.setSelected(false);
				}else if(desavunmys.isSelected()){
					desavunmys.setSelected(false);
				}
			}
		});
		desavunmys.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(avdeuxmys.isSelected()){
					avdeuxmys.setSelected(false);
				}else if(avunmys.isSelected()){
					desavunmys.setSelected(false);
				}
			}
		});

		avunpsy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(avdeuxpsy.isSelected()){
					avdeuxpsy.setSelected(false);
				}else if(desavunpsy.isSelected()){
					desavunpsy.setSelected(false);
				}
			}
		});
		avdeuxpsy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(avunpsy.isSelected()){
					avunpsy.setSelected(false);
				}else if(desavunpsy.isSelected()){
					desavunpsy.setSelected(false);
				}
			}
		});
		
		
		résistance.add(résistext);
		résistance.add(résisnbr);
		résistance.add(résisbutton);	
		
		JPanel general1=new JPanel();
		general1.setLayout(new BoxLayout(general1, BoxLayout.X_AXIS));
		general1.add(description);
		general1.add(pdv);
		general1.add(résistance);
		
		
		
		JPanel general2= new JPanel();
		general2.setLayout(new BoxLayout(general2, BoxLayout.Y_AXIS));
		
		JPanel carac=new JPanel(new GridLayout(9, 4));
		carac.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
		ptrept=new JSpinner();
		ptrept.setValue((int)niveau.getValue()/2);
		carac.add(ptrept);
		ButtonGroup caracgroupbutt=new ButtonGroup();
		JPanel basepan=new JPanel(new FlowLayout());		
		basepan.add(new JLabel("Base"));
		JRadioButton basebutt=new JRadioButton();
		basebutt.setSelected(true);
		caracgroupbutt.add(basebutt);
		basepan.add(basebutt);
		carac.add(basepan);
		JPanel actpan=new JPanel(new FlowLayout());		
		actpan.add(new JLabel("Act"));
		JRadioButton actbutt=new JRadioButton();
		caracgroupbutt.add(actbutt);
		actpan.add(actbutt);
		carac.add(actpan);
		carac.add(new JLabel("Mod"));	
		for(i=0; i<7;i++){
			if(i==0)carac.add(new JLabel("Agi"));
			if(i==1)carac.add(new JLabel("Con"));
			if(i==2)carac.add(new JLabel("Dex"));
			if(i==3)carac.add(new JLabel("For"));
			if(i==4)carac.add(new JLabel("Int"));
			if(i==5)carac.add(new JLabel("Per"));
			if(i==6)carac.add(new JLabel("Pou"));
			if(i==7)carac.add(new JLabel("Vol"));
		}
		basebutt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(basebutt.isSelected()){
					for(int i=0; i<7;i++){
						caracprim.set(i,(int)caracprimbase.get(i).getValue());
						caracmod.get(i).setValue(mod.get((int)caracprimbase.get(i).getValue()-1));
					}
				}else{
					for(int i=0; i<7;i++){
						caracprim.set(i,(int)caracprimact.get(i).getValue());
						caracmod.get(i).setValue(mod.get((int)caracprimact.get(i).getValue()-1));
					}
				}
			}
		});
		actbutt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(basebutt.isSelected()){
					for(int i=0; i<7;i++){
						caracprim.set(i,(int)caracprimbase.get(i).getValue());
						caracmod.get(i).setValue(mod.get((int)caracprimbase.get(i).getValue()-1));
					}
				}else{
					for(int i=0; i<7;i++){
						caracprim.set(i,(int)caracprimact.get(i).getValue());
						caracmod.get(i).setValue(mod.get((int)caracprimact.get(i).getValue()-1));
					}
				}
			}
		});
		for(int i=0; i<7;i++){
			caracmod.get(i).setEnabled(false);
		}
		
		general2.add(carac);
		
		
		
		
		
		
		
		general.add(general1);
		general.add(general2);
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
		classe.addItem("Maétre d'Arme");
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
		classe.addItem("Touche-à-Tout");	
		
		
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
		
		caracmod=new ArrayList<JSpinner>();
		caracprim=new ArrayList<Integer>();
		caracprimact=new ArrayList<JSpinner>();
		caracprimbase=new ArrayList<JSpinner>();
		for(i=0; i<7;i++){

			caracmod.add(new JSpinner());
			caracmod.get(i).setValue(0);
			caracprimbase.add(new JSpinner());
			caracprimbase.get(i).setValue(5);
			caracprim.add((int)caracprimbase.get(i).getValue());
			caracprimbase.get(i).addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					if ((int)caracprimbase.get(i).getValue()<1){
						caracprimbase.get(i).setValue(1);
					}else if((int) caracprimbase.get(i).getValue()>20){
						caracprimbase.get(i).setValue(20);
					}
					if(basebutt.isSelected()){
						caracprim.remove(i);
						caracprim.add((int)caracprimbase.get(i).getValue());
						caracprimbase.get(i).setValue(mod.get((int)caracprimbase.get(i).getValue()-1));
					}
					
				}
			});
			caracprimact.add(new JSpinner());
			caracprimact.get(i).setValue(5);
			caracprim.add((int)caracprimact.get(i).getValue());
			caracprimact.get(i).addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					if ((int)caracprimact.get(i).getValue()<1){
						caracprimact.get(i).setValue(1);
					}else if((int) caracprimact.get(i).getValue()>20){
						caracprimact.get(i).setValue(20);
					}
					if(actbutt.isSelected()){
						caracprim.set(i,(int)caracprimact.get(i).getValue());
						caracprimact.get(i).setValue(mod.get((int)caracprimact.get(i).getValue()-1));
					}
					
				}
			});
		}	
		
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
