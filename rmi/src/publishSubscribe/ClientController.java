package publishSubscribe;

import java.math.BigInteger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.SecureRandom;
import java.util.Scanner;

public class ClientController {
	private static Registry registryServer;
	private static Client eu;
	private static String IpServer;
	private static final int porta = 1099;
	

	public static void main(String[] args) throws InterruptedException {
		
		Scanner teclado = new Scanner(System.in);
		System.out.println("Digite o IP do servidor: ");
		ClientController.IpServer = teclado.next();
		System.out.println("Digite seu time: ");
		String time = teclado.next();
		teclado.close();

		SecureRandom random = new SecureRandom();
		String nomeCliente = new BigInteger(30, random).toString(32);
		eu = new Client(nomeCliente);		

		try {
			ClientController.registryServer = LocateRegistry.getRegistry(ClientController.IpServer, porta);
			ITime obj = (ITime) ClientController.registryServer.lookup(time);
			obj.subscribe(ClientController.eu);

		} catch (java.rmi.NotBoundException e1) {
			System.err.println("O time não foi criado");
			System.exit(-1);
		}
		catch(Exception e2){
			System.out.println("Erro");
			e2.printStackTrace();
		}
		
		//A partir de notificado o cliente pode descobrir a idade do time
		
		Thread.sleep(3000);		
		
		try {
			ITime obj = (ITime) ClientController.registryServer.lookup(time);
			System.out.println(time + " tem "+ obj.getIdade() + " anos de idade.");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
