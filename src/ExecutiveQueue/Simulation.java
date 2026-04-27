package ExecutiveQueue;

import java.util.Scanner;

public class Simulation
{
    public static void main(String[] args)
    {
        InputControl input = new InputControl();
        Scanner textInput = new Scanner(System.in);
        String currentInput;
        boolean programActive;

        do
        {
            programActive = input.take();
        } while (programActive);

    }
}
