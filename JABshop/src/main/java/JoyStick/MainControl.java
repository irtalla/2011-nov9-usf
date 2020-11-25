package JoyStick;
import java.util.Scanner;
import java.util.Set;

import Entity.Bike;
import Entity.Brand;
import Entity.Human;
import Entity.Role;
import Entity.Status;
import Providers.BikeP;
import Providers.BikePImp;
import Providers.HumanP;
import Providers.HumanPImp;


public class MainControl {
	private static Scanner sc;
	private static BikeP bikep = new BikePImp();
	private static HumanP humanp = new HumanPImp();
	
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		Boolean active = true;
		
		
		 while(active) {
			System.out.println("hey welcome to blackmarket bikes!!");
			System.out.println("where all of our bikes are questionable....");
			Human loggedin = null;
			
			while(loggedin == null) {
				System.out.println("Here are your options");
				System.out.println("1 = Signup");
				System.out.println("2 = Signin");
				System.out.println("other = close");
				int youpick = Integer.valueOf(sc.nextLine());
				
				switch(youpick) {
				case 1:
					loggedin = signup();
					break;
				case 2:
					loggedin = signin();
					break;
				default:
					active=false;
					break;
				}
				
			}
			System.out.println("you're in!!");
			System.out.println();
			menuLoop: while (true) {
				System.out.println("1 = Check out our bike inventory");
				System.out.println("2 = look at your bikes");
				if (loggedin.getPurp().getName().equals("user")) {
					System.out.println("other. Log out");
				} else if (loggedin.getPurp().getName().equals("employee")) {
					System.out.println("3 = Edit bikes");
					System.out.println("4 = Add/Remove users");
					System.out.println("other. Log out");
				}
				int youpick = Integer.valueOf(sc.nextLine());
				switch (youpick) {
				case 1:
					loggedin = viewBikeInventory(loggedin);
					break;
				case 2:
					 viewOwnedBikes(loggedin);
					break;
				case 3:
					loggedin = editBikes(loggedin);
					break;
				case 4:
					loggedin = editUsers(loggedin);
					break;
				default:
					System.out.println("goodbye");
					System.out.println();
					loggedin = null;
					break menuLoop;
				}
				if (loggedin == null) {
					System.out.println("adios");
					System.out.println();
					break menuLoop;
				}
			}
		}
		sc.close();
	}


	private static Human signup() {
		while (true) {
			Human newAccount = new Human();
			System.out.println("Enter a username: ");
			newAccount.setUser(sc.nextLine());
			System.out.println("Enter a password: ");
			newAccount.setPass(sc.nextLine());
			Role r = new Role();
			r.setId(2);
			r.setName("user");
			newAccount.setPurp(r);
			
					newAccount.setId(humanp.addHuman(newAccount));
					System.out.println("thank you");
					return newAccount;
				
			
		}
	}
	

	private static Human signin() {
		while (true) {
			System.out.println("Enter username: ");
			String username = sc.nextLine();
			System.out.println("Enter password: ");
			String password = sc.nextLine();
			
			Human user = humanp.getHumanByUsername(username);
			if (user == null) {
				System.out.print("invalid username ");
			} else if (user.getPass().equals(password)) {
				System.out.println("long time no see");
				return user;
			} else {
				System.out.print("password does not match ");
			}
			System.out.println("Do you want to try again?");
			System.out.println("1 = yes");
			System.out.println("other = no");
			int input = Integer.valueOf(sc.nextLine());
			if (input != 1) {
				break;
			}
		}
		return null;
	}
	

	private static Human viewBikeInventory(Human user) {
		Set<Bike> availablebikes = bikep.getAvailableBikes();
		
		
		
		System.out.println("you wanna buy a bike?");
		System.out.println("1 = yes");
		System.out.println("other = no");
		int youpick = Integer.valueOf(sc.nextLine());
		if (youpick == 1) {
			while (true) {
				System.out.println("All bikes here start at a low price of$250!!!");
				System.out.println("please give me a good offer");
				for (Bike bike : availablebikes) {
					System.out.println(bike.getId()+" = "+bike.getName()+" "+bike.getBrand().getBname());
				}
				System.out.println("Enter the id of the bike you want");
				youpick = Integer.valueOf(sc.nextLine());
				Bike bike = bikep.getBikeById(youpick);
				if (bike != null && bike.getStatus().getName().equals("available")) {
					System.out.println(bike.getName());
					System.out.println("You want to put an offer for " + bike.getName()+ " ?");
					System.out.println("1 = yes");
					System.out.println("other = no");
					youpick = Integer.valueOf(sc.nextLine());
					if (youpick == 1) {
						System.out.println("enter a bid:");
						youpick = Integer.valueOf(sc.nextLine());
						if(user.makeoffer(youpick)=="sure") {
						bikep.buyBike(user, bike);
						System.out.println("the "+bike.getName() + " bike is yours. nice doing business with you");
						System.out.println("remember you didnt get that bike from me");
						
						user= humanp.getHumanById(user.getId());
						break;
						}else {
							System.out.println("no deal, you can do better than that. try again");
							System.out.println();
						}
					} else {
						System.out.println("Okay, did you want to look at a different bike?");
						System.out.println("1 = yes");
						System.out.println("other = no");
						youpick = Integer.valueOf(sc.nextLine());
						if (youpick != 1)
							break;
					}
				} else {
					System.out.println("yeaaaaaa..... we dont have that bike. Do you want to try another one?");
					System.out.println("1 = yes");
					System.out.println("other = no");
					youpick = Integer.valueOf(sc.nextLine());
					if (youpick != 1) {
						System.out.println("thats cool");
						break;
					}
				}
			}
		} else {
			System.out.println("thats cool");
		}
		
		return user;
	}
	
	private static void viewOwnedBikes(Human user) {
		if (user.getBikeList().size() > 0) {
			System.out.println("Here are your bikes");
			for (Bike bike : user.getBikeList()) {
				System.out.println(bike);
			}
			
		} else {
			System.out.println("You don't have any bikes right now.");
			System.out.println();
		}
	}
	
	
	private static Human editBikes(Human user) {
		if (!(user.getPurp().getName().equals("employee")))
			return null;
		
		while (true) {
			System.out.println("Edit Bikes:");
			System.out.println("1 = Add a bike");
			System.out.println("2 = Edit a bike");
			System.out.println("other = go back");
			int youpick = Integer.valueOf(sc.nextLine());
			
			if (youpick == 1) {
				Bike newBike = new Bike();
				System.out.println("Enter the name of the bike: ");
				newBike.setName(sc.nextLine());
				System.out.println("pick a brand by entering the ID:");
				System.out.println("1 = Harley"); System.out.println("2 = Trek");
				System.out.println("3 = Cannondale"); System.out.println("other = Other");
				Brand brand = new Brand();
				brand.setId(Integer.valueOf(sc.nextLine()));
				switch(brand.getId()) {
				case 1:
					brand.setBname("Harley");
					break;
				case 2:
					brand.setBname("Trek");
					break;
				case 3:
					brand.setBname("Cannondale");
					break;
				default:
					brand.setBname("Other");
					break;
				}
				newBike.setBrand(brand);
				Status status = new Status();
				status.setId(1);
				status.setName("available");
				newBike.setStatus(status);
				System.out.println(newBike.getName());
				System.out.println("I like that name");
				System.out.println("1 = yes");
				System.out.println("other = retry");
				youpick = Integer.valueOf(sc.nextLine());
				if (youpick == 1) {
					newBike.setId(bikep.addBike(newBike));
					System.out.println("You successfully added " + newBike.getName() + "!");
					System.out.println("looks like we got us a new ride");
				}
			} else if (youpick == 2) {
				for (Bike bike : bikep.getAvailableBikes()) {
					System.out.println(bike.getId()+" "+bike.getName());
				}
				System.out.println("Pick a bike you want to edit by entering its ID.");
				Bike bike = bikep.getBikeById(Integer.valueOf(sc.nextLine()));
				Bike newBike = bike;
				if (bike != null) {
					System.out.println("Editing " + bike.getName());
					System.out.println("Current changes:\nName: " + newBike.getName());
					boolean editing = true;
					while (editing) {
						System.out.println("Edit:");
						System.out.println("1 = Name"); System.out.println("2 = Save changes");
						System.out.println("other = go back");
						youpick = Integer.valueOf(sc.nextLine());
						switch (youpick) {
						case 1:
							System.out.println("enter the new name: ");
							bike.setName(sc.nextLine());
							break;
						case 2:							
							bikep.updateBike(newBike);
							System.out.println(newBike.getName() + " has been updated");
						default:
							editing = false;
							break;
						}
					}
				}
			} else {
				break;
			}
		}
		
		return user;
	}
	

	private static Human editUsers(Human user) {
		
		if (!(user.getPurp().getName().equals("employee")))
			return null;
		
		while (true) {
			System.out.println("pick an option:");
			System.out.println("1 = Remove a user");
			System.out.println("2 = Add a user");
			System.out.println("other = go back");
			int youpick = Integer.valueOf(sc.nextLine());
			
			if (youpick == 1) {
				System.out.println("1 = delete by ID");
				System.out.println("2 = delete by username");
				System.out.println("other. go back");
				youpick = Integer.valueOf(sc.nextLine());
				Human deleteHuman = null;
				if (youpick == 1) {
					System.out.println("sure give us the id of the person you wanna delete");
					deleteHuman = humanp.getHumanById(Integer.valueOf(sc.nextLine()));
				} else if (youpick == 2) {
					System.out.println("sure, give us the username of the person you wanna delete");
					deleteHuman = humanp.getHumanByUsername(sc.nextLine());
				}
				if (youpick == 1 || youpick == 2) {
					if (deleteHuman != null) {
							humanp.removeHuman(deleteHuman);
							System.out.println("You removed "+ deleteHuman.getUser() + " successfully.");					
					} else {
						System.out.println("we dont have anyone with that user name");
					}
				}
			} else if (youpick == 2) {
				Human newAccount = new Human();
				System.out.println("Enter a username: ");
				newAccount.setUser(sc.nextLine());
				System.out.println("Enter a password: ");
				newAccount.setPass(sc.nextLine());
				System.out.println("are they an employee or a customer?");
				System.out.println("1 = Employee");
				System.out.println("other = Customer");
				youpick = Integer.valueOf(sc.nextLine());
				Role role = new Role();
				if (youpick == 1) {
					role.setId(1);
					role.setName("employee");
				} else {
					role.setId(2);
					role.setName("user");
				}
				newAccount.setPurp(role);
			
						humanp.addHuman(newAccount);
						System.out.println("looks like we got us a new member");
						System.out.println(newAccount.getUser() + " has been added to our store");

			} else {
				break;
			}
		}
		
		return user;
	}
}
