	CREATE TABLE Csatorna(
		cskod INTEGER PRIMARY KEY AUTO_INCREMENT,
		nev TEXT NOT NULL,
		UNIQUE(cskod)
	);
	CREATE TABLE Tematika(
		tkod INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
		tnev TEXT NOT NULL,
		UNIQUE(tkod)
	);

	CREATE TABLE Javasolt_tematika(
		cskod INTEGER NOT NULL,
		tkod INTEGER NOT NULL,
		PRIMARY KEY (cskod, tkod),
		FOREIGN KEY (cskod) REFERENCES Csatorna (cskod) ON DELETE CASCADE ON UPDATE NO ACTION,
		FOREIGN KEY (tkod) REFERENCES Tematika (tkod) ON DELETE CASCADE ON UPDATE NO ACTION
	);

	CREATE TABLE Musor(
		mkod INTEGER PRIMARY KEY,
		cim TEXT NOT NULL, 
		leiras TEXT, 
		jatekido INTEGER,
		UNIQUE(mkod)
	);

	CREATE TABLE Sugarozza(
		musor_mkod INTEGER NOT NULL,
		csatorna_cskod INTEGER NOT NULL,
		nap TEXT NOT NULL,
		ora INTEGER NOT NULL,
		perc INTEGER NOT NULL,
		ido TIME DEFAULT NULL,
		PRIMARY KEY (musor_mkod, csatorna_cskod),
		FOREIGN KEY (musor_mkod) REFERENCES Musor (mkod) ON DELETE CASCADE ON UPDATE NO ACTION,
		FOREIGN KEY (csatorna_cskod) REFERENCES Csatorna (cskod) ON DELETE CASCADE ON UPDATE NO ACTION
	);

	CREATE TABLE Felhasznalo(
		nev VARCHAR(20) NOT NULL, 
		szulev INTEGER,
		email VARCHAR(100) NOT NULL,
		jelszo TEXT NOT NULL,
		UNIQUE(email)
	);


	CREATE TABLE Koveti(
		musor_mkod INTEGER,
		felhasznalo_email VARCHAR(100) NOT NULL,
		PRIMARY KEY (musor_mkod, felhasznalo_email),
		FOREIGN KEY (musor_mkod) REFERENCES Musor (mkod) ON DELETE CASCADE ON UPDATE NO ACTION,
		FOREIGN KEY (felhasznalo_email) REFERENCES Felhasznalo (email) ON DELETE CASCADE ON UPDATE NO ACTION
	);


	INSERT INTO Csatorna(nev) VALUES('TV2');
	INSERT INTO Csatorna(nev) VALUES('RTL');
	INSERT INTO Csatorna(nev) VALUES('NatGeo');
	INSERT INTO Csatorna(nev) VALUES('SzegedTv');

	INSERT INTO Tematika (tnev) VALUES('Közszolgálati');
	INSERT INTO Tematika (tnev) VALUES('Dokumentum');
	INSERT INTO Tematika (tnev) VALUES('Helyi');

	INSERT INTO Javasolt_tematika (cskod, tkod) VALUES(1,1);
	INSERT INTO Javasolt_tematika (cskod, tkod) VALUES(2,1);
	INSERT INTO Javasolt_tematika (cskod, tkod) VALUES(3,2);
	INSERT INTO Javasolt_tematika (cskod, tkod) VALUES(4,3);

	/* Hétfõ RTL*/
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2101, 'Csucsformaban', 'Amerikai akciofilm-sorozat', 70);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2102, 'Autogram', '185.rész', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2103, 'CSI: New York-i helyszinelok', 'Amerikai-kanadai krimisoroat', 60);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2104, 'Anyak gyongye', 'Szerb-magyar ismeretterjeszto filmsorozat', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2105, 'Carter ugynok', 'Amerikai sci-fi sorozat', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2106, 'Top Shop', 'Reklamfilm', 70);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2107, 'Reggeli', 'Reggeli musor', 180);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2108, 'Astro-show', 'Ismeretterjeszto musor', 65);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2109, 'Top Shop', 'Reklamfilm', 110);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2110, 'Fokusz', 'Magazinmusor', 60);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2111, 'A konyhafonok', 'Magyar szorakoztato musor', 150);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2112, 'Az en kis csaladom', 'Torok filmsorozat', 70);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2113, 'Baratok kozt', 'Magyar dramasorozat', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2114, 'RTL Hirado Esti kiadas', 'Hirado musor', 55);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2115, 'Fokusz', 'Magazinmusor', 60);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2116, 'A konyhafonok', 'Magyar szorakoztato musor', 150);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2117, 'Baratok kozt', 'Magyar dramasorozat', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2118, 'Hazon kivul', 'Riportmusor', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2119, 'RTL Hirado', 'Hirado musor', 40);

	/* Kedd RTL*/
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2201, 'Csucsformaban', 'Amerikai akciofilm-sorozat', 70);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2202, 'Instagram', '92.rész', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2203, 'CSI: New York-i helyszinelok', 'Amerikai-kanadai krimisoroat', 60);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2204, 'Anyak gyongye', 'Szerb-magyar ismeretterjeszto filmsorozat', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2205, 'Carter ugynok', 'Amerikai sci-fi sorozat', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2206, 'Top Shop', 'Reklamfilm', 70);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2207, 'Reggeli', 'Reggeli musor', 180);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2208, 'Astro-show', 'Ismeretterjeszto musor', 65);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2209, 'Top Shop', 'Reklamfilm', 110);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2210, 'Fokusz', 'Magazinmusor', 60);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2211, 'A konyhafonok', 'Magyar szorakoztato musor', 150);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2212, 'Az en kis csaladom', 'Torok filmsorozat', 70);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2213, 'Baratok kozt', 'Magyar dramasorozat', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2214, 'RTL Hirado Esti kiadas', 'Hirado musor', 55);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2215, 'Fokusz', 'Magazinmusor', 60);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2216, 'A konyhafonok', 'Magyar szorakoztato musor', 150);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2217, 'Baratok kozt', 'Magyar dramasorozat', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2218, 'Anyam banja', 'Magyar szorakoztato musor', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2219, 'RTL Hirado', 'Hirado musor', 40);

	/* Szerda RTL*/
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2301, 'Csucsformaban', 'Amerikai akciofilm-sorozat', 70);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2302, 'Instagram', '92.rész', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2303, 'CSI: New York-i helyszinelok', 'Amerikai-kanadai krimisoroat', 60);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2304, 'Anyak gyongye', 'Szerb-magyar ismeretterjeszto filmsorozat', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2305, 'Carter ugynok', 'Amerikai sci-fi sorozat', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2306, 'Top Shop', 'Reklamfilm', 70);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2307, 'Reggeli', 'Reggeli musor', 180);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2308, 'Astro-show', 'Ismeretterjeszto musor', 65);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2309, 'Top Shop', 'Reklamfilm', 110);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2310, 'Fokusz', 'Magazinmusor', 60);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2311, 'A konyhafonok', 'Magyar szorakoztato musor', 150);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2312, 'Az en kis csaladom', 'Torok filmsorozat', 70);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2313, 'Baratok kozt', 'Magyar dramasorozat', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2314, 'RTL Hirado Esti kiadas', 'Hirado musor', 55);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2315, 'Fokusz', 'Magazinmusor', 60);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2316, 'A konyhafonok', 'Magyar szorakoztato musor', 150);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2317, 'Baratok kozt', 'Magyar dramasorozat', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2318, 'Ejjel-nappal Budapest Szeged', 'Magyar szorakoztato musor', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2319, 'RTL Hirado', 'Hirado musor', 40);

	/* Csütörtök RTL*/
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2401, 'Csucsformaban', 'Amerikai akciofilm-sorozat', 70);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2402, 'Kopaj', '35.rész', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2403, 'CSI: New York-i helyszinelok', 'Amerikai-kanadai krimisoroat', 60);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2404, 'Anyak gyongye', 'Szerb-magyar ismeretterjeszto filmsorozat', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2405, 'Carter ugynok', 'Amerikai sci-fi sorozat', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2406, 'Top Shop', 'Reklamfilm', 70);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2407, 'Reggeli', 'Reggeli musor', 180);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2408, 'Astro-show', 'Ismeretterjeszto musor', 65);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2409, 'Top Shop', 'Reklamfilm', 110);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2410, 'Fokusz', 'Magazinmusor', 60);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2411, 'A konyhafonok', 'Magyar szorakoztato musor', 150);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2412, 'Az en kis csaladom', 'Torok filmsorozat', 70);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2413, 'Baratok kozt', 'Magyar dramasorozat', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2414, 'RTL Hirado Esti kiadas', 'Hirado musor', 55);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2415, 'Fokusz', 'Magazinmusor', 60);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2416, 'A konyhafonok', 'Magyar szorakoztato musor', 150);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2417, 'Baratok kozt', 'Magyar dramasorozat', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2418, 'Karolyi vilaga', 'Magyar szorakoztato musor', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2419, 'RTL Hirado', 'Hirado musor', 40);

	/* Péntek RTL*/
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2501, 'Csucsformaban', 'Amerikai akciofilm-sorozat', 70);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2502, 'Nicsak ki beszel', '21.rész', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2503, 'CSI: New York-i helyszinelok', 'Amerikai-kanadai krimisoroat', 60);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2504, 'Anyak gyongye', 'Szerb-magyar ismeretterjeszto filmsorozat', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2505, 'Carter ugynok', 'Amerikai sci-fi sorozat', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2506, 'Top Shop', 'Reklamfilm', 70);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2507, 'Reggeli', 'Reggeli musor', 180);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2508, 'Astro-show', 'Ismeretterjeszto musor', 65);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2509, 'Top Shop', 'Reklamfilm', 110);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2510, 'Fokusz', 'Magazinmusor', 60);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2511, 'A konyhafonok', 'Magyar szorakoztato musor', 150);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2512, 'Az en kis csaladom', 'Torok filmsorozat', 70);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2513, 'Baratok kozt', 'Magyar dramasorozat', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2514, 'RTL Hirado Esti kiadas', 'Hirado musor', 55);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2515, 'Fokusz', 'Magazinmusor', 60);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2516, 'A konyhafonok', 'Magyar szorakoztato musor', 150);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2517, 'Baratok kozt', 'Magyar dramasorozat', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2518, 'Macskafogo', 'Magyar szorakoztato musor', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2519, 'RTL Hirado', 'Hirado musor', 40);

	/* Szombat RTL */

	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2601, 'Birdman', 'Amerikai vigjatek', 160);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2602, 'Rejtjelek', 'Amerikai krimisoroat', 85);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2603, 'Fokusz Plusz', 'Magazinmusor', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2604, 'Kolyokklub', 'Rajzfilm', 280);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2605, 'Top Shop', 'Reklamfilm', 110);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2606, 'Lifestyle', 'Magyar magazinmusor', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2607, 'Highlife', '31.resz', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2608, 'Kalandozo', 'Magyar eletmodmagazin', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2609, 'Edes otthon', '60.resz', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2610, 'Az ev hotele', '93.resz', 45);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2611, 'Lila fuge', 'Magyar magazinmusor', 45);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2612, 'Street Kitchen', 'Fozoshow', 45);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2613, 'Hiperkekusok', 'Olasz akcio-vigjatek', 145);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2614, 'RTL Hirado', 'Hirado musor', 55);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2615, 'Jofiuk', 'Magyar filmsorozat', 65);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2616, 'Ujra otthon', 'Amerikai romantikus vigjatek', 125);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2617, 'A csajok haboruja', 'Amerikai romantikus vigjatek', 120);

	/*Vasarnapi musor*/
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2701, 'Birdman', 'Amerikai vigjatek', 160);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2702, 'Rejtjelek', 'Amerikai krimisoroat', 85);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2703, 'Fokusz Plusz', 'Magazinmusor', 50);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2704, 'Kolyokklub', 'Rajzfilm', 280);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2705, 'Top Shop', 'Reklamfilm', 110);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2706, 'Lifestyle', 'Magyar magazinmusor', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2707, 'Highlife', '31.resz', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2708, 'Kalandozo', 'Magyar eletmodmagazin', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2709, 'Edes otthon', '60.resz', 30);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2710, 'Az ev hotele', '93.resz', 45);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2711, 'Lila fuge', 'Magyar magazinmusor', 45);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2712, 'Street Kitchen', 'Fozoshow', 45);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2713, 'Hiperkekusok', 'Olasz akcio-vigjatek', 145);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2714, 'RTL Hirado', 'Hirado musor', 55);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2715, 'Jofiuk', 'Magyar filmsorozat', 65);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2716, 'Ujra otthon', 'Amerikai romantikus vigjatek', 125);
	INSERT INTO Musor (mkod, cim, leiras, jatekido)  VALUES (2717, 'A csajok haboruja', 'Amerikai romantikus vigjatek', 120);

	/*Hétfõ sugárzás*/
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2101, 2, 1, 0, 25,'0:25');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2102, 2, 1, 1, 35,'1:35');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2103, 2, 1, 2, 25,'2:25');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2104, 2, 1, 3, 30,'3:30');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2105, 2, 1, 4, 0,'4:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2106, 2, 1, 4, 50,'4:50');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2107, 2, 1, 6, 0, '6:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2108, 2, 1, 9, 5, '9:05');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2109, 2, 1, 10, 10,'10:10');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2110, 2, 1, 11, 50, '11:50');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2111, 2, 1, 12, 30, '12:30');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2112, 2, 1, 14, 15, '14:15');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2113, 2, 1, 15, 30,'15:30');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2114, 2, 1, 16, 0, '16:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2115, 2, 1, 17, 10, '17:10');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2116, 2, 1, 18, 0, '18:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2117, 2, 1, 18, 55, '18:55');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2118, 2, 1, 20, 05, '20:05');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2119, 2, 1, 22, 30, '22:30');

