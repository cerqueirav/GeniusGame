package src;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Jogo{
	private Botao but1,but2,but3,but4;
	private int[] lista1 = new int[100];//vetor de escolhas do computador
	private int[] lista2= new int[100];//vetor de jogadas humanas 
	private int indice1,indice2;
	private int contagem; //quantidade de botoes apertados em uma rodada
    private int velocidade = 1;
	private JButton butiniciar = new JButton();
	private JFrame geniusFrame;
	volatile boolean acabou = true; // Essencial, pois, as alterações serão realizadas dinamicamente
    volatile boolean liberado = true; // Essencial, pois, as alterações serão realizadas dinamicamente
	
	public static void main(String[] args){
		Jogo game = new Jogo();
		// Interface visual do Jogo
		game.montaJogo();

		// Loop do Jogo (parte lógica) 
		game.Run();
		
		System.exit(0);
		
	}

	public void Run(){
		ArrayList<Usuario> users = new ArrayList<>();
		
		users.add(new Usuario("Jonas", "U01"));
		users.add(new Usuario("Jamilly", "U02"));
		users.add(new Usuario("Janilda", "U03"));

		// Realiza-se as jogadas pelos n Usuarios
		for(int i = 0; i < users.size(); i++){
            this.Jogadas(users.get(i));
        }

		// Ranking dos usuários
		this.exibirRanking(users);
	}
	
	public void montaJogo(){	
		/*Configura a frame e adiciona os botoes*/
		
		geniusFrame = new JFrame();
		geniusFrame.setLayout(new GridLayout(2,2));
        geniusFrame.setSize(370, 300);
		geniusFrame.setLocationRelativeTo(null);
        geniusFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        but1 = new Botao("/Users/Victor/Desktop/Genius/assets/VermelhoNormal.Gif","/Users/Victor/Desktop/Genius/assets/VermelhoApertado.Gif","/Users/Victor/Desktop/Genius/sound/som1.wav");
        but2 = new Botao("/Users/Victor/Desktop/Genius/assets/AzulNormal.Gif","/Users/Victor/Desktop/Genius/assets/AzulApertado.Gif","/Users/Victor/Desktop/Genius/sound/som2.wav");
        but3 = new Botao("/Users/Victor/Desktop/Genius/assets/VerdeNormal.Gif","/Users/Victor/Desktop/Genius/assets/VerdeApertado.Gif","/Users/Victor/Desktop/Genius/sound/som3.wav");
        but4 = new Botao("/Users/Victor/Desktop/Genius/assets/AmareloNormal.Gif","/Users/Victor/Desktop/Genius/assets/AmareloApertado.Gif","/Users/Victor/Desktop/Genius/sound/som4.wav");
        
        but1.getBotao().addActionListener(new Innerbut1());
        but2.getBotao().addActionListener(new Innerbut2());
        but3.getBotao().addActionListener(new Innerbut3());
        but4.getBotao().addActionListener(new Innerbut4());
       
        geniusFrame.getContentPane().add(but1.getBotao());
        geniusFrame.getContentPane().add(but2.getBotao());
        geniusFrame.getContentPane().add(but3.getBotao());
        geniusFrame.getContentPane().add(but4.getBotao());	
        
        
        geniusFrame.setVisible(true);
        
    }
	
	public void escolhaPC(){
		/*Escolha dos botoes e alocacao de seus numeros no vetor de escolhas do computador.
		 *Pisca os botoes contidos no vetor.
		 */	 
			
		int numeroBotao = (int)(Math.random() * 4); 
			lista1[indice1] = numeroBotao;
			
		for (int z = 0;z<=indice1;z++){//pisca os botoes escolhidos pelo computador ate o momento
			if (lista1[z]==0){ 
				try{
					Thread.sleep(900); 
					but1.apertaBotao();
					Thread.sleep(500); 
					but1.desapertaBotao();
						
				}catch(Exception e){
				}
			}
			if (lista1[z]==1){ 
				try{
					Thread.sleep(900);
					but2.apertaBotao();
					Thread.sleep(500);
					but2.desapertaBotao();
						
				}catch(Exception e){
				}
			}
			if (lista1[z]==2){ 
				try{
					Thread.sleep(900);
					but3.apertaBotao();
					Thread.sleep(500);
					but3.desapertaBotao();
						
				}catch(Exception e){
				}
			}
			if (lista1[z]==3){ 
				try{
					Thread.sleep(900);
					but4.apertaBotao();
					Thread.sleep(500);
					but4.desapertaBotao();
						
				}catch(Exception e){
				}
			}	
		}
	}
	
	public void Jogadas(Usuario usuario){
		//*Roda o loop das jogadas*/
		
		//inicializa as variaveis no inicio do jogo
		indice1=0;
		indice2=0;
		contagem = 0;
		acabou = true;
		liberado = true;
		
		while(acabou){
			//vez do COMPUTADOR
			if (liberado == true){//Se o computador estiver liiberado , faz sua jogada
				geniusFrame.setTitle("-> Aguarde....");
				escolhaPC();
				
				indice1++;//incrementa a posicao para a proxima rodada
				liberado = false;
				geniusFrame.setTitle("-> Sua vez, " + usuario.getNome() + " | Pontuacao:  " + usuario.getPontuacao());
			}
			
			//vez do JOGADOR
			if(contagem == indice1){//Aguarda ate que o jogador aperte a quantidade certa de botoes
				for (int x = 0;x<indice1;x++){
						if (lista2[x] == lista1[x]){
							contagem = 0;
							liberado = true;
							//Se acertou, zera a contagem de botoes apertados e libera o computador para a proxima rodada
						}else{
							JOptionPane.showMessageDialog(geniusFrame,"Game Over!");
							return;
							//System.exit(0);
						}
				}
				atualizaPlacar(usuario);
			}
		}
	}

	public void exibirRanking(ArrayList<Usuario> usuarios){
		String pontuacaoFinal = " ";
        for(int i = 0; i < usuarios.size(); i++){
            pontuacaoFinal += usuarios.get(i).getNome() + "\n * Pontuacao: " + usuarios.get(i).getPontuacao() + "\n\n";
        }

        JOptionPane.showMessageDialog(geniusFrame, pontuacaoFinal);
    }

	public void setVelocidadeDoJogo(int velocidade){
		this.velocidade = velocidade;
	}

	public int getVelocidadeDoJogo(){
		return this.velocidade;
	}

	public int modificaVelocidadeDoJogo(){
		int velocidade = getVelocidadeDoJogo();
		switch(velocidade){
			case 2:{
				return 600;
			}
			case 3:{
				return 300;
			}
			case 4:{
				return 150;
			}
			case 5:{
				return 50;
			}
			default:{
				return 900;
			}
		}
	}
	
	public class Innerbut1 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			but1.getSom().play();
			lista2[indice2]=0;
			indice2++;
			contagem++;
		}				
	}

	class Innerbut2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			but2.getSom().play();
			lista2[indice2]=1;
			indice2++;
			contagem++;
		}					
	}

	class Innerbut3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			but3.getSom().play();
			lista2[indice2]=2;
			indice2++;
			contagem++;
		}				
	}
    
	class Innerbut4 implements ActionListener{
        public void actionPerformed(ActionEvent e) {
			but4.getSom().play();
			lista2[indice2]=3;
			indice2++;
			contagem++;
        }                           
    }

	public void atualizaPlacar(Usuario usuarioAtual){
		this.indice2 = 0; //indice de jogadas humanas retorna a 0 para a proxima rodada
		usuarioAtual.setPontuacao(usuarioAtual.getPontuacao() + 1);
	}
}
