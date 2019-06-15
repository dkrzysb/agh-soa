<%--
  Created by IntelliJ IDEA.
  User: rafal
  Date: 3/16/2019
  Time: 12:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Numbers</title>
  </head>
  <body>
    <div>
      <h1>GetNumbers</h1>
      <form action="getNumbers" method="get">
        Number1: <input type="number" name="number1" /><br />
        Number2: <input type="number" name="number2" /><br />
        Number3: <input type="number" name="number3" /><br />
        Number4: <input type="number" name="number4" /><br />
        Number5: <input type="number" name="number5" /><br />
        <button type="submit">Oblicz średnią</button>
      </form>
    </div>
    <div>
      <h1>PostNumbers</h1>
      <form action="postNumbers" method="post">
        Number1 <input type="text" name="number1" /><br />
        Number2: <input type="text" name="number2" /><br />
        Number3: <input type="text" name="number3" /><br />
        Number4: <input type="text" name="number4" /><br />
        Text: <input type="text" name="text" /><br />
        <button type="submit">Sort</button>
      </form>
    </div>
  </body>
</html>
