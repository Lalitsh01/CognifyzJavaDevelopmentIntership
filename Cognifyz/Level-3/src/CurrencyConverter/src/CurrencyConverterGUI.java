package CurrencyConverter.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CurrencyConverterGUI extends JFrame implements ActionListener {
    private static final String API_KEY = "b84ec73e3c8d5bc6bb471a88";
    private static final String API_ENDPOINT = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";
    public static String[] CURRENCIES = {
            "USD", "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG",
            "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB",
            "BRL", "BSD", "BTN", "BWP", "BYN", "BZD", "CAD", "CDF", "CHF", "CLP",
            "CNY", "COP", "CRC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD",
            "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "FOK", "GBP", "GEL", "GGP",
            "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG",
            "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD",
            "JOD", "JPY", "KES", "KGS", "KHR", "KID", "KMF", "KRW", "KWD", "KYD",
            "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LYD", "MAD", "MDL", "MGA",
            "MKD", "MMK", "MNT", "MOP", "MRU", "MUR", "MVR", "MWK", "MXN", "MYR",
            "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN",
            "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF",
            "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL", "SOS", "SRD",
            "SSP", "STN", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY",
            "TTD", "TVD", "TWD", "TZS", "UAH", "UGX", "UYU", "UZS", "VEF", "VND",
            "VUV", "WST", "XAF", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", "ZMW",
            "ZWL"
    };

    private JSpinner amountSpinner;
    private JComboBox<String> fromCurrencyBox, toCurrencyBox;
    private JLabel resultLabel, timeLabel;
    private JLabel backgroundLabel;
    private Timer timeTimer;

    public CurrencyConverterGUI() {
        setTitle("Dev.Lalit ©");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        backgroundLabel = new JLabel();
        backgroundLabel.setLayout(new GridBagLayout()); // Ensures backgroundLabel uses GridBagLayout
        setContentPane(backgroundLabel);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(amountLabel, gbc);

        amountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) amountSpinner.getEditor();
        editor.getTextField().setFont(new Font("Arial", Font.PLAIN, 16));
        editor.getTextField().setCaretPosition(0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        centerPanel.add(amountSpinner, gbc);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(fromLabel, gbc);

        fromCurrencyBox = new JComboBox<>(CURRENCIES);
        fromCurrencyBox.setFont(new Font("Arial", Font.PLAIN, 16));
        fromCurrencyBox.setSelectedItem("USD");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        centerPanel.add(fromCurrencyBox, gbc);

        JLabel toLabel = new JLabel("To:");
        toLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        centerPanel.add(toLabel, gbc);

        toCurrencyBox = new JComboBox<>(CURRENCIES);
        toCurrencyBox.setFont(new Font("Arial", Font.PLAIN, 16));
        toCurrencyBox.setSelectedItem("INR");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        centerPanel.add(toCurrencyBox, gbc);

        JButton convertButton = new JButton("Convert 💰");
        convertButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        convertButton.setBackground(new Color(144, 238, 144)); // Light green color
        convertButton.setForeground(Color.BLACK);
        convertButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        centerPanel.add(convertButton, gbc);

        resultLabel = new JLabel("Converted Amount: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        centerPanel.add(resultLabel, gbc);

        // Adding centerPanel to backgroundLabel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        backgroundLabel.add(centerPanel, gbc);

        // Adding timeLabel to the bottom-right corner
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Bold and larger font
        timeLabel.setForeground(Color.WHITE);               // White text
        timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        gbc.gridx = 0;
        gbc.gridy = 1; // Push to the bottom
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.SOUTHEAST; // Align to bottom-right
        gbc.fill = GridBagConstraints.NONE;
        backgroundLabel.add(timeLabel, gbc);

        timeTimer = new Timer(1000, e -> updateTime());
        timeTimer.start();

        setVisible(true);
    }

    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        timeLabel.setText(sdf.format(new Date()));
    }

    private void updateBackgroundImage() {
        ImageIcon icon = new ImageIcon("C:\\Users\\Lalit Sharma\\OneDrive\\Desktop\\Cognifyz\\Level-3\\src\\CurrencyConverter\\src\\currencyConvertorIMG.jpg");
        Image img = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        backgroundLabel.setIcon(new ImageIcon(img));
    }

    public double convert(double amount, String fromCurrency, String toCurrency) throws IOException {
        URL url = new URL(API_ENDPOINT);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        JsonObject conversion_rates = jsonobj.getAsJsonObject("conversion_rates");
        double rateFrom = conversion_rates.get(fromCurrency).getAsDouble();
        double rateTo = conversion_rates.get(toCurrency).getAsDouble();
        return amount * (1 / rateFrom) * rateTo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = (Integer) amountSpinner.getValue();
            String fromCurrency = (String) fromCurrencyBox.getSelectedItem();
            String toCurrency = (String) toCurrencyBox.getSelectedItem();
            double result = convert(amount, fromCurrency, toCurrency);
            resultLabel.setText(String.format("Converted Amount: %.2f %s = %.2f %s", amount, fromCurrency, result, toCurrency));
        } catch (Exception ex) {
            resultLabel.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        CurrencyConverterGUI gui = new CurrencyConverterGUI();
        gui.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                gui.updateBackgroundImage();
            }
        });
    }
}


