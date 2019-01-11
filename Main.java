import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author diana tiburcio
 *Main
 */
public class Main 
{
	public static void main(String[] args)
	{
		ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		final double OVER_DRAFT_FEE = 15;
		final double RATE = .0025;
		final double TRANSACTION_FEE = 1.5;
		final double MIN_BAL = 300;
		final double MIN_BAL_FEE = 10; 
		final int FREE_TRANSACTIONS = 10;
		String action = "";
		double balance = 0;
		double amount = 0;
		int accNum;
		String response;
		Scanner n = new Scanner(System.in);
		
		boolean cont = true;
		while (cont)
		{
			System.out.println("Would you like to add an account [A], make a transaction [M], or terminate the program [T]?");
			action = n.nextLine();
			while (!action.equals("A") && !action.equals("M") && !action.equals("T")) 
			{
				System.out.println("Please type a valid response. You can type: \n A \n M \n T");
				action = n.nextLine();
			}
			if (action.equals("T"))
				cont = false;
			
			else if (action.equals("A"))
			{
				System.out.println("Would you like to add a savings account [S] or a checking account [C]?");
				action = n.nextLine();
				while (!action.equals("S") && !action.equals("C")) 
				{
					System.out.println("Please type a valid response. You can type: \n S \n C");
					action = n.nextLine();
				}
				if (action.equals("S"))
				{
					System.out.println("What name will the account be under?");
					String name = n.nextLine();
					System.out.println("Would you like to set an initial balance?");
					String balanceAns = n.nextLine();
					while (!balanceAns.equals("yes") && !balanceAns.equals("no")) 
					{
						System.out.println("Please type a valid response. You can type yes or no");
						balanceAns = n.nextLine();
					}
					switch (balanceAns)
					{
						case "no":	
						{
							bankAccounts.add(new SavingsAccount(name, RATE, MIN_BAL, MIN_BAL_FEE));
							break;
						}
						case "yes":
						{
							System.out.println("What will be the intial balance? Please type the number without a dollar sign.");
							String input = n.nextLine();
							while(!isNumeric(input)) 
							{
								System.out.println("That is an invalid value, please type a numeric value without any characters");
								input = n.nextLine();
							}
							balance = Double.parseDouble(input);
							bankAccounts.add(new SavingsAccount(name, balance, RATE, MIN_BAL, MIN_BAL_FEE));
							break;
						}
					}
				}
				else if (action.equals("C"))
				{
					System.out.println("What name will the account be under?");
					String name = n.nextLine();
					name = name.trim();
					System.out.println("Would you like to set an initial balance?");
					String balanceAns = n.nextLine();
					while (!balanceAns.equals("yes") && !balanceAns.equals("no")) 
					{
						System.out.println("Please type a valid response. You can type yes or no");
						balanceAns = n.nextLine();
					}
					if (balanceAns.equals("no"))
					{
						bankAccounts.add(new CheckingAccount(name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
					}
					else if (balanceAns.equals("yes"))
					{
						System.out.println("What will be the intial balance? Please type the number without a dollar sign.");
						String input = n.nextLine();
						while(!isNumeric(input)) 
						{
							System.out.println("That is an invalid value, please type a numeric value without any characters");
							input = n.nextLine();
						}
						balance = Double.parseDouble(input);
						bankAccounts.add(new SavingsAccount(name, balance, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
					}
				}
			}
			else if (action.equals("M"))
			{
				System.out.println("What is your account number?");
				String input = n.nextLine();
				while(!isInteger(input)) 
				{
					System.out.println("That is an invalid value, please type a numeric value without any characters");
					input = n.nextLine();
				}
				accNum = Integer.parseInt(input);
				BankAccount yourBA = null;
				String name;
				String accName = null;
				ArrayList<BankAccount> bankAccountsName = new ArrayList<BankAccount>();
				for (BankAccount ba: bankAccounts)
				{
					if (ba.getAccNum() == accNum)
						yourBA = ba;
				}
				while (yourBA == null)
				{
					System.out.println("That is an invalid account number. Would you like to re-enter the account number (type R) or get all your account numbers (type G)?");
					response = n.nextLine();
					while (!response.equals("R") && !response.equals("G")) 
					{
						System.out.println("Please type a valid response. You can type: \n R \n G");
						response = n.nextLine();
					}
					if (response.equals("R"))
					{
						System.out.println("What is your account number?");
						input = n.nextLine();
						while(!isInteger(input)) 
						{
							System.out.println("That is an invalid value, please type a numeric value without any characters");
							input = n.nextLine();
						}
						accNum = Integer.parseInt(input);
						for (BankAccount ba: bankAccounts)
						{
							if (ba.getAccNum() == accNum)
								yourBA = ba;
						}
					}
					else
					{
						bankAccountsName = new ArrayList<BankAccount>();
						System.out.println("Please type your full name");
						name = n.nextLine();
						name = name.trim();
						for (BankAccount ba: bankAccounts)
						{
							accName = ba.getName();
							accName = accName.trim();
							if (accName.equalsIgnoreCase(name))
								bankAccountsName.add(ba);
						}
						if (bankAccountsName.size() == 0)
						{
							System.out.println("That name is not in our system");
						}
						else
						{
							System.out.println("Your bank account numbers are: ");
							for (BankAccount ban: bankAccountsName)
							{
								System.out.print(ban.toString());
								if (ban instanceof CheckingAccount)
									System.out.println(": checking account");
								else if (ban instanceof SavingsAccount)
									System.out.println(": savings account");
							}
						}
						System.out.println("What is your account number?");
						input = n.nextLine();
						while(!isInteger(input)) 
						{
							System.out.println("That is an invalid value, please type a numeric value without any characters");
							input = n.nextLine();
						}
						accNum = Integer.parseInt(input);
						for (BankAccount ba: bankAccounts)
						{
							if (ba.getAccNum() == accNum)
								yourBA = ba;
						}
					}	
				}
				System.out.println("Would you like to withdraw [W], deposit [D], transfer [T], or get account numbers [G]?");
				action = n.nextLine();
				while (!action.equals("W") && !action.equals("D") && !action.equals("T") && !action.equals("G")) 
				{
					System.out.println("Please type a valid response. You can type: \n W \n D \n T \n G");
					action = n.nextLine();
				}
				if (action.equals("W"))
				{
					System.out.println("How much money would you like to withdraw?");
					input = n.nextLine();
					while(!isNumeric(input)) 
					{
						System.out.println("That is an invalid value, please type a numeric value without any characters");
						input = n.nextLine();
					}
					amount = Double.parseDouble(input);
					try
					{
						yourBA.withdraw(amount);
					}
					catch(IllegalArgumentException e)
					{
						System.out.println("transaction not authorized");
					}
				}
				else if (action.equals("D"))
				{
					System.out.println("How much money would you like to deposit?");
					input = n.nextLine();
					while(!isNumeric(input)) 
					{
						System.out.println("That is an invalid value, please type a numeric value without any characters");
						input = n.nextLine();
					}
					amount = Double.parseDouble(input);
					try
					{
						yourBA.deposit(amount);
					}
					catch(IllegalArgumentException e)
					{
						System.out.println("transaction not authorized");
					}
				}
				else if (action.equals("T"))
				{
					BankAccount otherBA = null;
					System.out.println("How much money would you like to transfer?");
					input = n.nextLine();
					while(!isNumeric(input)) 
					{
						System.out.println("That is an invalid value, please type a numeric value without any characters");
						input = n.nextLine();
					}
					amount = Double.parseDouble(input);
					System.out.println("To what account would like to transfer?");
					input = n.nextLine();
					while(!isInteger(input)) 
					{
						System.out.println("That is an invalid value, please type a numeric value without any characters");
						input = n.nextLine();
					}
					int accountNum = Integer.parseInt(input);
					for (BankAccount ba: bankAccounts)
					{
						if (ba.getAccNum() == accountNum)
							otherBA = ba;
					}
					while (otherBA == null)
					{
						System.out.println("That is an invalid account number. Would you like to re-enter the account number (type R) or get all your account numbers (type G)?");
						response = n.nextLine();
						while (!response.equals("R") && !response.equals("G")) 
						{
							System.out.println("Please type a valid response. You can type: /n R /n G");
							response = n.nextLine();
						}
						if (response.equals("R"))
						{
							System.out.println("What is the account number?");
							input = n.nextLine();
							while(!isInteger(input)) 
							{
								System.out.println("That is an invalid value, please type a numeric value without any characters");
								input = n.nextLine();
							}
							accountNum = Integer.parseInt(input);
							for (BankAccount ba: bankAccounts)
							{
								if (ba.getAccNum() == accountNum)
									otherBA = ba;
							}
						}
						else
						{
							bankAccountsName = new ArrayList<BankAccount>();
							System.out.println("Please type your full name");
							name = n.nextLine();
							name = name.trim();
							for (BankAccount ba: bankAccounts)
							{
								accName = ba.getName();
								accName = accName.trim();
								if (accName.equalsIgnoreCase(name))
									bankAccountsName.add(ba);
							}
							if (bankAccountsName.size() == 0)
							{
								System.out.println("That name is not in our system");
							}
							else
							{
								System.out.println("Your bank account numbers are: ");
								for (BankAccount ban: bankAccountsName)
								{
									System.out.print(ban.toString());
									if (ban instanceof CheckingAccount)
										System.out.println(": checking account");
									else if (ban instanceof SavingsAccount)
										System.out.println(": savings account");
								}
							}
							System.out.println("What is the account number?");
							input = n.nextLine();
							while(!isInteger(input)) 
							{
								System.out.println("That is an invalid value, please type a numeric value without any characters");
								input = n.nextLine();
							}
							accountNum = Integer.parseInt(input);
							for (BankAccount ba: bankAccounts)
							{
								if (ba.getAccNum() == accountNum)
									otherBA = ba;
							}
						}	
					}
					try
					{
						yourBA.transfer(otherBA, amount);
					}
					catch(IllegalArgumentException e)
					{
						System.out.println("transaction not authorized");
					}
				}
				else if (action.equals("G"))
				{
					bankAccountsName = new ArrayList<BankAccount>();
					System.out.println("Please type your full name");
					name = n.nextLine();
					name = name.trim();
					for (BankAccount ba: bankAccounts)
					{
						accName = ba.getName();
						accName = accName.trim();
						if (accName.equalsIgnoreCase(name))
							bankAccountsName.add(ba);
					}
					if (bankAccountsName.size() == 0)
					{
						System.out.println("That name is not in our system");
					}
					else
					{
						System.out.println("Your bank account numbers are: ");
						for (BankAccount ban: bankAccountsName)
						{
							System.out.print(ban.toString());
							if (ban instanceof CheckingAccount)
								System.out.println(": checking account");
							else if (ban instanceof SavingsAccount)
								System.out.println(": savings account");
						}
					}
				}
			}
		}
		
	}
	
	public static boolean isNumeric(String num) 
	{
		try 
		{
			Double.parseDouble(num);
			return true;
		} 
		
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean isInteger(String num) 
	{
		try 
		{
			Integer.parseInt(num);
			return true;
		} 
		catch(Exception e)
		{
			return false;
		}
	}
}
