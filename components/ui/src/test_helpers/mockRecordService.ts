import {RfiLink} from "../app/models/rfi-link.model";
import {Target} from '../app/models/target.model'
import {RecordActivity} from '../app/models/record-activity.model'
import {RecordType} from "../app/models/record.model";
import {Sensor} from "../app/models/sensor.model";

export class MockRecordService {
    fetchRecordSelectOptions() {}

    deleteRecordLink(link: RfiLink) {}

    deleteRecordTarget(target: Target) {}

    deleteRecordSensor(sensor: Sensor) {}

    deleteRecordActivity(activity: RecordActivity) {}

    saveRecordLink(rfiLink: RfiLink) {}

    saveRecordActivity(activity: RecordActivity) {}

    fetchRecords(recordType: RecordType) {}
}