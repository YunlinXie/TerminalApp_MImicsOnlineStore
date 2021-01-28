/**
 * Project Name: College Supplies
 * 
 * Group Members:
 * Sarthak Agarwal
 * Bhargavi Kumar Sundaresan
 * Le Minh Long Nguyen
 * Yunlin Xie
 * Xin Xin
 * 
 * Defines Login.java
 * @author Sarthak Agarwal
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {

	private static String EmailCus;
	
	public String getEmail() {
		return EmailCus;
	}
	
	
	
    public static void main(String[] args) throws IOException {
    	Store store = new Store();
        Scanner input = new Scanner(System.in);

        int choice_1;
        int choice_2;

        System.out.println("Are you a :\n");
        System.out.println("1) Employee");
        System.out.println("2) Customer");
        System.out.println("3) Leave the App!");
        System.out.print("Please choose the index for the preferred options:");
        choice_1 = input.nextInt();
        input.nextLine();
        System.out.println();

        switch (choice_1) {
            case 1:
                System.out.println("Hello Employee,\n");
                employeeLogIn(input);

                break;
            case 2:
                System.out.println("Hello Customer,");
                System.out.println("Are you a :");
                System.out.println("1)New Customer");
                System.out.println("2)Existing Customer");
                System.out.println("3)Guest");
                System.out.println("4)Back to main menu");
                System.out.print("Please choose the index for the preferred options:");
                choice_2 = input.nextInt();
                input.nextLine();
                switch (choice_2) {
                    case 1:
                        System.out.println("************* Welcome to College Supplies *************");
                        registerNewUser(input);
                        System.out.println("Redirecting to main menu:");
                        main(args);
                        break;

                    case 2:
                        System.out.println("************* Welcome back to College Supplies *************!\n");
                        customerLogIn(input);
                        break;

                    case 3:
                        System.out.println("************* Welcome to College Supplies *************\n");
               
                        store.view(3);


                    case 4:
                        main(args);
                        break;
                }
                break;
            case 3:
                System.out.println("Thanks for visiting us!\n");
                System.exit(0);

            default:
                System.out.println("\nPlease enter a valid option\n");
                break;
        }

    }

    private static void employeeLogIn(Scanner input) {
        boolean foundUser = false;
        boolean foundPass = false;
        String record = null;
        String record2 = null;
        FileReader in = null;
        System.out.print("Please Enter User Name:");
        String e_Uname = input.next();
        System.out.print("Please Enter Password:");
        String e_Pass = input.next();

        try {
            in = new FileReader("employee.txt");
            BufferedReader bin = new BufferedReader(in);
            record = new String();
            while ((record = bin.readLine()) != null && (record2 = bin.readLine()) != null) {
                if (e_Uname.contentEquals(record)) {
                    foundUser = true;

                    if (e_Pass.contentEquals(record2)) {
                        foundPass = true;

                        if (foundPass) {
                            System.out.println("Welcome " + record + "\n");
                      
                            store.view(2);

                            break;
                        }
                    } else {
                        System.out.println("Information Mismatch");
                        System.out.println("Please Enter Information Again");
                        employeeLogIn(input);
                    }
                }

                if (foundPass) {
                    break;
                }

                record = bin.readLine();
            }

            if (!foundUser) {
                System.out.println("Information Mismatch");
                System.out.println("Please Enter Information Again");
                employeeLogIn(input);
            }

            bin.close();
            bin = null;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void customerLogIn(Scanner input) {
        boolean foundUser = false;
        boolean foundPass = false;
        String record = null;
        String record2 = null;
        FileReader in = null;
        System.out.print("Please Enter User Name:");
        String e_Uname = input.next();
        System.out.print("Please Enter Password:");
        String e_Pass = input.next();

        try {
            in = new FileReader("customer.txt");
            BufferedReader bin = new BufferedReader(in);
            record = new String();
            boolean ok = true;
            while (ok) {
                for (int i = 0; i < 7; i++) {
                    record = bin.readLine();
                }

                record = null;

                if ((record = bin.readLine()) == null || (record2 = bin.readLine()) == null) {

                }

                if (e_Uname.contentEquals(record)) {
                    foundUser = true;

                    if (e_Pass.contentEquals(record2)) {
                        foundPass = true;

                        if (foundPass) {
                            System.out.println("Welcome " + record + "\n");
                            Store store= new Store();
                             store.setEmail(e_Uname);
                            store.view(1);
                            break;
                        }
                    } else {
                        System.out.println("Information Mismatch");
                        System.out.println("Please Enter Information Again");
                        customerLogIn(input);
                    }
                }

                if (foundPass) {
                    break;
                }

                record = bin.readLine();
                record = null;
                Store store= new Store();
                store.view(1);
            }

            if (!foundUser) {
                System.out.println("Information Mismatch");
                System.out.println("Please Enter Information Again");
                customerLogIn(input);
            }

            bin.close();
            bin = null;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void registerNewUser(Scanner input) throws IOException {
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        System.out.println("Please enter the following Information to register");
        System.out.print("Please enter your first name：");
        String fName = input.nextLine();
        boolean check1 = fName instanceof String;
        while(!check1) {
            System.out.println("Invalid Input Type！");
            System.out.println("First Name should be a word");
            System.out.println("Please enter your first name again");
            fName = input.nextLine();
            check1 = fName instanceof String;
        }
        System.out.print("Please enter your last name：");
        String lName = input.nextLine();
        boolean check2 = lName instanceof String;
        while(!check2) {
            System.out.println("Invalid Input Type");
            System.out.println("Last Name should be a word");
            System.out.println("Please enter your first name again");
            lName = input.nextLine();
        }
        System.out.print("Please enter your Street Address：");
        String address = input.nextLine();

        System.out.print("Please enter your City：");
        String city = input.nextLine();
        boolean checkCity = city instanceof String;
        while(!checkCity) {
            System.out.println("Invalid Input Type");
            System.out.println("City should be a word");
            System.out.println("Please enter your city again");
            city = input.nextLine();
        }
        System.out.print("Please enter your State：");
        String state = input.nextLine();
        boolean checkState = state instanceof String;
        while(!checkState) {
            System.out.println("Invalid Input Type");
            System.out.println("State should be a word");
            System.out.println("Please enter your state again");
            state = input.nextLine();
        }
        System.out.print("Please enter your ZIP code：");
        String zip = input.nextLine();

        System.out.println("Please enter your Phone Number");
        System.out.print("(Phone Number must be in the form XXX-XXXXXXX):");
        String phNumber = input.nextLine();
        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        Matcher matcher = pattern.matcher(phNumber);
        boolean checkPhNumber = matcher.matches();
        while(!checkPhNumber){
            System.out.println("Invalid Input ");
            System.out.println("Please enter Valid Phone Number:");
            System.out.println("Phone Number must be in the form XXX-XXXXXXX");
            phNumber = input.nextLine();
            matcher = pattern.matcher(phNumber);
            checkPhNumber = matcher.matches();
        }
        System.out.println("Please enter your email id:");
        System.out.print("(THIS WILL ALSO BE YOUR ID:)");
        String id = input.nextLine();
        boolean check3 = id.contains("@");
        while(!check3){
            System.out.println("Invalid Input ");
            System.out.println("Please enter Valid email with (Should contain : @ ):");
            id = input.nextLine();
            check3 = id.contains("@");
        }
        System.out.print("Please enter your password:");
        String password = input.nextLine();
        System.out.print("Please re-enter your password:");
        String password2 = input.nextLine();
        boolean check4 = password.equals(password2);
        while (!check4) {
            System.out.println("Passwords don't match!\nPlease Enter password again");
            System.out.print("Please enter your password:");
            password = input.nextLine();
            System.out.print("Please re-enter your password:");
            password2 = input.nextLine();
            check4 = password.equals(password2);
        }

        try {
            fw = new FileWriter("customer.txt", true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            pw.println(fName);
            pw.println(lName);
            pw.println(address);
            pw.println(city);
            pw.println(state);
            pw.println(zip);
            pw.println(phNumber);
            pw.println(id);
            pw.println(password);
            pw.println();


            System.out.println("Data Successfully added to database");
            pw.flush();

        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            } catch (IOException io) {// can't do anything }
            }

        }
    }
}