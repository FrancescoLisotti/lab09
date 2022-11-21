# Reactive GUIs 

## Instructions

Solve the exercises in the following order:

1. `reactivegui01` (see the corresponding package)
    - Here look carefully at the example, which is already solved for you. Read the comments, as they point out some things that should have been done better.
2. `reactivegui02` (see the corresponding package)
    - Read the instructions in the `Test` class, and take a look at the picture `reactivegui02.png` as a visual indication of the goal.
3. `reactivegui03` (see the corresponding package)
    - Read the instructions in the `Test` class, and take a look at the picture `reactivegui03.png` as a visual indication of the goal.

     * Realizzare una classe ConcurrentGUI con costruttore privo di argomenti,
     * tale che quando istanziata crei un JFrame con l'aspetto mostrato nella
     * figura allegata (e contatore inizialmente posto a zero).
     * 
     * Il contatore venga aggiornato incrementandolo ogni 100 millisecondi
     * circa, e il suo nuovo valore venga mostrato ogni volta (l'interfaccia sia
     * quindi reattiva).
     * 
     * Alla pressione del pulsante "down", il conteggio venga da lì in poi
     * aggiornato decrementandolo; alla pressione del pulsante "up", il
     * conteggio venga da lì in poi aggiornato incrementandolo; e così via, in
     * modo alternato.
     * 
     * Alla pressione del pulsante "stop", il conteggio si blocchi, e i tre
     * pulsanti vengano disabilitati. Per far partire l'applicazioni si tolga il
     * commento nel main qui sotto.
     * 
     * Suggerimenti: - si mantenga la struttura dell'esercizio precedente - per
     * pilotare la direzione su/giù si aggiunga un flag booleano all'agente --
     * deve essere volatile? - la disabilitazione dei pulsanti sia realizzata
     * col metodo setEnabled