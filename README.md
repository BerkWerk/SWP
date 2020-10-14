# SWP Aufgabe
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


