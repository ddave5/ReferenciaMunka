<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Review</title>
        <link href="<%= request.getContextPath() %>/css/replayStyle.css" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div id="title_container">
            <div class="setup_value">
                <select id="time"  class="click_setup">
                    <option value="PvCpu_2020-04-19 14:12:13">PvCpu_2020-04-19 14:12:13</option>
                </select>
            </div>
            <div class="menu_button"><button type="button" class="load_button">Load this Game</button></div>
        </div>
        <div id="main_area">
            <div id="info_panel">
                <div class="info_block">
                    <div class="game_label">Game Type:</div>
                    <div class="game_value">Player vs CPU</div>
                </div>
                <div class="info_block">
                    <div class="game_label">Table Size:</div>
                    <div>15</div>
                </div>
                <div class="info_block">
                    <div class="game_label">Time Limit:</div>
                    <div>No Limit</div>
                </div>
                <div id="button_container">
                    <div class="center_button"><button type="button" class="back_button">Back to main</button></div>
                    <div class="center_button"><button type="button" class="back_button">Exit</button></div>
                </div>
            </div>
            <div id="game_container">
                <div id="game_area"></div>
                <div id="play_settings">
                    <div class="img_container"><img src="../images/moveToEnd.png"></div>
                    <div class="img_container"><img src="../images/oneStepBackward.png"></div>
                    <div class="img_container"><img src="../images/pause.png"></div>
                    <div class="img_container"><img src="../images/play.png"></div>
                    <div class="img_container"><img src="../images/oneStepAhead.png"></div>
                    <div class="img_container"><img src="../images/moveToEnd.png"></div>
                </div>
            </div>
        </div>
    </body>
</html>
