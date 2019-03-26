//The purpose of this file is to open the window in which the converter is located

package currencyconverter;

import javax.swing.JFrame;

public class CurrencyConverter 
{
    public static void main(String[] args) 
    {
        Changer changer = new Changer();
        changer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        changer.setSize(700, 400);
        changer.setVisible(true);
    }
    
}
