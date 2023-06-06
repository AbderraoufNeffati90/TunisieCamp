package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.entities.CampingCenter;
import com.esprit.alphadev.TunisieCamp.entities.Reservation;
import com.esprit.alphadev.TunisieCamp.repository.CampSiteRepository;
import com.esprit.alphadev.TunisieCamp.repository.CampingCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService {
    @Autowired
    CampingCenterRepository campingCenterRepository;
    @Autowired
    CampSiteRepository campSiteRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendConfirmationEmail(Reservation reservation) {
        CampingCenter campingCenter= reservation.getCampingCenter();
        CampSite campsite = reservation.getCampsite();
        String recipientEmail = reservation.getUser().getEmail();
        String subject = "Reservation Confirmation";
        String message = "Dear " + reservation.getUser().getFirstname() + ",\n\n" +
                "Your reservation at " + campsite.getName() + " has been confirmed. Thank you for choosing our service.";

        sendEmail(campingCenter.getEmail(), recipientEmail, subject, message);
    }


    @Override
    public void sendCancellationEmail(Reservation reservation) {
        CampingCenter campingCenter= reservation.getCampingCenter();
        CampSite campsite = reservation.getCampsite();
        String recipientEmail = reservation.getUser().getEmail();
        String subject = "Reservation Cancellation";
        String message = "Dear " + reservation.getUser().getFirstname() + ",\n\n" +
                "We regret to inform you that your reservation at " + campsite.getName() + " has been cancelled.";

        sendEmail(campingCenter.getEmail(), recipientEmail, subject, message);
    }
    private void sendEmail(String fromEmail, String toEmail, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }
}
