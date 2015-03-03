package com.kylin.activity;

import org.teleal.cling.binding.annotations.UpnpAction;
import org.teleal.cling.binding.annotations.UpnpInputArgument;
import org.teleal.cling.binding.annotations.UpnpOutputArgument;
import org.teleal.cling.binding.annotations.UpnpService;
import org.teleal.cling.binding.annotations.UpnpServiceId;
import org.teleal.cling.binding.annotations.UpnpServiceType;
import org.teleal.cling.binding.annotations.UpnpStateVariable;

@UpnpService(
        serviceId = @UpnpServiceId("MessageHandler"),
        serviceType = @UpnpServiceType(value = "MessageHandler", version = 1)
)
public class MessageHandler {
    
    @UpnpStateVariable(defaultValue = "")
    private String msg = "";
    
    @UpnpStateVariable(defaultValue = "")
    private String from = "";

    @UpnpAction(out = @UpnpOutputArgument(name = "Ret"))
    public String setMsg(@UpnpInputArgument(name = "Msg") String newMsg, 
    		@UpnpInputArgument(name = "From") String from) {
    	this.msg = newMsg; 
    	this.from = from;
    	System.out.println("receive new msg:" + newMsg);
    	MainActivity.mInstance.messageReceived(newMsg,from);
    	return "Received";
    }

}
