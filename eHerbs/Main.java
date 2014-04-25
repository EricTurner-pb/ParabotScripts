package eHerbs;

/**
 * Created by EricTurner on 4/24/2014.
 */
import eHerbs.strategies.*;
import eHerbs.data.*;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.Skill;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


@ScriptManifest(author = "EricTurner", category = Category.HERBLORE, description = "Buys and cleans herbs", name = "eHerbs", servers = { "Torva" }, version = 1)
public class Main extends Script {

    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    Gui g = new Gui();
    public boolean guiWait = true;

    @Override
    public boolean onExecute() {
        strategies.add(new buy());
        strategies.add(new clean());
        strategies.add(new drop());
        strategies.add(new bank());
        strategies.add(new walkBank());
        strategies.add(new walkShop());
        provide(strategies);
        g.setVisible(true);
        while (guiWait == true) {
            sleep(500);
        }
        return true;
    }

    @Override
    public void onFinish() {
    }

    public class Gui extends JFrame {

        /**
         *
         */
        private JPanel contentPane;

        /**
         * Launch the application.
         */
        public void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Gui frame = new Gui();
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @SuppressWarnings("unchecked")
        public Gui() {
            initComponents();
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setBounds(300, 200, 400, 300);
            contentPane = new JPanel();
            contentPane.setBorder(new LineBorder(Color.GRAY, 4, false));
            setContentPane(contentPane);
            contentPane.setLayout(null);

            eHerbs = new JLabel("Choose Herb.");
            eHerbs.setFont(new Font("Arial", Font.ITALIC, 13));
            eHerbs.setBounds(150, 100, 200, 20);
            contentPane.add(eHerbs);

            eHerbs1 = new JLabel("Choose to Bank or Drop.");
            eHerbs1.setFont(new Font("Arial", Font.ITALIC, 13));
            eHerbs1.setBounds(133, 20, 200, 20);
            contentPane.add(eHerbs1);

            HerbLvl = new JLabel("To clean this herb you need a level of :");
            HerbLvl.setFont(new Font("Arial", Font.ITALIC, 13));
            HerbLvl.setBounds(20, 220, 280, 20);
            contentPane.add(HerbLvl);

            HerbLvl1 = new JLabel("");
            HerbLvl1.setFont(new Font("Arial", Font.ITALIC, 13));
            HerbLvl1.setBounds(249, 220, 200, 20);
            contentPane.add(HerbLvl1);

            HerbName = new JLabel("The herb you have chose to clean is :");
            HerbName.setFont(new Font("Arial", Font.ITALIC, 13));
            HerbName.setBounds(20, 205, 280, 20);
            contentPane.add(HerbName);

            HerbName1 = new JLabel("");
            HerbName1.setFont(new Font("Arial", Font.ITALIC, 13));
            HerbName1.setBounds(240, 205, 200, 20);
            contentPane.add(HerbName1);

            DOBC = new JLabel("You have chose to :");
            DOBC.setFont(new Font("Arial", Font.ITALIC, 13));
            DOBC.setBounds(20, 235, 200, 20);
            contentPane.add(DOBC);

            DOBC1 = new JLabel("");
            DOBC1.setFont(new Font("Arial", Font.ITALIC, 13));
            DOBC1.setBounds(140, 235, 200, 20);
            contentPane.add(DOBC1);

            HerbList = new JComboBox();
            HerbList.setModel(new DefaultComboBoxModel<Object>(new String[]{"GUAM", "MARRENTILL", "TARROMIN", "HARRALANDER", "WERGALI"}));
            HerbList.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (HerbList.getSelectedItem().equals("GUAM")) {
                        HerbLvl1.setText("3");
                        HerbName1.setText("GUAM");

                    }
                    if (HerbList.getSelectedItem().equals("MARRENTILL")) {
                        HerbLvl1.setText("5");
                        HerbName1.setText("MARRENTILL");

                    }
                    if (HerbList.getSelectedItem().equals("TARROMIN")) {
                        HerbLvl1.setText("11");
                        HerbName1.setText("TARROMIN");

                    }
                    if (HerbList.getSelectedItem().equals("HARRALANDER")) {
                        HerbLvl1.setText("20");
                        HerbName1.setText("HARRALANDER");

                    }
                    if (HerbList.getSelectedItem().equals("WERGALI")) {
                        HerbLvl1.setText("41");
                        HerbName1.setText("WERGALI");

                    }
                }
            });
            HerbList.setBounds(/*LEFT N RIGHT*/150, /*UP N DOWN*/120, 100, 40);
            contentPane.add(HerbList);
            DropOrBank = new JComboBox();
            DropOrBank.setModel(new DefaultComboBoxModel<Object>(new String[] { "DROP HERBS" , "BANK HERBS" }));
            DropOrBank.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (DropOrBank.getSelectedItem().equals("DROP HERBS")) {
                        DOBC1.setText("DROP HERBS");

                    }
                    if (DropOrBank.getSelectedItem().equals("BANK HERBS")) {
                        DOBC1.setText("BANK HERBS");
                    }
                }
            });
            DropOrBank.setBounds(150, 40, 100, 40);
            contentPane.add(DropOrBank);

            btnStart = new JButton("Start Script");
            btnStart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (btnStart.isEnabled()) {
                        btnStart.setText("Change Settings");
                    }
                    if (HerbList.getSelectedItem().equals("GUAM")) {
                        data.HERB = 0;
                        data.HERBID = 249;
                    }
                    if (HerbList.getSelectedItem().equals("MARRENTILL")) {
                        data.HERB = 1;
                        data.HERBID = 201;
                    }
                    if (HerbList.getSelectedItem().equals("TARROMIN")) {
                        data.HERB = 2;
                        data.HERBID = 203;
                    }
                    if (HerbList.getSelectedItem().equals("HARRALANDER")) {
                        data.HERB = 3;
                        data.HERBID = 205;
                    }
                    if (HerbList.getSelectedItem().equals("WERGALI")) {
                        data.HERB = 4;
                        data.HERBID = 14836;
                    }
                    if (DropOrBank.getSelectedItem().equals("DROP HERBS")) {
                        data.DOB = 0;
                        
                    }
                    if (DropOrBank.getSelectedItem().equals("BANK HERBS")) {
                        data.DOB = 1;
                    }

                    guiWait = false;
                   // g.dispose();
                }
            });
            btnStart.setBounds(270, 239, 115, 23);
            contentPane.add(btnStart);
        }

        private void initComponents() {
            eHerbs = new JLabel();
            eHerbs1 = new JLabel();
            DOBC = new JLabel();
            DOBC1 = new JLabel();
            HerbName = new JLabel();
            HerbName1 = new JLabel();
            HerbLvl = new JLabel();
            HerbLvl1 = new JLabel();
            HerbList = new JComboBox<Object>();
            DropOrBank = new JComboBox<Object>();
        }

        private JLabel eHerbs;
        private JLabel eHerbs1;
        private JLabel DOBC;
        private JLabel DOBC1;
        private JLabel HerbName;
        private JLabel HerbName1;
        private JLabel HerbLvl;
        private JLabel HerbLvl1;
        private JButton btnStart;
        private JComboBox HerbList;
        private JComboBox DropOrBank;
    }

}
