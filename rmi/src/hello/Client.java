package hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	public static void main(String[] args) {
		try{
			Registry registry = LocateRegistry.getRegistry(); 
			IHello obj = (IHello) registry.lookup("Hello");
			System.out.println(obj.sayHello());
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}