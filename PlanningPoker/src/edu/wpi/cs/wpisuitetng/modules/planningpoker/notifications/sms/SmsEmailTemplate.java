// $codepro.audit.disable lineLength
/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.sms;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.email.EmailMessage;

/**
 * Provides a way to convert SMS messages into email messages.
 *
 * @author Team Romulus
 * @version Apr 24, 2014
 */
public class SmsEmailTemplate implements INotificationTemplate<EmailMessage> {
    private final INotificationTemplate<SmsMessage> sms;
    private String[][] carriers = 
    {
        { "3 River Wireless", "sms.3rivers.net" },
        { "ACS Wireless", "paging.acswireless.com" },
        { "Alltel", "message.alltel.com" },
        { "AT&T", "txt.att.net" },
        { "Bell Canada", "txt.bellmobility.ca" },
        { "Bell Canada", "bellmobility.ca" },
        { "Bell Mobility (Canada)", "txt.bell.ca" },
        { "Bell Mobility", "txt.bellmobility.ca" },
        { "Blue Sky Frog", "blueskyfrog.com" },
        { "Bluegrass Cellular", "sms.bluecell.com" },
        { "Boost Mobile", "myboostmobile.com" },
        { "BPL Mobile", "bplmobile.com" },
        { "Carolina West Wireless", "cwwsms.com" },
        { "Cellular One", "mobile.celloneusa.com" },
        { "Cellular South", "csouth1.com" },
        { "Centennial Wireless", "cwemail.com" },
        { "CenturyTel", "messaging.centurytel.net" },
        { "Cingular (Now AT&T)", "txt.att.net" },
        { "Clearnet", "msg.clearnet.com" },
        { "Comcast", "comcastpcs.textmsg.com" },
        { "Corr Wireless Communications", "corrwireless.net" },
        { "Dobson", "mobile.dobson.net" },
        { "Edge Wireless", "sms.edgewireless.com" },
        { "Fido", "fido.ca" },
        { "Golden Telecom", "sms.goldentele.com" },
        { "Helio", "messaging.sprintpcs.com" },
        { "Houston Cellular", "text.houstoncellular.net" },
        { "Idea Cellular", "ideacellular.net" },
        { "Illinois Valley Cellular", "ivctext.com" },
        { "Inland Cellular Telephone", "inlandlink.com" },
        { "MCI", "pagemci.com" },
        { "Metrocall", "page.metrocall.com" },
        { "Metrocall 2-way", "my2way.com" },
        { "Metro PCS", "mymetropcs.com" },
        { "Microcell", "fido.ca" },
        { "Midwest Wireless", "clearlydigital.com" },
        { "Mobilcomm", "mobilecomm.net" },
        { "MTS", "text.mtsmobility.com" },
        { "Nextel", "messaging.nextel.com" },
        { "OnlineBeep", "onlinebeep.net" },
        { "PCS One", "pcsone.net" },
        { "President's Choice", "txt.bell.ca" },
        { "Public Service Cellular", "sms.pscel.com" },
        { "Qwest", "qwestmp.com" },
        { "Rogers AT&T Wireless", "pcs.rogers.com" },
        { "Rogers Canada", "pcs.rogers.com" },
        { "Satellink", "10digitpagernumber.satellink.net" },
        { "Southwestern Bell", "email.swbw.com" },
        { "Sprint", "messaging.sprintpcs.com" },
        { "Sumcom", "tms.suncom.com" },
        { "Surewest Communicaitons", "mobile.surewest.com" },
        { "T-Mobile", "tmomail.net" },
        { "Telus", "msg.telus.com" },
        { "Tracfone", "txt.att.net" },
        { "Triton", "tms.suncom.com" },
        { "Unicel", "utext.com" },
        { "US Cellular", "email.uscc.net" },
        { "Solo Mobile", "txt.bell.ca" },
        { "Sprint", "messaging.sprintpcs.com" },
        { "Sumcom", "tms.suncom.com" },
        { "Surewest Communicaitons", "mobile.surewest.com" },
        { "T-Mobile", "tmomail.net" },
        { "Telus", "msg.telus.com" },
        { "Triton", "tms.suncom.com" },
        { "Unicel", "utext.com" },
        { "US Cellular", "email.uscc.net" },
        { "US West", "uswestdatamail.com" },
        { "Verizon", "vtext.com" },
        { "Virgin Mobile", "vmobl.com" },
        { "Virgin Mobile Canada", "vmobile.ca" },
        { "West Central Wireless", "sms.wcc.net" },
        { "Western Wireless", "cellularonewest.com" },
    };
    
    /**
     * Creates an email template for the given SMS template.
     *
     * @param sms SMS template
     */
    public SmsEmailTemplate(INotificationTemplate<SmsMessage> sms) {
        this.sms = sms;
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate#shouldReceiveNotifications(edu.wpi.cs.wpisuitetng.modules.core.models.User)
     */
    @Override
    public boolean shouldReceiveNotifications(User user) {
        return sms.shouldReceiveNotifications(user);
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate#generateMessage(edu.wpi.cs.wpisuitetng.modules.core.models.User)
     */
    @Override
    public EmailMessage generateMessage(User user) {
        final SmsMessage message = sms.generateMessage(user);
        
        // Find the associated email domain for this carrier
        String domain = null;
        for (String[] carrier : carriers) {
            if (carrier[0].equals(message.getCarrier())) {
                domain = carrier[1];
                break;
            }
        }
        
        if (domain == null) {
            throw new RuntimeException("Carrier not found");
        }
        
        final String to = message.getNumber() + "@" + domain;
        
        return new EmailMessage("planningpokerwpi@gmail.com", to, "", message.getBody());
    }

}
