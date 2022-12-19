package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.GetTheWeather;
import model.WeatherBean;

import java.io.IOException;

@WebServlet(name = "OWServlet", value = "/OWServlet")
public class OWServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //getting attributes from another page
        String cityStr = request.getParameter("city");
        String countryStr = request.getParameter("country");

        //Creating object
        WeatherBean wBean = new WeatherBean(cityStr, countryStr);
        //Creating object and putting weatherbean object into this one
        GetTheWeather getTheWeather = new GetTheWeather(wBean);

        //getting the tag and attribute with bean object
        wBean.setCloudsStr(getTheWeather.getValueFromTag("clouds", "name"));
        wBean.setTemperature(getTheWeather.getValueFromTag("temperature", "value"));
        wBean.setUpdate(getTheWeather.getValueFromTag("lastupdate", "value"));
        request.setAttribute("wBean", wBean);

        //creating cookies and getting parameters
        Cookie cityCookie = new Cookie("city", cityStr);
        Cookie countryCookie = new Cookie("country", countryStr);

        //set expiry date after 24 Hrs for both the cookies.
        cityCookie.setMaxAge(60 * 60 * 24);
        countryCookie.setMaxAge(60 * 60 * 24);

        response.addCookie(cityCookie);
        response.addCookie(countryCookie);

        RequestDispatcher rd = request.getRequestDispatcher("views/showWeather.jsp");
        rd.forward(request, response);
    }
}
