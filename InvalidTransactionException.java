public class InvalidTransactionException extends Exception {
    
    private final String userMessage;

    public InvalidTransactionException(String userMessage, String devMessage) {
        super(devMessage);
        this.userMessage = userMessage;
    }

    protected String getUserMessage() {
        return userMessage;
    }
}