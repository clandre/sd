package hello;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements IHello {
	
	public Server() throws RemoteException{
		super();
	}

	public String sayHello() throws RemoteException {
		return "Hello World :)";
	}

	public static void main(String[] args) {
		try{
			Registry registry = LocateRegistry.createRegistry(1099);
			IHello obj = new Server();
			registry.bind("Hello", obj);
			System.out.println("Servidor executando!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}