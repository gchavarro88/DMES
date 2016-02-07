/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.utilities;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author gchavarro88
 */
class SocialAuth extends Authenticator {  
  
    
        private String FROM_ADDRESS = "notification.sipingenieria";  
        private String PASSWORD = "notification";  
    
        @Override  
        protected PasswordAuthentication getPasswordAuthentication() {  
  
            return new PasswordAuthentication(FROM_ADDRESS, PASSWORD);  
  
        }  
    }  