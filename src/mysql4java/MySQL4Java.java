/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql4java;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author Telematica-2-0
 */
public final class MySQL4Java extends JFrame {

    private final SQL sql;
    private final NanosoftLayOut ns;
    private final funciones fnc;
    public int trys = 0;

    public MySQL4Java() {
        this.fnc = new funciones();
        this.ns = new NanosoftLayOut(350, 300, 4);
        this.sql = new SQL();
        initComponents();
    }

    public void initComponents() {
        JButton btn = new JButton();
        JTextField txt_username = new JTextField();
        JTextField txt_password = new JTextField();

        setTitle("Clase 1 Progamación 3");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setPreferredSize(ns.getJFrameDimension());

        txt_username.setBounds(ns.getRectangle(100, 30));
        txt_password.setBounds(ns.getRectangle(100, 30));
        btn.setBounds(ns.getRectangle(100, 40));

        btn.setText("Login");
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                btnMouseClicked(txt_username.getText(), txt_password.getText());
            }
        });

        add(txt_username);
        add(txt_password);
        add(btn);
        pack();
    }

    public void btnMouseClicked(String usr, String psswrd) {

        try {

            ArrayList<Object> arr = new ArrayList<>();
            arr.addAll(Arrays.asList(usr, fnc.MD5(psswrd)));

            HashMap<String, Object> result = sql.SELECT("SELECT `idusuario`, `Nombre`, `Password`, `Lock` FROM `Mario_Login` WHERE `Nombre`=? AND Password=? ", arr);

            if (result.containsValue(true)) {
                JOptionPane.showMessageDialog(this, "Su cuenta esta bloqueada.Contacte al administrador");
            } else if (result.containsKey("Nombre")) {
                JOptionPane.showMessageDialog(null, "Bienvenido: " + result.get("Nombre"));
                trys = 0;

            } else {
                JOptionPane.showMessageDialog(null, "Invalid user or password");
                trys++;
                if (trys >= 3) {
                    sql.executeUpdate("update Mario_Login set `Lock` =1  WHERE `Nombre`like `Mario%` AND `Password`like `1`",arr);
                    
                }

            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Missing Fields");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()
                -> {
            MySQL4Java mySQL4Java = new MySQL4Java();
            mySQL4Java.setVisible(true);
        });

    }

}
