# java-feriados
Classes java para tratamento dos feriados nacionais do Brasil

# instalação
Importe as classes Feriados e GregorianCalendarWrapper no seu projeto java

# exemplo de uso

		//Inicia a classe
		Feriados feriados = new Feriados();
		
		// 02/08/2018
		Calendar calendarTestar = GregorianCalendar.getInstance();
		calendarTestar.set(Calendar.DAY_OF_MONTH, 02);
		calendarTestar.set(Calendar.MONTH, Calendar.AUGUST);
		calendarTestar.set(Calendar.YEAR, 2018);
		
		//Testar se 02/02/2018 é feriado 
		System.out.println(feriados.isFeriado(calendarTestar.getTime())); // FALSE
		

		// 01/01/2018
		calendarTestar.set(Calendar.DAY_OF_MONTH, 01);
		calendarTestar.set(Calendar.MONTH, Calendar.JANUARY);
		calendarTestar.set(Calendar.YEAR, 2018);
		
		//Testar se 01/01/2018 é feriado
		System.out.println(feriados.isFeriado(calendarTestar.getTime())); // TRUE (Ano novo)



