# SWP(Softwareparadigmen) Aufgabe
## Servermonitoring und -skalierung
Software Paradigmen

Dieses Programm soll den Betrieb und die Auslastung von unterschiedlichen Server simulieren.
Mithilfe von unterschiedlichen Design Patterns sollen die Server ab gewissen Werten gestartet und gestoppt werden.
Alle erhobenen Werte werden jede 2. Sekunde in einer Log Datei aufgezeichnet.

### Patterns:
* Singleton
* Observer
* Command

### Trigger & Aktionen:
* Starte einen neuen Server, wenn die durchschnittliche Serverauslastung > 60 % ist
* Stoppe den Server mit der derzeit niedrigsten Auslastung, wenn die durchschnittliche
Serverauslastung < 30 % ist
* Logge die Anzahl der derzeit laufenden Server bei jedem Kennzahlupdate
* Logge die derzeitige durchschnittliche Serverauslastung bei jedem Kennzahlupdate

# SWP(Software Paradigms) Assignment
## Servermonitoring and -scaling
Software Paradigms

This program is supposed to simulate the usage and resource consumption of several servers.
With the implementation of some design patterns, the beforementioned servers are supposed to start and stop at a certain level of performance/activity.
Every 2 seconds the program records all the collected data in a log file.

### Patterns:
* Singleton
* Observer
* Command

### Triggers & Actions:
* starts a new server when the average activity is above 60 %
* stops the server with the lowest activity when the average activity is bellow 30 %
* logging the number of currently running servers on each update(every 2 seconds)
* logging the average server usage on each update
