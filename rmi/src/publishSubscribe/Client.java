package publishSubscribe;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Client implements IClient, Serializable {
	private String nome;

	public Client(String nome) {
		this.nome = nome;
	}

	public String getName() {
		return nome;
	}

	public void notificar(String time) throws RemoteException {
		System.err.println("Fui Notificado, " + nome + "\n");
	}

}
