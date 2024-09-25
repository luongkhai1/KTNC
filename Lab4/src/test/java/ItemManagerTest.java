import controller.ItemManager;
import model.Item;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ItemManagerTest {
    ItemManager manager = new ItemManager();

    //Add
    @Test
    public void testAddItem_ValidName() {
        assertDoesNotThrow(() -> manager.addItem(new Item(1, "Khaii")));
    }

    @Test
    public void testAddItem_nameTrong() {
        assertThrows(IllegalArgumentException.class, () -> manager.addItem(new Item(1, "")));
    }

    @Test
    public void testAddItem_NullName() {
        assertThrows(IllegalArgumentException.class, () -> manager.addItem(new Item(1, null)));
    }

    @Test
    public void testAddItem_Namedai() {
        assertThrows(IllegalArgumentException.class, () -> manager.addItem(new Item(1, "LuongDuyKhaiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii")));
    }

    @Test
    public void testAddItem_NamevaSo() {
        assertThrows(IllegalArgumentException.class, () -> manager.addItem(new Item(1, "Khaii123")));
    }

    @Test
    public void testAddItem_Namekytudacbiet() {
        assertThrows(IllegalArgumentException.class, () -> manager.addItem(new Item(1, "Name@#")));
    }

    @Test
    public void testAddItem_NameWithSpaces() {
        assertDoesNotThrow(() -> manager.addItem(new Item(1, "Valid Name")));
    }

    @Test
    public void testAddItem_NegativeId() {
        assertThrows(IllegalArgumentException.class, () -> manager.addItem(new Item(-1, "ValidName")));
    }

    @Test
    public void testAddItem_ZeroId() {
        assertThrows(IllegalArgumentException.class, () -> manager.addItem(new Item(0, "ValidName")));
    }

    @Test
    public void testAddItem_NullItem() {
        assertThrows(NullPointerException.class, () -> manager.addItem(null));
    }

    // Update
    @Test
    public void testUpdateItem_ValidName() {
        manager.addItem(new Item(1, "OldName"));
        assertDoesNotThrow(() -> manager.updateItem(1, "NewName"));
    }

    @Test
    public void testUpdateItem_EmptyName() {
        manager.addItem(new Item(1, "OldName"));
        assertThrows(IllegalArgumentException.class, () -> manager.updateItem(1, ""));
    }

    @Test
    public void testUpdateItem_NullName() {
        manager.addItem(new Item(1, "OldName"));
        assertThrows(IllegalArgumentException.class, () -> manager.updateItem(1, null));
    }

    @Test
    public void testUpdateItem_LongName() {
        manager.addItem(new Item(1, "OldName"));
        assertThrows(IllegalArgumentException.class, () -> manager.updateItem(1, "ThisNameIsReallyReallyReallyReallyReallyReallyLong"));
    }

    @Test
    public void testUpdateItem_NameWithNumbers() {
        manager.addItem(new Item(1, "OldName"));
        assertThrows(IllegalArgumentException.class, () -> manager.updateItem(1, "Name123"));
    }

    @Test
    public void testUpdateItem_NameWithSpecialCharacters() {
        manager.addItem(new Item(1, "OldName"));
        assertThrows(IllegalArgumentException.class, () -> manager.updateItem(1, "Name@#"));
    }

    @Test
    public void testUpdateItem_NameWithSpaces() {
        manager.addItem(new Item(1, "OldName"));
        assertDoesNotThrow(() -> manager.updateItem(1, "New Valid Name"));
    }

    @Test
    public void testUpdateItem_NegativeId() {
        manager.addItem(new Item(1, "OldName"));
        assertThrows(IllegalArgumentException.class, () -> manager.updateItem(-1, "NewName"));
    }

    @Test
    public void testUpdateItem_ZeroId() {
        manager.addItem(new Item(1, "OldName"));
        assertThrows(IllegalArgumentException.class, () -> manager.updateItem(0, "NewName"));
    }

    @Test
    public void testUpdateItem_NonExistingId() {
        assertThrows(IllegalArgumentException.class, () -> manager.updateItem(999, "NewName"));
    }

    //Delete
    @Test
    public void testDeleteItem_ExistingId() {
        manager.addItem(new Item(1, "Name"));
        assertDoesNotThrow(() -> manager.deleteItem(1));
    }

    @Test
    public void testDeleteItem_NonExistingId() {
        assertThrows(IllegalArgumentException.class, () -> manager.deleteItem(999));
    }

    @Test
    public void testDeleteItem_AfterAddingMultipleItems() {
        manager.addItem(new Item(1, "Name Items s s"));
        manager.addItem(new Item(2, "Name Items s"));
        manager.addItem(new Item(3, "Name Items"));
        assertDoesNotThrow(() -> manager.deleteItem(2));
    }

    @Test
    public void testDeleteItem_AfterDeletingAllItems() {
        manager.addItem(new Item(1, "Name Items s s"));
        manager.addItem(new Item(2, "Name Items s"));
        manager.addItem(new Item(3, "Name Items"));
        manager.deleteItem(1);
        manager.deleteItem(2);
        manager.deleteItem(3);
        assertThrows(IllegalArgumentException.class, () -> manager.deleteItem(1));
    }

    @Test
    public void testDeleteItem_NegativeId() {
        assertThrows(IllegalArgumentException.class, () -> manager.deleteItem(-1));
    }

    @Test
    public void testDeleteItem_ZeroId() {
        assertThrows(IllegalArgumentException.class, () -> manager.deleteItem(0));
    }

    @Test
    public void testDeleteItem_AfterAddingAndDeletingMultipleItems() {
        manager.addItem(new Item(1, "Name Items"));
        manager.addItem(new Item(2, "Name Items s"));
        manager.addItem(new Item(3, "Name Items s s"));
        manager.deleteItem(1);
        manager.deleteItem(2);
        assertDoesNotThrow(() -> manager.deleteItem(3));
    }

    @Test
    public void testDeleteItem_AfterDeletingAllItemsAndAddingNewItem() {
        manager.addItem(new Item(1, "Name Items s s"));
        manager.addItem(new Item(2, "Name Items s"));
        manager.addItem(new Item(3, "Name Items"));
        manager.deleteItem(1);
        manager.deleteItem(2);
        manager.deleteItem(3);
        manager.addItem(new Item(4, "Name Item"));
        assertDoesNotThrow(() -> manager.deleteItem(4));
    }

}
