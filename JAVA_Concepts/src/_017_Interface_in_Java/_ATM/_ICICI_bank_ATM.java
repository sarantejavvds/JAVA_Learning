package _017_Interface_in_Java._ATM;

public class _ICICI_bank_ATM implements  ATM_Machine
{

	@Override
	public void Withdraw_Money() 
	{
		System.out.println("Withdraw Money from ICICI ATM ...");
		
	}

	@Override
	public void Deposit_Money() 
	{
		System.out.println("Deposit Money into ICICI ATM ...");
		
	}

	@Override
	public void Display_Current_Balance()
	{
		System.out.println("Display Current Balance of User in ICICI ATM ...");
		
	}

}
