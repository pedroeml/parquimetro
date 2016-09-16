package negocio;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Mes<E> implements IList<E> {
	private int mes;
	private List<E> lista;
	
	public Mes(int mes) {
		this.mes = mes;
		this.lista = new LinkedList<>();
	}
	
	public int getMes() {
		return mes;
	}
	
	public String getMesStringFormat() {
		String mes;
		switch(this.mes) {
			case 0:		mes = "JANEIRO";
						break;
			case 1:		mes = "FEVEREIRO";
						break;
			case 2:		mes = "MARÇO";
						break;
			case 3:		mes = "ABRIL";
						break;
			case 4:		mes = "MAIO";
						break;
			case 5:		mes = "JUNHO";
						break;
			case 6:		mes = "JULHO";
						break;
			case 7:		mes = "AGOSTO";
						break;
			case 8:		mes = "SETEMBRO";
						break;
			case 9:		mes = "OUTUBRO";
						break;
			case 10:	mes = "NOVEMBRO";
						break;
			case 11:	mes = "DEZEMBRO";
						break;
			default:	mes = "MÊS INVÁLIDO!";
						break;
		}
		return mes;
	}
	
	public E getLast() {
		return this.lista.get(this.lista.size()-1);
	}
	
	@Override
	public Iterator<E> iterate_list() {
		return lista.iterator();
	}
	
	@Override
	public boolean addObjToList(E obj) {
		return this.lista.add(obj);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(String.format("-- Mês:\t%s\n", this.getMesStringFormat()));
		Iterator<E> it = this.iterate_list();
		
		while (it.hasNext()) {
			E obj = it.next();
			str.append(String.format("%s\n", obj.toString()));
		}
		
		return str.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Mes<?>) {
			Mes<E> mes = (Mes<E>) obj;
			if (this.getMes() == mes.getMes())
				return true;
		}
		return false;
	}
}
