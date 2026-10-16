package datastructure.linkedlist.singlylink;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LinkedList<T> Unit Tests")
class LinkedListTest {

    @Nested
    @DisplayName("1. When String LinkedList is Newly Created (Empty) ")
    class whenEmpty {

        private LinkedList<String> ll;
        private final String key = "John"; // for testing DeleteKey and Find


        @BeforeEach
        void setUp() {
            ll = new LinkedList<>();
        }

        @DisplayName("isEmpty on Empty LinkedList must return true")
        @Test
        void GivenEmptyLinkedListWhenisEmptyThenReturnTrue() {
            assertTrue(ll.isEmpty(), "Empty LinkedList must return true");
        }

        @DisplayName("DeleteFirst(), DeleteLast(), DeleteAt(), DeleteKey() on empty LinkedList must return null")
        @Test
        void GivenEmptyLinkedListWhenDeleteThenReturnFalse() {
            assertAll("Empty LinkedList boundary checks",
                    () -> assertFalse(ll.deleteAt(1), "deleteAt must return false whenLinked List empty"),
                    () -> assertFalse(ll.deleteFirst(), "deleteAt must return false when Linked List empty"),
                    () -> assertFalse(ll.deleteLast(), "deleteLast must return false whenL inked List empty"),
                    () -> assertFalse(ll.deleteKey(key), "deleteKey must return false when Linked List empty")

            );
        }

        @DisplayName("Find() on empty LinkedList must return -1")
        @Test
        void givenEmptyLinkedListWhenFindThenReturnMinusOne() {
            assertEquals(-1, ll.find(key), "Find must return -1 when Linked List is empty");
        }


    }


}