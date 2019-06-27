package Annotation;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import Annotation.Check;

import java.lang.reflect.Field;

public class PrintOut {
	static String CHANGED_STRING = "You have changed PI string value";
	static double CHANGED_PI = 3.1416;
	static String STATIC_STRING = "static";
	static String MODIFIER_OF_FIELD = "modifiers";
	
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException{
		for(Method method : Demo.class.getMethods()) {
            Check checkAnnotation = (Check)method.getAnnotation(Check.class);
            if(checkAnnotation != null) {
                System.out.println("Methods use @Check: " +method.getName());
                System.out.println();
            }
        }
		System.out.println("-------------------------------------------");

		for(Field field : Demo.class.getDeclaredFields()) {
			Check checkAnnotation = (Check)field.getAnnotation(Check.class);

			if(checkAnnotation != null) {
				System.out.println("Fields use @Check: "+field.getName()+" || Modifiers: "+Modifier.toString(field.getModifiers()));
				System.out.println();
				
				if(Modifier.toString(field.getModifiers()).contains(STATIC_STRING)) {
					
					field.setAccessible(true);					
					Field modifiersField = Field.class.getDeclaredField(MODIFIER_OF_FIELD);
		            modifiersField.setAccessible( true );
		            modifiersField.setInt( field, field.getModifiers() & ~Modifier.FINAL );
		            
		            switch(field.getName()) {
		            	case "PI":
						System.out.println("The intialized value: "+Demo.getPi());
						field.set(null , CHANGED_PI);	
			            System.out.println( "The changed value: "+Demo.getPi() );	
			            break;
			            
		            	case "CONST_NAME":
		            	System.out.println("The intialized value: "+Demo.getConst());
		            	field.set(null, CHANGED_STRING);
		            	System.out.println("The changed value: "+Demo.getConstName() );
		            	break;
		        
		            	default:
		            	break;
		            }
					System.out.println("-------------------------------------------");
				}
			}
		}
	}
}
