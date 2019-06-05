package com.omega.Util;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.omega.Database.FlashCards;
import com.omega.Database.Groups;

import java.util.List;

public class FlashCardViewModel extends AndroidViewModel {

    private FlashCardRepository flashCardRepository;

    private LiveData<List<FlashCards>> allFlashCard;

    private LiveData<List<Groups>> allGroups;

    public FlashCardViewModel(Application application) {
        super(application);
        flashCardRepository = new FlashCardRepository(application);
        allFlashCard = flashCardRepository.getAllFlashCards();
        allGroups = flashCardRepository.getAllGroups();
    }

    public LiveData<List<FlashCards>> getAllFlashCard() {
        return allFlashCard;
    }

    public LiveData<List<FlashCards>> getAllFlashCardsOfGroup(String groupName) {
        return flashCardRepository.getAllFlashCardsOfGroup(groupName);
    }

    public LiveData<List<Groups>> getAllGroups() {
        return allGroups;
    }

    public void createFlashCard(String title,String content,String group){
        FlashCards flashCards = new FlashCards(title, content, group);
        flashCardRepository.insertFlashCard(flashCards);
    }

    public void createGroup(String name,String desc){
        Groups groups = new Groups(name, desc);
        flashCardRepository.insertGroup(groups);

    }

    public void deleteGroup(Groups group) {
        flashCardRepository.deleteGroup(group);
    }

    public void deleteFlashcard(String title) {
        flashCardRepository.deleteFlashcard(new FlashCards(title));
    }

    public void updateFlashCard(String title, String content, String groupName) {
        flashCardRepository.deleteFlashcard(new FlashCards(title, content, groupName));
    }

    public void updateGroup(String name, String desc) {
        Groups tempGroup = new Groups(name, desc);
        flashCardRepository.updateGroup(tempGroup);
    }

    public void deleteFlashcard(FlashCards itemTitle) {
        flashCardRepository.deleteFlashcard(itemTitle);
    }

    public void updateFlashCard(FlashCards flashCard) {
        flashCardRepository.updateFlashcard(flashCard);
    }
}
