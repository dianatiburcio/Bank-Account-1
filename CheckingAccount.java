/**
 * 
 * @author diana tiburcio 
 *Checking Account 
 */
public class CheckingAccount extends BankAccount
{
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	private int numTransactions;
	
	/**
	 * creates a checking account with the specified information.
	 * @param n: the name under the account.
	 * @param b: the initial balance for the account; 
	 * @param odf: the over draft fee.
	 * @param tf: the transactions fee.
	 * @param freeTrans: the amount of free transactions. 
	 */
	CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n, b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	/**
	 * creates a checking account with the specified information and initializes the balance 
	 * to 0.
	 * @param n: the name under the account.
	 * @param odf: the over draft fee.
	 * @param tf: the transactions fee.
	 * @param freeTrans: the amount of free transactions. 
	 */
	CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		this(n,0,odf,tf,freeTrans);
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
		numTransactions ++;
		if (numTransactions > FREE_TRANS)
			super.withdraw(TRANSACTION_FEE);
	}
	
	/**
	 * Will withdraw the specified amount from the account.
	 * @param amt: the amount of money to be withdrawn. 
	 */
	public void withdraw(double amt)
	{
		if (super.getBalance() < 0)
			throw new IllegalArgumentException();
		if (amt < 0)
			throw new IllegalArgumentException();
		super.withdraw(amt);
		numTransactions ++;
		if (numTransactions > FREE_TRANS)
			super.withdraw(TRANSACTION_FEE);
		if (super.getBalance() < 0)
			super.withdraw(OVER_DRAFT_FEE);
	}
	
	/**
	 * transfers desired amount from the current bank account into the 
	 * desired bank account.
	 * @param other: The bank account which will receive the money. 
	 * @param amt: the amount of money to be transfered.
	 */
	public void transfer(BankAccount other, double amt)
	{
		if (!other.getName().equals(this.getName()))
			throw new IllegalArgumentException();
		if (super.getBalance() - amt < 0)
			throw new IllegalArgumentException();
		super.transfer(other, amt);
	}
	
	/**
	 * Resets number of transactions to 0.
	 */
	public void endOfMonthUpdate()
	{
		numTransactions = 0;
	}
	
	
}
