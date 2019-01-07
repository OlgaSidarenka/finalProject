package com.sidarenka.alien.command.impl;

import com.sidarenka.alien.command.Command;
import com.sidarenka.alien.entity.Alien;
import com.sidarenka.alien.resource.ConfigurationManager;
import com.sidarenka.alien.resource.MessageManager;
import com.sidarenka.alien.service.AlienService;
import com.sidarenka.alien.service.ServiceException;
import org.apache.logging.log4j.Level;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.sidarenka.alien.dao.AbstractDao.logger;

public class AddAlienCommand implements Command {
    private static final String ALIEN_NAME = "alienName";
    private static final String ALIEN_HOMELAND = "alienHomeland";
    private static final String ALIEN_DESCRIPTION = "alienDescription";

    private static final String FILE_TYPE = "jpg";
    private static final String UPLOAD_DIR = "uploads";

    private AlienService alienService = new AlienService();

    @Override
    public String execute(HttpServletRequest request) {


//        String applicationPath = request.getServletContext().getRealPath("");
//        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
//
//        File fileSaveDir = new File(uploadFilePath);
//        if (!fileSaveDir.exists()) {
//            fileSaveDir.mkdirs();
//        }
//
//        String filePath = "";
//        try {
//            for (Part part : request.getParts()) {
//                if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
//                    part.write(uploadFilePath + File.separator + part.getSubmittedFileName());
//                    filePath = uploadFilePath + File.separator + part.getSubmittedFileName();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }





        String alienName = request.getParameter(ALIEN_NAME);
        String alienHomeland = request.getParameter(ALIEN_HOMELAND);
        String image = request.getParameter("content");
        String alienDescription = request.getParameter(ALIEN_DESCRIPTION).replaceAll("<","");
        String page = null;
        String message;
        try {
            List<Alien> aliens = alienService.createAlien(alienName, alienHomeland, alienDescription, image);
            if (aliens.isEmpty()) {
                message = MessageManager.getProperty("message.notAddAlien");
                request.setAttribute("infoData", message);
                page = ConfigurationManager.getProperty("path.page.new-alien-form-page");
            } else {
                aliens = alienService.selectAll();
                message = MessageManager.getProperty("message.addAlien");
                request.setAttribute("infoData", message);
                request.setAttribute("aliens", aliens);
                page = ConfigurationManager.getProperty("path.page.new-alien-form-page");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
