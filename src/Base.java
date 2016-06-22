import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


public class Base{
	private JFrame f=new JFrame("Anima");
	private CardLayout card = new CardLayout();
	private JPanel pan = new JPanel();
	//Liste des noms de nos conteneurs pour la pile de cartes
	private String[] list = {"gest", "perso", "music"};
	private int indice = 0;
	
	public Base() {
		JPanel panbutton =new JPanel();
		panbutton.setLayout(new FlowLayout());
		
		Gestion gestion= new Gestion();
		Perso perso= new Perso();
		Musique musique= new Musique();
		
		JButton gest=new JButton("Gestion");
		gest.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				card.show(pan, list[0]);
			}
		});
		panbutton.add(gest);
		
		JButton personnage=new JButton("Liste personnage");
		personnage.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				card.show(pan, list[1]);
			}
		});		
		panbutton.add(personnage);
		
		JButton music=new JButton("Musique");
		music.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				card.show(pan, list[2]);
			}
		});
		panbutton.add(music);
		
		panbutton.add(new JLabel("PJ"));
		JSlider slide= new JSlider(0, 1);
		panbutton.add(slide);
		panbutton.add(new JLabel("MJ"));
		
		pan.setLayout(card);
		pan.add(gestion, list[0]);
		pan.add(perso, list[1]);
		pan.add(musique, list[2]);
		
		f.getContentPane().add(panbutton,BorderLayout.NORTH);
		f.getContentPane().add(pan,BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String args[]){

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Base();
			}
		});
	}

}
