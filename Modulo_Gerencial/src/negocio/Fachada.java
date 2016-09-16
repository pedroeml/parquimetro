package negocio;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Fachada {
	private ColecaoParquimetro colecao_parquimetro; // key: id do parquímetro, value: objeto parquímetro
	private String id_parquimetro_pesquisado, endereco_parquimetro_pesquisado;
	private Parquimetro parquimetro_pesquisado;
	private List<String[]> dados_grafico;
	
	public Fachada() {
		this.colecao_parquimetro = new ColecaoParquimetro();
		this.id_parquimetro_pesquisado = "";
		this.endereco_parquimetro_pesquisado = "";
	}
	
	public void loadLogFile(File file) {
		new Assembler_fromLogFile(file, this.colecao_parquimetro);
	}
	
	public String getId_parquimetro_pesquisado() {
		return id_parquimetro_pesquisado;
	}
	
	public String getEndereco_parquimetro_pesquisado() {
		return endereco_parquimetro_pesquisado;
	}
	
	public boolean pesquisarIDParquimetro(String id_parquimetro) {
		Parquimetro parquimetro = this.colecao_parquimetro.getParquimetro(id_parquimetro);
		if (parquimetro == null)
			return false;
		this.id_parquimetro_pesquisado = parquimetro.getId();
		this.endereco_parquimetro_pesquisado = parquimetro.getEndereco();
		this.parquimetro_pesquisado = parquimetro;
		return true;
	}
	
	public String relatorio_AgrupadoEmAnos() {
		return Relatorio.relatorio_AgrupadoEmAnos(parquimetro_pesquisado);
	}
	
	public String relatorio_AgrupadoEmMeses(int numero_do_ano) {
		String txt;
		try {
			txt = Relatorio.relatorio_AgrupadoEmMeses(this.parquimetro_pesquisado, numero_do_ano);
			this.dados_grafico = Relatorio.grafico_AgrupadoEmMeses(this.parquimetro_pesquisado, numero_do_ano);
		} catch (Exception e) {
			txt = e.getMessage();
		}
		return txt;
	}
	
	public Iterator<String[]> iterateDados_grafico()  {
		return this.dados_grafico.iterator();
	}
	
	public String relatorio_TodosParquimetros(int numero_do_dia, int numero_do_mes, int numero_do_ano) {
		return Relatorio.relatorio_TodosParquimetros(new ArrayList<Parquimetro>(this.colecao_parquimetro.values()), numero_do_dia, numero_do_mes, numero_do_ano);
	}
	
	public String relatorio_TodosParquimetros(int numero_do_mes, int numero_do_ano) {
		return Relatorio.relatorio_TodosParquimetros(new ArrayList<Parquimetro>(this.colecao_parquimetro.values()), numero_do_mes, numero_do_ano);
	}
}
