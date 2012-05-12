package chimp.service.inspector;

import chimp.service.ServiceMessage;

/**
 * @author koosha
 * @since 1.0 (5/12/12, 4:44 PM)
 */
public class InspectorServiceMessage implements ServiceMessage{
    
    private String name;

    public InspectorServiceMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
