package it.unipv.ings.messaggioDiGruppo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ings.connessione.DBConnection;

public class MessaggioDiGruppoDao implements IMessaggioDiGruppoDao{
	private String schema;
	private Connection conn;


	public MessaggioDiGruppoDao() {
		super();
		this.schema = "socialnetwork";
	}

	public ArrayList<MessaggioDiGruppo> selectAll() {
		
		ArrayList<MessaggioDiGruppo> result = new ArrayList<>();

		conn=DBConnection.startConnection(conn,schema);
		Statement st1;
		ResultSet rs1;

		try
		{
			st1 = conn.createStatement();
			String query="SELECT * from messaggiodigruppo";
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				MessaggioDiGruppo msg=new MessaggioDiGruppo(rs1.getString(1), rs1.getDate(2),rs1.getTime(3),rs1.getString(4),rs1.getString(5), rs1.getString(6));

				result.add(msg);
			}
		}catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return result;
		
	}


	public boolean scriviMessaggioDiGruppo(MessaggioDiGruppo m) {
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		boolean esito = true;

		try
		{
			String query="insert into messaggiodigruppo (idMsgGrp,dataInvio,oraInvio,testo,multimedia) values (?,?,?,?,?)";

			st1 = conn.prepareStatement(query);
			st1.setString(1, m.getIdMsgGrp());
			st1.setDate(2, m.getDataInvio());
			st1.setTime(3, m.getOraInvio());
			st1.setString(4, m.getTesto());
			st1.setString(5, m.getMultimedia());
			
			
			st1.executeUpdate();


		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DBConnection.closeConnection(conn);
		return esito;
	}


	
	public boolean rimuoviMessaggioDiGruppo(MessaggioDiGruppo m) {
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query="delete from messaggiodigruppo where idMsgGrp=?";
			st1 = conn.prepareStatement(query);
			st1.setString(1, m.getIdMsgGrp());

			st1.executeUpdate();

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DBConnection.closeConnection(conn);
		return esito;
	}

	public ArrayList<MessaggioDiGruppo> cercaMessaggioDiGruppo(MessaggioDiGruppo m) {
		ArrayList<MessaggioDiGruppo> result = new ArrayList<>();

		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * FROM messaggiodigruppo WHERE idMsgGrp=?";

			st1 = conn.prepareStatement(query);
			st1.setString(1, m.getIdMsgGrp());

			rs1=st1.executeQuery();

			while(rs1.next())
			{
				MessaggioDiGruppo msg=new MessaggioDiGruppo(rs1.getString(1), rs1.getDate(2),rs1.getTime(3),rs1.getString(4),rs1.getString(5), rs1.getString(6));

				result.add(msg);
			}
		}catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return result;
	}

	@Override
	public boolean inserisciChiavi(MessaggioDiGruppo m) {
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		boolean esito = true;

		try
		{
			String query="update messaggioDiGruppo set gruppo=? where idMsgGrp=?";
			st1 = conn.prepareStatement(query);
			st1.setString(1, m.getIdMsgGrp());
			

			st1.executeUpdate();


		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DBConnection.closeConnection(conn);
		return esito;
	}
}
