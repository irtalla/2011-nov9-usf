package com.revature.app;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;


import com.revature.controllers.ApprovalController;
import com.revature.controllers.DepartmentController;
import com.revature.controllers.EmployeeController;
import com.revature.controllers.EventController;
import com.revature.controllers.InformationRequestController;
import com.revature.controllers.ReimbursementFormController;

public class TRMSJavalin {

	public static void main(String[] args)
	{
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static"); // pulling from src/main/resources
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(()->{
			path("users", () -> {
				get(EmployeeController::checkLogin);
				put(EmployeeController::login);
				delete(EmployeeController::logout);
				path(":id", () -> {
					get(EmployeeController::getEmployeeById);
				});
				
			});
			path("events", () ->{
				get(EventController::getAllEvents);
				post(EventController::addEvent);
				delete(EventController::deleteEvent);
				path("/types", () -> {
					get(EventController::getAllEventTypes);
					path(":id", () -> {
						get(EventController::getEventTypeById);
					});
				});
				path(":id", () -> {
					get(EventController::getEventById);
				});
			});
			path("forms", () -> {
				path("/reimbursement", () -> {
					get(ReimbursementFormController::getAllReimbursementForms);
					post(ReimbursementFormController::addReimbursementForm);
					put(ReimbursementFormController::updateReimbursementForm);
					delete(ReimbursementFormController::deleteReimbursementForm);
					path(":id", () -> {
						get(ReimbursementFormController::getReimbursementFormById);
					});
					
				});
				path("/stages/:id", () -> {
					get(ReimbursementFormController::getStageById);
				});
				path("/statuses/:id", () -> {
					get(ReimbursementFormController::getStatusById);
				});
				path("/gradingformats", () -> {
					path("/all", () -> {
						get(ReimbursementFormController::getAllGradingFormats);
					});
					path("/:id", () -> {
						get(ReimbursementFormController::getGradingFormatById);
					});
					
				});
				path("/approval", () -> {
					get(ApprovalController::getAllApprovals);
					post(ApprovalController::addApproval);
					path(":id", () ->{
						get(ApprovalController::getApprovalById);
						delete(ApprovalController::deleteApproval);
					});
				});
				path("/inforequest", () -> {
					get(InformationRequestController::getAllInformationRequests);
					post(InformationRequestController::addInformationRequest);
					put(InformationRequestController::updateInformationRequest);
					path(":id", () -> {
						get(InformationRequestController::getInformationRequestById);
						delete(InformationRequestController::deleteInformationRequest);
					});
				});
			});
			path("departments", () -> {
				get(DepartmentController::getAllDepartments);
				path("/:id", () -> {
					get(DepartmentController::getDepartmentById);
				});
			});
			path("uploads", () -> {
				path("/presentations", () -> {
					post(ReimbursementFormController::addGradePresentationFile);
				});
				path("/eventattatchments", () -> {
					post(EventController::addEventAttatchment);
				});
				path("/approvalfiles", () -> {
					post(ApprovalController::addApprovalFile);
				});
			});
			path("downloads", () -> {
				path("/presentations", () -> {
					get(ReimbursementFormController::getGradePresentationFileByFormId);
				});
				path("/eventattatchments", () -> {
					get(EventController::getEventAttatchmentByEventId);
				});
				path("/approvalfiles", () -> {
					get(ApprovalController::getApprovalFileByFormId);
				});
			});
			path("notifications", () -> {
				post(ReimbursementFormController::addReimbursementChangeNotification);
				put(ReimbursementFormController::updateReimbursementChangeNotification);
				path("/:id", () -> {
					get(ReimbursementFormController::getReimbursementChangeNotificationByFormId);
				});
			});
		});
	}
}
