package src;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.util.ArrayList;
import java.util.Scanner;

public class Jogo{
	private Botao but1,but2,but3,but4;
	private int[] lista1 = new int[100];//vetor de escolhas do computador
	private int[] lista2= new int[100];//vetor de jogadas humanas 
	private int indice1, indice2;
	private int contagem; //quantidade de botoes apertados em uma rodada
    private int velocidade = 0;
	private int qtdComp;
	private String dificuldade;
	private JButton butiniciar = new JButton();
	private JTable tabela;
	private JFrame tela = new JFrame("Tela de inicio");
	private JFrame geniusFrame;
	volatile boolean acabou = true; // Essencial, pois, as alterações serão realizadas dinamicamente
    volatile boolean liberado = true; // Essencial, pois, as alterações serão realizadas dinamicamente
	ArrayList<Competidor> competidores = new ArrayList<>();

	public static void main(String[] args){
		Jogo jogo = new Jogo ();

		// Define o numero de Jogadores
		jogo.setQtdCompetidores();

		// Define o nível do Jogo
		jogo.setDificuldade();

		// Cadastra os competidores
		for (int i=0; i<jogo.getQtdCompetidores(); i++)
			jogo.cadastrarCompetidor(jogo.criarNovoCompetidor());
		
		// Desenha a tela do jogo
		jogo.montaJogo();
			
		// Executa a parte lógica do jogo 
		jogo.Play();
	}

	public void Play(){
		int ultimoIndice = competidores.size();	

		// Aonde ocorre as jogadas dos competidores
		for(int i = 0; i < this.competidores.size(); i++){
            this.Jogadas(this.competidores.get(i));
			if (i==ultimoIndice-1){
				while (retornaEmpate()){
					JOptionPane.showMessageDialog(geniusFrame,"+ Houve empate!");
					competidores = retornaEmpatados();
					desempatarJogo(competidores);
				}
				
				//JOptionPane.showMessageDialog(geniusFrame,"** Vencedor: " + competidores.get(vencedorRodada()).getNome() + " | Pontuacao: " + competidores.get(vencedorRodada()).getPontuacao());
				JOptionPane.showMessageDialog(geniusFrame,"# Parabens, " + competidores.get(vencedorRodada()).getNome()  + ", você ganhou! placar: [" + competidores.get(vencedorRodada()).getPontuacao() + "]");
				
			}
		}

		// Ranking dos usuários
		this.exibirRanking(competidores);
	}

	public void placarDoJogo(){
		JLabel aviso = new JLabel("Alerta");

		aviso.setSize(100, 100);
		tela.setLayout(null);
		tela.add(aviso);
		tela.setSize(370, 300);
		tela.setVisible(true);
		aviso.getText();
		tela.getContentPane();	
	}

	public void cadastrarCompetidor(Competidor competidor){
		this.competidores.add(competidor);
	}

	public Competidor criarNovoCompetidor(){
		String nome = (String) JOptionPane.showInputDialog(geniusFrame, "Nome: ", "<Informe aqui o nome>");
    	
		String apelido = (String) JOptionPane.showInputDialog(geniusFrame, "Apelido: ", "<Informe o apelido>");

		return (new Competidor(nome, apelido));
	}

	public void setQtdCompetidores(){
		String qtd =  JOptionPane.showInputDialog(geniusFrame, "Quantidade de Jogadores: ");
		this.qtdComp = Integer.parseInt(qtd);
	}

	public int getQtdCompetidores(){
		return this.qtdComp;
	}

	public void setDificuldade(){
		String[] dificuldades = {"Facil", "Medio", "Dificil", "Muito Dificil"};
    	
    	String definirDificuldade = (String) JOptionPane.showInputDialog(geniusFrame, "Dificuldade: ", " Dificuldade do Jogo", JOptionPane.QUESTION_MESSAGE, null, dificuldades, dificuldades[2]);
    	
		this.dificuldade = definirDificuldade;
	}

	public String getDificuldade(){
		return this.dificuldade;
	}

	public int converterVelocidade(int velocidade){
		switch(this.getDificuldade()){
			case "Facil":{
				return (velocidade);
			}
			case "Medio":{
				return (velocidade/2);
			}
			case "Dificil":{
				return (velocidade/3);
			}
			case "Muito Dificil":{
				return (velocidade/4);
			}
			default:{
				return (velocidade);
			}
		}
	}
	
