package PackDep;

import java.io.*;
import java.util.ArrayList;

public class Principal {

	public static void main(String[] args) {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String linea = "";
		ArrayList<Deposito> lista_depositos = new ArrayList<Deposito>();
		int volumen = 0;
		int volumen_total=0;
		int cantidad_depositos = 0;
		try {
			archivo = new File(
					"C:/eclipse-jee-luna-SR2-win32/Deposito/src/PackDep/deposito.in.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			if ((linea = br.readLine()) != null)
				cantidad_depositos = Integer.parseInt(linea);

			for (int i = 0; i < cantidad_depositos; i++) {
				if ((linea = br.readLine()) != null) {
					String[] partes = linea.split(" ");
					Deposito auxiliar = new Deposito(
							Integer.parseInt(partes[0]),
							Integer.parseInt(partes[1]));
					lista_depositos.add(auxiliar);
				}
			}
			if ((linea = br.readLine()) != null)
			{
				volumen = Integer.parseInt(linea);
				volumen_total=volumen;
			}
			
			int cantidad = lista_depositos.size();
			int sumatoria_volumenes = 0;
			for (int i = 0; i < cantidad; i++) {
				sumatoria_volumenes += lista_depositos.get(i).getProfundidad()
						* lista_depositos.get(i).getSuperficie();
			}

			if (sumatoria_volumenes < volumen){// desbordamiento
				System.out.println("Se desborda y el volumen sobrante es: "
						+ Math.abs((sumatoria_volumenes - volumen)));
			}else {
				int cant_usados = 0;
				float altura = 0;
				while (volumen > 0 && cant_usados < cantidad - 1) {
					cant_usados++;
					altura = lista_depositos.get(cant_usados - 1).getProfundidad()
							- lista_depositos.get(cant_usados).getProfundidad();

					for (int i = 0; i < cant_usados; i++) {
						volumen -= altura
								* lista_depositos.get(i).getSuperficie();
					}
					

				}
//				System.out.println(volumen);		
//				System.out.println(cant_usados);
//				System.out.println(cantidad);
				if (cant_usados == (cantidad) -1&& volumen>0) {
					int j=0;
					System.out.println();
					for (int i = 0; i <=cant_usados; i++) {
						volumen -=lista_depositos.get(cant_usados).getProfundidad()
								* lista_depositos.get(i).getSuperficie();
						j+=lista_depositos.get(cant_usados).getProfundidad()
								* lista_depositos.get(i).getSuperficie();
					}
					cant_usados++;
				}
//				System.out.println(volumen);
				
//				System.out.println("volumen total: " + volumen_total);
				if(cant_usados==1)
					System.out.println( (	lista_depositos.get(0).getSuperficie() *
											lista_depositos.get(0).getProfundidad() - volumen_total)
											/ lista_depositos.get(0).getSuperficie() );
				
				
				System.out.println("Esto es aproximadamente la altura:  "
						+ Math.abs((float)volumen / cant_usados));
				System.out.println("Esto es la cantidada de usados"
						+ cant_usados);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
