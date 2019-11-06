import {of} from 'rxjs/internal/observable/of'
import {RecordItem} from "../app/models/record.model";

export class MockRfiService {
    fetchRfis() {}

    saveRfi(rfi: RecordItem) {}

    search() { return of({}) }

    fetchRfi(id: number) {}

    fetchRfiLinks(rfiId: number) {}

    deleteRfi(rfi: RecordItem) {}
}