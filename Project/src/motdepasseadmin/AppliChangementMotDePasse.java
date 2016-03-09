package motdepasseadmin;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;



public class AppliChangementMotDePasse {
	
	public static void main(String[] args) {
	
		String requeteSQL =
				"UPDATE Utilisateur " + "SET mdp=? " + "WHERE login='Admin'";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@vs-oracle2:1521:ORCL";
			Connection connect =
					DriverManager.getConnection(url, "gr11u1", "KL251093");
			System.out.println("Entrez le nouveau mot de passe");
			Scanner clavier = new Scanner(System.in);
			String mdp = clavier.nextLine();
			PreparedStatement requete = connect.prepareStatement(requeteSQL);
			requete.setString(1, mdp);
			requete.execute();
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
}
