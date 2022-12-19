<%@ page import="model.WeatherBean" %>
<%@ page import="model.GetTheWeather" %>
<%@ page import="org.w3c.dom.Document" %><%--
  Created by IntelliJ IDEA.
  User: kelly
  Date: 2022-12-13
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weather</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>

<body class="bg-blue-400">
<div>

    <div class="bg-white mx-auto text-center w-1/2 py-5 shadow-x1 rounded-3xl my-25 max-w-2xl ">
        <h2 class="text-4xl font-semibold border-b pb-2 mx-6">Choose location: </h2>
        <form action="<%=request.getContextPath()%>/OWServlet" method="get">

            <div class="grid grids-cols-2 gap-4 my-5 mx-8">
                <label for="city" class="text-xl flex items-center">City: </label>
                <input class="w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg focus:outline-none focus:ring-2"
                       id="city" type="text" name="city"/>
                <label for="country" class="text-xl flex items-center">Country: </label>
                <input class="w-full p-1 border-2 placeholder-blue-800 border-blue-700 appearance-none rounded-lg focus:outline-none focus:ring-2"
                       id="country" type="text" name="country"/>
                <input class="bg-blue-300 text-xl font-semibold px-4 py-1 rounded-lg hover:bg-blue-800 hover:text-white"
                       type="submit" value="go"/>
            </div>

        </form>

        <%
            WeatherBean wBean = (WeatherBean) request.getAttribute("wBean");
            out.print("<p class=\"bg-fuchsia-50 text-gray-400 font-semibold text-l w-80 rounded-lg my-2 mx-auto\">The weather in " + wBean.getCityStr() + " is now a " + wBean.getCloudsStr()
                    + ". The temperature is " + wBean.getTemperature() + "(Kelvin) and the last update was " + wBean.getUpdate() + "</p>");
        %>
    </div>
</div>


</body>
</html>
