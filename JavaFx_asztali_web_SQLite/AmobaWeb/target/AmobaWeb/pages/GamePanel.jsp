
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Game Panel</title>
        <link href="<%= request.getContextPath() %>/css/gamePanelStyle.css" type="text/css" rel="stylesheet" />
    </head>
    <body>
    <jsp:include page="/GamePanelController"/>
        <div id="title_container">
            <div class="player">
                <div class="player_name">Player1</div>
                <div id="player_x">X</div>
            </div>
            <div id="limit">No Limit</div>
            <div class="player">
                <div class="player_name">Player1</div>
                <div id="player_o">O</div>
            </div>
        </div>
        <form action="../GamePanelController" method="post">
            <div id="game_container">
                <div id="menu">
                    <div class="menu_button"><button type="submit" class="load_button" name="hintButton">Hint</button></div>
                    <div class="menu_button"><button type="submit" class="load_button" name="newgameButton">New Game</button></div>
                    <div class="menu_button"><button type="submit" class="load_button" name="backToMainButton">Back to main</button></div>
                    <div class="menu_button"><button type="submit" class="load_button" name="saveButton">Save a game</button></div>
                    <div class="menu_button"><button type="submit" class="load_button" name="replayButton">Replay a game</button></div>
                </div>
                <div id="game_area">

                </div>
            </div>
        </form>
    </body>
</html>