/*Keddi sugárzás*/
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2201, 2, 2, 0, 25,'0:25');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2202, 2, 2, 1, 35,'1:35');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2203, 2, 2, 2, 25,'2:25');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2204, 2, 2, 3, 30,'3:30');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2205, 2, 2, 4, 0,'4:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2206, 2, 2, 4, 50,'4:50');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2207, 2, 2, 6, 0, '6:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2208, 2, 2, 9, 5, '9:05');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2209, 2, 2, 10, 10,'10:10');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2210, 2, 2, 11, 50, '11:50');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2211, 2, 2, 12, 30, '12:30');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2212, 2, 2, 14, 15, '14:15');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2213, 2, 2, 15, 30,'15:30');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2214, 2, 2, 16, 0, '16:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2215, 2, 2, 17, 10, '17:10');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2216, 2, 2, 18, 0, '18:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2217, 2, 2, 18, 55, '18:55');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2218, 2, 2, 20, 05, '20:05');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2219, 2, 2, 22, 30, '22:30');

/*Szerdai sugárzás*/
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2301, 2, 3, 0, 25,'0:25');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2302, 2, 3, 1, 35,'1:35');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2303, 2, 3, 2, 25,'2:25');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2304, 2, 3, 3, 30,'3:30');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2305, 2, 3, 4, 0,'4:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2306, 2, 3, 4, 50,'4:50');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2307, 2, 3, 6, 0, '6:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2308, 2, 3, 9, 5, '9:05');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2309, 2, 3, 10, 10,'10:10');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2310, 2, 3, 11, 50, '11:50');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2311, 2, 3, 12, 30, '12:30');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2312, 2, 3, 14, 15, '14:15');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2313, 2, 3, 15, 30,'15:30');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2314, 2, 3, 16, 0, '16:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2315, 2, 3, 17, 10, '17:10');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2316, 2, 3, 18, 0, '18:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2317, 2, 3, 18, 55, '18:55');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2318, 2, 3, 20, 05, '20:05');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2319, 2, 3, 22, 30, '22:30');

