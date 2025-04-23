package _Connect_SSMS_with_ECLIPSE.MAIN_Container;

import java.util.HashMap;
import java.util.Map;

public class CRUD_Operation_Selector 
{
	private static final Map<Character, CRUD_Operation> operations = new HashMap<>();

    static 
    {
    	operations.put('i', new Calling_INSERT_Operation_Method());
        operations.put('s', new Calling_SELECT_Operation_Method());
        operations.put('u', new Calling_UPDATE_Operation_Method());
        operations.put('d', new Calling_DELETE_Operation_Method());
        operations.put('e', new Calling_EXIT_Operation_Method());
    }

    public static CRUD_Operation get_Operation(char choice)
    {
        return operations.get(choice);
    }
}
