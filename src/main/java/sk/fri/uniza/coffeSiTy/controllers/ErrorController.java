package sk.fri.uniza.coffeSiTy.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = Integer.parseInt(status.toString());

        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            logger.error("Pouzivatel sa pokusal najst stranku ktora nexistuje");
            return "notFound";
        }

        if (statusCode == HttpStatus.FORBIDDEN.value()) {
            logger.error("Pouzivatel sa pokusal pristupi k stranke ku ktorej nema pristup");
            return "accessDenied";
        }
        return "notFound";
    }
}
