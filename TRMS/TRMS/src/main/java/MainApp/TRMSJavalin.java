package MainApp;


import static io.javalin.apibuilder.ApiBuilder.*;
import io.javalin.Javalin;
import JoyStick.EmployeeContr;
import JoyStick.SubmitFormContr;





public class TRMSJavalin {

	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static"); // pulling from src/main/resources
			config.enableCorsForAllOrigins();
		});
		
		app.start(8531);
		
		app.routes(() -> {
			path("submitForms", () -> {
				post(SubmitFormContr::addForm); 
				//put(SubmitFormContr::updateForm); // update a form
				//get(SubmitFormContr::getFormById); // get a form by id's
				path ("all", () -> {
					
					get(SubmitFormContr::getAll);
					
				});
				path ("DS", () -> {
					
					get(SubmitFormContr::getDS);
					
				});
				path ("DH", () -> {
					
					get(SubmitFormContr::getDH);
					
				});
				path ("HY", () -> {
					
					get(SubmitFormContr::getHY);
					
				});
				path ("Pile", () -> {
					
					get(SubmitFormContr::getPile);
					
				});
				path(":id", () -> {
					put(SubmitFormContr::updateForm); // update a form
					get(SubmitFormContr::getFormById); // get a form by id's
				}); 
			});
			// all requests to /users go to this handler
			path("users", () -> {
				get(EmployeeContr::checkLogin);
				put(EmployeeContr::logIn);
				delete(EmployeeContr::logOut);
				path (":id", () -> {
					get(EmployeeContr::getUserById);
					put(EmployeeContr::updateUser);
				});
			});
		});
	}

}
