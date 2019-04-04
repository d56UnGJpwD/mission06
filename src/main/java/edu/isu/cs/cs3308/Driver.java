package edu.isu.cs.cs3308;

import java.util.Scanner;

public class Driver
{
    public static void main(String[] args)
    {
        String file = "data/en-US.dic";

        SpellCheck checker = new SpellCheck(file);

        System.out.println("Type something to spell check or type quit to quit:");
        Scanner s = new Scanner(System.in);

        while(s.hasNextLine())
        {
            String input = s.nextLine();
            if(input.toLowerCase().equals("quit"))
            {
                break;
            }

            String[] words = input.split(" ");
            for(String word: words)
            {
                checker.check(word);
            }
        }
        s.close();
        System.out.println("Ending\n");
    }

}
