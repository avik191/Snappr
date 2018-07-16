<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
    <link rel="stylesheet" href="${css }/header.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
    <div class="header-main-container">
        <div class="header">
            <div class="header-logo">
                <img src="${images }/logo.png" alt="Snappr Logo">
            </div>
            <div class="header-search">
                <form action="" method="get">
                    <input type="text" name="query" placeholder="Search people..">
                </form>
            </div>
            <div class="header-settings" id="header-settings" data-dropdown-status="inactive" onclick="headerDropdown.showDropdown()">
                Settings
                <div class="header-dropdown">
                    <ul id="header-settings-dropdown" tabindex="-1" onblur="headerDropdown.hideDropdown()">
                        <li><a href="">FAQ</a></li>
                        <li><a href="">About Us</a></li>
                        <li><a href="">Contact</a></li>
                        <li><a href="">Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>


    <script src="${js }/dropdown.js"></script>
</body>
</html>