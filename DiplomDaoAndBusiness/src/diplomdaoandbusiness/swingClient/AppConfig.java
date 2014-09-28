/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package diplomdaoandbusiness.swingClient;

import java.util.concurrent.Callable;
import javax.swing.JFrame;


public class AppConfig {
    private static IServiceFactory serviceFactory = null;
    private static JFrame mainFrame = null;
    private static LoadDlg loadDlg = null;
    
    public static void init(JFrame frame, IServiceFactory iServiceFactory){
        serviceFactory = iServiceFactory;
        mainFrame = frame;
        loadDlg = new LoadDlg(mainFrame);
    }
    
    public static IServiceFactory getServiceFactory(){
        return serviceFactory;
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }
    
    public static void doJob(Runnable job){
        loadDlg.doJob(job);
    }
    
    
}
