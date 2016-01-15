package publishSubscribe;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;

public class Time extends UnicastRemoteObject implements ITime {
	private String nome;
	private int anoFundacao;
	private ArrayList<Client> clientes = new ArrayList<>(); // lista de clientes

	public Time() throws RemoteException {
		super();
	}

	public Time(String nome, int anoFundacao) throws RemoteException {
		this.nome = nome;
		this.anoFundacao = anoFundacao;
	}

	public void setName(String nome) {
		this.nome = nome;
	}

	public String getName() {
		return nome;
	}

	public void setAnoFundacao(int anoFundacao) {
		this.anoFundacao = anoFundacao;
	}

	public int getAnoFundacao() {
		return anoFundacao;
	}

	public int getIdade() {
		int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		return anoAtual - anoFundacao;
	}

	public void subscribe(Client c) throws RemoteException {
		clientes.add(c);	
	}

	public void publish() throws RemoteException {
		for (Client c : clientes) {
			System.out.println("Publicando para o cliente " + c.getName() + " torcedor do " + nome);
			c.notificar(nome);
		}
	}
}
