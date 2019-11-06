import {RecordActivity} from '../app/models/record-activity.model'
import {of} from 'rxjs/internal/observable/of'

export class MockActivityService {
    fetchRecordActivities(rfiId: number) {
        return of([])
    }

    createRecordActivity(activity: RecordActivity) {
        return of({})
    }
}