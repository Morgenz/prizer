package com.steam.kmicic.prizer.serivce;

import com.steam.kmicic.prizer.domain.Item;
import com.steam.kmicic.prizer.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock(name = "itemRepository")
    private ItemRepository itemRepository;

    @Mock
    private SteamMarketItemService steamMarketItemService;

    @InjectMocks
    private ItemService itemService;

    List<Item> items = new LinkedList<>();

    @Before
    public void setUpTest() {
        Item item1 = new Item("1", "1", 430, "TestItem1", "123456", new BigDecimal("1.23"), new BigDecimal("2.23"), null, 1, null, '$');
        Item item2 = new Item("2", "2", 430, "TestItem2", "6543", new BigDecimal("2.23"), new BigDecimal("3.23"), null, 10, null, '$');
        items.add(item1);
        items.add(item2);
    }

    @Test
    public void getAllItems_shouldReturnListOfItems() {
        //given
        when(itemRepository.findAll()).thenReturn(items);

        //when
        List<Item> returnedItems = itemService.getAllItems();

        //then
        Assert.assertEquals(items, returnedItems);
    }

    @Test
    public void addItem_shouldReturnAddedItem() {
        //given
        Item item3 = new Item("2", "2", 430, "TestItem2", "6543", new BigDecimal("2.23"), new BigDecimal("3.23"), null, 10, null, '$');
        when(itemRepository.save(item3)).thenReturn(item3);

        //when
        Item savedItem = itemService.addItem(item3);

        //then
        Assert.assertEquals(savedItem, item3);
    }

    @Test
    public void getItemById_shouldReturnItemWithGivenId() {
        //given
        String id = "1";
        Item item1 = new Item("1", "1", 430, "TestItem1", "123456", new BigDecimal("1.23"), new BigDecimal("2.23"), null, 1, null, '$');
        when(itemRepository.findById(id)).thenReturn(Optional.of(item1));

        //when
        Item returnedItem = itemService.getItemById(id);

        //then
        Assert.assertEquals(item1, returnedItem);
    }

    @Test
    public void getItemById_shouldThrowExceptionWhenThereNoItemWithGivenId() {
        //given
        String id = "1";
        when(itemRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        //when-then
        Assert.assertThrows(NoSuchElementException.class, () -> itemService.getItemById(id));
    }

    @Test
    public void updateItemById_shouldReturnUpdatedItem() {
        //given
        String id = "1";
        Item item1 = new Item("1", "1", 430, "TestItem1", "123456", new BigDecimal("1.23"), new BigDecimal("2.23"), null, 1, null, '$');
        when(itemRepository.findById(id)).thenReturn(Optional.of(item1));
        when(itemRepository.save(item1)).thenReturn(item1);

        //when
        Item updatedItem = itemService.updateItemById(item1, id);

        //then
        Assert.assertEquals(item1, updatedItem);
    }

    @Test
    public void updateItemById_shouldThrowExceptionWhenThereIsNoItemWithGivenId() {
        //given
        String id = "1";
        Item item1 = new Item("1", "1", 430, "TestItem1", "123456", new BigDecimal("1.23"), new BigDecimal("2.23"), null, 1, null, '$');
        when(itemRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        //when-then
        Assert.assertThrows(NoSuchElementException.class, () -> itemService.updateItemById(item1, id));
    }

    @Test
    public void updateSteamItemInformation_shouldReturnUpdatedItem() {
        //given
        String id = "1";
        Item item1 = new Item("1", "1", 430, "TestItem1", "123456", new BigDecimal("1.23"), new BigDecimal("2.23"), null, 1, null, '$');
        when(itemRepository.findById(id)).thenReturn(Optional.of(item1));
        when(itemRepository.save(item1)).thenReturn(item1);
        doNothing().when(steamMarketItemService).updateMarketInfo(isA(Item.class));

        //when
        Item updatedItem = itemService.updateSteamItemInformation(item1, id);

        //then
        Assert.assertEquals(item1, updatedItem);
    }

    @Test
    public void updateSteamItemInformation_shouldThrowExceptionWhenNFE() {
        //given
        String id = "1";
        Item item1 = new Item("1", "1", 430, "TestItem1", "123456", new BigDecimal("1.23"), new BigDecimal("2.23"), null, 1, null, '$');
        when(itemRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        //when-then
        Assert.assertThrows(NoSuchElementException.class, ()->itemService.updateSteamItemInformation(item1,id));
    }

    @Test
    public void deleteItem_shouldThrowNoSuchElementException() {
        //given
        String id = "1";
        Item item1 = new Item("1", "1", 430, "TestItem1", "123456", new BigDecimal("1.23"), new BigDecimal("2.23"), null, 1, null, '$');
        when(itemRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        //when-then
        Assert.assertThrows(NoSuchElementException.class, ()->itemService.deleteItem(id));
    }
}