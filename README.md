## Dinámica General
El juego es de batallas entre ejércitos, para dos jugadores. Toda la mecánica del juego está
organizada por turnos.
Cada ejército de cada jugador se compone de una o más legiones, o de soldados sueltos. A su
vez, cada legión tiene un nombre y puede estar compuesta por una cantidad X de soldados
sueltos de cada tipo (ver tipos de soldados en sección 2.2.1) u otras legiones.
Por ejemplo, la legión “Divide et impera" está compuesta por 1000 auxiliares, 2000 legionarios y
50 centuriones; mientras que el ejército “SPQR” está compuesto por las legiones “I” (que
cuenta con 500 legionarios y 3 centuriones), “II” (que cuenta con 100 auxiliares), junto a 30
legionarios sueltos (sobrevivientes de una batalla de las guerras púnicas).
Almacenados en disco, existen dos archivos con legiones pre-construidas. Cada archivo está
formateado con uno de los dos formatos soportados. (Ver detalles acerca de cada uno de los
formatos en la sección 2.2.1.1.)
Existen varias fases en el juego:
1. Los jugadores tiran un dado para elegir quién comienza a armar su ejército en primer
lugar.
2. Siguiendo el orden indicado por los dados (el mayor primero), los jugadores eligen, de
una lista de legiones ya preconstruidas y soldados sueltos, la combinación que
constituirá cada uno de sus ejércitos. Cada legión tiene un costo asociado (que es la
suma de los costos de cada uno de sus soldados). Cada ejército tiene un costo
asociado (que es la suma de los costos de cada una de sus legiones, más el de sus
soldados sueltos). Cada jugador cuenta con 500.000 puntos para armar su ejército.
Para que cada jugador pueda elegir cómo armar su ejército, el sistema debe desplegar
una lista de las legiones indicando su nombre, y el total de soldados de cada tipo que
posee cada legión. (En el caso de legiones compuestas por otras legiones, el sistema
debe ser capaz de mostrar en forma transparente las cantidades totales de cada tipo de
soldado). También debe mostrar el costo total de cada legión, el costo de comprar
soldados individuales y la cantidad de dinero restante para realizar las compras.
El jugador puede elegir algunos de los ítems listados (legiones prearmadas, auxiliares,
legionarios y centuriones) y la cantidad a comprar.
Si el dinero con el que cuenta el jugador es suficiente entonces se incorpora a su
ejército. En caso contrario se debe rechazar la compra con un mensaje acorde.
No es preciso que el jugador gaste todo su dinero, sino que en cualquier momento
puede terminar con la formación de su ejército.
3. Comienza la batalla, organizada por turnos. Por una cuestión de equilibrio, el jugador
que ataca primero será quien empezó en segundo lugar a armar su ejército. En cada
turno cada jugador ataca (una vez) al ejército rival. En el turno siguiente, el rival
responde de la misma manera. Pierde el ejército que primero se queda sin soldados.
(Ver Asignación de Daño en la Sección 2.2.2.).
Si el jugador en acción (atacante) todavía cuenta con dinero, el sistema le debe dar la
opción de comprar más soldados y/o legiones prearmadas.