	public void montaJogo(){	
		/*Configura a frame e adiciona os botoes*/
		
		geniusFrame = new JFrame();
		geniusFrame.setLayout(new GridLayout(2,2));
        geniusFrame.setSize(370, 300);
		geniusFrame.setLocationRelativeTo(null);
        geniusFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        but1 = new Botao("/users/Victor/Desktop/Genius/assets/VermelhoNormal.Gif","/users/Victor/Desktop/Genius/assets/VermelhoApertado.Gif","/users/Victor/Desktop/Genius/sound/som1.wav");
        but2 = new Botao("/users/Victor/Desktop/Genius/assets/AzulNormal.Gif","/users/Victor/Desktop/Genius/assets/AzulApertado.Gif","/users/Victor/Desktop/Genius/sound/som2.wav");
        but3 = new Botao("/users/Victor/Desktop/Genius/assets/VerdeNormal.Gif","/users/Victor/Desktop/Genius/assets/VerdeApertado.Gif","/users/Victor/Desktop/Genius/sound/som3.wav");
        but4 = new Botao("/users/Victor/Desktop/Genius/assets/AmareloNormal.Gif","/users/Victor/Desktop/Genius/assets/AmareloApertado.Gif","/users/Victor/Desktop/Genius/sound/som4.wav");
        
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
		
		int v1 = 900; // Ambas variaveis sao referentes ao tempo de espera dos botes 
		int v2 = 500;

		v1 = converterVelocidade(v1);
		v2 = converterVelocidade(v2);
			
		for (int z = 0;z<=indice1;z++){//pisca os botoes escolhidos pelo computador ate o momento
			if (lista1[z]==0){ 
				try{
					Thread.sleep(v1); 
					but1.apertaBotao();
					Thread.sleep(v2); 
					but1.desapertaBotao();
						
				}catch(Exception e){
				}
			}
			if (lista1[z]==1){ 
				try{
					Thread.sleep(v1);
					but2.apertaBotao();
					Thread.sleep(v2);
					but2.desapertaBotao();
						
				}catch(Exception e){
				}
			}
			if (lista1[z]==2){ 
				try{
					Thread.sleep(v1);
					but3.apertaBotao();
					Thread.sleep(v2);
					but3.desapertaBotao();
						
				}catch(Exception e){
				}
			}
			if (lista1[z]==3){ 
				try{
					Thread.sleep(v1);
					but4.apertaBotao();
					Thread.sleep(v2);
					but4.desapertaBotao();
						
				}catch(Exception e){
				}
			}	
		}
	}
	
	public void Jogadas(Competidor competidor){
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
				geniusFrame.setTitle("-> Sua vez, @" + competidor.getNome() + " | Score:  " + competidor.getPontuacao());
			}
			
			//vez do JOGADOR
			if(contagem == indice1){//Aguarda ate que o jogador aperte a quantidade certa de botoes
				for (int x = 0;x<indice1;x++){
						if (lista2[x] == lista1[x]){
							contagem = 0;
							liberado = true;
							//Se acertou, zera a contagem de botoes apertados e libera o computador para a proxima rodada
						}else{
							JOptionPane.showMessageDialog(geniusFrame, competidor.getNome() + ", você errou!");
							exibirRanking(competidores);
							
							//JOptionPane.showMessageDialog(geniusFrame,"Game Over!");
							return;
							//System.exit(0);
						}
				}
				atualizaPlacar(competidor);
			}
		}
	}

	public void desempatarJogo(ArrayList<Competidor> cEmpatados){
		int uIndice=0;
		for(int i = 0; i < cEmpatados.size(); i++){
			this.Jogadas(cEmpatados.get(i));
			if (i==uIndice-1){
				if (retornaEmpate()){
					JOptionPane.showMessageDialog(geniusFrame,"+ Houve empate!");					
					competidores = retornaEmpatados();
				}
			}
		}
	}


	public void exibirRanking(ArrayList<Competidor> competidores){
		String pontuacaoFinal = "";
		System.out.println();
        for(int i = 0; i < competidores.size(); i++){
            pontuacaoFinal += "+ Competidor: " + competidores.get(i).getNome() 
						  + "\n+ Apelido: " + competidores.get(i).getApelido() 
						  + "\n+ Pontuacao: " + competidores.get(i).getPontuacao() + "\n\n";
        }
		
        JOptionPane.showMessageDialog(geniusFrame, pontuacaoFinal);
    }

	public int vencedorRodada(){
		int indice=0, maior = competidores.get(0).getPontuacao();
		
		for (int i=1; i<competidores.size(); i++){
			if (competidores.get(i).getPontuacao() > maior){
				maior = competidores.get(i).getPontuacao();
				indice = i;
			}
		}
		return indice;
	}
	

	public int maiorValorArray(){
		int maior = competidores.get(0).getPontuacao();

		for (int i=1; i<competidores.size(); i++){
			if (competidores.get(i).getPontuacao() > maior)
				maior = competidores.get(i).getPontuacao();
		}
		return maior;
	}
	
	public boolean retornaEmpate(){
		int maior = maiorValorArray();
		int repeticoes = 0;

		for (int i=0; i<competidores.size(); i++)
			if (maior==competidores.get(i).getPontuacao())
				repeticoes++;
		
		if (repeticoes>1)
			return true;

		return false;
	}

	public ArrayList<Competidor> retornaEmpatados(){
		ArrayList<Competidor> competidoresEmpatados = new ArrayList<>();
		int maior = maiorValorArray();

		for (int i=0; i<competidores.size(); i++)
			if (maior==competidores.get(i).getPontuacao())
				competidoresEmpatados.add(competidores.get(i));
		
		return competidoresEmpatados;
	}

	public void setVelocidadeDoJogo(int velocidade){
		this.velocidade = velocidade;
	}

	public int getVelocidadeDoJogo(){
		return this.velocidade;
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

	public void atualizaPlacar(Competidor competidorAtual){
		this.indice2 = 0; //indice de jogadas humanas retorna a 0 para a proxima rodada
		competidorAtual.setPontuacao(competidorAtual.getPontuacao() + 1);			
	}
}
