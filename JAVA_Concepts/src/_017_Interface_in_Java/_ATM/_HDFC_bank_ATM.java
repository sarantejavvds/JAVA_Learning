package _017_Interface_in_Java._ATM;

public class _HDFC_bank_ATM implements ATM_Machine 
{

	@Override
	public void Withdraw_Money() 
	{
		System.out.println("Withdraw Money from HDFC ATM ...");
		
	}

	@Override
	public void Deposit_Money()
	{
		System.out.println("Deposit Money into HDFC ATM ...");
		
	}

	@Override
	public void Display_Current_Balance()
	{
		System.out.println("Display Current Balance of User in HDFC ATM ...");
		
	}

}
