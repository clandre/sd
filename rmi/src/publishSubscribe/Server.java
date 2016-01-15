package publishSubscribe;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;

public class Server {

	public static void main(String[] args) {

		String nome[] = { "Botafogo", "Flamengo", "Fluminense", "Vasco", "Corinthians", "Palmeiras", "Santos",
				"São Paulo" };
		int ano[] = { 1894, 1895, 1902, 1898, 1910, 1914, 1912, 1930 };

		try {
			Registry registry = LocateRegistry.createRegistry(1099);
			ArrayList<Time> objetosCriados = new ArrayList<>();
			ArrayList<Integer> indicesSorteados = new ArrayList<>();

			Random randomGenerator = new Random();
			int qtdObjetosCriados = randomGenerator.nextInt(nome.length) + 1;

			System.out.println(qtdObjetosCriados + " objetos serão criados.");

			for (int i = 0; i < qtdObjetosCriados; i++) {
				int indiceObjeto = randomGenerator.nextInt(nome.length);
				if (!indicesSorteados.contains(indiceObjeto)) {
					Time time = new Time(nome[indiceObjeto], ano[indiceObjeto]);
					objetosCriados.add(time);
					indicesSorteados.add(indiceObjeto);
				}
			}

			for (Time t : objetosCriados) {
				ITime obj = t;
				registry.bind(t.getName(), obj);
				System.out.println("Objeto criado: " + t.getName());
			}

			System.out.println(
					"Servidor executando no ip " + InetAddress.getLocalHost().getHostAddress() + " na porta 1099.");

			System.out.println("Publicando em 10 segundos.");
			Thread.sleep(10000);

			while (true) {
				for (Time t : objetosCriados) {
					t.publish();
					System.out.println(t.getName() + " publicado.");
				}
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
