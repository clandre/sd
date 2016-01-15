package publishSubscribe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote{
	public void notificar(String time) throws RemoteException;
}
