package currencyconverter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Changer extends JFrame
{
    JLabel rate = new JLabel("1.00000");
    
    final JLabel ONE = new JLabel("1");
    final JLabel EQUALS1 = new JLabel("=");
    final JLabel EQUALS2 = new JLabel("=");
    final JLabel POINT = new JLabel(".");
    
    JLabel amount = new JLabel("");
    
    //Rates on October 8, 2018 - could be edited to produce live values  
    final String[] CURRENCY = {"United States Dollar", "Euro", "Japanese Yen", "Russian Ruble", "Chinese Yuan", "British Pound", "Canadian Dollar", "Israeli New Shekel", "Mexican Peso", "Brazilian Real", "Indian Rupee"};
    final double[] RATE = {1.0000, 0.87143, 113.02202, 66.75693, 6.93047, 0.76575, 1.29770, 3.64981, 18.93801, 3.77166, 74.05605};
    
    final JComboBox<String> CurrencyFrom = new JComboBox<String>(CURRENCY);
    final JComboBox<String> CurrencyTo = new JComboBox<String>(CURRENCY);
    
    JTextField whole = new JTextField("");
    JTextField decimal = new JTextField("");
    
    final JButton CONVERT = new JButton("Convert");
    
    public Changer()
    {
        super("Currency Converter 2018");
        JPanel p = new JPanel();
        
        p.setLayout(null);
        getContentPane().add(p);
        
        ONE.setBounds(220, 80, 100, 100);
        ONE.setFont(new Font("Sans Serif", Font.BOLD, 30));
        p.add(ONE);
        
        CurrencyFrom.setBounds(50, 150, 200, 70);
        p.add(CurrencyFrom);
        
        EQUALS1.setBounds(340, 80, 100, 100);
        EQUALS1.setFont(new Font("Sans Serif", Font.BOLD, 30));
        p.add(EQUALS1);
        
        EQUALS2.setBounds(340, 220, 100, 100);
        EQUALS2.setFont(new Font("Sans Serif", Font.BOLD, 30));
        p.add(EQUALS2);
        
        CurrencyTo.setBounds(450, 150, 200, 70);
        p.add(CurrencyTo);
        
        rate.setBounds(455, 80, 150, 100);
        rate.setFont(new Font("Sans Serif", Font.BOLD, 30));
        p.add(rate);
        
        whole.setBounds(90, 230, 70, 30);
        p.add(whole);
        
        POINT.setBounds(170, 240, 20, 20);
        p.add(POINT);
        
        decimal.setBounds(180, 230, 70, 30);
        p.add(decimal);
        
        CONVERT.setBounds(90, 270, 160, 50);
        p.add(CONVERT);
        
        amount.setBounds(455, 230, 250, 100);
        amount.setFont(new Font("Sans Serif", Font.BOLD, 30));
        p.add(amount);
        
        HandlerClass handoraa = new HandlerClass();
        CurrencyFrom.addActionListener(handoraa);
        CurrencyTo.addActionListener(handoraa);
        CONVERT.addActionListener(handoraa);
    }
    
    private class HandlerClass implements ActionListener
    {
        public void actionPerformed(ActionEvent ebento) 
        {
            int currency1 = CurrencyFrom.getSelectedIndex();
            int currency2 = CurrencyTo.getSelectedIndex();
            
            double curRate = RATE[currency2] / RATE[currency1];
            
            if (ebento.getSource() == CurrencyFrom || ebento.getSource() == CurrencyTo)
            {

                BigDecimal bd = new BigDecimal(curRate).setScale(5, RoundingMode.HALF_EVEN);
                curRate = bd.doubleValue();

                rate.setText("" + curRate);
            }
            else if (ebento.getSource() == CONVERT)
            {
                if (whole.getText().equals(""))
                {
                    whole.setText("0");
                }
                if (decimal.getText().equals(""))
                {
                    decimal.setText("00000");
                }
                
                try 
                {
                    String wholeValueS = whole.getText();
                    int wholeValue = parseInt(wholeValueS);
                    
                    String decimalValueS = decimal.getText();
                    int decimalAmount = decimalValueS.length();
                    double decimalValue = parseDouble(decimalValueS) / Math.pow(10.0, decimalAmount);
                    
                    double amountFinal = (curRate * wholeValue) + (curRate * decimalValue);
                    
                    BigDecimal bd2 = new BigDecimal(amountFinal).setScale(5, RoundingMode.HALF_EVEN);
                    amountFinal = bd2.doubleValue();
                    
                    amount.setText("" + amountFinal);
                }
                catch (NumberFormatException eku)
                {
                    JOptionPane.showMessageDialog(null, String.format("Error: Not a number. Please check input."));
                }
                catch (Exception eku)
                {
                    whole.setText("");
                    decimal.setText("");
                }
            } 
        }     
    }
}
