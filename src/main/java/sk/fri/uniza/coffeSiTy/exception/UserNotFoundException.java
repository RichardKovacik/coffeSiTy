package sk.fri.uniza.coffeSiTy.exception;

public class UserNotFoundException extends Throwable {
    @Override
    public String getMessage() {
        return "Uzivatel s tymto id nexistuje";
    }
}
