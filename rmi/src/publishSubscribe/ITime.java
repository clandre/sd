package publishSubscribe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITime extends Remote {
	public int getIdade() throws RemoteException;
	public void subscribe(Client c) throws RemoteException;
	public void publish() throws RemoteException;
}
