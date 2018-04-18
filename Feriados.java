import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Classe para calculo de feriados nacionais 
 * 
 * @author Daniel Perin Tavares aka cetr1n
 */
public class Feriados {
	public Calendar pascoa = GregorianCalendarWrapper.getInstance();
	public Calendar carnaval = GregorianCalendarWrapper.getInstance();
	public Calendar corpusChristi = GregorianCalendarWrapper.getInstance();
	public Calendar sextaFeiraSanta = GregorianCalendarWrapper.getInstance();
	public Calendar diaDoTrabalhador =  GregorianCalendarWrapper.getInstance();
	public Calendar natal =  GregorianCalendarWrapper.getInstance();
	public Calendar seteDeSetembro =  GregorianCalendarWrapper.getInstance();
	public Calendar diasDasCriancas =  GregorianCalendarWrapper.getInstance();
	public Calendar finados =  GregorianCalendarWrapper.getInstance();
	public Calendar proclamacaoDaRepublica =  GregorianCalendarWrapper.getInstance();
	public Calendar tiradentes = GregorianCalendarWrapper.getInstance();
	public Calendar primeiroDiaDoAno = GregorianCalendarWrapper.getInstance();
	
	private List<Calendar> lstCalendar;
	private int ano;

	public Calendar getPascoa() {
		return pascoa;
	}

	public Calendar getCarnaval() {
		return carnaval;
	}

	public Calendar getCorpusChristi() {
		return corpusChristi;
	}

	public Calendar getSextaFeiraSanta() {
		return sextaFeiraSanta;
	}

	public Calendar getDiaDoTrabalhador() {
		return diaDoTrabalhador;
	}

	public Calendar getNatal() {
		return natal;
	}

	public Calendar getSeteDeSetembro() {
		return seteDeSetembro;
	}

	public Calendar getDiasDasCriancas() {
		return diasDasCriancas;
	}

	public Calendar getFinados() {
		return finados;
	}

	public Calendar getProclamacaoDaRepublica() {
		return proclamacaoDaRepublica;
	}

	public Calendar getTiradentes() {
		return tiradentes;
	}
	
	public Calendar getPrimeiroDiaDoAno() {
		return primeiroDiaDoAno;
	}
	
	public void setPrimeiroDiaDoAno(Calendar primeiroDiaDoAno) {
		this.primeiroDiaDoAno = primeiroDiaDoAno;
	}
	
	public int getAno() {
		return ano;
	}
	
	public void setAno(int ano) {
		this.calculaTodasAsDatasDeUmAno(ano);
	}
	
	/**
	 * Inicia a classe com o ano da data corrente
	 */
	public Feriados() {
		calculaTodasAsDatasDeUmAno(GregorianCalendar.getInstance().getTime());
	}

	/**
	 * Calcula todas as datas de um ano de uma data
	 * 
	 * @param Date data a ser extraída o ano
	 */
	private void calculaTodasAsDatasDeUmAno(Date data) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(data);
		int ano = calendar.get(Calendar.YEAR);
		
