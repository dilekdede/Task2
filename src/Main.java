import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main {
    private static final String sadEmoji = "\uD83D\uDE12 \uD83D\uDE0F";
    private static final String smileEmoji = "\uD83D\uDE00";
    private static int timeLeft=60;
    private static int count=5;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Sayı Tahmin Oyunu");
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
        frame.setLocation(500, 70);

        JPanel rulesScreen = new JPanel();
        rulesScreen.setBackground(Color.cyan);
        rulesScreen.setSize(400, 400);
        rulesScreen.setBounds(50, 50, 400, 150);

        JPanel timeScreen = new JPanel();
        timeScreen.setBackground(Color.pink);
        timeScreen.setSize(400, 50);
        timeScreen.setBounds(50, 220, 400, 50);

        JPanel controlScreen = new JPanel();
        controlScreen.setBackground(Color.magenta);
        controlScreen.setSize(400, 400);
        controlScreen.setBounds(50, 290, 400, 150);

        JLabel labelUserMessage = new JLabel();
        JLabel labelGameMessage = new JLabel();
        JLabel labelRules = new JLabel("<html>" +
                "<h2>OYUN KURALLARI</h2>" +
                "<ul>" +
                "<li>Sayı tahmininizi yapın.</li>" +
                "<li>Süreniz 60 saniyedir.</li>" +
                "<li>5 Deneme hakkınız vardır.</li>" +
                "</ul>" +
                "</html>");
        JLabel timerLabel = new JLabel("60", SwingConstants.CENTER);

        frame.add(rulesScreen);
        frame.add(timeScreen);
        rulesScreen.add(labelRules);
        frame.add(controlScreen);


        JLabel inputLabel = new JLabel("Tahmin ettiğiniz sayıyı giriniz (0-100 arasında): ");
        JTextField inputText = new JTextField(15);
        inputText.setSize(200, 500);
        JButton button = new JButton("Kontrol et");
        button.setSize(200, 10);

        Random rand = new Random();
        int number = rand.nextInt(101);
        System.out.println(number);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                    timerLabel.setText(String.valueOf(timeLeft));
                    
                } else {
                    ((Timer) e.getSource()).stop();
                    labelUserMessage.setText("Süre Bitti ,Kaybettiniz " + sadEmoji);
                    labelGameMessage.setText("");
                    button.setEnabled(false);
                    inputText.setEnabled(false);
                    labelGameMessage.setText("");
                }
            }
        });
        timer.start();

        button.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                int number2 = Integer.parseInt(inputText.getText().isEmpty() ? "0" : inputText.getText());
                if (number2 < 0 || number2 > 100) {
                    labelUserMessage.setText("0 ile 100 arasında bir sayı giriniz.");
                } else if (number2 == number) {
                    labelUserMessage.setText("Doğru Tahminde bulundunuz. Tebrikler! " + smileEmoji);
                    button.setEnabled(false);
                    labelGameMessage.setText("");
                    timer.stop(); // Oyunu kazanınca zamanlayıcıyı durdur
                } else {
                    count--;
                    int difference = Math.abs(number2 - number);
                    if (difference <= 10) {
                        labelGameMessage.setText("Tahmininiz çok yakın!");
                    } else if (difference <= 20) {
                        labelGameMessage.setText("Tahmininiz yakın!");
                    } else {
                        labelGameMessage.setText("Tahmininiz çok uzak!");
                    }

                    labelUserMessage.setText("YANLIŞ TAHMİN, TEKRAR DENEYİNİZ (Kalan Deneme Hakkı: " + count + " )");
                }

                if (count == 0) {
                    button.setEnabled(false);
                    inputText.disable();
                    labelGameMessage.setText("DENEME HAKKINIZ BİTMİŞTİR :( Doğru sayı:"+number);
                    labelUserMessage.setText("");
                    timer.stop();
                }
            }
        });

        controlScreen.add(inputLabel);
        controlScreen.add(inputText);
        controlScreen.add(button);
        controlScreen.add(labelUserMessage);
        controlScreen.add(labelGameMessage);
        timeScreen.add(timerLabel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
