public class BookUpdateException {
    static class BookNotFoundException extends Exception {
        public BookNotFoundException(String message) {
            super(message);
        }
    }
}
