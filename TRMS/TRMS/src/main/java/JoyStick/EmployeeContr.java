package JoyStick;



import Entity.Employee;
import Service.EmployeeServ;
import Service.EmployeeServImp;
import io.javalin.http.Context;

public class EmployeeContr {
	
	private static EmployeeServ employeeServ = new EmployeeServImp();
	
	public static void checkLogin(Context ctx) {
		System.out.println("Please wait while I check your login credentials");
		Employee emp = ctx.sessionAttribute("user");
		if (emp != null) {
			System.out.println("You are logged in as " + emp.getUser());
			ctx.json(emp);
			ctx.status(200);
		} else {
			System.out.println("Not logged in");
			ctx.status(400);
		}
	}
	public static void logIn(Context ctx) {
		System.out.println("You are logging in");
		String username = ctx.queryParam("user");
		String password = ctx.queryParam("pass");
		
		Employee emp = employeeServ.getEmployeeByUsername(username);
		if (emp != null) {
			if (emp.getPass().equals(password))
			{
				System.out.println("You are logged in as " + emp.getUser());
				ctx.status(200);
				ctx.json(emp);
				ctx.sessionAttribute("user", emp);
			}
			else
			{
				ctx.status(400);
			}
		}
		else
		{
			ctx.status(404);
		}
	}
	
	public static void logOut(Context ctx) {
		System.out.println("You are logging out. Goodbye");
		ctx.req.getSession().invalidate();
		ctx.status(200);
	}
	
	public static void getUserById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Employee emp = employeeServ.getEmployeeById(id);
		if (emp != null) {
			ctx.status(200);
			ctx.json(emp);
		} else {
			ctx.status(404);
		}
	}
	
	public static void updateUser(Context ctx) {
		Employee tempEmployee = ctx.bodyAsClass(Employee.class);
		if(tempEmployee != null) {
		employeeServ.updateEmployee(tempEmployee);
		ctx.status(200);
		System.out.println("made it through empcontr");
		}else {
			ctx.status(404);
		}
	}
}
