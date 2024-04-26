<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>My Website</title>
  </head>

  <body>
    <div>
        SUM Studios
    </div>
    <br>
    <br>

    <form action="addAlien" method="POST">
         Enter your id : <input type="text" name="aid"><br><br>
         Enter your name : <input type="text" name="aname"><br>
         <button type="submit">Add</button>
    </form>
    <br>
    <br>
    <hr>
    <br>
    <form action="getAlien">
         Enter alien id : <input type="text" name="aid"><br><br>
         <button type="submit">Search</button>
    </form>
    <br>
    <br>
    <hr>
    <br>
    <form action="getAlienByName">
         Enter alien name : <input type="text" name="aname"><br><br>
         <button type="submit">Search</button>
    </form>

  </body>
</html>