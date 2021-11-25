package com.naftulinconsulting.jgroupsms;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;


@Component
public class JGroupsReceiver extends ReceiverAdapter {
    private static Logger LOGGER = LoggerFactory.getLogger(JGroupsReceiver.class);
    JChannel channel;
    private boolean activeNode;

    @PostConstruct
    public void startListening() throws Exception {
        channel=new JChannel();
        channel.setReceiver(this);
        channel.connect("SampleJGroupsCluster");
    }

    @PreDestroy
    public void stopListening() {
        if (channel != null) {
            channel.close();
        }
    }

    @Override
    public void viewAccepted(View view) {
        super.viewAccepted(view);
        System.out.println("View accepted" + view);
        List<Address> members = channel.getView().getMembers();
        Address myAddress = channel.getAddress();
        boolean activeNodeNow = (members.get(0).equals(myAddress));
        if (activeNodeNow != activeNode && activeNodeNow) {
            LOGGER.info("Becoming an active node");
        } else if (activeNodeNow != activeNode && !activeNodeNow) {
            LOGGER.info("Becoming an inactive node");
        }
        activeNode = activeNodeNow;
    }

    public boolean isActiveNode() {
        return activeNode;
    }

}
