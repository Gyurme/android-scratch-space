import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.inventory.data.InventoryDatabase
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class ItemDaoTest {
    private lateinit var itemDao: ItemDao
    private lateinit var inventoryDatabase: InventoryDatabase
    private var firstItem = Item(1, "Apples", 10.5, 20)
    private var secondItem = Item(2, "Bananas", 15.0, 97)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context, InventoryDatabase::class.java)
            .allowMainThreadQueries().build()
        itemDao = inventoryDatabase.itemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        inventoryDatabase.close()
    }

    private suspend fun addOneItemToDb() {
        itemDao.insert(firstItem)
    }

    private suspend fun addTwoItemsToDb() {
        itemDao.insert(firstItem)
        itemDao.insert(secondItem)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsItemIntoDB() = runBlocking {
        addOneItemToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], firstItem)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_returnAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], firstItem)
        assertEquals(allItems[1], secondItem)
    }

    @Test
    @kotlin.Throws(Exception::class)
    fun daoUpdateItems_updatesItemsInDB() = runBlocking {
        addTwoItemsToDb()
        itemDao.update(Item(1, "Apples", 15.0, 25))
        itemDao.update(Item(2, "Bananas", 5.0, 50))
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], Item(1, "Apples", 15.0, 25))
        assertEquals(allItems[1], Item(2, "Bananas", 5.0, 50))
    }

    @Test
    @kotlin.Throws(Exception::class)
    fun daoDeleteItesm_itemsInDBGetDeleted() = runBlocking {
        addTwoItemsToDb()
        itemDao.delete(firstItem)
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], secondItem)
        assertEquals(allItems.size, 1)
        itemDao.delete(secondItem)
        assertTrue(itemDao.getAllItems().first().isEmpty())
    }
}