/*Csütörtöki sugárzás*/
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2401, 2, 4, 0, 25,'0:25');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2402, 2, 4, 1, 35,'1:35');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2403, 2, 4, 2, 25,'2:25');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2404, 2, 4, 3, 30,'3:30');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2405, 2, 4, 4, 0,'4:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2406, 2, 4, 4, 50,'4:50');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2407, 2, 4, 6, 0, '6:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2408, 2, 4, 9, 5, '9:05');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2409, 2, 4, 10, 10,'10:10');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2410, 2, 4, 11, 50, '11:50');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2411, 2, 4, 12, 30, '12:30');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2412, 2, 4, 14, 15, '14:15');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2413, 2, 4, 15, 30,'15:30');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2414, 2, 4, 16, 0, '16:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2415, 2, 4, 17, 10, '17:10');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2416, 2, 4, 18, 0, '18:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2417, 2, 4, 18, 55, '18:55');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2418, 2, 4, 20, 05, '20:05');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2419, 2, 4, 22, 30, '22:30');

/*Pénteki sugárzás*/
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2501, 2, 5, 0, 25,'0:25');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2502, 2, 5, 1, 35,'1:35');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2503, 2, 5, 2, 25,'2:25');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2504, 2, 5, 3, 30,'3:30');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2505, 2, 5, 4, 0,'4:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2506, 2, 5, 4, 50,'4:50');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2507, 2, 5, 6, 0, '6:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2508, 2, 5, 9, 5, '9:05');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2509, 2, 5, 10, 10,'10:10');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2510, 2, 5, 11, 50, '11:50');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2511, 2, 5, 12, 30, '12:30');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2512, 2, 5, 14, 15, '14:15');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2513, 2, 5, 15, 30,'15:30');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2514, 2, 5, 16, 0, '16:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2515, 2, 5, 17, 10, '17:10');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2516, 2, 5, 18, 0, '18:00');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2517, 2, 5, 18, 55, '18:55');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2518, 2, 5, 20, 05, '20:05');
	INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2519, 2, 5, 22, 30, '22:30');
