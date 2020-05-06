<html>
<head>
  <title> Gomoku - Ionita Andra 2A7 </title>
</head>
<body>
  <h1> Desfasurarea jocului: </h1>
  <#list allMoves as move>
    <p> Player-ul ${move.playerIndex} a mutat "bila" pe pozitia: coloana ${move.col}, linia ${move.row} </p>
  </#list>
  <p> Jocul a luat sfarsit! <br> <#if player??> ${player.mark} a castigat <#else> Jocul a fost intrerupt </#if>  </p>
</body>
</html>