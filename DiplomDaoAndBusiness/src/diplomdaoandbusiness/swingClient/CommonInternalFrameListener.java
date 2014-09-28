
package diplomdaoandbusiness.swingClient;

import java.util.Map;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;


public class CommonInternalFrameListener extends InternalFrameAdapter {

    private Map<String, JInternalFrame> frameMap = null;
    private String ref = null;

    public CommonInternalFrameListener(Map<String, JInternalFrame> frameMap, String ref) {
        this.frameMap = frameMap;
        this.ref = ref;
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        frameMap.remove(ref);
    }
}