		this.calculaTodasAsDatasDeUmAno(ano);
	}

	/**
	 * Classe para calcular todos os feriados de um ano
	 * 
	 * @param ano Ano a ser calculado
	 * @exception RuntimeException se a data for 0
	 * @exception RuntimeException se a data for menor que 0
	 */
	private void calculaTodasAsDatasDeUmAno(int ano) {
		
		if (ano == 0) {
			throw new RuntimeException("O ano 0 é inválido");
		}
		
		if (ano < 0) {
			throw new RuntimeException("Apenas anos depois de Cristo são válidos");
		}
		
		this.ano = ano;

		this.calcularDatasReligiosasDinamicas();
		this.calcularDatasFixas();
		this.carregarList();
	}

	/**
	 * Calcula as datas religiosas
	 * 
	 * @see https://pt.wikipedia.org/wiki/Cálculo_da_Páscoa
	 */
	private void calcularDatasReligiosasDinamicas() {
		
		//Calculo da Pascoa
		int a = ano % 19;
		int b = ano / 100;
		int c = ano % 100;
		int d = b / 4;
		int e = b % 4;
		int f = (b + 8) / 25;
		int g = (b - f + 1) / 3;
		int h = (19 * a + b - d - g + 15) % 30;
		int i = c / 4;
		int k = c % 4;
		int l = (32 + 2 * e + 2 * i - h - k) % 7;
		int m = (a + 11 * h + 22 * l) / 451;
		int mes = (h + l - 7 * m + 114) / 31;
		int dia = ((h + l - 7 * m + 114) % 31) + 1;
		this.pascoa.set(Calendar.YEAR, ano);
		this.pascoa.set(Calendar.MONTH, mes-1);
		this.pascoa.set(Calendar.DAY_OF_MONTH, dia);
		
		//Carnaval 47 dias antes da pascoa
		this.carnaval.setTimeInMillis(pascoa.getTimeInMillis());
		this.carnaval.add(Calendar.DAY_OF_MONTH, -47);
		
		//CorpusChristi 60 dias apos a pascoa
		this.corpusChristi.setTimeInMillis(pascoa.getTimeInMillis());
		this.corpusChristi.add(Calendar.DAY_OF_MONTH, 60);
		
		//Sexta Feira santa 2 dias antes da pascoa
		this.sextaFeiraSanta.setTimeInMillis(pascoa.getTimeInMillis());
		this.sextaFeiraSanta.add(Calendar.DAY_OF_MONTH, -2);
	}
	
	/**
	 * Apenas seta os feriados que dias fixos
	 * todo ano ex: natal todo dia 25/12
	 */
	protected void calcularDatasFixas() {
		
		// 01/05
		this.diaDoTrabalhador.set(Calendar.YEAR, ano);
		this.diaDoTrabalhador.set(Calendar.MONTH, Calendar.MAY);
		this.diaDoTrabalhador.set(Calendar.DAY_OF_MONTH, 1);
		
		// 25/12
		this.natal.set(Calendar.YEAR, ano);
		this.natal.set(Calendar.MONTH, Calendar.DECEMBER);
		this.natal.set(Calendar.DAY_OF_MONTH, 25);
		
		// 07/09
		this.seteDeSetembro.set(Calendar.YEAR, ano);
		this.seteDeSetembro.set(Calendar.MONTH, Calendar.SEPTEMBER);
		this.seteDeSetembro.set(Calendar.DAY_OF_MONTH, 7);
		
		// 12/10
		this.diasDasCriancas.set(Calendar.YEAR, ano);
		this.diasDasCriancas.set(Calendar.MONTH, Calendar.OCTOBER);
		this.diasDasCriancas.set(Calendar.DAY_OF_MONTH, 12);

		// 02/11
		this.finados.set(Calendar.YEAR, ano);
		this.finados.set(Calendar.MONTH, Calendar.NOVEMBER);
		this.finados.set(Calendar.DAY_OF_MONTH, 2);
		
		// 15/11
		this.proclamacaoDaRepublica.set(Calendar.YEAR, ano);
		this.proclamacaoDaRepublica.set(Calendar.MONTH, Calendar.NOVEMBER);
		this.proclamacaoDaRepublica.set(Calendar.DAY_OF_MONTH, 15);

		// 21/04
		this.tiradentes.set(Calendar.YEAR, ano);
		this.tiradentes.set(Calendar.MONTH, Calendar.APRIL);
		this.tiradentes.set(Calendar.DAY_OF_MONTH, 21);
		
		// 01/01
		this.primeiroDiaDoAno.set(Calendar.YEAR, ano);
		this.primeiroDiaDoAno.set(Calendar.MONTH, Calendar.JANUARY);
		this.primeiroDiaDoAno.set(Calendar.DAY_OF_MONTH, 01);
		
	}
	
	/**
	 * Verifica se a data vindo de parametro é ou não 
	 * feriado nacional dentro do ano passado por parametro
	 * 
	 * @param Date date a ser comparada
	 * @return true se sim
	 */
	public boolean isFeriado(Date date) {
		
		//Calcula todos os feriados de um ano 
		this.calculaTodasAsDatasDeUmAno(date);
		
		//Insancia um calendar para comparação
		Calendar calendarAComparar = GregorianCalendarWrapper.getInstance();
		calendarAComparar.setTime(date);

		//Compara propriamente
		for (Calendar calendar: this.lstCalendar) {
			if (
				calendarAComparar.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH) && 
				calendarAComparar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
				calendarAComparar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
			) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Carrega as datas dessa classe na lista
	 */
	protected void carregarList() {
		this.lstCalendar = new ArrayList<Calendar>();
		
		for (Field field : Feriados.class.getFields()) {
			
			//Carrega apenas os calendar no list
			try {
				this.lstCalendar.add((Calendar)field.get(this));
			} catch (Exception e
					) {
				continue;
			}
		}
	
	}
	
}
