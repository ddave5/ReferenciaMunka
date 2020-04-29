
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Main</title>
        <link href="<%= request.getContextPath() %>/css/mainPageStyle.css" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div id="main_title">Amőba</div>
        <form action="../MainPageController" method="post">
            <div id="menu">
                <div class="container">
                    <div class="setup_container">
                        <div class="setup_label"><label for="gametypes">Gametype:</label></div>
                        <div class="setup_value">
                            <select id="gametypes" name="pve" class="click_setup">
                                <option value="volvo">Player v CPU</option>
                                <option value="volvo">Player v Player</option>
                            </select>
                        </div>
                    </div>
                    <div class="setup_container">
                        <div class="setup_label">
                            <label for="number">Table size:</label>
                        </div>
                        <div class="setup_label">
                            <input id="number" name="number" type="number" min="5" max="20" value="15" class="click_setup">
                        </div>
                    </div>
                    <div class="setup_container">
                        <div class="setup_label">
                            <label for="time">Time limit:</label>
                        </div>
                        <div class="setup_value">
                            <select id="time" name="timelimit"  class="click_setup">
                                <option value="none">None</option>
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="15">15</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="button_container">
                    <div><button type="submit" class="load_button" name="loadgame">Load a previous game</button></div>
                    <div><button type="submit" class="load_button" name="startgame">Start</button></div>
                </div>
            </div>
        </form>
    </body>
</html>