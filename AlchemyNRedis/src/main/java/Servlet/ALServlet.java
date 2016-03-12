package Servlet;

import Bean.AlchemyConnector;
import Bean.SetOperations;
import java.io.*;
import java.net.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.*;
/*import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;*/

@WebServlet(name = "ALServlet", urlPatterns = {"/ALServlet"})
public class ALServlet extends HttpServlet {

	private String FACE_ENDPOINT_URL = "http://gateway-a.watsonplatform.net/calls/url/URLGetRankedImageFaceTags";

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
				
		HttpSession s = request.getSession();
	
		AlchemyConnector connector = new AlchemyConnector();
		//AlchemyVision service = new AlchemyVision();
		//service.setApiKey(connector.getAPIKey());

		String input_url = (String) request.getParameter("gurl");
		StringBuilder sb = new StringBuilder();
		String line;
		
		URL face_url = new URL(FACE_ENDPOINT_URL+"?url="+input_url+"&apikey="+connector.getAPIKey()+"&outputMode=json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(face_url.openStream()));
		while ((line = reader.readLine()) != null){
			sb.append(line);
		}
		
		request.setAttribute("face",sb.toString());
		
		//parse
		String result = sb.toString();
		String agerange;
		String gender;
		
		final JSONObject obj = new JSONObject("result"); 
		final JSONArray arr = obj.getJSONArray("imageFaces");
		
		final int n = arr.length();
		
		for (int i = 0; i < n; ++i) {
				final JSONObject words = arr.getJSONObject(i);
				final JSONObject age = words.getJSONObject("age");
				agerange = age.getString("ageRange");
				final JSONObject obj2 = words.getJSONObject("gender");
				gender = obj2.getString("gender");
			}
		
		request.setAttribute("agerange",agerange);
		request.setAttribute("gender",gender);
		
		SetOperations so = (SetOperations)s.getAttribute("setoper");
		so.add(0, agerange);
		so.add(1, gender);
		
		//ImageFaces image_faces = service.recognizeFaces(input_url,false);
		//request.setAttribute("image_faces",image_faces);
	
		response.setContentType("text/html");
        response.setStatus(200);
        request.getRequestDispatcher("viewinfo.jsp").forward(request, response);

	}

}

