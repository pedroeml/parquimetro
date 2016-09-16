package negocio;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Ano<E> implements IList<Mes<E>> {
	private int ano;
	private List<Mes<E>> lista_de_meses;
	
	public Ano(int ano) {
		this.ano = ano;
		this.lista_de_meses = new LinkedList<>();
	}
	
	public int getAno() {
		return ano;
	}
	
	public Mes<E> getMes(int numero_do_mes) {
		if (numero_do_mes < 0 || numero_do_mes > 11)
			throw new IllegalArgumentException("O número do mês em argumento deve variar de 0 (janeiro) à 11 (dezembro)!");
		Iterator<Mes<E>> it = this.iterate_list();
		
		while (it.hasNext()) {
			Mes<E> mes = it.next();
			if (mes.getMes() == numero_do_mes)
				return mes;
		}
		return null;
	}
	
	@Override
	public Iterator<Mes<E>> iterate_list() {
		return lista_de_meses.iterator();
	}
	
	/**
	 * Adiciona um objeto do tipo Mes<E> na lista sem ter objetos repetidos na lista.
	 */
	@Override
	public boolean addObjToList(Mes<E> mes) {
		if (this.lista_de_meses.contains(mes))
			return false;
		return this.lista_de_meses.add(mes);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(String.format("- Ano:\t%d\n", this.ano));
		Iterator<Mes<E>> it = this.iterate_list();
		
		while (it.hasNext()) {
			Mes<E> mes = it.next();
			str.append(String.format("%s\n", mes.toString()));
		}
		
		return str.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Ano<?>) {
			Ano<E> ano = (Ano<E>) obj;
			if (this.getAno() == ano.getAno())
				return true;
		}
		return false;
	}
}
