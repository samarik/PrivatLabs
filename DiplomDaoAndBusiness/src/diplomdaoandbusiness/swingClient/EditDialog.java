
package diplomdaoandbusiness.swingClient;

import diplomdaoandbusiness.beans.entities.Manufacturer;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;


public class EditDialog extends JDialog{
    
    public static enum State{
        SAVED, CANCELLED
    }
    
    private State state;
    
    private Manufacturer u = null;
    private final JLabel label = new JLabel("Text");

    public EditDialog(Frame owner, Manufacturer manufacturer) {
        super(owner, "Редактирование записи такой-то "+manufacturer, true);
        this.u = u;
        Container c = getContentPane();
        Box box = Box.createVerticalBox();
        c.setLayout(new BorderLayout());
        c.add(box);
        box.add(label);
        JTextField txt = new JTextField(20);
        
        box.add(txt);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(new JButton(new AbstractAction("Сохранить") {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker<String, Object> wk = new SwingWorker<String, Object>(){

                    @Override
                    protected String doInBackground() throws Exception {
                        for(int i=0; i<10; i++ ){
                            class C1 implements Runnable {
                                
                                private int i;

                                public C1(int i) {
                                    this.i = i;
                                }
                                
                                
                                @Override
                                public void run() {
                                    label.setText(""+i);
                                }
                            }
                            Thread.sleep(1000);
                            EventQueue.invokeLater(new C1(i));
                            
                        }
                        return "test";
                    }

                    @Override
                    protected void done() {
                        state = State.SAVED;
                    }
                };
                
                wk.execute();
                
            }
        }));
        
        buttonPanel.add(new JButton(new AbstractAction("Закрыть") {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                state = State.CANCELLED;
            }
        }));
        box.add(buttonPanel);
        pack();
        
    }
    
    public State getState(){
        return state;
    }
    
    
}
