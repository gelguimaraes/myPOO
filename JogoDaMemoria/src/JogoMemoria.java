import java.awt.Color;

public class JogoMemoria {
	private final int DIMENSAO = 4;
	//private Color[] cores = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK, Color.BLACK, Color.WHITE };
	//private Color[][] corBotao = new Color[DIMENSAO][DIMENSAO];	
	private String[] path = {"1","2","3","4","5","6","7","8"};	
	private String[][] paths = new String[DIMENSAO][DIMENSAO];
	private int acertos;
	private int erros;
	
	
	public JogoMemoria() {
		// TODO Auto-generated constructor stub
	}
	
	public void initialize() {
		preencherPaths();
		embaralhaPaths();
		
	}
	
	public void preencherPaths() {
		int k=0;
		for (int i=0; i<DIMENSAO; i++) {
			for(int j=0; j<DIMENSAO; j++) {
				paths[i][j] = path[k];
				k++;
				if(k>7) 
					k=0;
			}
		
		}
		
		/*
		for (int i=0; i<DIMENSAO; i++) {
			for(int j=0; j<DIMENSAO; j++) {
				System.out.println("BotaoAntes "+ i + j+ " é: " + paths[i][j]);
			}
		}*/
	}
	
	
	public void embaralhaPaths() {		
	     int linhaSortada;
	     int colunaSorteada;
	     String[][] pathsAux = new String[DIMENSAO][DIMENSAO];
	     for(int i = 0; i < DIMENSAO; i++) {
			  for(int j = 0; j < DIMENSAO; j++){ 
				  linhaSortada =(int)(Math.random() * (DIMENSAO-1));
				  colunaSorteada =(int)(Math.random() * (DIMENSAO-1));
				  pathsAux[i][j] = paths[i][j];
				  paths[i][j]= paths[linhaSortada][colunaSorteada];
				  paths[linhaSortada][colunaSorteada]=pathsAux[i][j];
			  }
	     }
	     /*
	 	for (int i=0; i<DIMENSAO; i++) {
			for(int j=0; j<DIMENSAO; j++) {
				System.out.println("BotaoDepois "+ i + j+ " é: " + paths[i][j]);
			}
		}*/
	    
	}
	
	
	public String getPath(int x, int y) {
		return paths[x][y];
	}
	
	public boolean isSamePath(int x1, int y1, int x2, int y2) {
		if(paths[x1][y1].equals(paths[x2][y2])) {
			//System.out.println("CorBotao1 "+ corBotao[x1][y1] + " CorBotao2 " + corBotao[x2][y2]);
			return true;
		}
		else {
			//System.out.println("CorBotao1 "+ corBotao[x1][y1] + " CorBotao2 " + corBotao[x2][y2]);
			return false;
		}
		
	}
	
	public int setYes(int n, boolean fimJogo) {
		int score = n;
		if(fimJogo)
			this.acertos = 0;
		else {
			this.acertos += n;
			score = acertos;
		}
		return score;
	}
	
	public int setNo(int n, boolean fimJogo) {
		int score = n;
		if(fimJogo)
			this.erros = 0;
		else {
			this.erros += n;
		    score = erros;
		}
	  return score;
	}
	
	int getYes() {
		return acertos;
	}
	
	int getNo() {
		return erros;
	}
	
	
	public static void main(String[] args) {
		//JogoMemoria teste = new JogoMemoria();
		//teste.initialize();
	}
	
}
