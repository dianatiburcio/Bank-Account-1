/**
 * 
 * @author diana tiburcio
 *Savings Account
 */
public class SavingsAccount extends BankAccount
{
	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	
	/**
	 * Creates a savings account with all the specified information. 
	 * @param n: name under account
	 * @param b: initial balance 
	 * @param r: the interest rate 
	 * @param mb: the minimum balance 
	 * @param mbf: the minimum balance fee 
	 */
	SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n,b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	/**
	 * Creates a savings account with all the specified information and initializes 
	 * the balance to 0. 
	 * @param n: name under account
	 * @param r: the interest rate 
	 * @param mb: the minimum balance 
	 * @param mbf: the minimum balance fee 
	 */
	SavingsAccount(String n, double r, double mb, double mbf)
	{
		this(n, 0, r, mb, mbf);
	}
	
	/**
	 * Deposits the desired amount in the account. 
	 * @param amt: the desired amount of money to deposit.
	 */
	public void deposit(double amt)
	{
		if (super.getBalance()+amt < 0)
			throw new IllegalArgumentException();
		if (amt < 0)
			throw new IllegalArgumentException();
		super.deposit(amt);
	}
	
	/**
	 * Will withdraw the specified amount from the account only if the balance is not 
	 * under 0 and the new transaction doesn't cause it to go under 0. If the balance goes
	 * under the minimum balance, a fee is deducted from the account. 
	 * @param amt: the amount of money to be withdrawn. 
	 */
	public void withdraw(double amt)
	{
		if (super.getBalance() < 0)
			throw new IllegalArgumentException();
		if (super.getBalance() - amt < 0)
			throw new IllegalArgumentException();
		if (amt < 0)
			throw new IllegalArgumentException();
		super.withdraw(amt);
		if (super.getBalance() < MIN_BAL)
			super.withdraw(MIN_BAL_FEE);
	}
	
	/**
	 * Transfers desired amount from the current bank account into the 
	 * desired bank account as long as the transaction doesn't cause the balance to 
	 * go under 0. Also, the transfer can only occur if both accounts are under the 
	 * same name. If the balance goesunder the minimum balance, a fee is deducted from 
	 * the account. 
	 * @param other: The bank account which will receive the money. 
	 * @param amt: the amount of money to be transfered.
	 */
	public void transfer(BankAccount other, double amt)
	{
		if (!other.getName().equals(this.getName()))
			throw new IllegalArgumentException();
		super.transfer(other, amt);
		if (super.getBalance() < MIN_BAL)
			super.withdraw(MIN_BAL_FEE);
	}
	
	/**
	 * Deposits the interest into the account. 
	 */
	public void addInterest()
	{
		super.deposit(super.getBalance()*intRate);
	}
	/**
	 * Deposits the interest into the account at the end of the month. 
	 */
	public void endOfMonthUpdate()
	{
		this.addInterest();
	}
	
}
