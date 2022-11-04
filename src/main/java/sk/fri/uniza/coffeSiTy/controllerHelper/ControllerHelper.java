package sk.fri.uniza.coffeSiTy.controllerHelper;

import sk.fri.uniza.coffeSiTy.constants.CustomConstants;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public final class ControllerHelper {
    // je user strasi ako 18 rokov a zaroven mladsi ako 100
    public static boolean isValidAge(Date birthdate) {
        if (birthdate != null) {
            LocalDate curentDate = LocalDate.now();
            LocalDate bithDateLocal = dateToLocalDate(birthdate);
            return Period.between(bithDateLocal, curentDate).getYears() >= CustomConstants.MIN_AGE
                    && Period.between(bithDateLocal, curentDate).getYears() <= CustomConstants.MAX_AGE;
        }
        return true;
    }

    private static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