/*Sugárzás szombat*/
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2601, 2, 6, 0, 0,'0:00');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2602, 2, 6, 2, 40,'2:40');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2603, 2, 6, 4, 5, '4:05');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2604, 2, 6, 4, 55, '4:55');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2605, 2, 6, 9, 35, '9:35');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2606, 2, 6, 10, 35, '10:35');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2607, 2, 6, 11, 5, '11:05');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2608, 2, 6, 11, 40, '11:40');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2609, 2, 6, 12, 15, '12:15');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2610, 2, 6, 12, 45, '12:45');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2611, 2, 6, 13, 30, '13:30');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2612, 2, 6, 14, 15, '14:15');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2613, 2, 6, 15, 0, '15:00');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2614, 2, 6, 15, 35, '15:35');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2615, 2, 6, 18, 0,'18:00');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2616, 2, 6, 18, 55, '18:55');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2617, 2, 6, 20, 0, '20:00');

/*Sugárzás vasárnap*/
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2701, 2, 7, 0, 0,'0:00');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2702, 2, 7, 2, 40,'2:40');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2703, 2, 7, 4, 5, '4:05');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2704, 2, 7, 4, 55, '4:55');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2705, 2, 7, 9, 35, '9:35');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2706, 2, 7, 10, 35, '10:35');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2707, 2, 7, 11, 5, '11:05');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2708, 2, 7, 11, 40, '11:40');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2709, 2, 7, 12, 15, '12:15');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2710, 2, 7, 12, 45, '12:45');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2711, 2, 7, 13, 30, '13:30');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2712, 2, 7, 14, 15, '14:15');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2713, 2, 7, 15, 0, '15:00');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2714, 2, 7, 15, 35, '15:35');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2715, 2, 7, 18, 0,'18:00');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2716, 2, 7, 18, 55, '18:55');
INSERT INTO Sugarozza(musor_mkod, csatorna_cskod, nap, ora, perc,ido) VALUES (2717, 2, 7, 20, 0, '20:00');

/*felhasználó*/

INSERT INTO Felhasznalo(nev, szulev, email, jelszo) VALUES ('ad', 1996, 'ad@ad.hu', 'ad');



