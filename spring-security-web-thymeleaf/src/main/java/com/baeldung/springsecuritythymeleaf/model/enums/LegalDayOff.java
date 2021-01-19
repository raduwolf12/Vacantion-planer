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
//	25 decembrie, 26 decembrie — Crăciunul yyyy-MM-dd
	ANUL_NOU1("2021-01-01"),
	ANUL_NOU2("2021-01-02"),
	ZIUA_UNIRII_PRINCIPATELOR("2021-04-30"),
	PASTE_ORTODOX1("2021-05-02"),
	PASTE_ORTODOX2("2021-05-03"),
	ZIUA_MUNCII("2021-05-01"),
	ZIUA_COPILULUI("2021-06-01"),
	RUSALII1("2021-06-20"),
	RUSALII2("2021-06-21"),
	ADORMIREA_MAICIIDOMNULUI("2021-08-15"),
	SFANTUL_ANDREI("2021-11-30"),
	ZIUA_NATIONALA_ROMANIA("2021-12-01"),
	CRACIUNUL1("2021-12-25"),
	CRACIUNUL2("2021-12-26");
	
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
