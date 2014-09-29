/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diplomdaoandbusiness.swingClient;

import java.awt.EventQueue;


/**
 *
 * @author Olga
 */
public class MainSwingClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      AppConfig.init(new MainFrame());
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                AppConfig.getMainFrame().setVisible(true);
            }
        });
    }
}

