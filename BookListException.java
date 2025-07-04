public class BookListException
{
    static class BookInLibrary extends Exception {
        public BookInLibrary(String message) {
            super(message);
        }
    }
}
