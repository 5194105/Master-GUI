package testingonly;

public class Highest {

	 

    public static void main(String[] args) {
        
        String s = "I LOVE MY COUNTRY ";
        String[]array = s.split(" ");
        
        String small = array[0];
        String large = array[0];
        
        for(int i = 0;i<array.length;i++)
        {
        	System.out.println(array[i]);
        	
        }
        
        for(int i = 0;i<array.length;i++)
        {
            if(array[i].length() > large.length())
                large= array[i];
            else if(array[i].length() < small.length())
                small= array[i]    ;
            
        }
        
        System.out.println("Smallest word in string is = "+small);
        System.out.println("Largest word in string is = "+large);
    
    }

 

}