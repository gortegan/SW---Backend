package startup.sw.controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.Collections;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import startup.sw.entities.UserApp;
import startup.sw.services.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("")
@RestController
public class UserController implements ErrorController{

     
	
	@Autowired
	private static final String PATH = "/error";
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	@RequestMapping(method = RequestMethod.POST, value="/users")
	  public @ResponseBody String getUsers() {
	    return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
	           "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
	  }

	@RequestMapping(method = RequestMethod.POST, value="/login/google")
	  public String loginGoogle(@RequestBody String req) {
		try{
			JSONObject jsonObj = new JSONObject(req);
			String token = jsonObj.getString("token");
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
		    // Specify the CLIENT_ID of the app that accesses the backend:
		    .setAudience(Collections.singletonList("275008617646-isldmdlgopsoqhqlrl75phspbb6mlg3r.apps.googleusercontent.com"))
		    .build();

			GoogleIdToken idToken = verifier.verify(token);
			if (idToken != null) {
			  Payload payload = idToken.getPayload();
		
			  if(userService.findUserByEmail(payload.getEmail()) != null){
				  return "Login Correcto";
			  }else{
				  UserApp user = new UserApp();
				  user.setActive(1);
				  user.setName((String) payload.get("given_name"));
				  user.setLastname((String) payload.get("family_name"));
				  user.setEmail(payload.getEmail());
				  userService.create(user);
			  }
			} else {
			  System.out.println("Invalid ID token.");
			}
		}catch(GeneralSecurityException exc){
			System.out.println("Error.");
		}catch(JSONException ex){
			System.out.println("Error.");
		}catch(IOException e){
			System.out.println("Error.");
		}
		
		return "OK!";
	  }
	
	@RequestMapping (method = RequestMethod.POST, value="/registrar")
	public @ResponseBody UserApp registrarUsuario(@RequestBody UserApp user){
		if(userService.findUserByEmail(user.getEmail())==null){
			user.setActive(1);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userService.create(user);
		}
		return user;		
	}
	
	@Override
    public String getErrorPath() {
        return PATH;
    }
}
