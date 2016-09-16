package negocio;

public class Tarifa {
	private final double tarifa_por_incremento, valor_a_pagar;
	private final int incremento_em_minutos, tempo_minimo, tempo_maximo, tempo_de_permanencia;
	
	public Tarifa(double tarifa_por_incremento, int incremento_em_minutos, int tempo_minimo, int tempo_maximo, int tempo_de_permanencia) {
		this.tarifa_por_incremento = tarifa_por_incremento;
		this.incremento_em_minutos = incremento_em_minutos;
		if (tempo_de_permanencia % this.incremento_em_minutos != 0)
			throw new IllegalArgumentException("O módulo entre o tempo de permanência e o tempo do incremento deve ser 0!");
		this.tempo_minimo = tempo_minimo;
		this.tempo_maximo = tempo_maximo;
		this.tempo_de_permanencia = tempo_de_permanencia;
		if (this.tempo_de_permanencia < this.tempo_minimo || this.tempo_de_permanencia > this.tempo_maximo)
			throw new IllegalArgumentException(String.format("O tempo de permanência informado (%d min) não está no intervalo [%d; %d] em minutos!", this.tempo_de_permanencia, this.tempo_minimo, this.tempo_maximo));
		this.valor_a_pagar = (this.tempo_de_permanencia/this.incremento_em_minutos)*this.tarifa_por_incremento;
	}
	
	public double getTarifa_por_incremento() {
		return this.tarifa_por_incremento;
	}

	public int getIncremento_em_minutos() {
		return this.incremento_em_minutos;
	}

	public int getTempo_minimo() {
		return this.tempo_minimo;
	}

	public int getTempo_maximo() {
		return this.tempo_maximo;
	}

	public int getTempo_de_permanencia() {
		return this.tempo_de_permanencia;
	}

	public double getValor_a_pagar() {
		return this.valor_a_pagar;
	}
	
	public static Tarifa getInstance_forTF(int tempo_de_permanencia) {
		return new Tarifa(0.25, 10, 30, 120, tempo_de_permanencia);
	}
}
