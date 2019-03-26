package currencyconverter;

import javax.swing.JFrame;

public class CurrencyConverter 
{
    public static void main(String[] args) 
    {
        Changer kaireiyi = new Changer();
        kaireiyi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        kaireiyi.setSize(700, 400);
        kaireiyi.setVisible(true);
    }
    
}
