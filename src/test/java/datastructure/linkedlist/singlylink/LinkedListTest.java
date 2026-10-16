package datastructure.linkedlist.singlylink;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LinkedList<T> Unit Tests")
class LinkedListTest {

    @Nested
    @DisplayName("1. When LinkedList is Newly Created (Empty)")
    class whenEmpty{

        private LinkedList<String> ll;

        @BeforeEach
        void setUp() {
            ll = new LinkedList<>();
        }

        @DisplayName("Empty LinkedList must return true")
        @Test
         void GivenEmptyLinkedListWhenisEmptyThenReturnTrue() {
            assertTrue(ll.isEmpty(), "Empty LinkedList must return true");
        }

    }


}