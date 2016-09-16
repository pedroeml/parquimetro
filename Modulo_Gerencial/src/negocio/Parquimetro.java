package negocio;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Parquimetro implements IList<Ano<Ticket>> {
	private String id, endereco;
	private List<Ano<Ticket>> lista;
	
	public Parquimetro(String id, String endereco) {
		this.id = id;
		this.endereco = endereco;
		this.lista = new LinkedList<>();
	}
	
	public String getId() {
		return id;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public Ano<Ticket> getAno(int numero_do_ano) {
		if (numero_do_ano < 2016)
			throw new IllegalArgumentException("O número do ano está inválido!");
		Iterator<Ano<Ticket>> it = this.iterate_list();
		while (it.hasNext()) {
			Ano<Ticket> ano = it.next();
			if (ano.getAno() == numero_do_ano)
				return ano;
		}
		return null;
	}
	
	@Override
	public Iterator<Ano<Ticket>> iterate_list() {
		return this.lista.iterator();
	}
	
	/**
	 * Adiciona um objeto do tipo Ano<Ticket> na lista sem ter objetos repetidos na lista.
	 */
	@Override
	public boolean addObjToList(Ano<Ticket> obj) {
		if (lista.contains(obj))
			return false;
		return lista.add(obj);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(String.format("ID:\t%s\tEndereço:\t%s", this.getId(), this.getEndereco()));
		Iterator<Ano<Ticket>> it = this.iterate_list();
		
		while (it.hasNext()) {
			Ano<Ticket> ano = it.next();
			str.append(ano.toString());
		}
		
		return str.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Parquimetro) {
			Parquimetro parquimetro = (Parquimetro) obj;
			if (this.getId() == parquimetro.getId())
				return true;
		}
		return false;
	}
}
