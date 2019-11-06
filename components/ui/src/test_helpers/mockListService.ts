import {CustomList} from "../app/models/custom-list.model";

export class MockListService {
    fetchLists() {}
    saveList(listName: string) {}
    updateList(list: CustomList) {}
    deleteList(list: string) {}
    removeRecord() {}
    saveRecord() {}
}