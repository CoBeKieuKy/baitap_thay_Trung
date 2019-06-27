package Annotation;

import java.lang.reflect.Field;

public class Demo {
	@Check
	private static final Double PI = 3.14;
	
	@Check
	public static final String CONST_NAME = "This is constant string value of PI";
	
	@Check
	private String name;
	
	@Check
	public Integer hohohaha;
	
	@Check
	private Integer id;
	
	@Check
	public String getName() {
		return this.name;
	}

	@Check
	public int getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Check
	public void setId(int id) {
		this.id = id;
	}
	
	public static double getPi() {
        return PI;
    }
	
	public static String getConst() {
		return CONST_NAME;
	}
	
	public static String getConstName() {
	try {
	     final Field fld = Demo.class.getDeclaredField( "CONST_NAME" );
	     return (String) fld.get( null );
	    } catch (NoSuchFieldException e) {
	      return null;
	    } catch (IllegalAccessException e) {
	      return null;
	    }
	}
}
