import org.example.Library.Library;
import org.example.Library.LibraryOperations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GTests {

    @Test
    void checkOverdue(){
        Library lib = new Library();
        lib.addBook("GoneWithTheWind","MMitchel","9780708985489","novel"   );
        lib.registerUser("Poul", "1337ABC", "1234@gmail.com", LibraryOperations.UserType.fromString("guest") );
        lib.borrowBook("1337ABC","9780708985489" );
        lib.getBorrowingHistory().forEach(System.out::println);
        lib.goToNextDay();
        lib.goToNextDay();
        lib.goToNextDay();
        lib.goToNextDay();
        lib.goToNextDay();
        lib.goToNextDay();
        lib.goToNextDay();
        lib.goToNextDay();
        lib.goToNextDay();
        lib.returnBook("1337ABC","9780708985489" );
        lib.getBorrowingHistory().forEach(System.out::println);


        Assertions.assertEquals(1, lib.getOverdueBooks().size());

    }
}
