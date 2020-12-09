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
				
				path ("all", () -> {
					get(SubmitFormContr::getAll);
				});
				path(":id", () -> {
					get(SubmitFormContr::getFormByIds); // get a cat by id
					put(SubmitFormContr::updateForm); // update a cat
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
