import com.example.weather6.Weather6Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GangGangWeather {

    private JPanel swingWeather;
    private JButton button;

    public GangGangWeather() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(swingWeather,"服务器端启动成功");
            }
        });
    }

    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame("GangGangWeather");
            frame.setContentPane(new GangGangWeather().swingWeather);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setVisible(true);
            Weather6Application weather6Application =new Weather6Application();
            //weather6Application.main(args);
            weather6Application.main(args);
        } catch (HeadlessException e) {
            e.getStackTrace();
        }
    }
}
