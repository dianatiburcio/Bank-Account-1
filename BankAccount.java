/**
 * 
 * @author diana tiburcio
 *Bank Account 
 */
public abstract class BankAccount 
{
	private static int nextAccNum;
	private String name;
	private int acctNum = 0;
	private double balance;
	
	/**
	 * Creates a bank account with a certain name and initial balance.
	 * @param n: sets parameter n to the name under the bank account 
	 * @param b: sets parameter b to the name under the bank account
	 */
	BankAccount(String n, double b)
	{
		name = n;
		nextAccNum += 1;
		acctNum = nextAccNum;
		balance = b;
	}
	
	/**
	 * Creates a bank account with a certain name. 
	 * @param n: sets parameter n to the name under the bank account
	 */
	BankAccount(String n)
	{
		this(n,0);
	}
	
	/**
	 * Deposits the desired amount in the account. 
	 * @param amt: the desired amount of money to deposit.
	 */
	public void deposit(double amt)
	{
		balance += amt;
	}
	
	/**
	 * Will withdraw the specified amount from the account.
	 * @param amt: the amount of money to be withdrawn. 
	 */
	public void withdraw(double amt)
	{
		balance -= amt;
	}
	
	/**
	 * returns the name under the account.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * 
	 * @return the balance amount. 
	 */
	public double getBalance()
	{
		return balance;
	}
	/**
	 * 
	 * @return the account number
	 */
	public int getAccNum()
	{
		return acctNum;
	}
	
	/**
	 * abstract method for use in sub classes.
	 */
	public abstract void endOfMonthUpdate();
	
	/**
	 * transfers desired amount from the current bank account into the 
	 * desired bank account.
	 * @param other: The bank account which will receive the money. 
	 * @param amt: the amount of money to be transfered.
	 */
	public void transfer(BankAccount other, double amt)
	{
		other.deposit(amt);
		this.withdraw(amt);
	}
	
	/**
	 * returns all fields in a String. 
	 */
	public String toString()
	{
		return acctNum + "/t" + name + "/t" + balance;
	}
}
