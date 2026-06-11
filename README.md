# MPGC-RPG-125691
Progetto Java sviluppato per il corso di Metodologie di Programmazione.

L'applicazione implementa un piccolo gioco di ruolo a turni con un'interfaccia graifca JavaFx. Il giocatore può creare
un personaggio scegliendone il nome e la classe, affrontare e completare tre quest diverse, combattere con sistema a turni
ottenendo ricompense, gestire un inventario e salvare o caricare lo stato della partita tramite file JSON.

### Funzionalità principali
* Creazione di una nuova partita.
* Scelta del nome e della classe del personaggio.
* Visualizzazione delle statistiche del personaggio.
* Gestione di quest disponibili e completate.
* Combattimento a turni contro nemici.
* Azione di combattimento:
  * attacco;
  * difesa;
  * uso di pozioni;
  * fuga.
* Inventario del personaggio.
* Salvataggio e caricamento della partita.
* Interfaccia grafica JavaFx con tema RPG.

### Requisiti
Per compilare ed eseguire il progetto è necessario avere installato:
* JDK 25;
* Gradle Wrapper incluso nel progetto.

Il progetto utilizza JavaFx e Gson tramite Gradle.

### Compilazione

Da terminale, nella cartella principale del progetto:

./gradlew build

### Esecuzione

Da terminale, nella cartella principale del progetto:

./gradlew run

### Struttura del progetto

Tutte le classi del progetto sono contenute nel package:

it.unicam.cs.mpgc.rpg125691

La struttura principale è organizzata nei seguenti package:

- app
- controller
- model
- service
- persitence
- view

### App

Contiene il punto di ingresso dell'applicazione JavaFx.

### Controller

Contiene il controller principale dell'interfaccia grafica. Il controller riceve le azioni dell'utente, chiama i servizi
applicativi e aggiorna la vista.

### Model

Contiene le classi del dominio del gioco, come personaggio, statistiche, inventario, item, nemici, quest e risultati battaglia.

### Service

Contiene la logica applicativa del gioco, separata dall'interfaccia grafica. Sono presenti servizi per la gestione della partita,
delle quest, del combattimento, dell'inventario e delle ricompense.

### Persistence

Contiene le classi dedicate al salvataggio e al caricamento dello stato della partita. La persistenza è realizzata tramite JSON.

### View

Contiene i comportamenti di JavaFx dell'interfaccia grafica. La GUI è divisa in più classi per evitare una singola classe troppo grande.

### Persistenza

Il gioco utilizza un singolo file JSON come slot di salvataggio.

Il salvataggio viene effettuato nel file:

saves/save.json

Ogni nuovo salvataggio sovrascrive quello precedente. La scelta è stata fatta per mantenere semplice la prima versione dell'applicazione,
lasciando comunque aperta la possibilità di introdurre salvataggi multipli in futuro.

### Estendibilità

Il progetto è stato organizzato per facilitare future estensioni. Alcuni esempi possibili possono essere i seguenti:
* aggiunta di nuove classi personaggio;
* aggiunta di nuovi nemici e quest;
* introduzione di nuove tipologie di item;
* sistema di equipaggiamento;
* negozi e mercanti di oggetti;
* salvataggi multipli;
* persistenza su database;
* nuove interfacce utente;
* menù.

La separazione tra model, service, persistence, controller e view permette di modificare una parte del progetto senza dover riscrivere l'intera applicazione.

### Uso di strumenti di AI

Durante lo sviluppo sono stati utilizzati strumenti di AI come supporto occasionale per brainstorming, revisione di alcune 
scelte progettuali, rifinitura della documentazione e suggerimenti sullo stile grafico dell'interfaccia.

Le scelte implementative finali, l'integrazione del codice, l'adattamento al progetto e la verifica del funzionamento 
sono stati svolti manualmente.