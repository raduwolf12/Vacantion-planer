package com.baeldung.springsecuritythymeleaf.model.enums;

public enum LegalDayOff {
//	1 ianuarie, 2 ianuarie — Anul Nou
//	24 ianuarie — Ziua Unirii Principatelor Române
//	30 aprilie -Vinerea Mare,
//	2 -3 mai - Paște ortodox 2021
//	1 mai — Ziua Muncii
//	1 iunie - Ziua Copilului
//	20 iunie (duminică) - Rusalii, 21 iunie (luni) — A doua zi de Rusalii
//	15 august — Adormirea Maicii Domnului
//	30 noiembrie — Sfântul Andrei
//	1 decembrie — Ziua Națională a României
//	25 decembrie, 26 decembrie — Crăciunul
	ANUL_NOU1("01.01.2021"),
	ANUL_NOU2("01.01.2021"),
	ZIUA_UNIRII_PRINCIPATELOR("01.01.2021"),
	PASTE_ORTODOX1("01.01.2021"),
	PASTE_ORTODOX2("01.01.2021"),
	RUSALII1("01.01.2021"),
	RUSALII2("01.01.2021"),
	ADORMIREA_MAICIIDOMNULUI("01.01.2021"),
	SFANTUL_ANDREI("01.01.2020"),
	ZIUA_NATIONALA_ROMANIA("01.12.2021"),
	CRACIUNUL1("25.12.2021"),
	CRACIUNUL2("26.12.2021");
	
	private String val;
	LegalDayOff(String val)
	{
		this.val = val;
	}
	public String getVal()
	{
		return val;
	}
}
