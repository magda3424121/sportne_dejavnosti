# Športni Rezervacijski Sistem 🏀⚽

Aplikacija za upravljanje športnih centrov, dvoran in rezervacij terminov.

## Avtor
- **Ime:** Magdalena Saveva
- **Projekt:** Rezervacijski sistem (Šolski projekt)

## Navodila za zagon

### 1. Baza podatkov
Aplikacija uporablja **PostgreSQL** (ali tvojo izbrano bazo).
1. Ustvari bazo z imenom `rezervacije`.
2. Uvozi priloženo SQL datoteko `baza_podatkov.sql` (nahaja se v mapi `/sql`), ki vsebuje tabele, podatke, prožilce in podprograme.

### 2. Konfiguracija aplikacije
V datoteki `src/main/resources/application.properties` preveri/nastavi:
- `spring.datasource.url`: povezava do baze
- `spring.datasource.username`: tvoje uporabniško ime
- `spring.datasource.password`: tvoje geslo

### 3. Zagon
1. Odpri projekt v VS Code
2. Poženi glavno datoteko `RezervacijeApplication.java`. ./mvnw spring-boot:run    
3. Odpri brskalnik na: `http://localhost:8080/index.html`

## Funkcionalnosti
- Pregled športnih centrov in dvoran.
- Rezervacija termina preko interaktivnega koledarja.
- Preklic lastnih rezervacij.
- **Triggerji:** Preprečevanje dvojnih rezervacij, samodejno arhiviranje odpovedi in kontrola kapacitete.
- **Stored Procedure:** Hitro dodajanje novega centra skupaj z njegovo prvo dvorano.