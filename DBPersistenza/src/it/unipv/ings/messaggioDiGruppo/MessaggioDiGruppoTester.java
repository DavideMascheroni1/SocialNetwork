package it.unipv.ings.messaggioDiGruppo;

import java.util.ArrayList;

public class MessaggioDiGruppoTester {

	public static void main(String[] args) {
		MessaggioDiGruppoDao mdao = new MessaggioDiGruppoDao();
		ArrayList<MessaggioDiGruppo> selectAll = mdao.selectAll();
		MessaggioDiGruppo m = new MessaggioDiGruppo("005", null, null, null, null, null);
		//MessaggioDiGruppo m1 = new MessaggioDiGruppo("004", null, null, null, null, null);
		
		System.out.println(selectAll.toString());
		
		
		 ArrayList<MessaggioDiGruppo> cercaMessaggio = mdao.cercaMessaggioDiGruppo(m);
	     for(MessaggioDiGruppo msg : cercaMessaggio)
				System.out.println(msg.toString());
	          
	  //  System.out.println("Inserimento avvenuto? " + mdao.scriviMessaggioDiGruppo(m));
	   // System.out.println("L'inserimento delle chiavi ? avvenuto? " +mdao.inserisciChiavi(m));
	    // System.out.println("La rimozione ? avvenuta? " + mdao.rimuoviMessaggioDiGruppo(m));
	      
}
}
