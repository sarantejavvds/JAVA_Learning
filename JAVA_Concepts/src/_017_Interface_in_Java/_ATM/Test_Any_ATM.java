package _017_Interface_in_Java._ATM;

public class Test_Any_ATM 
{

	public static void main(String[] args)
	{
		_ICICI_bank_ATM icici = new _ICICI_bank_ATM();
		
		icici.Display_Current_Balance();
		icici.Withdraw_Money();
		icici.Deposit_Money();
		
		_HDFC_bank_ATM hdfc = new _HDFC_bank_ATM();
		
		hdfc.Display_Current_Balance();
		hdfc.Withdraw_Money();
		hdfc.Deposit_Money();

	}

}
