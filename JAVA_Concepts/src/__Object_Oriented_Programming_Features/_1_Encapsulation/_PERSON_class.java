package __Object_Oriented_Programming_Features._1_Encapsulation;

public class _PERSON_class 
{
	/* Wrapping data and methods into a single unit (class) 
	 * and restricting direct access to data */
	
	/* Encapsulation Achieved by using 
	 * classes, getters/setters, and access modifiers like private, public */
	
	private String _ID;
 
	private String _Name;
	private String _Email;
	private String _Phone_Number;
 
	private boolean _is_Alive;
 
public String get_ID() 
{
	return _ID;
}
public void set_ID(String _ID) 
{
	this._ID = _ID;
}

public String get_Name() 
{
	return _Name;
}
public void set_Name(String _Name) 
{
	this._Name = _Name;
}

public String get_Email() 
{
	return _Email;
}
public void set_Email(String _Email) 
{
	this._Email = _Email;
}

public String get_Phone_Number() 
{
	return _Phone_Number;
}
public void set_Phone_Number(String _Phone_Number)
{
	this._Phone_Number = _Phone_Number;
}

public boolean get_is_Alive() 
{
	return _is_Alive;
}
public void set_is_Alive(boolean _is_Alive) 
{
	this._is_Alive = _is_Alive;
}



}
